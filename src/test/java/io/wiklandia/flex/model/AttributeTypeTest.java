package io.wiklandia.flex.model;

import static io.wiklandia.flex.model.AttributeType.DATE;
import static io.wiklandia.flex.model.AttributeType.DATE_TIME;
import static io.wiklandia.flex.model.AttributeType.DECIMAL;
import static io.wiklandia.flex.model.AttributeType.LONG;
import static io.wiklandia.flex.model.AttributeType.TEXT;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AttributeTypeTest {

	@Test
	public void testNumberOfValueTypes() {
		assertEquals("When value types are added, please update tests in this class", 5, AttributeType.values().length);
	}

	@Test
	public void testColName() {
		assertEquals("Correct db col", "text_value", TEXT.getDbCol());
		assertEquals("Correct db col", "decimal_value", DECIMAL.getDbCol());
		assertEquals("Correct db col", "long_value", LONG.getDbCol());
		assertEquals("Correct db col", "date_value", DATE.getDbCol());
		assertEquals("Correct db col", "date_time_value", DATE_TIME.getDbCol());
	}

	@Test
	public void testFieldName() {
		assertEquals("Correct db col", "textValue", TEXT.getField());
		assertEquals("Correct db col", "decimalValue", DECIMAL.getField());
		assertEquals("Correct db col", "longValue", LONG.getField());
		assertEquals("Correct db col", "dateValue", DATE.getField());
		assertEquals("Correct db col", "dateTimeValue", DATE_TIME.getField());
	}

}
