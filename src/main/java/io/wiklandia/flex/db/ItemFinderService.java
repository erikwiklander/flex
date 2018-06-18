package io.wiklandia.flex.db;

import io.wiklandia.flex.dto.ItemDto;
import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.Item;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.service.ItemTypeService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ItemFinderService {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final ItemTypeService itemTypeService;

	public Page<ItemDto> getPage(ItemType itemType, Pageable pageable) {
		List<ItemDto> items = getItems(itemType, pageable);
		return new PageImpl<>(items, pageable, getCount(itemType));
	}

	private long getCount(ItemType itemType) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from items ");
		sql.append("where item_type_id=:item_type_id ");

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("item_type_id", itemType.getId());

		return jdbcTemplate.queryForObject(sql.toString(), namedParameters, Long.class);
	}

	public List<ItemDto> getItems(ItemType itemType, Pageable pageable) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("item_type_id", itemType.getId());

		StringBuilder sql = new StringBuilder();
		sql.append("select * from items ");
		sql.append("where item_type_id=:item_type_id ");
		sql.append("order by id ");

		if (pageable != null) {
			namedParameters.addValue("limit", pageable.getPageSize());
			namedParameters.addValue("offset", pageable.getPageNumber() * pageable.getPageSize());
			sql.append("limit :limit ");
			sql.append("offset :offset");
		}

		Iterable<Attribute> attributes = itemTypeService.findAttributesForItemType(itemType);

		return jdbcTemplate.query(sql.toString(), namedParameters, (rs, rowNum) -> getItemDto(attributes, rs));
	}

	public ItemDto getItem(Item item) {
		Iterable<Attribute> attributes = itemTypeService.findAttributesForItemType(item.getItemType());
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("item_id", item.getId());

		return jdbcTemplate
				.query("select * from items where id=:item_id",
						namedParameters, (rs, rowNum) -> getItemDto(attributes, rs))
				.get(0);

	}

	@SneakyThrows
	private ItemDto getItemDto(Iterable<Attribute> attributes, ResultSet rs) {
		ItemDto itemDto = ItemDto.of(
				rs.getLong("id"),
				getLocalDateTime(rs, "created_date"),
				getLocalDateTime(rs, "last_modified_date"));

		for (Attribute attribute : attributes) {
			itemDto.addValue(attribute.getId(),
					attribute.getAttributeType().getResultSetConverter().apply(rs, attribute.getColName()));
		}

		return itemDto;
	}

	private LocalDateTime getLocalDateTime(ResultSet rs, String col) throws SQLException {
		Timestamp ts = rs.getTimestamp(col);
		if (ts == null) {
			return null;
		}
		return ts.toLocalDateTime();
	}

}
