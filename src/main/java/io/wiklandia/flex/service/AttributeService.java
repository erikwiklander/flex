package io.wiklandia.flex.service;

import org.springframework.stereotype.Service;

import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.AttributeRepository;
import io.wiklandia.flex.model.AttributeType;
import io.wiklandia.flex.model.QAttribute;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AttributeService {

	private final AttributeRepository attributes;

	public Attribute findOrCreateAttribute(String name, AttributeType attributeType) {
		return attributes.findOne(QAttribute.attribute.name.eq(name))
				.orElseGet(() -> attributes.save(Attribute.of(name, attributeType)));
	}

}
