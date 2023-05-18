package dev.bookstore.library.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.bookstore.library.libraryUser.LibraryUser;
import dev.bookstore.library.loan.Loan;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private String number;

    @OneToOne
    @MapsId
    @JoinColumn(name = "library_user_id", referencedColumnName = "library_user_id")
    @JsonIgnore
    private LibraryUser libraryUser;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans = new ArrayList<>();

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LibraryUser getLibraryUser() {
        return libraryUser;
    }

    public void setLibraryUser(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void addLoan(Loan loan) {
        this.loans.add(loan);
    }

    public void removeLoan(Loan loan) {
        this.loans.remove(loan);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", libraryUser.id=" + libraryUser.getId() +
                ", loans=" + loans +
                '}';
    }
}
