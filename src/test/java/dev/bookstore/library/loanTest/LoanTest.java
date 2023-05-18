package dev.bookstore.library.loanTest;

import dev.bookstore.library.account.Account;
import dev.bookstore.library.book.Book;
import dev.bookstore.library.libraryUser.LibraryUser;
import dev.bookstore.library.loan.Loan;
import dev.bookstore.library.loan.LoanRepository;
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
public class LoanTest {

    @Autowired
    private LoanRepository loanRepository;

    private Loan loan;
    private Account account;
    private LibraryUser libraryUser;
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

        book = new Book();
        book.setTitle("Book Title");

        loan = new Loan();
        loan.setAccount(account);
        account.addLoan(loan);
        loan.setBook(book);
        book.setLoan(loan);

        loanRepository.save(loan);
    }

    @Test
    public void addLoan() {
        Loan savedLoan = loanRepository.findById(loan.getLoanId()).get();

        assertNotNull(savedLoan.getLoanId());
        assertThat(savedLoan.getLoanId(), equalTo(loan.getLoanId()));
        assertThat(savedLoan.getAccount().getNumber(), equalTo(loan.getAccount().getNumber()));
        assertThat(savedLoan.getBook().getTitle(), equalTo(loan.getBook().getTitle()));
    }

    @Test
    public void updateLoan() {
        Loan savedLoan = loanRepository.findById(loan.getLoanId()).get();

        savedLoan.getBook().setTitle("New Book Title");
        savedLoan.getAccount().setNumber("987654321");

        loanRepository.save(savedLoan);

        Loan updatedLoan = loanRepository.findById(loan.getLoanId()).get();
        assertThat(updatedLoan.getBook().getTitle(), equalTo(savedLoan.getBook().getTitle()));
        assertThat(updatedLoan.getAccount().getNumber(), equalTo(savedLoan.getAccount().getNumber()));
    }

    @Test
    public void removeLoan() {
        Loan savedLoan = loanRepository.findById(loan.getLoanId()).get();

        book.removeLoan();
        loanRepository.delete(savedLoan);

        assertEquals(0, loanRepository.count());
    }
}
