package io.wiklandia.flex.db;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import io.wiklandia.flex.dto.ItemDto;
import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.Item;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.service.ItemTypeService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ItemFinderService {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final ItemTypeService itemTypeService;

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


		jdbcTemplate.query(sql.toString(), namedParameters, (rs, rowNum) -> getItemDto(attributes, rs));

		// @formatter:off
		return jdbcTemplate
				.queryForList(sql.toString(), namedParameters)
				.stream().map(m -> getItemDto(attributes, m))
				.collect(Collectors.toList());
		// @formatter:on

	}

	public ItemDto getItem(Item item) {
		Iterable<Attribute> attributes = itemTypeService.findAttributesForItemType(item.getItemType());
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("item_id", item.getId());
		return getItemDto(attributes,
				jdbcTemplate.queryForMap("select * from items where id=:item_id", namedParameters));
	}

	@SneakyThrows
	private ItemDto getItemDto(Iterable<Attribute> attributes, ResultSet rs) {

		ItemDto itemDto = ItemDto.of(rs.getLong("id"));
		for (Attribute attribute : attributes) {

			itemDto.addValue(attribute.getId(),
					attribute.getAttributeType().getResultSetConverter().apply(rs, attribute.getColName()));
		}

		return itemDto;
	}

	private ItemDto getItemDto(Iterable<Attribute> attributes, Map<String, Object> m) {
		ItemDto item = ItemDto.of((Long) m.get("id"));
		for (Attribute a : attributes) {
			item.addValue(a.getId(), m.get(a.getColName()));
		}
		return item;
	}

}
