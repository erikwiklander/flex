package io.wiklandia.flex.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.wiklandia.flex.db.ItemFinderService;
import io.wiklandia.flex.dto.ItemDto;
import io.wiklandia.flex.model.ItemType;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ItemController {

	private final ItemFinderService itemFinderService;

	@GetMapping("dto/{itemType}")
	public List<ItemDto> getItems(@PathParam("itemType") ItemType itemType,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "20") int size) {
		return itemFinderService.getItems(itemType, PageRequest.of(page, size, Sort.unsorted()));
	}

}
