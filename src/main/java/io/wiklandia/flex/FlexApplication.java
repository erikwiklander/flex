package io.wiklandia.flex;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;

import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.AttributeRepository;
import io.wiklandia.flex.model.AttributeType;
import io.wiklandia.flex.model.Item;
import io.wiklandia.flex.model.ItemRepository;
import io.wiklandia.flex.model.ItemType;
import io.wiklandia.flex.model.ItemTypeRepository;
import io.wiklandia.flex.model.QItem;
import io.wiklandia.flex.model.QValue;
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
	public CommandLineRunner commandLineRunner(ItemRepository items, ItemTypeRepository types,
			AttributeRepository attributes, ItemService itemService, AttributeService attributeService,
			ItemTypeService itemTypeService) {
		return args -> {

			ItemType book = itemTypeService.findOrCreate("book");

			Attribute textAttribtue1 = attributeService.findOrCreateAttribute("first", AttributeType.TEXT);
			Attribute decAttribute = attributeService.findOrCreateAttribute("money", AttributeType.DECIMAL);

			Item i1 = itemService.create(book);
			itemService.save(i1, textAttribtue1, "Cool!");
			itemService.save(i1, decAttribute, BigDecimal.ONE);

			// @formatter:off
//			Predicate predicate1 = QItem.item.values.any()
//					.in(JPAExpressions.selectFrom(QValue.value)
//							.where(ExpressionUtils.and(
//									QValue.value.attribute.eq(textAttribtue1), 
//									QValue.value.textValue.startsWith("C"))));
			
			Predicate predicate1 = QItem.item.values.any()
					.in(JPAExpressions.selectFrom(QValue.value)
							.where(ExpressionUtils.and(
									QValue.value.attribute.eq(textAttribtue1), 
									QValue.value.textValue.startsWith("C"))));
			
//			Predicate predicate2 = QItem.item.values.any()
//					.in(JPAExpressions.selectFrom(QValue.value)
//							.where(ExpressionUtils.and(
//									QValue.value.attribute.eq(decAttribute), 
//									QValue.value.decimalValue.gt(BigDecimal.ZERO))));
			// @formatter:on
			// items.findAll(arg0, arg1)
			for (Item is : items.findAll(predicate1)) {
				log.info("item: {}", is);
			}

			log.info("??????????????????????");
			log.info("??????????????????????");
			itemService.test();

		};
	}
}
