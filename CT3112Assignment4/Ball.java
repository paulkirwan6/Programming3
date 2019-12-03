/*
 *	Paul Kirwan
 *	17321313
 */

public enum Ball {

	BASEBALL (149, 73),
	BASKETBALL (624, 239),
	FOOTBALL (450 , 216),
	GOLFBALL (46, 43),
	POOLBALL (156, 60),
	SOFTBALL (180, 97),
	TENNISBALL (59, 64),
	VOLLEYBALL (260, 218); 

	final int mass;		//grams
	final int diameter;	//millimetres

	Ball (int mass, int diameter) {
		this.mass = mass;
		this.diameter = diameter;
	}

	//getters
	public int mass() {
		return mass;
	}

	public int diameter() {
		return diameter;
	}

	//calculate the circumference for given ball
	public double getCircumference() {
		return 3.14*diameter;
	}

	//calculate the volume for the given ball 
	public double getVolume() {
		return (4./3) * Math.PI * Math.pow(diameter/2.,3);
	}
}
