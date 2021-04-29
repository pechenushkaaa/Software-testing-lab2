package math.functions.system;

import exceptions.FactorialValueOverflowException;
import exceptions.InfinityException;
import exceptions.InvalidBoundException;
import math.functions.logarithmic.Logarithmic;
import math.functions.logarithmic.LogarithmicBase;
import math.functions.trigonometric.Trigonometric;
import math.functions.trigonometric.TrigonometricBase;

public class SystemOfFunctions {
    private Logarithmic logarithmicFunc;
    private Trigonometric trigonometricFunc;

    public SystemOfFunctions(LogarithmicBase logarithmicBase, TrigonometricBase trigonometricBase){
        this.logarithmicFunc = new Logarithmic(logarithmicBase);
        this.trigonometricFunc = new Trigonometric(trigonometricBase);
    }

    public SystemOfFunctions(Logarithmic logarithmic, Trigonometric trigonometric){
        this.logarithmicFunc = logarithmic;
        this.trigonometricFunc = trigonometric;
    }


    public double getResult(double x) throws FactorialValueOverflowException, InfinityException, InvalidBoundException {
        return x <= 0 ? (Math.pow(trigonometricFunc.cos(x), 2) * trigonometricFunc.tan(x) * trigonometricFunc.cos(x) - trigonometricFunc.csc(x)) / trigonometricFunc.tan(x) : ((logarithmicFunc.log(x, 10) + logarithmicFunc.ln(x) + logarithmicFunc.log(x, 2) + Math.pow(logarithmicFunc.log(x, 2) - logarithmicFunc.log(x, 5), 2)) / (logarithmicFunc.log(x, 10) / logarithmicFunc.log(x, 5))) * (logarithmicFunc.log(x, 10) - logarithmicFunc.ln(x));
    }
}
