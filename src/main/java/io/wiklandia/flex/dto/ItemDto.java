package io.wiklandia.flex.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ItemDto {

	private Long id;
	private Map<Long, ValueDto> values = new HashMap<>();

	public void addValue(ValueDto value) {
		values.put(value.getId(), value);
	}

}
