package math.functions.trigonometric;

import exceptions.FactorialValueOverflowException;
import math.functions.MathSupport;

public class TrigonometricBase {
    private static final int N = 9;

    public double sin(double x) throws FactorialValueOverflowException {
        double result = 0;

        for (int i = 1; i <= N; i++){
            result += Math.pow(-1, i - 1) * (Math.pow(x, 2 * i - 1) / MathSupport.factorial(2 * i - 1));
        }

        return result;
    }
}
