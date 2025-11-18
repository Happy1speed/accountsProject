import java.util.Scanner;

public class StudentUI {

    public StudentAccount currentlyLoggedIn;

    public void viewGrade() {
        if (currentlyLoggedIn != null) {
            System.out.println("Your current grade: " + currentlyLoggedIn.getStudentGrade());
        } else {
            System.out.println("No student logged in.");
        }
    }

    public void exportGrade() {
        if (currentlyLoggedIn == null) {
            System.out.println("No student logged in.");
            return;
        }

        String exportData = "Student: " + currentlyLoggedIn + "\nGrade: " + currentlyLoggedIn.getStudentGrade();
        System.out.println("Exporting grade data...\n" + exportData);

        GlobalData.saveableList.add(currentlyLoggedIn);
    }

    public void printOptions(Scanner scnr) {
        int choice;
        do {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View Grade");
            System.out.println("2. Export Grade");
            System.out.println("3. Log Out");
            System.out.print("Enter choice: ");
            choice = scnr.nextInt();

            switch (choice) {
                case 1 -> viewGrade();
                case 2 -> exportGrade();
                case 3 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 3);
    }
}
