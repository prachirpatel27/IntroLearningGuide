package simulator;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Thing {
	protected PVector position, velocity, acceleration;

	protected double angle;
	protected float rotationSpeed;

	protected static PApplet p;
	protected PImage img;
	protected int color;

	protected static int next_id = 0;
	protected int id;

	protected float width;
	protected float height;

	/**
	 * A generic game object which can move, accelerate, be displayed, 
	 * and be part of collision detection.
	 * @param papplet a PApplet graphics window the object can draw itself on
	 * @param x object's x position
	 * @param y object's y position
	 * @param xspeed object's initial x speed
	 * @param yspeed object's initial y speed
	 * @param xacceleration object's initial x acceleration
	 * @param yacceleration object's initial y acceleration
	 * @param w object's width
	 * @param h object's height
	 */
	public Thing(PApplet papplet, double x, double y, double xspeed, double yspeed,
			double xacceleration, double yacceleration, double w, double h) {
		this.p = papplet;
		position = new PVector((float) x, (float) y);
		velocity = new PVector((float) xspeed, (float) yspeed);
		acceleration = new PVector((float) xacceleration, (float) yacceleration);

		angle = 0;
		img = null;
		id = next_id;
		next_id++;
		
		width = (float)w;
		height = (float)h;
	}
	
	public Thing(PApplet p, double x, double y, double xspeed, double yspeed,
			double xacceleration, double yacceleration) {
		this(p, x, y, xspeed, yspeed, xacceleration, yacceleration, 50.0, 50.0);
	}
	
	public void setSize(double width, double height) {
		this.width = (float) width;
		this.height = (float) height;
		
		if (this.img != null) {
			img.resize((int)width, (int)height);
		}
	}

	public double getSpeed() {
		return velocity.mag();
	}

	public double getX() {
		return position.x;
	}

	public double getY() {
		return position.y;
	}
	
	public double getXNextStep() {
		return position.x + (acceleration.x + velocity.x);
	}
	
	public double getYNextStep() {
		return position.y + (acceleration.y + velocity.y);
	}
	

	public double getXSpeed() {
		return velocity.x;
	}

	public double getYSpeed() {
		return velocity.y;
	}
	
	public void setXSpeed(double nx) {
		velocity.x = (float)nx;
	}

	public void setYSpeed(double ny) {
		velocity.y = (float)ny;
	}
	
	public void setXPosition(double nx) {
		position.x = (float)nx;
	}

	public void setYPosition(double ny) {
		position.y = (float)ny;
	}
	
	public void setXAcceleration(double nx) {
		acceleration.x = (float)nx;
	}

	public void setYAcceleration(double ny) {
		acceleration.y = (float)ny;
	}
	
	public void reverseXSpeed() {
		velocity.x = -velocity.x;
	}

	public void reverseYSpeed() {
		velocity.y = -velocity.y;
	}

	public double getXAcceleration() {
		return acceleration.x;
	}

	public double getYAcceleration() {
		return acceleration.y;
	}

	public double getAngle() {
		return p.degrees((float) angle);
	}
	
	public double getRotationSpeed() {
		return this.rotationSpeed;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getWidth() {
		return width;
	}

	public void setRandomPosition(int minx, int maxx, int miny, int maxy) {
		position.x = (float) rand(minx, maxx);
		position.y = (float) rand(miny, maxy);
	}
	
	public void setRandomDirection() {
		velocity.rotate((float)(Math.random()*2*Math.PI));
	}

	public void setRandomSpeed(int min, int max) {
		velocity.setMag((float) rand(min, max));
	}

	public void setRandomAcceleration(int min, int max) {
		acceleration.setMag((float) rand(min, max));
	}

	public void accelerate(double amt) {
		velocity.mult((float)amt);
	}
	
	public void accelerateInDirection(double amt, double direction) {
		PVector n = PVector.fromAngle(p.radians((float)direction));
		n.setMag((float) amt);

		velocity.add(n);
	}

	public void moveRight(double dist) {
		position.x += dist;
	}

	public void moveUp(double dist) {
		position.y -= dist;
	}
	
	public void moveDown(double dist) {
		position.y += dist;
	}

	public void moveLeft(double dist) {
		position.x -= dist;
	}
	
	public void addVelocity(double direction, double d) {
		PVector dv = new PVector(1,0);
		dv.setMag((float)d);
		dv.rotate((float)direction);
		this.velocity.add(dv);
	}
	
	public void setVelocity(double direction, double speed) {
		PVector dv = new PVector(1,0);
		dv.setMag((float)speed);
		dv.rotate((float)direction);
		velocity = dv;
	}
	
	public void addVelocity(PVector force) {
		this.velocity.add(force);
	}
	
	public void setVelocity(PVector vel) {
		this.velocity = vel;
	}

	public double getDirectionToward(int ox, int oy) {
		PVector t = new PVector(ox - position.x, oy - position.y);
		t.normalize();
		return t.heading();
	}
	
	public double getDistanceTo(int ox, int oy) {
		PVector t = new PVector(ox - position.x, oy - position.y);
		return t.mag();
	}
	
	public double getDistanceTo(int ox, int oy, int nx, int ny) {
		PVector t = new PVector(ox - nx, oy - ny);
		return t.mag();
	}
	
	public double getDirectionToward(int startx, int endx, int starty, int endy) {
		PVector t = new PVector(startx - position.x, starty - position.y);
		t.normalize();
		return t.heading();
	}
	
	public PVector getDirectionVectorToward(int ox, int oy) {
		PVector t = new PVector(ox - position.x, oy - position.y);
		t.normalize();
		return t;
	}
	
	public PVector getDirectionVectorToward(int startx, int endx, int starty, int endy) {
		PVector t = new PVector(startx - position.x, starty - position.y);
		t.normalize();
		return t;
	}

	public boolean isHitting(Thing t) {
		if (t.id == id)
			return false; // we can't hit ourselves

		if (isHittingPoint(t.position.x, t.position.y))
			return true;
		if (isHittingPoint(t.position.x + t.width, t.position.y))
			return true;
		if (isHittingPoint(t.position.x + t.width, t.position.y + t.height))
			return true;
		if (isHittingPoint(t.position.x, t.position.y + t.height))
			return true;

		if (t.isHittingPoint(position.x, position.y))
			return true;
		if (t.isHittingPoint(position.x + width, position.y))
			return true;
		if (t.isHittingPoint(position.x + width, position.y + height))
			return true;
		if (t.isHittingPoint(position.x, position.y + height))
			return true;

		return false;
	}

	/**
	 * Return true if object will hit another in the next time step.
	 * 
	 * @param other
	 *            the other object to check for a collision with.
	 * @return true if this object will hit the other object in the next time
	 *         step. False otherwise.
	 */
	public boolean willHitNextStep(Thing other) {
		velocity.add(acceleration);
		position.add(velocity);
		boolean willHit = isHitting(other);
		position.sub(velocity);
		velocity.sub(acceleration);

		return willHit;
	}

	public boolean isHittingPoint(float x, float y) {
		return (x > position.x && x < position.x + width)
				&& (y > position.y && y < position.y + height);
	}

	public void rotate(double i) {
		angle += p.radians((float) i);
	}

	public void setImage(PImage i) {
		this.img = i;
		this.width = img.width;
		this.height = img.height;
	}

	public void draw() {
		p.pushMatrix();
		p.translate(position.x + width/2, position.y + height/2);
		p.rotate((float) angle);
		p.translate(-position.x - width/2, -position.y - height/2);

		if (img != null) {
			p.image(img, position.x, position.y);
		} else {
			p.fill(color);
			p.stroke(color);
			p.rect(position.x, position.y, width, height);
		}

		p.popMatrix();
	}

	// Return random integer between a and b inclusive
	public double rand(int a, int b) {
		return a + Math.random()*(b-a+1);
	}
	
	public void setColor(int r, int g, int b) {
		color = p.color(r, g, b);
	}

	public void setColor(int c) {
		color = c;
	}

	public void move() {
		velocity.add(acceleration);
		position.add(velocity);
		angle += rotationSpeed;
	}

	public float getLeftSideX() {
		return position.x;
	}

	public float getRightSideX() {
		return position.x + width;
	}

	public float getTopSideY() {
		return position.y;
	}

	public float getBottomSideY() {
		return position.y + height;
	}

	public boolean isOffScreen() {
		// is it off the right?
		if (getLeftSideX() > p.width)
			return true;

		// is it off the left?
		if (getRightSideX() < 0)
			return true;

		// is it off the top?
		if (getBottomSideY() < 0)
			return true;

		// is it off the bottom
		if (getTopSideY() > p.height)
			return true;

		return false;
	}

	public void setBottomYValue(double y) {
		position.y = (float)(y - height);
	}
	
	public void setTopYValue(float y) {
		position.y = y;
	}

	public void setLeftXValue(float x) {
		position.x = x;
	}
	
	public void setRightXValue(float x) {
		position.x = x - width;
	}
	
	public double getMovementDirection() {
		double d = p.degrees((float)(-velocity.heading()));
		if (d >= 0) return d;
		return 360 + d;
	}

	public boolean isMovingDownish() {
		double direction = getMovementDirection();
		return ( direction > 210 && direction < 330);
	}
	
	public boolean isMovingUpish() {
		double direction = getMovementDirection();
		return (direction > 30 && direction < 150);
	}

	public void setRotationSpeed(float rot) {
		rotationSpeed = rot;
	}

	public int getCenterX() {
		return (int)(position.x + width/2.0);
	}

	public int getCenterY() {
		return (int)(position.y + height/2.0);
	}
}
