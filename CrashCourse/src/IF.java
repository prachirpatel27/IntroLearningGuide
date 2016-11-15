import java.util.Scanner;
public class IF {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a number");
		int x = scan.nextInt();
		System.out.println("got here");
		if (x > 500){
			while(true){
				System.out.println("Buenos dias");
			}
		}
		else if (x < 250) {
			System.out.println("got here 2");

			while(true){
				System.out.println("Buenas tardes");
				x++;
			}
		}

	}

}
