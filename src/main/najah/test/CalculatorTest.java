package main.najah.test;

import main.najah.code.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("calculator tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalculatorTest {

	Calculator calculator;

	@BeforeAll
	static void before_all()
	{
		System.out.println("before all tests setup environment.");
	}

	@AfterAll
	static void after_all()
	{
		System.out.println("after all tests cleanup resources.");
	}

	@BeforeEach
	void before_each()
	{
		calculator = new Calculator();
		System.out.println("before each test new calculator instance created.");
	}

	@AfterEach
	void after_each()
	{
		System.out.println("test finished.");
	}
// test add function
	@Test
	@Order(1)
	@Timeout(2)// if test takes more than 2 seconds, it fails
	void test_add_multiple_numbers()
	{
		assertEquals(15, calculator.add(5, 3, 7));
	}

	@Test
	@Order(2)
		// test if add with no numbers returns 0
	void test_add_empty()
	{
		assertEquals(0, calculator.add());
	}

	// test divide function
	@Test
	@Order(3)
		// test divide with normal values
	void test_divide_normal()
	{
		assertEquals(2, calculator.divide(10, 5));
	}

	@Test
	@Order(4)
		// test divide by zero. It should throw an error.
	void test_divide_by_zero()
	{
		assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
	}

	// test factorial function

	@Test
	@Order(5)
	@Timeout(2)
		// test factorial of 0 = 1
	void test_factorial_of_zero()
	{
		assertEquals(1, calculator.factorial(0));
	}

	@Test
	@Order(6)
		// test factorial of 5 = 120

	void test_factorial_of_five()
	{
		assertEquals(120, calculator.factorial(5));
	}

	@Test
	@Order(7)
		// test factorial of negative number. Should give error.
	void test_factorial_negative()
	{
		assertThrows(IllegalArgumentException.class, () -> calculator.factorial(-3));
	}

	@Disabled("this test fails because 2 + 2 is not equal to 5. Fix by changing the expected value to 4.")
	@Test
	@Order(8)
		// wrong test on purpose. It will be skipped.
	void fail_test() {
		assertEquals(5, calculator.add(2, 2));
	}

	@ParameterizedTest
	@CsvSource({
			"-2, 3, 1",
			"3, 5, 8",
			"0, 0, 0",
			"12,9,21",
			"-2, 2, 0",
			"5,-8,-3",
			"2, 2,4",
			"-5,0,-5"

	})
	@Order(9)
	@Timeout(2)
		// test add with many inputs and check the result
	void test_add_parameter(int a, int b, int expected)
	{
		assertEquals(expected, calculator.add(a, b));
	}
}



