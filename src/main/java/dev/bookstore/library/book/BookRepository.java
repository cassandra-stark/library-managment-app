package dev.bookstore.library.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
    List<Book> findByTitleContaining(String title);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByIsbnNumber(String number);
    List<Book> findByCreditsAuthorFirstName(String firstName);
    List<Book> findByCreditsAuthorLastName(String lastName);
    List<Book> findByCreditsAuthorFirstNameAndCreditsAuthorLastName(String firstName, String lastName);
    List<Book> findAllByCategoryNameLike(String name);
    List<Book> findAllByOrderByTitleAsc();

}
