package io.wiklandia.flex.db;

import io.wiklandia.flex.FlexApplicationTests;
import io.wiklandia.flex.dto.ItemDto;
import io.wiklandia.flex.model.*;
import io.wiklandia.flex.service.ItemService;
import io.wiklandia.flex.service.ItemTypeService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.Assert.assertEquals;

public class ItemFinderServiceTest extends FlexApplicationTests {

  @Autowired
  ItemFinderService itemFinderService;
  @Autowired
  ItemTypeService itemTypeService;
  @Autowired
  ItemService itemService;
  @Autowired
  ItemTypeRepository itemTypes;
  @Autowired
  ItemRepository items;

  @Before
  public void setup() {
    itemTypeService.delete("test1234");
    ItemType itemType = itemTypeService.createItemType("test1234");
    itemTypeService.createAttribute("test", AttributeType.TEXT, itemType);
    itemService.create(itemType);
    itemService.create(itemType);
    itemService.create(itemType);
    itemService.create(itemType);
    itemService.create(itemType);
    itemService.create(itemType);
    itemService.create(itemType);
  }

  @Test
  public void getPage() {
    ItemType itemType = itemTypeService.findByNamne("test1234");
    Page<ItemDto> page = itemFinderService.getPage(itemType, PageRequest.of(0, 10, Sort.unsorted()));
    assertEquals(7, page.getTotalElements());
    assertEquals(7, page.getContent().size());
    assertEquals(0, page.getNumber());
    assertEquals(1, page.getTotalPages());

  }

  @Test
  public void getItem() {
    Item item = items.findAll().iterator().next();
    itemFinderService.getItem(item);
  }
}
