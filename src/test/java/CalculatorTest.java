import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class CalculatorTest {

    private SuperCalculator calc = new SuperCalculator();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        PrintStream stream = new PrintStream(outputStreamCaptor);
        System.setOut(stream);
    }

    @Test
    public void simpleArabic() {

        calc.calculateMain("10 + 10");
        Assert.assertEquals("20", outputStreamCaptor.toString().trim());

    }

    @Test
    public void simpleRoman() {

        calc.calculateMain("I + VII");
        Assert.assertEquals("VIII", outputStreamCaptor.toString().trim());

    }

    @Test
    public void bigRoman() {

        calc.calculateMain("X + VII");
        Assert.assertEquals("XVII", outputStreamCaptor.toString().trim());

    }

    @Test
    public void multiplicationRoman() {

        calc.calculateMain("X * VII");
        Assert.assertEquals("LXX", outputStreamCaptor.toString().trim());

    }

    @Test
    public void divRoman() {

        calc.calculateMain("X / II");
        Assert.assertEquals("V", outputStreamCaptor.toString().trim());

    }

    @Test
    public void incorrectRoman() {

        calc.calculateMain("IXIXIX + VII");
        Assert.assertEquals("IXIXIX cannot be converted to a Roman", outputStreamCaptor.toString().trim());

    }

    @Test
    public void simpleArabic_exceeding_the_limit() {

        calc.calculateMain("11 + 0");
        Assert.assertEquals("The entered values are greater than 10.", outputStreamCaptor.toString().trim());

    }

    @Test
    public void simpleRoman_exceeding_the_limit() {

        calc.calculateMain("XI + V");
        Assert.assertEquals("XI cannot be converted to a Roman", outputStreamCaptor.toString().trim());

    }

    @Test
    public void type_Integer() {

        calc.calculateMain("1 + 1.2");
        Assert.assertEquals("The input format is not correct. Check the input data.", outputStreamCaptor.toString().trim());

    }

    @Test
    public void mix_Arabic_Roman() {

        calc.calculateMain("IX + 1");
        Assert.assertEquals("The input format is not correct. Check the input data.", outputStreamCaptor.toString().trim());

    }


    @AfterEach
    public void cleanUpStreams() {
        System.setOut(standardOut);
    }

}
