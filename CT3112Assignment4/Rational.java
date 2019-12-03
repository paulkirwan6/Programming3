import java.text.DecimalFormat;

/*
 *	Paul Kirwan
 *	17321313
 */

public class Rational {
	private int numerator, denominator;	
	private String output = "";
	private DecimalFormat precision2 = new DecimalFormat("0.00");	//Display to 2 decimal places

	public Rational(int num, int den) {		//where num is the numerator and den is the denominator
		this.numerator = num;
		this.denominator = den;
		output += simplify(num, den);
	}

	//For printing the input arguments in the Test Class
	public String getInput() {
		return "(" + numerator + "/" + denominator + ")";
	}

	//This Rational object times another Rational object
	public Rational multiply(Rational b) {
		return new Rational(this.numerator*b.numerator, this.denominator*b.denominator);
	}

	//This Rational object divided another Rational object
	public Rational divide(Rational b) {
		return new Rational(this.numerator*b.denominator, this.denominator*b.numerator);
	}

	//This Rational object plus another Rational object
	public Rational plus(Rational b) {
		int newNum   = (this.numerator * b.denominator) + (this.denominator * b.numerator);
		int newDen = this.denominator * b.denominator;
		return new Rational(newNum, newDen);
	}

	//This Rational object minus another Rational object
	public Rational minus(Rational b) {
		int newNum   = (this.numerator * b.denominator) - (this.denominator * b.numerator);
		int newDen = this.denominator * b.denominator;
		return new Rational(newNum, newDen);
	}

	//Find the greatest common denominator, adapted from the Euclidean algorithm, uses recursion
	//https://en.wikipedia.org/wiki/Euclidean_algorithm
	public int gcd(int a, int b) {
		if (b == 0)
			return a; 
		else
			return gcd(b, a % b);
	}

	//Format the simplified rational number
	public String simplify(int a, int b) {
		int gcd = (int) gcd(a, b);
		return (a / gcd) + "/" + (b / gcd);
	}

	//Summary of the simplification of the Rational number
	@Override
	public String toString() {
		return output + " = " + precision2.format((double) numerator / denominator);
	}
}
