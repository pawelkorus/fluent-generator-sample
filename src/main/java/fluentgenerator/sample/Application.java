package fluentgenerator.sample;

import com.fasterxml.jackson.databind.JsonNode;
import fluentgenerator.core.GeneratorFactory;
import fluentgenerator.lib.core.reflect.ReflectGeneratorProxyFactory;
import fluentgenerator.sample.generator.GeneratorWithoutModel;
import fluentgenerator.sample.model.Author;
import fluentgenerator.sample.model.AuthorGenerator;
import fluentgenerator.sample.model.Book;
import fluentgenerator.sample.model.BookGenerator;
import fluentgenerator.supplier.RandomStringSupplier;
import fluentgenerator.supplier.Suppliers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		final GeneratorFactory generatorFactory = new ReflectGeneratorProxyFactory();

		// generating model classes
		AuthorGenerator authorGenerator = generatorFactory.generatorInstance(AuthorGenerator.class);
		authorGenerator.firstName(RandomStringSupplier.Builder.create().alphanumeric().length(5).build());
		authorGenerator.lastName(Suppliers.indexedString("authorLastName"));

		List<Author> authors = new ArrayList<>();
		authors.add(authorGenerator.build());
		authors.add(authorGenerator.build());

		System.out.println(authors);

		BookGenerator bookGenerator = generatorFactory.generatorInstance(BookGenerator.class);
		bookGenerator.author(authorGenerator);
		bookGenerator.publishYear(Suppliers.randomInt(1950, 2020));
		bookGenerator.title(RandomStringSupplier.Builder.create().randomLength(5, 30).alphabetic().prefix("Title ").build());
		bookGenerator.type(Suppliers.oneOf(Book.Type.class));

		List<Book> books = new ArrayList<>();
		books.add(bookGenerator.build());
		books.add(bookGenerator.build());
		books.add(bookGenerator.build());

		System.out.println(books);

		List<JsonNode> bookNodes = new ArrayList<>();
		bookNodes.add(bookGenerator.build(JsonNode.class));
		bookNodes.add(bookGenerator.build(JsonNode.class));
		bookNodes.add(bookGenerator.build(JsonNode.class));

		System.out.println(bookNodes);

		GeneratorWithoutModel generatorWithoutModel = generatorFactory.generatorInstance(GeneratorWithoutModel.class);
		generatorWithoutModel.stringProperty(Suppliers.oneOf(Arrays.asList("Sample 1", "Sample 2", "Sample 3")));
		generatorWithoutModel.intProperty(Suppliers.randomInt(10, 20));
		generatorWithoutModel.floatProperty(2.0f);

		List<JsonNode> nodes = new ArrayList<>();
		nodes.add(generatorWithoutModel.build());
		nodes.add(generatorWithoutModel.build());
		nodes.add(generatorWithoutModel.build());

		System.out.println(nodes);
	}

}
