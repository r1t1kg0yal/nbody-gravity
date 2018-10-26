
public class Body {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */

	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {

		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */

	public Body(Body b) {

		this(b.getX(), b.getY(), b.getXVel(), b.getYVel(), b.getMass(), b.getName());

	}

	/**
	* Return the X-position
	* @return x-position;
	*/

	public double getX() {

		return myXPos;

	}

	/**
	* Return the Y-position
	* @return y-position;
	*/

	public double getY() {

		return myYPos;

	}

	/**
	* Return the velocity in the X-direction
	* @return velocity x component;
	*/

	public double getXVel() {

		return myXVel;

	}

	/**
	* Return the velocity in the Y-direction
	* @return velocity y component;
	*/

	public double getYVel() {

		return myYVel;

	}

	/**
	* Return the mass of body
	* @return mass of the body;
	*/

	public double getMass() {

		return myMass;

	}

	/**
	* Return the name of body
	* @return filename of the body;
	*/

	public String getName() {

		return myFileName;

	}

	/**
	* Return the distance between this body and another
	* @param b the other body to which distance is calculated
	* @return distance between this body and break;
	*/

	public double calcDistance(Body b){

		double xDistance = myXPos - b.getX();
		double yDistance = myYPos - b.getY();

		return Math.sqrt(Math.pow(xDistance, 2)+Math.pow(yDistance, 2));

	}

	/**
	* Return the total force exerted by another body on this body
	* @param p the other body with which the force is calculated
	* @return force exerted by another body;
	*/

	public double calcForceExertedBy(Body p){

		return 6.67 * Math.pow(10, -11) * p.getMass() * myMass / calcDistance(p) / calcDistance(p);

	}

	/**
	* Return the x-component of the total force exerted by another body on this body
	* @param p the other body with which the force is calculated
	* @return x-component of force exerted by another body;
	*/

	public double calcForceExertedByX(Body p){

		return calcForceExertedBy(p) * (myXPos-p.getX()) / calcDistance(p) * (-1);

	}

	/**
	* Return the y-component of the total force exerted by another body on this body
	* @param p the other body with which the force is calculated
	* @return y-component of force exerted by another body;
	*/

	public double calcForceExertedByY(Body p){

		return calcForceExertedBy(p) * (myYPos-p.getY()) / calcDistance(p) * (-1);

	}

	/**
	* Return the sum of the total force exerted by another body on this body in the x direction
	* @param bodies the list of other bodies acting on this body
	* @return net force exerted by other bodies on this body in x direction;
	*/

	public double calcNetForceExertedByX(Body [] bodies){

		double xNetForce = 0;

		for(int i = 0; i<bodies.length; i++){
			if(!bodies[i].equals(this))
				xNetForce += calcForceExertedByX(bodies[i]);
		}

		return xNetForce;

	}

	/**
	* Return the sum of the total force exerted by another body on this body in the y direction
	* @param bodies the list of other bodies acting on this body
	* @return net force exerted by other bodies on this body in the y direction;
	*/

	public double calcNetForceExertedByY(Body [] bodies){

		double yNetForce = 0;

		for(int i = 0; i<bodies.length; i++){
			if(!bodies[i].equals(this))
				yNetForce += calcForceExertedByY(bodies[i]);
		}

		return yNetForce;

	}

	/**
	* Update the coordinates and velocity of this body by calculating
	* the acceleration of the body and accordingly the distance it travels in
	* a small unit of time
	* @param deltaT a small unit of time
	* @param xforce the x-component of force acting on this Body
	* @param yforce the y-component of force acting on this Body
	*/

	public void update(double deltaT, double xforce, double yforce){

		double ax = xforce / myMass;
		double ay = yforce / myMass;

		double nvx = myXVel + deltaT * ax;
		double nvy = myYVel + deltaT * ay;

		double nx = myXPos + deltaT * nvx;
		double ny = myYPos + deltaT * nvy;

		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;

	}

	/**
	* display the bodies on the space image
	* in the final program
	*/

	public void draw(){
		StdDraw.picture(myXPos, myYPos, "..//images//" + myFileName);
	}




}
