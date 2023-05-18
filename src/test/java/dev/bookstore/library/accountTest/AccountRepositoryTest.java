package dev.bookstore.library.accountTest;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    private Account account;
    private LibraryUser libraryUser;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setNumber("123456789");

        libraryUser = new LibraryUser();
        libraryUser.setFirstName("John");
        libraryUser.setLastName("Doe");

        account.setLibraryUser(libraryUser);
        libraryUser.setAccount(account);

        libraryUserRepository.save(libraryUser);
        accountRepository.save(account);

    }

    @Test
    public void shouldFindAccountByLibraryUserLibraryUserId() {
        // Retrieve account
        Account savedAccount = accountRepository.findByLibraryUserId(libraryUser.getId());

        // Check if account can be found by libraryUserId
        assertNotNull(savedAccount.getId());
        assertThat(savedAccount.getNumber(), equalTo(account.getNumber()));
        assertThat(savedAccount.getLibraryUser().getId(), equalTo(libraryUser.getId()));
    }

}


