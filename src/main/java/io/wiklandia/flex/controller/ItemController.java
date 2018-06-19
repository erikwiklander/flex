package io.wiklandia.flex.controller;

import io.wiklandia.flex.db.ItemFinderService;
import io.wiklandia.flex.dto.ItemDto;
import io.wiklandia.flex.model.Item;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class ItemController {

	private final ItemFinderService itemFinderService;
	private final ItemService itemService;

	@GetMapping("items/{itemType}")
  public FlexPage<ItemDto> getItems(@PathVariable("itemType") ItemType itemType,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "20") int size) {
    Assert.notNull(itemType, "no such itemType");
    return new FlexPage(itemFinderService.getPage(itemType, PageRequest.of(page, size, Sort.unsorted())));
  }

  @PostMapping("item/{item}")
  public ItemDto update(@PathVariable("item") Item item, @RequestBody(required = false) Map<Long, String> values) {

    Assert.notNull(item, "no such item");

		if (values != null) {
			for (Map.Entry<Long, String> value : values.entrySet()) {
				itemService.save(item, value.getKey(), value.getValue());
			}
		}

		return itemFinderService.getItem(item);
	}

}
