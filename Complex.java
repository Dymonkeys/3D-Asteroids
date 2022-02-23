 
/**
 * Follows rules of complex numbers for simulating rotation
 * 
 * 5/14/19
 */
import java.util.*;
public class Complex
{
    double r;//real element
    double i;//imaginary element
    /**
     * Constructor for objects of class Complex
     */
    public Complex(double real, double imaginary)
    {
        r=real;
        i=imaginary;
    }
    
    public void set(double real, double imaginary){
        r=real;
        i=imaginary;
    }
    
    public void add(double real, double imaginary){
        r+=real;
        i+=imaginary;
    }
    
    public double getReal(){
        return r;
    }
    
    public double getImaginary(){
        return i;
    }
    
    public Complex add(Complex B){
        double r2=B.getReal(); double i2=B.getImaginary();
        return new Complex(r+r2,i+i2);
    }
    
    public Complex subtract(Complex B){
        double r2=B.getReal(); double i2=B.getImaginary();
        return new Complex(r-r2,i-i2);
    }
    
    public Complex multiply(Complex B){
        double r2=B.getReal(); double i2=B.getImaginary();
        //r*r2 + r*i2 + r2*i +i*i2
        double r3 =  (r*r2) - (i*i2);
        double i3 =  (r*i2) + (r2*i);
        return new Complex(r3,i3);
    }
    
    public Complex divide(Complex B){
        double r2=1/B.getReal(); double i2=1/B.getImaginary();
        double r3 =  (r*r2) - (i*i2);
        double i3 =  (r*i2) + (r2*i);
        return new Complex(r3,i3);
    }
    
    public String toString(){
        return "("+getReal()+"+"+getImaginary()+"i)";
    }
}
