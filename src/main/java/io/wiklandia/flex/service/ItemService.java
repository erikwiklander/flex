package io.wiklandia.flex.service;

import java.lang.reflect.Field;

import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.querydsl.core.types.ExpressionUtils;

import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.Item;
import io.wiklandia.flex.model.ItemRepository;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.model.QValue;
import io.wiklandia.flex.model.Value;
import io.wiklandia.flex.model.ValueRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ItemService {

	private final ValueRepository values;
	private final ItemRepository items;

	public Item create(ItemType itemType) {
		return items.save(Item.of(itemType));
	}

	public void save(Item item, Attribute attribute, Object val) {

		log.info("SAVE");
		Assert.notNull(item, "Item cannot be null");
		Assert.notNull(attribute, "attribute cannot be null");

		Value value = values
				.findOne(ExpressionUtils.and(QValue.value.attribute.eq(attribute), QValue.value.item.eq(item)))
				.orElseGet(() -> values.save(Value.of(attribute, item)));

		Field field = ReflectionUtils.findRequiredField(Value.class, attribute.getAttributeType().getField());
		ReflectionUtils.setField(field, value, val);
		//
		// switch (attribute.getAttributeType()) {
		// case TEXT:
		// value.setTextValue((String) val);
		// break;
		// case DECIMAL:
		// value.setDecimalValue((BigDecimal) val);
		// break;
		// case LONG:
		// value.setLongValue((Long) val);
		// break;
		// default:
		// throw new IllegalArgumentException("Unknown type: " +
		// attribute.getAttributeType());
		// }

		values.save(value);
	}

}
