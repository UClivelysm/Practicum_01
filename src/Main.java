//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String firstName = "";

        firstName = SafeInput.getNonZeroLenString(in, "Enter your first name");
        System.out.println("First name is " + firstName);
    }
}