package dev.bookstore.library.libraryUserTest;

import dev.bookstore.library.account.Account;
import dev.bookstore.library.account.AccountRepository;
import dev.bookstore.library.libraryUser.LibraryUser;
import dev.bookstore.library.libraryUser.LibraryUserRepository;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class LibraryUserRepositoryTest {

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    private AccountRepository accountRepository;

    private LibraryUser libraryUser;
    private Account account;

    @BeforeEach
    public void setUp() {
        libraryUser = new LibraryUser();
        libraryUser.setFirstName("John");
        libraryUser.setLastName("Smith");

        account = new Account();
        account.setNumber("1234567890");

        libraryUser.setAccount(account);
        account.setLibraryUser(libraryUser);

        accountRepository.save(account);
        libraryUserRepository.save(libraryUser);
    }

    @Test
    public void shouldFindByLastName() {

        List<LibraryUser> foundLibraryUsers = libraryUserRepository.findByLastName(libraryUser.getLastName());

        assertNotNull(foundLibraryUsers.get(0).getId());
        assertThat(foundLibraryUsers.get(0).getLastName(), equalTo(libraryUser.getLastName()));
    }


}
