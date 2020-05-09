package mx.cpuatx.library.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.cpuatx.library.test.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}