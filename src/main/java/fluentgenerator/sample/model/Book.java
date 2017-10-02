package fluentgenerator.sample.model;

import fluentgenerator.annotation.FluentGenerator;

@FluentGenerator
public class Book {

	public enum Type {
		Hardcover,
		Paperback
	}

	private Author author;
	private String title;
	private int publishYear;
	private Type type;

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Integer publishYear) {
		this.publishYear = publishYear;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Book{" +
			"author=" + author +
			", title='" + title + '\'' +
			", publishYear=" + publishYear +
			", type=" + type +
			'}';
	}
}
