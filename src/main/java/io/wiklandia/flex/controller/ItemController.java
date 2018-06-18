package io.wiklandia.flex.controller;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.wiklandia.flex.db.ItemFinderService;
import io.wiklandia.flex.dto.ItemDto;
import io.wiklandia.flex.model.Item;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.service.ItemService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ItemController {

	private final ItemFinderService itemFinderService;
	private final ItemService itemService;

	@GetMapping("items/{itemType}")
	public List<ItemDto> getItems(@PathParam("itemType") ItemType itemType,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "20") int size) {
		return itemFinderService.getItems(itemType, PageRequest.of(page, size, Sort.unsorted()));
	}

	@PostMapping("items/{itemType}/{item}")
	public ItemDto update(@PathParam("item") Item item, @RequestBody(required = false) Map<Long, String> values) {

		if (values != null) {
			for (Map.Entry<Long, String> value : values.entrySet()) {
				itemService.save(item, value.getKey(), value.getValue());
			}
		}

		return itemFinderService.getItem(item);
	}

}
