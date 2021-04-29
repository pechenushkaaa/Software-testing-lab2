package math.functions.logarithmic;

import exceptions.FactorialValueOverflowException;
import exceptions.InvalidBoundException;
import logs.CsvLogger;
import math.functions.trigonometric.TrigonometricBase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogarithmicTest {
    private static final double ACCURACY = 0.0001;

    @Test
    public void logBorderConditionsEquality() throws InvalidBoundException {
        LogarithmicBase logarithmicBase = mock(LogarithmicBase.class);
        double x = 3;

        when(logarithmicBase.ln(10)).thenReturn(2.30258509);
        when(logarithmicBase.ln(5)).thenReturn(1.60943791);
        when(logarithmicBase.ln(2)).thenReturn(0.693147181);
        when(logarithmicBase.ln(x)).thenReturn(1.09861229);

        Logarithmic logarithmic = new Logarithmic(logarithmicBase);

        assertEquals(0.4771, logarithmic.log(x, 10), ACCURACY);
        assertEquals(0.6826, logarithmic.log(x, 5), ACCURACY);
        assertEquals(1.585, logarithmic.log(x, 2), ACCURACY);

        //logLogarithm();
    }

    private void logLogarithm(){
        List<double[]> results = new ArrayList<>();
        Logarithmic logarithmic = new Logarithmic(new LogarithmicBase(ACCURACY));
        double base = 4;

        for (double i = 1; i < 3; i += 0.1){
            try {
                results.add(new double[]{i, logarithmic.log(i, base)});
            } catch (InvalidBoundException e) {
                e.printStackTrace();
            }
        }

        CsvLogger logger = new CsvLogger("csv_output/logarithm.csv", results);
        logger.log();
    }
}
