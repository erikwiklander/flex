package io.wiklandia.flex.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item extends Base {

	@OneToMany(mappedBy = "item")
	private List<Value> values;

	@NotNull
	@ManyToOne
	private ItemType type;

	public static Item of(ItemType itemType) {
		Item item = new Item();
		item.setType(itemType);
		return item;
	}

}
