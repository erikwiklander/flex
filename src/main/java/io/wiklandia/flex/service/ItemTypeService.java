package io.wiklandia.flex.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.wiklandia.flex.db.ViewService;
import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.AttributeRepository;
import io.wiklandia.flex.model.AttributeType;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.model.ItemTypeRepository;
import io.wiklandia.flex.model.QAttribute;
import io.wiklandia.flex.model.QItemType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemTypeService {

	private final ItemTypeRepository itemTypes;
	private final ViewService viewService;
	private final AttributeRepository attributes;

	@Transactional
	public ItemType createItemType(String name) {

		if (itemTypes.exists(QItemType.itemType.name.eq(name))) {
			throw new IllegalStateException("Item Type already exists! " + name);
		}
		ItemType itemType = itemTypes.save(ItemType.of(name));
		udpateView(itemType);
		return itemType;
	}

	public void udpateView(ItemType itemType) {
		viewService.updateView(itemType, findAttributesForItemType(itemType));
	}

	public Attribute createAttribute(String name, AttributeType attributeType, ItemType itemType) {
		if (attributes.exists(QAttribute.attribute.name.eq(name))) {
			throw new IllegalStateException("This attribute already exists! " + name);
		}
		Attribute attribute = attributes.save(Attribute.of(name, attributeType, itemType));
		udpateView(itemType);
		return attribute;
	}

	public Iterable<Attribute> findAttributesForItemType(ItemType itemType) {
		return attributes.findAll(QAttribute.attribute.itemType.eq(itemType), Sort.by("id"));
	}

}
