public class NBody {
	public static String background_img = "images/starfield.jpg";

	/** read radius of universe in a file */
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int number_of_planets = in.readInt();
		double radius_of_universe = in.readDouble();
		return radius_of_universe;
	}

	/** read all bodies in the universe in a file */
	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);
		int number_of_planets = in.readInt();
		double radius_of_universe = in.readDouble();
		Body[] bodies = new Body[number_of_planets];
		for (int i = 0; i < number_of_planets; i++) {
			double pX = in.readDouble();
			double pY = in.readDouble();
			double vX = in.readDouble();
			double vY = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			bodies[i] = new Body(pX, pY, vX, vY, m, img);
		}
		return bodies;
	}

	public static void main (String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius_of_universe = readRadius(filename);
		Body[] bodies = readBodies(filename);

		/** draw background */
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius_of_universe, radius_of_universe);
		StdDraw.clear();

		StdDraw.picture(0, 0, background_img);
		StdDraw.show();
		/** draw all bodies */
		for (Body b: bodies) {
			b.draw();
		}
		/** create animation */
		int t=0;
		while (t<=T) {
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];
			int counter = 0;
			for (Body b: bodies) {
				xForces[counter] = b.calcNetForceExertedByX(bodies);
				yForces[counter] = b.calcNetForceExertedByY(bodies);
				counter += 1;
			}
			int counter2 = 0;
			for (Body b: bodies) {
				b.update(dt, xForces[counter2], yForces[counter2]);
				counter2 += 1;
			}

			StdDraw.enableDoubleBuffering();
			StdDraw.setScale(-radius_of_universe, radius_of_universe);
			StdDraw.clear();

			StdDraw.picture(0, 0, background_img);
			for (Body b: bodies) {
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}
		/** print the universe */
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius_of_universe);
		for (Body b: bodies) {
			StdOut.printf("%-11.4e %-11.4e %-11.4e %-11.4e %-11.4e %11s\n",
						  b.xxPos, b.yyPos, b.xxVel, b.yyVel,
						  b.mass, b.imgFileName);
		}
	}
}