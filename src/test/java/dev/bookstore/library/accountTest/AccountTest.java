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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional // Rollback after each test
public class AccountTest {

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
    public void accountCanBeAdded() {

        // Retrieve account
        Account savedAccount = accountRepository.findById(account.getId()).get();

        // Check if account was added
        assertNotNull(savedAccount.getId());
        assertThat(savedAccount.getId(), equalTo(account.getId()));
        assertThat(savedAccount.getNumber(), equalTo(account.getNumber()));
        assertThat(savedAccount.getLibraryUser().getFirstName(), equalTo("John"));
        assertThat(savedAccount.getLibraryUser().getLastName(), equalTo("Doe"));

    }

    @Test
    public void accountCanBeUpdated() {

        // Update account
        System.out.println(account.getId());
        Account savedAccount = accountRepository.findById(account.getId()).get();
        savedAccount.setNumber("987654321");

        LibraryUser savedLibraryUser = savedAccount.getLibraryUser();
        savedLibraryUser.setFirstName("Jane");
        savedLibraryUser.setLastName("Smith");

        // Save updated account
        libraryUserRepository.save(savedLibraryUser);
        accountRepository.save(savedAccount);

        // Check if account was updated
        Account updatedAccount = accountRepository.findById(savedAccount.getId()).get();
        assertThat(updatedAccount.getId(), equalTo(savedAccount.getId()));
        assertThat(updatedAccount.getNumber(), equalTo(savedAccount.getNumber()));
        assertThat(updatedAccount.getLibraryUser().getFirstName(), equalTo("Jane"));
        assertThat(updatedAccount.getLibraryUser().getLastName(), equalTo("Smith"));

    }

    @Test
    public void accountCanBeDeleted() {

        libraryUser.removeAccount();
        accountRepository.deleteById(account.getId());

        assertEquals(0, accountRepository.count());
    }

}
