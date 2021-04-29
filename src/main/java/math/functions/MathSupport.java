package math.functions;

import exceptions.FactorialValueOverflowException;

public class MathSupport {
    public static long factorial(int n) throws FactorialValueOverflowException {
        long result = 1;

        while (n > 0) {
            result *= n;
            if (result < 0) throw new FactorialValueOverflowException();
            n--;
        }

        return result;
    }
}
