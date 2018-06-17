package io.wiklandia.flex.db;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import io.wiklandia.flex.dto.ItemDto;
import io.wiklandia.flex.dto.ValueDto;
import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.service.ItemTypeService;
import lombok.AllArgsConstructor;

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

		// @formatter:off
		return jdbcTemplate
				.queryForList(sql.toString(), namedParameters)
				.stream().map(m -> {
					ItemDto item = new ItemDto();
					item.setId((Long) m.get("id"));
					for (Attribute a : attributes) {
						ValueDto value = new ValueDto();
						value.setId(a.getId());
						value.setAttributeType(a.getAttributeType());
						value.setValue(m.get(a.getColName()));
						item.addValue(value);
					}
					return item;
				}).collect(Collectors.toList());
		// @formatter:on

	}

}
