package io.wiklandia.flex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemType extends Base {

	@NotNull
	@NaturalId
	private String name;

	public static ItemType of(String name) {
		ItemType itemType = new ItemType();
		itemType.setName(name);
		return itemType;
	}

}
