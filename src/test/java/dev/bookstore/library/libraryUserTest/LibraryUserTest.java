package dev.bookstore.library.libraryUserTest;

import dev.bookstore.library.account.Account;
import dev.bookstore.library.libraryUser.LibraryUser;
import dev.bookstore.library.libraryUser.LibraryUserRepository;
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
public class LibraryUserTest {

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    private LibraryUser libraryUser;
    private Account account;

    @BeforeEach
    public void setUp() {
        libraryUser = new LibraryUser();
        libraryUser.setFirstName("John");
        libraryUser.setLastName("Doe");

        account = new Account();
        account.setNumber("123456789");

        libraryUser.setAccount(account);
        account.setLibraryUser(libraryUser);
        libraryUserRepository.save(libraryUser);
    }

    @Test
    public void libraryUserCanBeAdded() {
        LibraryUser savedLibraryUser = libraryUserRepository.findById(libraryUser.getId()).get();

        assertNotNull(savedLibraryUser.getId());
        assertThat(savedLibraryUser.getFirstName(), equalTo(libraryUser.getFirstName()));
        assertThat(savedLibraryUser.getLastName(), equalTo(libraryUser.getLastName()));
        assertThat(savedLibraryUser.getAccount().getNumber(), equalTo(libraryUser.getAccount().getNumber()));

    }

    @Test
    public void libraryUserCanBeUpdated() {
        LibraryUser savedLibraryUser = libraryUserRepository.findById(libraryUser.getId()).get();

        savedLibraryUser.setFirstName("Jane");
        savedLibraryUser.setLastName("Smith");

        libraryUserRepository.save(savedLibraryUser);

        LibraryUser updatedLibraryUser = libraryUserRepository.findById(savedLibraryUser.getId()).get();

        assertThat(updatedLibraryUser.getFirstName(), equalTo(savedLibraryUser.getFirstName()));
        assertThat(updatedLibraryUser.getLastName(), equalTo(savedLibraryUser.getLastName()));
    }

    @Test
    public void libraryUserCanBeDeleted() {
        LibraryUser savedLibraryUser = libraryUserRepository.findById(libraryUser.getId()).get();
        libraryUserRepository.delete(savedLibraryUser);
        assertEquals(0, libraryUserRepository.count());
    }

}
