package generator;

import com.fasterxml.jackson.databind.JsonNode;
import fluentgenerator.core.Generator;

import java.util.function.Supplier;

public interface GeneratorWithoutModel extends Generator<JsonNode> {
	GeneratorWithoutModel stringProperty(Supplier<String> v);
	GeneratorWithoutModel intProperty(Supplier<Integer> v);
	GeneratorWithoutModel floatProperty(float v);

	JsonNode build();
}
