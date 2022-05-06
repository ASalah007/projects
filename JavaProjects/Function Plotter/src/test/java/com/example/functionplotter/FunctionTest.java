package com.example.functionplotter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.example.functionplotter.Function.checkFunction;
class FunctionTest {


    @Test
    void isOperand() {
        Assertions.assertTrue(Function.isOperand('a'));
        Assertions.assertTrue(Function.isOperand('x'));
        Assertions.assertTrue(Function.isOperand('2'));
        Assertions.assertTrue(Function.isOperand('-'));


        Assertions.assertFalse(Function.isOperand('*'));
        Assertions.assertFalse(Function.isOperand('+'));
        Assertions.assertFalse(Function.isOperand('/'));
        Assertions.assertFalse(Function.isOperand('^'));
    }

    @Test
    void isOperation() {
        Assertions.assertTrue(Function.isOperation('+'));
        Assertions.assertTrue(Function.isOperation('-'));
        Assertions.assertTrue(Function.isOperation('/'));
        Assertions.assertTrue(Function.isOperation('*'));
        Assertions.assertTrue(Function.isOperation('^'));


        Assertions.assertFalse(Function.isOperation('1'));
        Assertions.assertFalse(Function.isOperation('a'));
        Assertions.assertFalse(Function.isOperation('&'));
        Assertions.assertFalse(Function.isOperation('('));
        Assertions.assertFalse(Function.isOperation(' '));
    }

    @Test
    void checkFunction_validFunctionsTest() {
        Assertions.assertEquals(-1, checkFunction("x"));
        Assertions.assertEquals(-1, checkFunction("2*x"));
        Assertions.assertEquals(-1, checkFunction("-x"));
        Assertions.assertEquals(-1, checkFunction("x*x +2*x + 5 - 0"));
        Assertions.assertEquals(-1, checkFunction("x^2 / 2"));
        Assertions.assertEquals(-1, checkFunction("x-2*x/4"));
        Assertions.assertEquals(-1, checkFunction("2+2-45*25 /125"));
    }

    @Test
    void checkFunction_invalidFunctionsTest() {
        Assertions.assertEquals(0, checkFunction("--x"));
        Assertions.assertEquals(2, checkFunction("x*-"));
        Assertions.assertEquals(3, checkFunction("x+2-"));
        Assertions.assertEquals(-3, checkFunction("x+a+2"));
        Assertions.assertEquals(1, checkFunction("2x"));
        Assertions.assertEquals(1, checkFunction("x2"));
        Assertions.assertEquals(1, checkFunction("2.5+x"));
        Assertions.assertEquals(-2, checkFunction(""));

    }
    @Test
    void solveFor() {
        Function func = Function.parseFunction("2*x");
        Assertions.assertEquals(24, func.solveFor(12));
        Assertions.assertEquals(-24, func.solveFor(-12));
        Assertions.assertEquals(0, func.solveFor(0));
        Assertions.assertEquals(1.0, func.solveFor(0.5));

    }
}
