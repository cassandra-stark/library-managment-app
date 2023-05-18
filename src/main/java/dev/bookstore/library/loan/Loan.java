package dev.bookstore.library.loan;

import dev.bookstore.library.account.Account;
import dev.bookstore.library.book.Book;
import jakarta.persistence.*;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    // Association with Account
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Association with Book
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Book book;

    @Column(name = "loan_status")
    private String loanStatus;

    // Constructor
    public Loan() {
    }

    // Setters and getters
    public Long getLoanId() {
        return loanId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

}
