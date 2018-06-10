package io.wiklandia.flex.model;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Iterables;

import io.wiklandia.flex.FlexApplicationTests;
import io.wiklandia.flex.service.ItemTypeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestQueries extends FlexApplicationTests {

	@Autowired
	private ItemTypeRepository itemTypes;

	@Autowired
	private ItemTypeService itemTypeService;

	@Test
	public void tet() {
		itemTypeService.findOrCreate("erik");
		assertEquals(1, itemTypes.count());
		assertEquals(1, Iterables.size(itemTypes.findAll(QItemType.itemType.name.eq("erik"))));

		log.info("Hej! {}", itemTypes.findOne(QItemType.itemType.name.eq("erik")));

		itemTypeService.findOrCreate("erik");
		// assertEquals(1, itemTypes.count());
	}

	@Test
	public void optional() {
		assertEquals("erik", Optional.empty().orElse("erik"));
	}

	@Test
	public void test() {
		//
		// Item i1 = new Item();
		//
		// Attribute a1 =
		// Attribute.builder().type("a100").decimalValue(BigDecimal.TEN).build();
		// Attribute a2 =
		// Attribute.builder().type("a2").decimalValue(BigDecimal.ONE).build();
		//
		// i1.addAttribute(a1);
		// i1.addAttribute(a2);
		//
		// items.save(i1);
		//
		// Item i2 = new Item();
		//
		// Attribute a3 =
		// Attribute.builder().type("a1").decimalValue(BigDecimal.TEN).build();
		// Attribute a4 =
		// Attribute.builder().type("a2").decimalValue(BigDecimal.ZERO).build();
		// i2.addAttribute(a3);
		// i2.addAttribute(a4);
		// items.save(i2);
		//
		// // Predicate p1 = QItem.item.attributes.any()
		// // .in(JPAExpressions.selectFrom(QAttribute.attribute).where(ExpressionUtils
		// // .and(QAttribute.attribute.type.eq("a1"),
		// // QAttribute.attribute.decimalValue.eq(BigDecimal.TEN)))
		// // .select(QAttribute.attribute));
		// // Predicate p1 =
		// //
		// QItem.item.attributes.any().in(JPAExpressions.selectFrom(QAttribute.attribute)
		// // .where(QAttribute.attribute.type.eq("a1")).select(QAttribute.attribute));
		//
		// Predicate p1 = QItem.item.attributes.any().type.eq("a1");
		//
//		// @formatter:off
//		Predicate p = QItem.item.attributes
//				.any().id.in(JPAExpressions.selectFrom(QAttribute.attribute)
//								.where(ExpressionUtils.and(
//										QAttribute.attribute.type.eq("a1"),
//										QAttribute.attribute.decimalValue.eq(BigDecimal.TEN)))
//								.select(QAttribute.attribute.id));
//		// @formatter:on
		//
		// for (Item i : items.findAll(p1)) {
		// log.info("item: {}", i.getClass());
		// }
		//
		// assertEquals(2, Iterables.size(items.findAll(p)));
		//
		// //
		// items.findAll(QItem.item.attributes.contains(QItem.item.attributes.contains(QAttribute.attribute.as(""))));
		//
		// // assertEquals(2,
		// // Iterables.size(items.findAll(ExpressionUtils
		// // .and(QAttribute.attribute.type.eq("a1"),
		// // QAttribute.attribute.decimalValue.eq(BigDecimal.TEN))
		// // .accept(v, context))));

	}

}
