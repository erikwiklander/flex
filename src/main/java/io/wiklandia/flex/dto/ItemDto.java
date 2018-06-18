package io.wiklandia.flex.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class ItemDto {

	private final Long id;
	private final LocalDateTime createdDate;
	private final LocalDateTime modifiedDate;
	private Map<Long, Object> values = new HashMap<>();

	public void addValue(Long attributeId, Object value) {
		this.values.put(attributeId, value);
	}

}
