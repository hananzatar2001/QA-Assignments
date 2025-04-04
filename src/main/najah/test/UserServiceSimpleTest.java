package main.najah.test;

import main.najah.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserService Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class UserServiceSimpleTest {

	private UserService service;

	@BeforeEach
	void set_up() {
		service = new UserService();
	}

	@ParameterizedTest
	@CsvSource({
			"valid@email.com, true",
			"another@domain.org, true",
			"wrongemail, false",
			"bad@nodot, false",
			"@missingprefix.com, true"
	})
	@Order(8)
	@Timeout(2)
		// test many email formats with expected result
	void test_multiple_emails(String email, boolean expected) {
		boolean result = service.isValidEmail(email);
		System.out.println("testing email: " + email + " => " + result);
		assertEquals(expected, result);
	}

	@Test
	@Order(1)
	@Timeout(2)
		// test correct email format
	void test_valid_email() {
		assertTrue(service.isValidEmail("test@example.com"));
	}

	@Test
	@Order(2)
	@Timeout(2)
		// test email without "@"
	void test_invalid_email_missing_at() {
		assertFalse(service.isValidEmail("testexample.com"));
	}

	@Test
	@Order(3)
	@Timeout(2)
		// test email without dot "."
	void test_invalid_email_missing_dot() {
		assertFalse(service.isValidEmail("test@examplecom"));
	}

	@Test
	@Order(4)
	@Timeout(2)
		// test correct username and password
	void test_auth_success() {
		assertTrue(service.authenticate("admin", "1234"));
	}

	@Test
	@Order(5)
	@Timeout(2)
		// test wrong username
	void test_auth_wrong_username() {
		assertFalse(service.authenticate("user", "1234"));
	}

	@Test
	@Order(6)
	@Timeout(2)
		// test wrong password
	void test_auth_wrong_password() {
		assertFalse(service.authenticate("admin", "wrongpass"));
	}

	@Disabled("this test is wrong on purpose. 'invalidEmail' should return false.")
	@Test
	@Order(7)
	@Timeout(2)
	void test_invalid_email_fail() {
		assertTrue(service.isValidEmail("invalidEmail"));
	}
}
