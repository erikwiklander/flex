package io.wiklandia.flex.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.AttributeRepository;
import io.wiklandia.flex.model.AttributeType;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.model.QAttribute;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AttributeService {

	private final AttributeRepository attributes;

	public Attribute findOrCreateAttribute(String name, AttributeType attributeType, ItemType itemType) {
		Attribute attribute = attributes.findOne(QAttribute.attribute.name.eq(name))
				.orElseGet(() -> attributes.save(Attribute.of(name, attributeType, itemType)));

		if (!attribute.getAttributeType().equals(attributeType)) {
			throw new IllegalStateException(
					"Attribute type mismatch! An attribute exists with that name with different type: " + name);
		}

		return attribute;
	}

	public Iterable<Attribute> findAttributesForItemType(ItemType itemType) {
		return attributes.findAll(QAttribute.attribute.itemType.eq(itemType), Sort.by("id"));
	}

}
