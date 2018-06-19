package io.wiklandia.flex.model;

import org.junit.Test;

import static io.wiklandia.flex.model.AttributeType.*;
import static org.junit.Assert.assertEquals;

public class AttributeTypeTest {


  @Test
  public void testNumberOfValueTypes() {
    assertEquals("When value types are added, please update tests in this class",
      5, AttributeType.values().length);
  }

  @Test
  public void testColName() {
    assertEquals("Correct db col", TEXT.getDbCol(), "text_value");
    assertEquals("Correct db col", DECIMAL.getDbCol(), "decimal_value");
    assertEquals("Correct db col", LONG.getDbCol(), "long_value");
    assertEquals("Correct db col", DATE.getDbCol(), "date_value");
    assertEquals("Correct db col", DATE_TIME.getDbCol(), "date_time_value");
  }

  @Test
  public void testFieldName() {
    assertEquals("Correct db col", TEXT.getField(), "textValue");
    assertEquals("Correct db col", DECIMAL.getField(), "decimalValue");
    assertEquals("Correct db col", LONG.getField(), "longValue");
    assertEquals("Correct db col", DATE.getField(), "dateValue");
    assertEquals("Correct db col", DATE_TIME.getField(), "dateTimeValue");
  }

}
