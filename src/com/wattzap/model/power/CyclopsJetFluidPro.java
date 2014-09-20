package com.wattzap.model.power;

/* CycleOps JetFluidPro
 * (c) 2013 David George / Wattzap.com
 * 
 * @author David George
 * @date 11 November 2013
 * 
 * CycleOps power curve for this trainer (the Jet Fluid Pro
 * http://www.e-scape.co.uk/e-scape-blogs/jason-stratford/creating-a-virtual-power-meter/
 * 
 *  Jason Stratford (MPG
 * -0.46095 + 2.4455x + 0.2232x^2 + 0.01844x^3
 * 
 * mph to kmh = 1.60934, 2.5899752356, 4.168150745660504
 * 
 * -0.2864 + 1.5196 + 0,0862 + 0.004424
 * 
 * http://www.powercurvesensor.com/cycling-trainer-power-curves/
 * 
 * 
 */
@PowerAnnotation
public class CyclopsJetFluidPro extends Power {
	private static final double a = 0.004424;
	private static final double b = 0.0862;
	private static final double c = 1.5196;
	private static final double d = -0.2864;
	private static final Cubic cubic = new Cubic();

	public int getPower(double speed, int resistance) {
		double power = (c * speed) + (b * speed * speed)
				+ (a * speed * speed * speed);

		return (int) power;
	}

	public double getSpeed(int power, int resistance) {
		cubic.solve(a, b, c, d - power);
		return cubic.x1;
	}

	public String description() {
		return "CycleOps Jet Fluid Pro";
	}

	@Override
	public int getResitanceLevels() {
		return 1;
	}
}