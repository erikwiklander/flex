package io.wiklandia.flex.service;

import org.springframework.stereotype.Service;

import io.wiklandia.flex.model.Attribute;
import io.wiklandia.flex.model.AttributeRepository;
import io.wiklandia.flex.model.AttributeType;
import io.wiklandia.flex.model.QAttribute;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AttributeService {

	private final AttributeRepository attributes;

	public Attribute findOrCreateAttribute(String name, AttributeType attributeType) {
		log.info("findOrCreate");
		return attributes.findOne(QAttribute.attribute.name.eq(name))
				.orElse(attributes.save(Attribute.of(name, attributeType)));
	}

}
