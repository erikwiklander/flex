package io.wiklandia.flex.db;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.wiklandia.flex.FlexApplicationTests;
import io.wiklandia.flex.model.AttributeType;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.service.AttributeService;
import io.wiklandia.flex.service.ItemTypeService;

public class ViewServiceTest extends FlexApplicationTests {

	@Autowired
	ItemTypeService itemTypeService;
	@Autowired
	AttributeService attributeService;
	@Autowired
	ViewService viewService;

	@Test
	public void test() {

		ItemType test = itemTypeService.findOrCreate("test");
		attributeService.findOrCreateAttribute("a1", AttributeType.TEXT, test);

		viewService.updateView(test);

	}

}
