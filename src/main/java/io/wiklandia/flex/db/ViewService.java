package io.wiklandia.flex.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.ItemType;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ViewService {

	private final JdbcTemplate jdbcTemplate;
	// private final AttributeService attributeService;

	public void updateView(ItemType itemType, Iterable<Attribute> attributes) {
		// Iterable<Attribute> attributes =
		// attributeService.findAttributesForItemType(itemType);
		String sql = getSql(itemType, attributes);
		jdbcTemplate.execute(sql);
	}

	protected String getSql(ItemType itemType, Iterable<Attribute> attributes) {
		// @formatter:off
		StringBuilder sb = new StringBuilder();
		sb.append("create or replace view items")
			.append(" as ")
			.append("select item.id, ")
			.append("item.item_type_id");
		// @formatter:on

		for (Attribute attribute : attributes) {
			// @formatter:off
			sb.append(", ")
				.append("v")
				.append(attribute.getId())
				.append(".")
				.append(attribute.getAttributeType().getDbCol())
				.append(" ")
				.append(attribute.getAttributeType().getDbCol())
				.append("_")
				.append(attribute.getId());
			// @formatter:on
		}

		sb.append(" from item");

		for (Attribute attribute : attributes) {
			String alias = "v" + attribute.getId();
			// @formatter:off
			sb.append(" left join value ")
				.append(alias)
				.append(" on ")
				.append("item.id=")
				.append(alias)
				.append(".item_id ")
				.append("and ")
				.append(alias)
				.append(".attribute_id=")
				.append(attribute.getId());
			// @formatter:on
		}

		return sb.toString();
	}

}
