/**
 * The class containing your tests for the {@link Demo} class.  Make sure you
 * test all methods in this class (including both 
 * {@link Demo#main(String[])} and 
 * {@link Demo#isTriangle(double, double, double)}).
 */

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class DemoTest {

    ByteArrayInputStream in;
    ByteArrayOutputStream out;

    @Test
    public void testMainIsTriangle() {

        StringBuilder sb = new StringBuilder();
        sb.append("2\n");
        sb.append("4\n");
        sb.append("3\n");

        setIOStreams(sb);

        String[] args = {};
        Demo.main(args);

        String consoleOutput = formatOutput("This is a triangle.");

        assertEquals("The output is a triangle",consoleOutput, out.toString());
    }

    @Test
    public void testMainIsNotTriangle() {

        StringBuilder sb = new StringBuilder();
        sb.append("1\n");
        sb.append("4\n");
        sb.append("3\n");

        setIOStreams(sb);

        String[] args = {};
        Demo.main(args);

        String consoleOutput = formatOutput("This is not a triangle.");

        assertEquals("The output is not a triangle",consoleOutput, out.toString());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testInvalidOutput() {

        thrown.expect(java.util.InputMismatchException.class);

        StringBuilder sb = new StringBuilder();
        sb.append("a");
        sb.append("4\n");
        sb.append("3\n");

        setIOStreams(sb);

        String[] args = {};
        Demo.main(args);
    }

    @Test
    public void testIsTriangleScalene() {
        double sideA = 5;
        double sideB = 3;
        double sideC = 6;

        boolean result = Demo.isTriangle(sideA, sideB, sideC);

        assertTrue("It is a Triangle", result);
    }

    @Test
    public void testIsTriangleEquilateral() {
        double sideA = 9;
        double sideB = 9;
        double sideC = 9;

        boolean result = Demo.isTriangle(sideA, sideB, sideC);

        assertTrue("It is a Triangle", result);
    }

    @Test
    public void testIsTriangleIsosceles() {
        double sideA = 3;
        double sideB = 3;
        double sideC = 5;

        boolean result = Demo.isTriangle(sideA, sideB, sideC);

        assertTrue("It is a Triangle", result);
    }

    @Test
    public void testIsNotTriangleScalene() {
        double sideA = 6;
        double sideB = 1;
        double sideC = 4;

        boolean result = Demo.isTriangle(sideA, sideB, sideC);

        assertFalse("It is not a Triangle", result);
    }

    @Test
    public void testIsNotTriangleNegativeSide() {
        double sideA = -2;
        double sideB = 5;
        double sideC = 3;

        boolean result = Demo.isTriangle(sideA, sideB, sideC);

        assertFalse("It is not a Triangle", result);
    }

    @Test
    public void testIsNotTriangleSideZero() {
        double sideA = 9;
        double sideB = 0;
        double sideC = 5;

        boolean result = Demo.isTriangle(sideA, sideB, sideC);

        assertFalse("It is not a Triangle", result);
    }

    @Test
    public void testIsNotTriangleSidesZero() {
        double sideA = 0;
        double sideB = 0;
        double sideC = 0;

        boolean result = Demo.isTriangle(sideA, sideB, sideC);

        assertFalse("It is not a Triangle", result);
    }

    public void setIOStreams(StringBuilder sb) {
        in = new ByteArrayInputStream(sb.toString().getBytes());
        System.setIn(in);

        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    public String formatOutput(String expectedMessage) {

        String consoleOutput = "Enter side 1: " + System.getProperty("line.separator");
        consoleOutput += "Enter side 2: " + System.getProperty("line.separator");
        consoleOutput += "Enter side 3: " + System.getProperty("line.separator");
        consoleOutput += expectedMessage + System.getProperty("line.separator");

        return consoleOutput;

    }

}
