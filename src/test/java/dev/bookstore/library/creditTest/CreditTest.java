package dev.bookstore.library.creditTest;

import dev.bookstore.library.author.Author;
import dev.bookstore.library.credit.Credit;
import dev.bookstore.library.credit.CreditRepository;
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
public class CreditTest {

    @Autowired
    CreditRepository creditRepository;

    private Credit credit;
    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        credit = new Credit();
        credit.setAuthor(author);
        author.addCredit(credit);

        creditRepository.save(credit);
    }

    @Test
    public void creditCanBeAdded() {
        Credit savedCredit = creditRepository.save(credit);

        assertNotNull(savedCredit.getId());
        assertThat(savedCredit.getAuthor().getFirstName(), equalTo("John"));
        assertThat(savedCredit.getAuthor().getLastName(), equalTo("Doe"));

    }

    @Test
    public void creditCanBeUpdated() {
        Credit savedCredit = creditRepository.save(credit);

        savedCredit.getAuthor().setFirstName("Jane");
        savedCredit.getAuthor().setLastName("Smith");
        creditRepository.save(savedCredit);

        Credit updatedCredit = creditRepository.findById(savedCredit.getId()).get();

        assertThat(updatedCredit.getAuthor().getFirstName(), equalTo("Jane"));
        assertThat(updatedCredit.getAuthor().getLastName(), equalTo("Smith"));
    }

    @Test
    public void creditCanBeDeleted() {
        Credit savedCredit = creditRepository.save(credit);

        author.removeCredit(credit);
        creditRepository.delete(savedCredit);

        assertEquals(0, creditRepository.count());

    }
}
