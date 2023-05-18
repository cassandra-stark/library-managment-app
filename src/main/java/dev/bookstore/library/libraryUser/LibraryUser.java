package dev.bookstore.library.libraryUser;

import dev.bookstore.library.account.Account;
import dev.bookstore.library.address.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "library_user")
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_user_id")
    private Long id;

    @Column(name = "library_user_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "library_user_surname", nullable = false, length = 100)
    private String lastName;

    @OneToOne(mappedBy = "libraryUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Account account;

    @OneToOne(mappedBy = "libraryUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Address address;

    public LibraryUser() {
    }

    public LibraryUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void removeAccount() {
        if (account != null) {
            account.setLibraryUser(null);
            this.account = null;
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void removeAddress() {
        if (address != null) {
            address.setLibraryUser(null);
            this.address = null;
        }
    }

    @Override
    public String toString() {
        return "LibraryUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accountId=" + account.getId() +
                '}';
    }
}
