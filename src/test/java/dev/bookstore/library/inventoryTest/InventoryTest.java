package dev.bookstore.library.inventoryTest;

import dev.bookstore.library.inventory.Inventory;
import dev.bookstore.library.inventory.InventoryRepository;
import dev.bookstore.library.library.Library;
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
public class InventoryTest {

    @Autowired
    InventoryRepository inventoryRepository;

    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        inventoryRepository.save(inventory);
    }

    @Test
    public void inventoryCanBeAdded() {
        Inventory savedInventory = inventoryRepository.findById(inventory.getId()).get();

        assertNotNull(savedInventory.getId());
    }

    @Test
    public void inventoryCanBeAddedWithLibrary() {
        Inventory inventory = new Inventory();

        Library library = new Library();
        library.setName("Library");

        inventory.setLibrary(library);
        library.setInventory(inventory);

        inventoryRepository.save(inventory);

        Inventory savedInventory = inventoryRepository.findById(inventory.getId()).get();

        assertNotNull(savedInventory.getId());
        assertThat(savedInventory.getLibrary().getName(), equalTo(library.getName()));
    }

    @Test
    public void inventoryCanBeDeleted() {
        Inventory savedInventory = inventoryRepository.findById(inventory.getId()).orElse(null);
        inventoryRepository.delete(savedInventory);

        assertEquals(0, inventoryRepository.count());
    }
}
