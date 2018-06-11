package io.wiklandia.flex.dto;

import io.wiklandia.flex.model.AttributeType;
import lombok.Data;

@Data
public class ValueDto {

	private AttributeType attributeType;
	private Long id;
	private Object value;

}
