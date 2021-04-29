package math.functions.system;

import exceptions.FactorialValueOverflowException;
import exceptions.InfinityException;
import exceptions.InvalidBoundException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import logs.CsvLogger;
import math.functions.logarithmic.Logarithmic;
import math.functions.logarithmic.LogarithmicBase;
import math.functions.trigonometric.Trigonometric;
import math.functions.trigonometric.TrigonometricBase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class SystemOfFunctionsTest {
    private static final double ACCURACY = 0.0001;

    private Logarithmic logarithmic;
    private Trigonometric trigonometric;

    @Before
    public void setup(){
        logarithmic = mock(Logarithmic.class);
        trigonometric = mock(Trigonometric.class);
    }

    @Test
    @Parameters({
            "3, 0.47712125472, 0.68260619449, 1.5849625007, 1.09861229, -3.5343",
            "5, 0.698970004, 1.0, 0.430676558, 1.60943791, -3.990096435",
    })
    public void systemResult_whenPositiveX(double x, double log10res, double log5res, double log2res, double lnRes, double excepted) throws InvalidBoundException, FactorialValueOverflowException, InfinityException {
        when(logarithmic.log(x, 10)).thenReturn(log10res);
        when(logarithmic.log(x, 5)).thenReturn(log5res);
        when(logarithmic.log(x, 2)).thenReturn(log2res);
        when(logarithmic.ln(x)).thenReturn(lnRes);

        SystemOfFunctions system = new SystemOfFunctions(logarithmic, trigonometric);

        Assert.assertEquals(excepted, system.getResult(x), ACCURACY);
    }

    @Test
    @Parameters({
            "-3, -0.9899924966, 7.015252552, 0.14254654307, -7.08616739574, 48.740976966",
            "-5, 0.28366218546, 0.295812916, 3.38051500625, 1.04283521277, -0.285659463",
    })
    public void systemResult_whenNegativeX(double x, double cos, double cot, double tan, double csc, double excepted) throws InvalidBoundException, FactorialValueOverflowException, InfinityException {
        when(trigonometric.cos(x)).thenReturn(cos);
        when(trigonometric.cot(x)).thenReturn(cot);
        when(trigonometric.tan(x)).thenReturn(tan);
        when(trigonometric.csc(x)).thenReturn(csc);

        SystemOfFunctions system = new SystemOfFunctions(logarithmic, trigonometric);

        Assert.assertEquals(excepted, system.getResult(x), ACCURACY);
    }

    @AfterClass
    public static void logSystemOfFunctionResult(){
        List<double[]> results = new ArrayList<>();
        SystemOfFunctions system = new SystemOfFunctions(new LogarithmicBase(ACCURACY), new TrigonometricBase());

        for (double i = 1; i < 3; i += 0.1){
            try {
                results.add(new double[]{i, system.getResult(i)});
            } catch (InvalidBoundException e) {
                e.printStackTrace();
            } catch (InfinityException e) {
                e.printStackTrace();
            } catch (FactorialValueOverflowException e) {
                e.printStackTrace();
            }
        }

        CsvLogger logger = new CsvLogger("csv_output/system_result.csv", results);
        logger.log();
    }
}
