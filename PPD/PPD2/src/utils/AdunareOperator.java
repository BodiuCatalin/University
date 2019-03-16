package utils;

import Model.*;

abstract class Operator{
    abstract int apply(int x, int y);

    abstract Complex applyComplex(Complex x, Complex y);
}


public class AdunareOperator extends Operator {
    @Override
    public int apply(int x, int y){
        return x+y;
    }

    @Override
    public Complex applyComplex(Complex x, Complex y){
        Complex c= new Complex();

        double realValue=Math.floor((x.getReal()+ y.getReal())*100)/100;
        double imaginaryValue=Math.floor((x.getImaginary()+y.getImaginary())*100)/100;

        c.setReal(realValue);
        c.setImaginary(imaginaryValue);

        return c;

    }
}
