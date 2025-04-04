package main.najah.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        main.najah.test.CalculatorTest.class,
        main.najah.test.ProductTest.class,
        main.najah.test.UserServiceSimpleTest.class,
        main.najah.test.RecipeBookTest.class,
        main.najah.test.RecipeTest.class

})
public class AllTests {
}
