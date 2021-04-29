package math.functions.trigonometric;

import exceptions.FactorialValueOverflowException;
import exceptions.InfinityException;

public class Trigonometric {
    private TrigonometricBase trigonometricBase;
    private static final double NULL_BOUND = 0.000000000000001;

    public Trigonometric(TrigonometricBase trigonometricBase){
        this.trigonometricBase = trigonometricBase;
    }

    public double cos(double x) throws FactorialValueOverflowException{
        double result = 1 - 2 * Math.pow(trigonometricBase.sin(x / 2), 2);

        return Math.abs(result) < NULL_BOUND ? 0.0 : result;
    }

    public double tan(double x) throws InfinityException, FactorialValueOverflowException {
        double result = trigonometricBase.sin(x) / cos(x);

        if (Double.isInfinite(result)) throw new InfinityException();

        return result;
    }

    public double csc(double x) throws FactorialValueOverflowException, InfinityException {
        double result = 1 / trigonometricBase.sin(x);

        if (Double.isInfinite(result)) throw new InfinityException();

        return result;
    }

    public double cot(double x) throws FactorialValueOverflowException, InfinityException {
        double result = cos(x) / trigonometricBase.sin(x);

        if (Double.isInfinite(result)) throw new InfinityException();

        return result;
    }
}
