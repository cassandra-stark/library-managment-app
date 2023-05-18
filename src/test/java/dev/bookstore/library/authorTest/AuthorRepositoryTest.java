package dev.bookstore.library.authorTest;

import dev.bookstore.library.author.Author;
import dev.bookstore.library.author.AuthorRepository;
import dev.bookstore.library.book.Book;
import dev.bookstore.library.book.BookRepository;
import dev.bookstore.library.credit.Credit;
import dev.bookstore.library.credit.CreditRepository;
import dev.bookstore.library.isbn.ISBN;
import dev.bookstore.library.isbn.ISBNRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ISBNRepository isbnRepository;

    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setFirstName("William");
        author.setLastName("Shakespeare");
        authorRepository.save(author);
    }

    @Test
    public void shouldFindAuthorByFirstName() {
        List<Author> authors = authorRepository.findByFirstName(author.getFirstName());
        assertThat(authors.size(), equalTo(1));
    }

    @Test
    public void shouldFindAuthorByLastName() {
        List<Author> authors = authorRepository.findByLastName(author.getLastName());
        assertThat(authors.size(), equalTo(1));
    }

    @Test
    public void shouldFindAuthorByAFirstNameAndLastName() {
        List<Author> authors = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
        assertThat(authors.size(), equalTo(1));
    }

    // TODO - refactor (shorten) this test
    @Test
    public void shouldFindAuthorByCreditsBookTitle() {
        Book book = new Book();
        book.setTitle("Hamlet");

        ISBN isbn = new ISBN();
        isbn.setNumber("123456789L");
        isbn.setBook(book);
        book.setIsbn(isbn);

        Credit credit = new Credit();
        book.addCredit(credit);
        credit.setBook(book);

        credit.setAuthor(author);
        author.addCredit(credit);

        isbnRepository.save(isbn);
        authorRepository.save(author);
        creditRepository.save(credit);
        bookRepository.save(book);

        List<Author> authors = authorRepository.findByCreditsBookTitle(book.getTitle());
        assertThat(authors.size(), equalTo(1));
    }


}
