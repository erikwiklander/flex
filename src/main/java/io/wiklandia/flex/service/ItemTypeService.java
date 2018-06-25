package io.wiklandia.flex.service;

import io.wiklandia.flex.db.ViewService;
import io.wiklandia.flex.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemTypeService {

	private final ItemTypeRepository itemTypes;
	private final ViewService viewService;
	private final AttributeRepository attributes;
  private final ItemRepository items;

	@Transactional
	public ItemType createItemType(String name) {

		if (itemTypes.exists(QItemType.itemType.name.eq(name))) {
			throw new IllegalStateException("Item Type already exists! " + name);
		}

		updateView();

		return itemTypes.save(ItemType.of(name));
	}

  public void delete(String name) {
    attributes.deleteAll(attributes.findAll(QAttribute.attribute.itemType.name.eq(name)));
    items.deleteAll(items.findAll(QItem.item.itemType.name.eq(name)));
    itemTypes.deleteAll(itemTypes.findAll(QItemType.itemType.name.eq(name)));
  }

  public ItemType findByNamne(String name) {
    return itemTypes.findOne(QItemType.itemType.name.eq(name)).orElseThrow(() -> new IllegalStateException("No such itemType " + name));
  }

	public void updateView() {
		viewService.updateView(attributes.findAll(Sort.by("id")));
	}

	public Attribute createAttribute(String name, AttributeType attributeType, ItemType itemType) {
		if (attributes.exists(QAttribute.attribute.name.eq(name))) {
			throw new IllegalStateException("This attribute already exists! " + name);
		}
		Attribute attribute = attributes.save(Attribute.of(name, attributeType, itemType));
		updateView();
		return attribute;
	}

	public Iterable<Attribute> findAttributesForItemType(ItemType itemType) {
		return attributes.findAll(QAttribute.attribute.itemType.eq(itemType), Sort.by("id"));
	}

}
