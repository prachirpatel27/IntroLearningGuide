import processing.core.PApplet;

public class Turtle {
        private int x, y;
        private double direction;	// stored in degrees
        private PApplet p;
        private int color;
        private int weight = 1;
        private boolean pendown = true;
        
        public Turtle(int x, int y, double direction, PApplet p) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.p = p;
            this.color = p.color(0, 180, 0);
            p.fill(color);
        }
        
        public Turtle(PApplet p) {
        	this (p.width/2, p.height/2, 0, p);
        }

        public int getX() {
			return x;
		}

        public void setThickness(int t) {
        	this.weight = t;
        }
        
        public int getThickness() {
        	return this.weight;
        }
        
		public int getY() {
			return y;
		}

		public double getDirection() {
			return direction;
		}

		public int getColor() {
			return color;
		}

		public void forward(double distance) {
            double xmag, ymag, newx, newy;
            xmag = distance*Math.cos(Math.toRadians(direction));
            ymag = distance*Math.sin(Math.toRadians(direction));
            newx = Math.round(x + xmag); 
            newy = Math.round(y - ymag);
            if (pendown) {
            	p.stroke(this.color);
            	p.strokeWeight(weight);
            	p.line(x, y, (int)newx, (int)newy);
            }
            x = (int) newx;
            y = (int) newy;
        }

        public void turnRight(double angle) {
            direction -= angle;
            direction = (direction+360)%360.0;
        }

        public void turnLeft(double angle) {
        	direction += angle;
            direction = (direction+360)%360.0;
        }
        
        public void penup() {
        	this.pendown = false;
        }
        
        public void pendown() {
        	this.pendown = true;
        }
        
        public void setX(double x) {
        	this.x = (int)x;
        }
        
        public void setY(double y) {
        	this.y = (int)y;
        }
        
        public void goToPoint(int x, int y) {
        	this.x = x;
        	this.y = y;
        }
        
        public void setDirection(float direction) {
        	this.direction = direction;
        }
        
        public void setColor(int c) {
        	this.color = c;
        }
    }