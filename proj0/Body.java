import java.lang.Math;

public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	static final double G = 6.67e-11;  //Nm2/kg2

	/** constructor 1: create a new instance of body */
	public Body (double xP, double yP, double xV,
					double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** constructor 2: create a new instance of body by
	copying variables from existing instance of body */
	public Body (Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	/** Calculate distance between this body (doing calculation)
	and the provided body */
	public double calcDistance(Body b) {
		double dx = b.xxPos - this.xxPos;
		double dy = b.yyPos - this.yyPos;
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}

	/** calculate the force exerted on this body by the given body*/
	public double calcForceExertedBy(Body b) {
		double distance = this.calcDistance(b);
		double force = (G * this.mass * b.mass) / (distance * distance);
		return force;
	}

	/** calculate the force exerted in the x direction */
	public double calcForceExertedByX(Body b) {
		double r = this.calcDistance(b);
		double f = this.calcForceExertedBy(b);
		double dx = b.xxPos - this.xxPos;
		double fx = f * dx / r;
		return fx;
	}

	/** calculate the force exerted in the y direction */
	public double calcForceExertedByY(Body b) {
		double r = this.calcDistance(b);
		double f = this.calcForceExertedBy(b);
		double dy = b.yyPos - this.yyPos;
		double fy = f * dy / r;
		return fy;
	}

	/** calculate the next force exerted on this body by all bodys
	in x direction */
	public double calcNetForceExertedByX (Body[] allBodys) {
		double netForceX = 0;
		for (Body b: allBodys) {
			if (!this.equals(b)) {
				netForceX += this.calcForceExertedByX(b);
			}
		}
		return netForceX;
	}

	/** calculate the next force exerted on this body by all bodys
	in y direction */
	public double calcNetForceExertedByY (Body[] allBodys) {
		double netForceY = 0;
		for (Body b: allBodys) {
			if (!this.equals(b)) {
				netForceY += this.calcForceExertedByY(b);
			}
		}
		return netForceY;
	}

	/** determine how much forces exerted on this body
	will cause it to accelerate*/
	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel += dt * aX;
		this.yyVel += dt * aY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	/** draw one body */
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos,
							"images/"+this.imgFileName);
		StdDraw.show();
	}
} 