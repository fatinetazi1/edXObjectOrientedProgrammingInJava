import java.util.InputMismatchException;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator){
        if(denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }

        if(denominator < 0){
            numerator *= -1;
            denominator *= -1;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int numerator){
        this(numerator, 1);
    }

    public Fraction(){
        this(0);
    }

    public int getNumerator(){
        return numerator;
    }

    public int getDenominator(){
        return denominator;
    }

    public String toString(){
        return numerator + "/" + denominator;
    }

    public double toDouble(){
        return (double) (numerator/denominator);
    }

    public Fraction add(Fraction f){
        int den = this.denominator*f.denominator;
        int num = (f.denominator*this.numerator) + (this.denominator*f.numerator);
        Fraction sum = new Fraction(num, den);
        return sum;
    }

    public Fraction subtract(Fraction f){
        int den = this.denominator*f.denominator;
        int num = (f.denominator*this.numerator) - (this.denominator*f.numerator);
        Fraction difference = new Fraction(num, den);
        return difference;
    }

    public Fraction multiply(Fraction f){
        int den = this.denominator*f.denominator;
        int num = this.numerator*f.numerator;
        Fraction product = new Fraction(num, den);
        return product;
    }

    public Fraction divide(Fraction f){
        if(f.numerator == 0){
            throw new IllegalArgumentException("Cannot divide by zero.");
        }

        int temp = f.denominator;
        f.denominator = f.numerator;
        f.numerator = temp;

        Fraction division = this.multiply(f);
        return division;
    }

    public boolean equals(Object f){
        boolean result = false;
        if(f instanceof Fraction){
            Fraction otherFrac = (Fraction)f;
            otherFrac.toLowestTerms();

            Fraction thisFrac = new Fraction(this.numerator, this.denominator);
            thisFrac.toLowestTerms();

            if ((thisFrac.numerator == otherFrac.numerator) && (thisFrac.denominator == otherFrac.denominator)) {
                result = true;
            } else {
                result = false;
            }
        } else {
            throw new InputMismatchException();
        }
        return result;
    }

    public static int gcd(int num, int den){
        int smaller = num < den ? num : den;
        int GCD = -1;
        for (int i = smaller; i > 0; --i) {
            if (num%i == 0 && den%i == 0) {
                GCD = i;
                break;
            }
        }
        return GCD;
    }

    public Fraction toLowestTerms(){
        int GCD = gcd(this.numerator, this.denominator);

        this.numerator /= GCD;
        this.denominator /= GCD;

        if(this.denominator < 0) {
            this.numerator *= -1;
            this.denominator *= -1;
        }

        return this;
    }

}
