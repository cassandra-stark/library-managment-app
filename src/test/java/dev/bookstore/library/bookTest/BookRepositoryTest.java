package dev.bookstore.library.bookTest;

import dev.bookstore.library.author.Author;
import dev.bookstore.library.author.AuthorRepository;
import dev.bookstore.library.book.Book;
import dev.bookstore.library.book.BookRepository;
import dev.bookstore.library.category.Category;
import dev.bookstore.library.category.CategoryRepository;
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
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ISBNRepository isbnRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Book book;
    private ISBN isbn;
    private Credit credit;
    private Author author;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setTitle("Hamlet");

        isbn = new ISBN();
        isbn.setNumber("123456789L");
        isbn.setBook(book);
        book.setIsbn(isbn);

        credit = new Credit();
        credit.setBook(book);

        author = new Author();
        author.setFirstName("William");
        author.setLastName("Shakespeare");

        credit.setAuthor(author);
        author.addCredit(credit);

        isbnRepository.save(isbn);
        authorRepository.save(author);
        creditRepository.save(credit);

        book.addCredit(credit);

        bookRepository.save(book);
    }

    @Test
    public void shouldFindBookByTitle() {
        String title = "Hamlet";
        List<Book> books = bookRepository.findByTitle(title);

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getTitle(), equalTo(book.getTitle()));
    }

    @Test
    public void shouldFindByTitleContaining() {
        String title = "Ham";
        List<Book> books  = bookRepository.findByTitleContaining(title);

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getTitle(), equalTo(book.getTitle()));
    }

    @Test
    public void shouldFindByTitleContainingIgnoreCase() {
        String title = "ham";
        List<Book> books  = bookRepository.findByTitleContainingIgnoreCase(title);

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getTitle(), equalTo(book.getTitle()));
    }

    @Test
    public void shouldFindBookByIsbnNumber() {
        String number = "123456789L";
        List<Book> books = bookRepository.findByIsbnNumber(number);

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getTitle(), equalTo(book.getTitle()));
    }

    @Test
    public void shouldFindBookByAuthorFirstName() {
        String firstName = "William";
        List<Book> books = bookRepository.findByCreditsAuthorFirstName(firstName);

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getTitle(), equalTo(book.getTitle()));

    }

    @Test
    public void shouldFindBookByAuthorSurname() {
        String lastName = "Shakespeare";
        List<Book> books  = bookRepository.findByCreditsAuthorLastName(lastName);

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getTitle(), equalTo(book.getTitle()));
    }

    @Test
    public void shouldFindBookByAuthorFirstNameAndLastName() {
        String firstName = "William";
        String lastName = "Shakespeare";
        List<Book> books  = bookRepository.findByCreditsAuthorFirstNameAndCreditsAuthorLastName(firstName, lastName);

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getTitle(), equalTo(book.getTitle()));
    }

    @Test
    public void shouldFindAllBooksWithNameLike() {
        Category category = new Category();
        category.setName("Fantasy");
        book.setCategory(category);

        categoryRepository.save(category);
        bookRepository.save(book);

        List<Book> books = bookRepository.findAllByCategoryNameLike("Fantasy");

        assertThat(books.size(), equalTo(1));
        assertThat(books.get(0).getTitle(), equalTo(book.getTitle()));
    }

    @Test
    public void shouldFindAllAndSortByTitle() {
        Book bookLast = new Book();
        bookLast.setTitle("Weathering Heights");
        bookRepository.save(bookLast);

        List<Book> books = bookRepository.findAllByOrderByTitleAsc();

        assertThat(books.size(), equalTo(2));
        assertThat(books.get(0).getTitle(), equalTo(book.getTitle()));
        assertThat(books.get(1).getTitle(), equalTo(bookLast.getTitle()));
    }


}
