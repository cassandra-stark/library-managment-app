package dev.bookstore.library.loanTest;

import dev.bookstore.library.account.Account;
import dev.bookstore.library.account.AccountRepository;
import dev.bookstore.library.book.Book;
import dev.bookstore.library.libraryUser.LibraryUser;
import dev.bookstore.library.libraryUser.LibraryUserRepository;
import dev.bookstore.library.loan.Loan;
import dev.bookstore.library.loan.LoanRepository;
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
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Loan loan;
    private LibraryUser libraryUser;
    private Account account;
    private Book book;

    @BeforeEach
    public void setUp() {
        libraryUser = new LibraryUser();
        libraryUser.setFirstName("John");
        libraryUser.setLastName("Doe");

        account = new Account();
        account.setNumber("123456789");
        account.setLibraryUser(libraryUser);
        libraryUser.setAccount(account);

        accountRepository.save(account);

        book = new Book();
        book.setTitle("Book Title");

        loan = new Loan();
        loan.setLoanStatus("active");
        loan.setAccount(account);
        account.addLoan(loan);
        loan.setBook(book);
        book.setLoan(loan);

        loanRepository.save(loan);
    }

    @Test
    public void shouldFindLoanByAccountNumber() {

        List<Loan> foundLoans = loanRepository.findByAccountNumber(account.getNumber());

        assertNotNull(foundLoans);
        assertThat(foundLoans.size(), equalTo(1));
        assertThat(foundLoans.get(0).getBook().getTitle(), equalTo(book.getTitle()));
    }

    @Test
    public void shouldFindLoanByAccountNumberAndLoanStatus() {
        List <Loan> foundLoans = loanRepository.findByAccountNumberAndLoanStatus(account.getNumber(), loan.getLoanStatus());

        assertNotNull(foundLoans);
        assertThat(foundLoans.size(), equalTo(1));
        assertThat(foundLoans.get(0).getBook().getTitle(), equalTo(book.getTitle()));
    }

}
