package main.najah.test;

import main.najah.code.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

// run tests in specific order
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductTest {

	Product product;

	// create a new product before each test
	@BeforeEach
	void setUp() {
		product = new Product("laptop", 1000.0);
	}

	// test product constructor

	@Test
	@Order(1)
		// check the original price of the product
	void test_initial_Price() {
		assertEquals(1000.0, product.getPrice());
	}

	@Test
	@Order(2)
		// product should not allow negative price at creation
	void test_negative_price_on_creation() {
		assertThrows(IllegalArgumentException.class, () -> new Product("invalid", -100));
	}

	// test apply discount

	@Test
	@Order(3)
		// apply 10% discount and check the final price
	void test_apply_valid_discount() {
		product.applyDiscount(10.0);
		assertEquals(900.0, product.getFinalPrice());
	}

	@Test
	@Order(4)
		// apply 0% discount and expect same price
	void test_apply_zero_discount() {
		product.applyDiscount(0);
		assertEquals(1000.0, product.getFinalPrice());
	}

	@Test
	@Order(5)
		// apply 50% discount (the maximum allowed)
	void test_apply_max_allowed_discount() {
		product.applyDiscount(50);
		assertEquals(500.0, product.getFinalPrice());
	}

	@Test
	@Order(6)
		// discount above 50% should throw an error
	void test_apply_invalid_discount_above50() {
		assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(55));
	}

	@Test
	@Order(7)
		// negative discount should also throw an error
	void test_apply_negative_discount() {
		assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-5));
	}

	@ParameterizedTest
	@ValueSource(doubles = {5, 10, 25, 50})
	@Order(8)
	@Timeout(2)
		// apply different valid discounts and check if final price is not higher than original
	void test_Valid_Discounts(double discount) {
		product.applyDiscount(discount);
		assertTrue(product.getFinalPrice() <= product.getPrice());
	}

	// test get final price and other getters

	@Test
	@Order(9)
		// apply discount and check if getters return correct values
	void test_getters() {
		product.applyDiscount(25);
		assertEquals("laptop", product.getName());
		assertEquals(1000.0, product.getPrice());
		assertEquals(25.0, product.getDiscount());
	}

	@Disabled("this test fails because 10% discount on 1000 is 900, not 800. Fix expected to 900.")
	@Test
	@Order(10)
		// this test has wrong expected result on purpose
	void test_invalid_expected_discount() {
		product.applyDiscount(10.0);
		assertEquals(800.0, product.getFinalPrice());
	}
}
