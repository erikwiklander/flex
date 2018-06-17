package io.wiklandia.flex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.wiklandia.flex.db.ItemFinderService;
import io.wiklandia.flex.db.ViewService;
import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.AttributeType;
import io.wiklandia.flex.model.Item;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.service.ItemService;
import io.wiklandia.flex.service.ItemTypeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class FlexApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ItemService itemService, ItemTypeService itemTypeService,
			ViewService viewService, ItemFinderService ites) {
		return args -> {

			ItemType book = itemTypeService.createItemType("book");

			Attribute textA = itemTypeService.createAttribute("haha", AttributeType.TEXT, book);
			Attribute longA = itemTypeService.createAttribute("haha1", AttributeType.LONG, book);
			Attribute decimalA = itemTypeService.createAttribute("haha2", AttributeType.DECIMAL, book);
			Attribute dateTimeA = itemTypeService.createAttribute("haha3", AttributeType.DATE_TIME, book);
			Attribute dateA = itemTypeService.createAttribute("h1h1", AttributeType.DATE, book);

			Item i1 = itemService.create(book);
			itemService.save(i1, textA, "Cool!");
			itemService.save(i1, decimalA, BigDecimal.ONE);

			Item i2 = itemService.create(book);
			itemService.save(i2, textA, "Yey!");
			itemService.save(i2, longA, 90L);
			itemService.save(i2, dateTimeA, LocalDateTime.now());
			itemService.save(i2, dateA, LocalDate.now());

			log.info("lll: {} ", ites.getItems(book, null));

		};
	}
}
