package io.wiklandia.flex.it.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Iterables;

import io.wiklandia.flex.it.FlexIT;
import io.wiklandia.flex.model.ItemTypeRepository;
import io.wiklandia.flex.model.QItemType;
import io.wiklandia.flex.service.ItemTypeService;

public class ItemIT extends FlexIT {

	@Autowired
	private ItemTypeRepository itemTypes;

	@Autowired
	private ItemTypeService itemTypeService;

	@Test
	public void test() {
		itemTypeService.findOrCreate("erik");

		assertEquals(1, Iterables.size(itemTypes.findAll(QItemType.itemType.name.eq("erik"))));

		assertEquals(1, itemTypes.count());
		itemTypeService.findOrCreate("erik");
		assertEquals(1, itemTypes.count());
	}

}
