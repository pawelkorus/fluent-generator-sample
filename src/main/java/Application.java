import fluentgenerator.lib.core.GeneratorFactory;
import fluentgenerator.lib.core.reflect.ReflectGeneratorProxyFactory;
import fluentgenerator.supplier.RandomStringSupplier;
import fluentgenerator.supplier.Suppliers;
import model.Author;
import model.AuthorGenerator;
import model.Book;
import model.BookGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

	public static void main(String[] args) {
		final GeneratorFactory generatorFactory = new ReflectGeneratorProxyFactory();

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
	}

}
