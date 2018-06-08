package io.wiklandia.flex.service;

import org.springframework.stereotype.Service;

import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.model.ItemTypeRepository;
import io.wiklandia.flex.model.QItemType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemTypeService {

	private final ItemTypeRepository itemTypes;

	public ItemType findOrCreate(String name) {
		return itemTypes.findOne(QItemType.itemType.name.eq(name)).orElse(itemTypes.save(ItemType.of(name)));
	}

}
