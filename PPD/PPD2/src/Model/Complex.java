package Model;

public class Complex {

    private double real;
    private double imaginary;
    public Complex(){
        this.real=0.0;
        this.imaginary=0.0;

    }

    public Complex(double real,double imaginary){
        this.real=real;
        this.imaginary=imaginary;
    }

    public double getReal(){return this.real;}

    public double getImaginary(){return this.imaginary;}

    public void setReal(double value){this.real=value;}

    public void setImaginary(double value){this.imaginary=value;}
}
