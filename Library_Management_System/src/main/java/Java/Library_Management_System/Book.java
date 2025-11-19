package Java.Library_Management_System;

public class Book {
	private String title;
	private String author;
	private String bookId;

	public Book(String title, String author, String bookId) {
		this.title = title;
		this.author = author;
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}

	public String getBookId() {
		return bookId;
	}

	@Override
	public String toString() {
		return title + " | " + author + "|" + bookId;
	}
}