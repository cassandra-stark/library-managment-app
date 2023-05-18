package dev.bookstore.library.library;

import dev.bookstore.library.inventory.Inventory;
import jakarta.persistence.*;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "library_name")
    private String name;

    @OneToOne(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    Inventory inventory;

    public Library() {
    }

    public Library(String name, Inventory inventory) {
        this.name = name;
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
