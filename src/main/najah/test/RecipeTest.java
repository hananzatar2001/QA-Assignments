package main.najah.test;

import main.najah.code.Recipe;
import main.najah.code.RecipeException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("recipe class tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
public class RecipeTest {

    Recipe recipe;

    @BeforeEach
    void init_recipe() throws RecipeException {
        recipe = new Recipe();
        recipe.setName("mocha");
        recipe.setAmtCoffee("2");
        recipe.setAmtMilk("1");
        recipe.setAmtSugar("1");
        recipe.setAmtChocolate("1");
        recipe.setPrice("10");
    }

    @ParameterizedTest
    @CsvSource({
            "2,1,1,1,10",
            "3,2,2,2,15"
    })
    @Order(1)
    @Timeout(2)
        // set and get values and check if correct
    void test_valid_amounts(int coffee, int milk, int sugar, int chocolate, int price) throws RecipeException {
        recipe.setAmtCoffee(String.valueOf(coffee));
        recipe.setAmtMilk(String.valueOf(milk));
        recipe.setAmtSugar(String.valueOf(sugar));
        recipe.setAmtChocolate(String.valueOf(chocolate));
        recipe.setPrice(String.valueOf(price));

        assertEquals(coffee, recipe.getAmtCoffee());
        assertEquals(milk, recipe.getAmtMilk());
        assertEquals(sugar, recipe.getAmtSugar());
        assertEquals(chocolate, recipe.getAmtChocolate());
        assertEquals(price, recipe.getPrice());
    }

    @Test
    @Order(2)
    @Timeout(2)
        // check if object equals itself
    void test_equals_self() {
        assertEquals(recipe, recipe);
    }

    @Test
    @Order(3)
    @Timeout(2)
        // object should not equal null
    void test_equals_null() {
        assertNotEquals(recipe, null);
    }

    @Test
    @Order(4)
    @Timeout(2)
        // object should not equal another class
    void test_equals_different_class() {
        assertNotEquals(recipe, "string");
    }

    @Test
    @Order(5)
    @Timeout(2)
        // object should not equal another recipe with different name
    void test_equals_different_name() throws RecipeException {
        Recipe r2 = new Recipe();
        r2.setName("latte");
        assertNotEquals(recipe, r2);
    }

    @Test
    @Order(6)
    @Timeout(2)
        // object should equal another recipe with same name
    void test_equals_same_name() throws RecipeException {
        Recipe r2 = new Recipe();
        r2.setName("mocha");
        assertEquals(recipe, r2);
    }

    @Test
    @Order(7)
    @Timeout(2)
        // toString should return the name
    void test_to_string() {
        assertEquals("mocha", recipe.toString());
    }

    @Test
    @Order(8)
    @Timeout(2)
        // hash code should not be 0
    void test_hash_code() {
        assertNotEquals(0, recipe.hashCode());
    }

    @Disabled("this test fails intentionally to show how to handle invalid input. Fix by using valid integer.")
    @Test
    @Order(9)
    @Timeout(2)
        // set invalid price string, should throw or handle error
    void test_invalid_price() throws RecipeException {
        recipe.setPrice("abc");
        assertEquals(0, recipe.getPrice());
    }

    @Test
    @Order(10)
    @Timeout(2)
        // set name and check if it returns correctly
    void test_set_name() throws RecipeException {
        recipe.setName("cappuccino");
        assertEquals("cappuccino", recipe.getName());
    }
}
