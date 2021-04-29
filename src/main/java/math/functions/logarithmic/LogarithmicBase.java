package math.functions.logarithmic;

import exceptions.InvalidBoundException;

public class LogarithmicBase {
    private double epsilon;

    public LogarithmicBase(double epsilon){
        this.epsilon = epsilon;
    };

    public double ln(double x) throws InvalidBoundException {
        if (x <= 0) throw new InvalidBoundException("x must be greater than 0");

        double currentResult = 0;
        double prevResult;
        int power = 1;

        currentResult += 1.0 / power * Math.pow((x - 1) / (x + 1), power);

        do {
            prevResult = currentResult;
            power += 2;
            currentResult = prevResult + 1.0 / power * Math.pow((x - 1) / (x + 1), power);
        } while (Math.abs(2 * (prevResult - currentResult)) > epsilon);


        return currentResult * 2;
    }
}
