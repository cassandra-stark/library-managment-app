package dev.bookstore.library.categoryTest;

import dev.bookstore.library.category.Category;
import dev.bookstore.library.category.CategoryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setName("Fantasy");
        categoryRepository.save(category);
    }

    @Test
    public void shouldFindCategoryByName() {
        Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());

        assertTrue(optionalCategory.isPresent());
        Category foundCategory = optionalCategory.get();

        assertNotNull(foundCategory);
        assertThat(foundCategory.getName(), equalTo(category.getName()));
    }

}
