import java.util.Scanner;

public class test {

    /*
    Example solution for example problem
     */

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int lines = scan.nextInt();
        scan.nextLine();

        for (int l = 0; l < lines; l += 2) {
            String line1 = scan.nextLine();
            String line2 = scan.nextLine();
            System.out.println(line1 + " " + line2);
        }
    }
}