import java.util.Scanner;

public class LoggedInAsStudentLoop {
    public static void loop(Scanner scnr, StudentAccount user) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Student Interface Home");
            System.out.println("Options: grade | exit");

            String getChoice = scnr.nextLine();

            if (getChoice.equalsIgnoreCase("grade")) {
                System.out.println("Your grade is: " + user.formatGrade());
            }
            else if (getChoice.equalsIgnoreCase("exit")) {
                exit = true;
                break;
            }
            else {
                System.out.println("Invalid option; please make sure you spelled the command correctly!");
            }
        }
    }
}
