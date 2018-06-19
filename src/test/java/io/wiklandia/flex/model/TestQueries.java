package io.wiklandia.flex.model;

import io.wiklandia.flex.FlexApplicationTests;
import io.wiklandia.flex.service.ItemTypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestQueries extends FlexApplicationTests {

	@Autowired
	private ItemTypeRepository itemTypes;

	@Autowired
	private ItemTypeService itemTypeService;

	@Test
	public void tet() {
		itemTypeService.createItemType("erik");
	}

}
