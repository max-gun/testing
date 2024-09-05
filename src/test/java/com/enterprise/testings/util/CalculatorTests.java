package com.enterprise.testings.util;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author max.gun
 * @since 29/08/2024
 */
public class CalculatorTests {

    private final Calculator calc = new Calculator();

    @BeforeAll
    public static void setupTests() {
        System.out.println("Running Setup!");
    }

    @BeforeEach
    public void prepareTest() {
        System.out.println("Preparing for testing...");
    }

    @Test
    public void testAddWithPositiveNumbers() {
        int result = calc.add(3, 4);
        int expected = 7;

        assertEquals(expected, result, "the sum of 3 and 4 should be 7");
    }

    @Test
    public void testAddWithNegativeNumbers() {
        int result = calc.add(-3, -4);
        int expected = -7;

        assertEquals(expected, result, "the sum of (-3) and (-4) should be (-7)");
    }

    @Test
    public void testDivWithPositiveNumbers() {
        int result = calc.div(10, 2);
        int expected = 5;
        assertEquals(expected, result, "the result should be 5");
    }

    @Test
    public void testDivWithZero() {
        int result = calc.div(10, 0);
        int expected = 5;
        assertEquals(expected, result, "the result should be 5");
    }

    @AfterAll
    public static void finalizeTests() {
        System.out.println("Shuting Down...");
    }

    @AfterEach
    public void cleanTest() {
        System.out.println("cleaning DB...");
    }
}
