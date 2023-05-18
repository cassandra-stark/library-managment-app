package dev.bookstore.library.isbn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISBNRepository extends JpaRepository<ISBN, Long>{
}
