
public class practice {
	public static void main(String[] args){
		int a = 0;
		int b = 100;
		int c = 7;
		int sum = a + b + c;
		System.out.println("the sum of "+ a + ", " + b + ", and " + c + " is " + sum);
		int prt = a * b * c;
		System.out.println("the product of "+ a + ", " + b + ", and " + c + " is " + prt);
		double avg = (double)(a + b + c)/3;
		System.out.println("the average of "+ a + ", " + b + ", and " + c + " is " + avg);
		double r = 7;
		double pi = 3.14159;
		double circ = 2 * pi * r;
		double d = r * 2;
		double area = pi * r * r;
		System.out.println("A circle with radius " + r + " has circumference of " + circ + ", diameter " + d + ", and area " + area);
	}

}
