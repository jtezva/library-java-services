package mx.cpuatx.library.test.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.cpuatx.library.test.model.Book;
import mx.cpuatx.library.test.service.TestService;

@RestController
@RequestMapping("/test")
public class TestEndpoint {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestEndpoint.class);
	
	@Autowired
	private TestService service;

	@GetMapping("/get")
	public String get() {
		return "hola";
	}
	
	@PostMapping("/post")
	public String post(@RequestBody Map<String, String> body) {
		return "Your input is: " + body.toString();
	}
	
	@GetMapping("/get2")
	public ResponseEntity<String> getResponseEntity() {
		LOGGER.info("hola soy una linea de debug dentro de getResponseEntity()");
		ResponseEntity<String> result = new ResponseEntity<>("hola", HttpStatus.ACCEPTED);
		return result;
	}
	
	@PostMapping("/post2")
	public ResponseEntity<List<String>> postResponseEntity(@RequestBody Map<String, String> body) {
		LOGGER.info("hola soy una linea de debug dentro de postResponseEntity(), el body es {}", body);
		List<String> list = new ArrayList<>();
		list.add("Primer Elemento Hello World!");
		
		for (Entry<String, String> entry : body.entrySet()) {
			list.add(entry.getValue());
		}
		
		ResponseEntity<List<String>> result = new ResponseEntity<>(list, HttpStatus.CREATED);
		return result;
	}
	
	@PostMapping("/sum")
	public ResponseEntity<Integer> sumEndpoint(@RequestBody Map<String, Integer> body) {
		LOGGER.info("Estoy en la suma, cuerpo es {}", body);
		
		boolean valid = true;
		if (body.get("a") == null) {
			valid = false;
		}
		if (body.get("b") == null) {
			valid = false;
		}
		if (!valid) {
			ResponseEntity<Integer> result = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			return result;
		}
		
		int a = body.get("a");
		int b = body.get("b");
		
		int c = service.sum(a, b);
		
		ResponseEntity<Integer> result = new ResponseEntity<>(c, HttpStatus.OK);
		return result;
	}
	
	@GetMapping("/sum")
	public ResponseEntity<Integer> sumEndpoint(@RequestParam("a") int a, @RequestParam("b") int b) {
		LOGGER.info("Estoy en la suma, a = {}, b = {}", a, b);
		
		int c = service.sum(a, b);
		
		ResponseEntity<Integer> result = new ResponseEntity<>(c, HttpStatus.OK);
		return result;
	}
	
	@GetMapping("/getBooks")
	public ResponseEntity<List<Book>> getBooks() {
		LOGGER.info("getBooks");
		
		List<Book> books = service.getBooks();
		
		ResponseEntity<List<Book>> result = new ResponseEntity<>(books, HttpStatus.OK);
		return result;
	}
	
	@PostMapping("/insertBook")
	public ResponseEntity<Void> insertBook(@RequestBody Book book) {
		LOGGER.info("insertBook: {}", book);
		service.insertBook(book);
		ResponseEntity<Void> result = new ResponseEntity<>(null, HttpStatus.CREATED);
		return result;
	}
	
	@GetMapping("/deleteBook/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable int id) {
		LOGGER.info("deleteBook");
		service.deleteBook(id);
		ResponseEntity<Void> result = new ResponseEntity<>(null, HttpStatus.OK);
		return result;
	}
	
	@PostMapping("/updateBook/{id}")
	public ResponseEntity<Void> updateBook(@PathVariable int id, @RequestBody Map<String, String> data) {
		LOGGER.info("updateBook id = {}, data = {}", id, data);
		service.updateBook(id, data);
		ResponseEntity<Void> result = new ResponseEntity<>(null, HttpStatus.OK);
		return result;
	}
}