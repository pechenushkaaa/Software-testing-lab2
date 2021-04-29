package math.functions.logarithmic;

import exceptions.InvalidBoundException;

public class Logarithmic {
    private LogarithmicBase logarithmicBase;

    public Logarithmic(LogarithmicBase logarithmicBase){
        this.logarithmicBase = logarithmicBase;
    }

    public double log(double x, double base) throws InvalidBoundException {

        return logarithmicBase.ln(x) / logarithmicBase.ln(base);
    }

    public double ln(double x) throws InvalidBoundException {
        return logarithmicBase.ln(x);
    }
}
