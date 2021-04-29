package math.functions.logarithmic;

import exceptions.InvalidBoundException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import logs.CsvLogger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class LogarithmicBaseTest {
    private static final double ACCURACY = 0.0001;
    private static LogarithmicBase actual;

    @BeforeClass
    public static void setup() {
        actual = new LogarithmicBase(ACCURACY);
    }

    @Test(expected = InvalidBoundException.class)
    public void lnBorderConditionsEquality() throws InvalidBoundException {
        actual.ln(0);
    }

    @Test
    @Parameters({
            "1.60943791, 5",
            "0.693147181, 2",
    })
    public void lnConditionsEquality(double excepted, double x) throws InvalidBoundException {
        assertEquals(excepted, actual.ln(x), ACCURACY);
    }

    @AfterClass
    public static void logLn(){
        List<double[]> results = new ArrayList<>();
        LogarithmicBase logarithmicBase = new LogarithmicBase(ACCURACY);

        for (double i = 1; i < 3; i += 0.1){
            try {
                results.add(new double[]{i, logarithmicBase.ln(i)});
            } catch (InvalidBoundException e) {
                e.printStackTrace();
            }
        }

        CsvLogger logger = new CsvLogger("csv_output/ln.csv", results);
        logger.log();
    }
}
