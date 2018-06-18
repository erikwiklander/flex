package io.wiklandia.flex.service;

import com.querydsl.core.types.ExpressionUtils;
import io.wiklandia.flex.model.*;
import lombok.AllArgsConstructor;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.lang.reflect.Field;

@Service
@AllArgsConstructor
public class ItemService {

	private final ValueRepository values;
	private final ItemRepository items;
	private final AttributeRepository attributes;

	public Item create(ItemType itemType) {
		return this.items.save(Item.of(itemType));
	}

	public void save(Item item, Long attributeId, String val) {
		Attribute attribute = this.attributes.findById(attributeId)
				.orElseThrow(() -> new IllegalStateException("No such attribute: " + attributeId));
		this.save(item, attribute, attribute.getAttributeType().getObjectConverter().apply(val));
	}

	public void save(Item item, Attribute attribute, Object val) {
		Assert.notNull(item, "item cannot be null");
		Assert.notNull(attribute, "attribute cannot be null");

		Value valueObject = this.values
				.findOne(ExpressionUtils.and(QValue.value.attribute.eq(attribute), QValue.value.item.eq(item)))
				.orElseGet(() -> this.values.save(Value.of(attribute, item)));

		AttributeType attributeType = attribute.getAttributeType();
		Field field = ReflectionUtils.findRequiredField(Value.class, attributeType.getField());

		ReflectionUtils.setField(field, valueObject, val);

		this.values.save(valueObject);
	}

}
