import java.text.DecimalFormat;

/*
 *	Paul Kirwan
 *	17321313
 */

public class Test {

	// Test Ball enum
	public static void enumTest() {
		System.out.println("Enum Testing:");
		for (Ball b : Ball.values()) {
			System.out.println(b + "\t" + b.mass() + "g\t" + b.diameter() + "mm");
		}

		//Formatting
		System.out.println();

		//Display values to 2 decimal places
		DecimalFormat precision2 = new DecimalFormat("0.00");

		//Show circumference of golf ball and volume of baseball
		System.out.println("The circumference of a golf ball is " + precision2.format(Ball.GOLFBALL.getCircumference()) + "mm");
		System.out.println("The volume of a baseball is " + precision2.format(Ball.BASEBALL.getVolume()) + "mm3");
	}

	//Test Rational class methods
	//Comments show the desired output
	public static void rationalTest () {

		System.out.println("Rational Testing:");
		Rational r1 = new Rational(Ball.BASEBALL.mass, Ball.BASKETBALL.mass);
		Rational r2 = new Rational(Ball.FOOTBALL.mass, Ball.GOLFBALL.mass);
		Rational r3 = new Rational(Ball.POOLBALL.diameter, Ball.SOFTBALL.diameter);
		Rational r4 = new Rational(Ball.TENNISBALL.diameter, Ball.VOLLEYBALL.diameter);

		//Test simplification
		//15/40 = 3/8 = 0.38
		Rational r = new Rational(15, 40);
		System.out.println(r.getInput() + " = " + r);

		//Test plus method
		//(149/624) + (450/46) = 143827/14352 = 10.02
		System.out.println(r1.getInput() + " + " + r2.getInput() + " = " + r1.plus(r2));

		//Test minus method
		//(450/46) - (60/97) = 20445/2231 = 9.16
		System.out.println(r2.getInput() + " - " + r3.getInput() + " = " + r2.minus(r3));

		//Test multiply method
		//(60/97) * (64/218) = 1920/10573 = 0.18
		System.out.println(r3.getInput() + " * " + r4.getInput() + " = " + r3.multiply(r4));

		//Test divide method
		//(64/218) / (149/624) = 19968/16241 = 1.23
		System.out.println(r4.getInput() + " / " + r1.getInput() + " = " + r4.divide(r1));
	}

	//enumTest and rationalTest are called here
	public static void main(String args[]) {

		//Test enum 
		enumTest();

		//Formatting
		System.out.println();

		//Test Rational
		rationalTest();
	}
}
