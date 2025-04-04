package main.najah.test;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("recipebook tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecipeBookTest {

    RecipeBook book;
    Recipe recipe1;
    Recipe recipe2;

    @BeforeEach
    void init_book() throws RecipeException {
        book = new RecipeBook();

        recipe1 = new Recipe();
        recipe1.setName("mocha");
        recipe1.setAmtCoffee("2");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setAmtChocolate("1");
        recipe1.setPrice("10");

        recipe2 = new Recipe();
        recipe2.setName("latte");
        recipe2.setAmtCoffee("1");
        recipe2.setAmtMilk("2");
        recipe2.setAmtSugar("1");
        recipe2.setAmtChocolate("0");
        recipe2.setPrice("8");
    }

    @Test
    @Order(1)
        // add one recipe to the book
    void add_recipe() {
        assertTrue(book.addRecipe(recipe1));
    }

    @Test
    @Order(2)
        // try to add the same recipe again
    void add_duplicate_recipe() {
        book.addRecipe(recipe1);
        assertFalse(book.addRecipe(recipe1));
    }

    @Test
    @Order(3)
        // delete recipe at index 0
    void delete_recipe() {
        book.addRecipe(recipe1);
        assertEquals("mocha", book.deleteRecipe(0));
    }

    @Test
    @Order(4)
        // delete recipe at invalid index
    void delete_invalid_index() {
        assertNull(book.deleteRecipe(2));
    }

    @Test
    @Order(5)
        // edit recipe at index 0
    void edit_recipe() {
        book.addRecipe(recipe1);
        assertEquals("mocha", book.editRecipe(0, recipe2));
    }

    @Test
    @Order(6)
        // edit recipe at invalid index
    void edit_invalid_index() {
        assertNull(book.editRecipe(2, recipe2));
    }

    @Test
    @Order(7)
        // get all recipes and check array size
    void get_recipes() {
        assertNotNull(book.getRecipes());
        assertEquals(4, book.getRecipes().length);
    }

    @Disabled("this test fails because no recipe is added before deleting.")
    @Test
    @Order(8)
        // try to delete recipe without adding any
    void delete_without_add() {
        assertEquals("mocha", book.deleteRecipe(0));
    }
}
