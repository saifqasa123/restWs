/**
 * 
 */
package edu.saif.restWs;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

//import com.sun.jersey.spi.resource.Singleton;


/**
 * @author saifddd
 *
 */
@Path("bookresource")
@Produces("application/xml")
//@Singleton
public class BookResource {
	private TreeMap<Integer, Book> bookMap = new TreeMap<Integer, Book>();

	public BookResource() {
		// hardcode a single book information into the database for
		// demonstration purposes
		Book book = new Book();
		book.setBookAuthor("Bhaveh Thaker");
		book.setBookName("Introduction to RESTful Web Services");
		book.setBookISBN("ISBN 10: 0-596-52926-0");
		addBook(book);
	}

	@GET
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		books.addAll(bookMap.values());
		return books;
	}

	@GET
	@Path("{id}")
	public Book getBook(@PathParam("id") int bookId) {
		return bookMap.get(bookId);
	}

	@POST
	@Path("add")
	@Produces("text/plain")
	@Consumes("application/xml")
	public String addBook(Book book) {
		int id = bookMap.size();
		book.setId(id);
		bookMap.put(id, book);
		return "Book \"" + book.getBookName() + "\" added with Id " + id;
	}

}
