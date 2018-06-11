package io.wiklandia.flex;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.wiklandia.flex.db.ItemFinderService;
import io.wiklandia.flex.db.ViewService;
import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.AttributeType;
import io.wiklandia.flex.model.Item;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.service.AttributeService;
import io.wiklandia.flex.service.ItemService;
import io.wiklandia.flex.service.ItemTypeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class FlexApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ItemService itemService, AttributeService attributeService,
			ItemTypeService itemTypeService, ViewService viewService, ItemFinderService ites) {
		return args -> {

			ItemType book = itemTypeService.findOrCreate("book");

			Attribute textAttribtue1 = attributeService.findOrCreateAttribute("first", AttributeType.TEXT, book);
			Attribute decAttribute = attributeService.findOrCreateAttribute("money", AttributeType.DECIMAL, book);
			Attribute la = attributeService.findOrCreateAttribute("lloonnngg", AttributeType.LONG, book);

			viewService.updateView(book);

			Item i1 = itemService.create(book);
			itemService.save(i1, textAttribtue1, "Cool!");
			itemService.save(i1, decAttribute, BigDecimal.ONE);

			Item i2 = itemService.create(book);
			itemService.save(i2, textAttribtue1, "Yey!");

			log.info("lll: {} ", ites.getItems(book, null));

		};
	}
}
