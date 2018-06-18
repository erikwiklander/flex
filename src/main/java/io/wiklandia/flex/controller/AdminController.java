package io.wiklandia.flex.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.AttributeType;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.service.ItemTypeService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("itemType")
@AllArgsConstructor
public class AdminController {

	private final ItemTypeService itemTypeService;

	@PostMapping("/create")
	public ItemType create(@RequestParam("name") String name) {
		return itemTypeService.createItemType(name);
	}

	@PostMapping("/addAttribute")
	public Attribute addAttribute(@RequestParam("id") ItemType itemType,
			@RequestParam("attributeType") AttributeType attributeType,
			@RequestParam("attributeName") String attributeName) {
		return itemTypeService.createAttribute(attributeName, attributeType, itemType);
	}

}
