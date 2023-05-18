package dev.bookstore.library.authorTest;

import dev.bookstore.library.author.Author;
import dev.bookstore.library.author.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class AuthorTest {

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setFirstName("Test name");
        author.setLastName("Test surname");
        authorRepository.save(author);
    }

    @Test
    public void authorCanBeAdded() {
        Author savedAuthor = authorRepository.findById(author.getId()).get();
        assertNotNull(savedAuthor);
    }

    @Test
    public void authorCanBeUpdated() {
        Author savedAuthor = authorRepository.findById(author.getId()).get();

        savedAuthor.setFirstName("Updated name");
        savedAuthor.setLastName("Updated surname");
        authorRepository.save(savedAuthor);

        Author updatedAuthor = authorRepository.findById(savedAuthor.getId()).get();
        assertThat(updatedAuthor.getFirstName(), equalTo(savedAuthor.getFirstName()));
        assertThat(updatedAuthor.getLastName(), equalTo(savedAuthor.getLastName()));
    }

    @Test
    public void authorCanBeDeleted() {
        Author savedAuthor = authorRepository.findById(author.getId()).get();
        authorRepository.delete(savedAuthor);

        assertEquals(0, authorRepository.count());
    }
}
