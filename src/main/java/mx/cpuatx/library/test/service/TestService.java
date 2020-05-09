package mx.cpuatx.library.test.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.cpuatx.library.test.model.Book;
import mx.cpuatx.library.test.repository.BookRepository;

@Service
public class TestService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);
	
	@Autowired
	private BookRepository bookRepository;

	public int sum(int a, int b) {
		LOGGER.info("sum a = {}, b = {}", a, b);
		return a + b;
	}
	
	public List<Book> getBooks() {
		LOGGER.info("getBooks");
		List<Book> books = bookRepository.findAll();
		LOGGER.info("getBooks list = {}", books);
		return books;
	}
	
	public void insertBook(Book book) {
		LOGGER.info("insertBook: {}", book);
		bookRepository.save(book);
	}
	
	public void deleteBook(int id) {
		LOGGER.info("deleteBook: {}", id);
		bookRepository.deleteById(id);
	}
	
	public void updateBook(int id, Map<String, String> data) {
		LOGGER.info("updateBook id = {}, data = {}", id, data);
		Book book = bookRepository.getOne(id);
		// name
		if (data.containsKey("name")) {
			book.setName(data.get("name"));
		}
		//author
		if (data.containsKey("author")) {
			book.setAuthor(data.get("author"));
		}
		//category
		if (data.containsKey("categoryid")) {
			book.setCategoryid(data.get("categoryid"));
		}
		//editor
		if (data.containsKey("editorid")) {
			book.setEditorid(data.get("editorid"));
		}
		//status
		if (data.containsKey("statusid")) {
			book.setStatusid(data.get("statusid"));
		}
		bookRepository.save(book);
	}
}