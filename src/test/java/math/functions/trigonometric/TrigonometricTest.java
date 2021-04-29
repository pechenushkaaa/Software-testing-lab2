package math.functions.trigonometric;

import exceptions.FactorialValueOverflowException;
import exceptions.InfinityException;
import logs.CsvLogger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrigonometricTest {
    private static final double ACCURACY = 0.0001;
    private static TrigonometricBase trigonometricBase;
    private static Trigonometric actual;
    private static Trigonometric trigonometric;
    private static List<double[]> results;

    @BeforeClass
    public static void setup(){
        trigonometricBase = mock(TrigonometricBase.class);
        results = new ArrayList<>();

        try {
            when(trigonometricBase.sin(0)).thenReturn(0.0);
            when(trigonometricBase.sin(Math.PI / 4)).thenReturn(0.7071);
            when(trigonometricBase.sin(Math.PI)).thenReturn(0.0);
            when(trigonometricBase.sin(Math.PI / 2)).thenReturn(1.0);
            when(trigonometricBase.sin(-Math.PI / 2)).thenReturn(-1.0);
            when(trigonometricBase.sin(-Math.PI / 4)).thenReturn(-0.7071);

            when(trigonometricBase.sin(Math.PI / 8)).thenReturn(0.382683432);
            when(trigonometricBase.sin(Math.PI / 6)).thenReturn(0.5);
            when(trigonometricBase.sin(Math.PI / 12)).thenReturn(0.258819045);

            when(trigonometricBase.sin(Math.PI / 6)).thenReturn(0.5);
            when(trigonometricBase.sin(Math.PI / 4)).thenReturn(Math.sqrt(2) / 2);
            when(trigonometricBase.sin(Math.PI / 3)).thenReturn(Math.sqrt(3) / 2);
        } catch (FactorialValueOverflowException e) {
            e.printStackTrace();
        }

        trigonometric = new Trigonometric(new TrigonometricBase());
        actual = new Trigonometric(trigonometricBase);
    }

    @Before
    public void clearLogsList(){
        results.clear();
    }

    @Test
    public void cosConditionsEquality() throws FactorialValueOverflowException, InfinityException {
        assertEquals(1.0, actual.cos(0), ACCURACY);
        assertEquals(0.0, actual.cos(Math.PI / 2), ACCURACY);
        assertEquals(-1.0, actual.cos(Math.PI), ACCURACY);
        assertEquals(0.0, actual.cos(-Math.PI / 2), ACCURACY);

        assertEquals(Math.sqrt(2) / 2, actual.cos(Math.PI / 4), ACCURACY);
        assertEquals(0.5, actual.cos(Math.PI / 3), ACCURACY);
        assertEquals(Math.sqrt(3) / 2, actual.cos(Math.PI / 6), ACCURACY);

        //logCos();
    }

    @Test
    public void tanConditionsEquality() throws FactorialValueOverflowException, InfinityException {
        assertEquals(0.0, actual.tan(0.0), ACCURACY);
        assertEquals(0.0, actual.tan(Math.PI), ACCURACY);

        assertEquals(Math.sqrt(3), actual.tan(Math.PI / 3), ACCURACY);
        assertEquals(1.0, actual.tan(Math.PI / 4), ACCURACY);
        assertEquals(Math.sqrt(3) / 3, actual.tan(Math.PI / 6), ACCURACY);

        //logTan();
    }

    @Test
    public void cotConditionsEquality() throws FactorialValueOverflowException, InfinityException {
        assertEquals(0.0, actual.cot(Math.PI / 2), ACCURACY);
        assertEquals(0.0, actual.cot(-Math.PI / 2), ACCURACY);

        //logCot();
    }

    @Test
    public void cscConditionsEquality() throws FactorialValueOverflowException, InfinityException {
        assertEquals(1.0, actual.csc(Math.PI / 2), ACCURACY);

        assertEquals(2 * Math.sqrt(3) / 3, actual.csc(Math.PI / 3), ACCURACY);
        assertEquals(Math.sqrt(2), actual.csc(Math.PI / 4), ACCURACY);
        assertEquals(2, actual.csc(Math.PI / 6), ACCURACY);

        //logCsc();
    }


    @Test(expected = InfinityException.class)
    public void tanCondition_throwException() throws FactorialValueOverflowException, InfinityException {
        TrigonometricBase trigonometricBase = mock(TrigonometricBase.class);

        when(trigonometricBase.sin(anyDouble())).thenReturn(Math.sqrt(2) / 2);

        Trigonometric actual = new Trigonometric(trigonometricBase);

        actual.tan(1);
    }


    private void logCos(){
        for (double i = 1; i < 3; i += 0.1){
            try {
                results.add(new double[]{i, trigonometric.cos(i)});
            } catch (FactorialValueOverflowException e) {
                e.printStackTrace();
            }
        }

        CsvLogger logger = new CsvLogger("csv_output/cos.csv", results);
        logger.log();
    }

    public void logTan(){
        for (double i = 1; i < 3; i += 0.1){
            try {
                results.add(new double[]{i, trigonometric.tan(i)});
            } catch (FactorialValueOverflowException | InfinityException e) {
                e.printStackTrace();
            }
        }

        CsvLogger logger = new CsvLogger("csv_output/tan.csv", results);
        logger.log();
    }

    public void logCot(){
        for (double i = 1; i < 3; i += 0.1){
            try {
                results.add(new double[]{i, trigonometric.cot(i)});
            } catch (FactorialValueOverflowException | InfinityException e) {
                e.printStackTrace();
            }
        }

        CsvLogger logger = new CsvLogger("csv_output/cot.csv", results);
        logger.log();
    }

    public void logCsc(){
        for (double i = 1; i < 3; i += 0.1){
            try {
                results.add(new double[]{i, trigonometric.csc(i)});
            } catch (FactorialValueOverflowException | InfinityException e) {
                e.printStackTrace();
            }
        }

        CsvLogger logger = new CsvLogger("csv_output/csc.csv", results);
        logger.log();
    }

}
