package simulator;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class GamePiece {
	protected PVector position;

	protected static PApplet p;
	protected PImage img;

	protected float width;
	protected float height;

	public double getX() {
		return position.x;
	}

	public double getY() {
		return position.y;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public boolean isHitting(Thing t) {
//		if (t.id == id)
//			return false; // we can't hit ourselves
//
//		if (isHittingPoint(t.position.x, t.position.y))
//			return true;
//		if (isHittingPoint(t.position.x + t.width, t.position.y))
//			return true;
//		if (isHittingPoint(t.position.x + t.width, t.position.y + t.height))
//			return true;
//		if (isHittingPoint(t.position.x, t.position.y + t.height))
//			return true;
//
//		if (t.isHittingPoint(position.x, position.y))
//			return true;
//		if (t.isHittingPoint(position.x + width, position.y))
//			return true;
//		if (t.isHittingPoint(position.x + width, position.y + height))
//			return true;
//		if (t.isHittingPoint(position.x, position.y + height))
//			return true;

		return false;
	}

	public boolean isHittingPoint(float x, float y) {
		return (x > position.x && x < position.x + width) && (y > position.y && y < position.y + height);
	}

	public void setImage(PImage i) {
		this.img = i;
		this.width = img.width;
		this.height = img.height;
	}

	public void draw() {
		p.pushMatrix();
		p.translate(position.x + width / 2, position.y + height / 2);
//		p.rotate((float) angle);
		p.translate(-position.x - width / 2, -position.y - height / 2);

		if (img != null) {
			p.image(img, position.x, position.y);
		} else {
//			p.fill(color);
//			p.stroke(color);
			p.rect(position.x, position.y, width, height);
		}

		p.popMatrix();
	}
}