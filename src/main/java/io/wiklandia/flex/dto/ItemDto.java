package io.wiklandia.flex.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

	private Long id;
	private Map<Long, Object> values = new HashMap<>();

	private ItemDto(Long id) {
		this.id = id;
	}

	public static ItemDto of(Long id) {
		return new ItemDto(id);
	}

	public void addValue(Long attributeId, Object value) {
		values.put(attributeId, value);
	}

}
