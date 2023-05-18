package dev.bookstore.library.libraryUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {

    List<LibraryUser> findByLastName(String lastName);
}
