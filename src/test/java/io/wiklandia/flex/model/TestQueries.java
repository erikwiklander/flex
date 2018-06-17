package io.wiklandia.flex.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.wiklandia.flex.FlexApplicationTests;
import io.wiklandia.flex.service.ItemTypeService;

public class TestQueries extends FlexApplicationTests {

	@Autowired
	private ItemTypeRepository itemTypes;

	@Autowired
	private ItemTypeService itemTypeService;

	@Test
	public void tet() {
		itemTypeService.createItemType("erik");
		// assertEquals(1,
		// Iterables.size(itemTypes.findAll(QItemType.itemType.name.eq("erik"))));
		// itemTypeService.create("erik");
		assertEquals(1, itemTypes.count());
	}

}
