package math.functions.trigonometric;

import exceptions.EpsilonLimitException;
import exceptions.FactorialValueOverflowException;
import exceptions.InvalidBoundException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import logs.CsvLogger;
import math.functions.logarithmic.LogarithmicBase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class TrigonometricBaseTest {
    private static final double ACCURACY = 0.0001;
    private static TrigonometricBase actual;

    @BeforeClass
    public static void setup() {
        actual = new TrigonometricBase();
    }

    @Test
    @Parameters({
            "0, 0.0",
            "1.57079633, 1.0",
            "3.141592654, 0.0",
            "-1.57079633, -1.0",
    })
    public void sinBorderConditionsEquality(double x, double excepted) throws FactorialValueOverflowException {
        assertEquals(excepted, actual.sin(x), ACCURACY);
    }

    @Test
    public void sinOtherConditionsEquality() throws FactorialValueOverflowException {
        assertEquals(Math.sqrt(2) / 2, actual.sin(Math.PI / 4), ACCURACY);
        assertEquals(Math.sqrt(3) / 2, actual.sin(Math.PI / 3), ACCURACY);
        assertEquals(1.0 / 2, actual.sin(Math.PI / 6), ACCURACY);
    }

    @AfterClass
    public static void logSin(){
        List<double[]> results = new ArrayList<>();
        TrigonometricBase trigonometricBase = new TrigonometricBase();

        for (double i = 0; i < 2.5; i += 0.1){
            try {
                results.add(new double[]{i, trigonometricBase.sin(i)});
            } catch (FactorialValueOverflowException e) {
                e.printStackTrace();
            }
        }

        CsvLogger logger = new CsvLogger("csv_output/sin.csv", results);
        logger.log();
    }
}
