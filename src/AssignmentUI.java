import java.util.Scanner;

public class AssignmentUI {

    public static void writeAssignment(Scanner scnr, String course) {

        System.out.print("Enter assignment name: ");
        String name = scnr.nextLine();

        System.out.print("Enter max points: ");
        double maxPoints = scnr.nextDouble();
        scnr.nextLine(); 
        
        System.out.print("Enter due date (MM/DD/YYYY): ");
        String date = scnr.nextLine();

        Assignment newAssignment = new Assignment(maxPoints, name, date, course);
        newAssignment.save();


        GlobalData.assignmentList.add(newAssignment);
        GlobalData.saveableList.add(newAssignment);

        System.out.println("Assignment '" + name + "' successfully created!");
    }
}
