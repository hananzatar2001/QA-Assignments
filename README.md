##  Work Summary – JUnit 5 Assignment (HW#2)

This section summarizes all the steps followed to complete the JUnit 5 testing assignment for the Software Testing and Quality Assurance course.

---

### 1.  Project Setup
- The original source code was downloaded from the provided link.
- The project was opened in IntelliJ IDEA and reviewed to understand the existing classes and logic.

---

### 2.  Code Analysis
Analyzed each class inside the `main.najah.code` package:
- `Calculator.java`: Math operations (add, divide, factorial).
- `Product.java`: Product pricing and discounts.
- `UserService.java`: Email validation and authentication.
- `Recipe.java`: Coffee recipe data model with validation.
- `RecipeBook.java`: Management of a collection of recipes.

---

### 3.  Writing Test Classes
Created separate test classes for each source file inside `main.najah.test`, with full coverage:

| Test Class | Description |
|------------|-------------|
| `CalculatorTest` | Tests add, divide, and factorial methods with ordered execution and lifecycle hooks. |
| `ProductTest` | Tests discount logic with valid and invalid inputs. |
| `UserServiceSimpleTest` | Tests email formats and authentication, including parameterized tests. |
| `RecipeTest` | Tests all setters, equality methods, and includes concurrent execution. |
| `RecipeBookTest` | Tests add, delete, and edit functionality of recipes. |
| `AllTests.java` | A test suite to run all the above test classes using `@Suite`. |

---

### 4.  Assignment Requirements Implemented

Below are examples from different test files showing how each requirement was fulfilled.

####  1. `@DisplayName`
```java
@DisplayName("UserService Tests")
class UserServiceSimpleTest {
```
 *File: `UserServiceSimpleTest.java`*

####  2. Valid and Invalid Input Testing
```java
@Test
@Order(2)
void test_negative_price_on_creation() {
    assertThrows(IllegalArgumentException.class, () -> new Product("invalid", -100));
}
```
```java
@Test
@Order(3)
void test_invalid_email_missing_dot() {
    assertFalse(service.isValidEmail("test@examplecom"));
}
```
 *Files: `ProductTest.java`, `UserServiceSimpleTest.java`*

####  3. `@ParameterizedTest`
```java
@ParameterizedTest
@CsvSource({
    "-2, 3, 1",
    "3, 5, 8",
    "0, 0, 0"
})
@Order(9)
@Timeout(2)
void test_add_parameter(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
}
```
 *File: `CalculatorTest.java`*

####  4. `@Timeout`
```java
@Test
@Order(7)
@Timeout(2)
void test_to_string() {
    assertEquals("mocha", recipe.toString());
}
```
 *File: `RecipeTest.java`*

####  5. `@Disabled` for Failing Test
```java
@Disabled("this test fails because 2 + 2 is not equal to 5. Fix by changing the expected value to 4.")
@Test
@Order(8)
void fail_test() {
    assertEquals(5, calculator.add(2, 2));
}
```
 *File: `CalculatorTest.java`*

####  6. Lifecycle Hooks
```java
@BeforeAll
static void before_all() {
    System.out.println("before all tests setup environment.");
}

@AfterEach
void after_each() {
    System.out.println("test finished.");
}
```
 *File: `CalculatorTest.java`*

####  7. `@Order`
```java
@Test
@Order(1)
void add_recipe() {
    assertTrue(book.addRecipe(recipe1));
}
```
 *File: `RecipeBookTest.java`*

####  8. `@Execution(ExecutionMode.CONCURRENT)`
```java
@Execution(ExecutionMode.CONCURRENT)
public class RecipeTest {
```
 *File: `RecipeTest.java`*

####  9. `@Suite`
```java
@Suite
@SelectClasses({
    CalculatorTest.class,
    ProductTest.class,
    UserServiceSimpleTest.class,
    RecipeBookTest.class,
    RecipeTest.class
})
public class AllTests {}
```
 *File: `AllTests.java`*


---

### 5.  Maven Configuration
- Added required dependencies:
  - `junit-jupiter`, `junit-jupiter-params`, `junit-platform-suite-api`
- Configured:
  - `maven-surefire-plugin` for test execution.
  - `jacoco-maven-plugin` to generate test coverage reports.

---

### 6.  Code Coverage
- JaCoCo plugin was used to measure test coverage.
- Achieved **87%** test coverage across all classes.
- Coverage report generated at:  
  `target/site/jacoco/index.html`
![image](https://github.com/user-attachments/assets/78f53875-f8dc-4927-a4d3-93f2ee814c53)

![image](https://github.com/user-attachments/assets/c4db26ef-380f-462c-997f-797c0296d15d)


