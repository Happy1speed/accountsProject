import java.util.Scanner;

public class LoggedInAsStudentLoop {
    public static void loop(Scanner scnr, StudentAccount user) {

        boolean exitCourseChoose = false;

        while (!exitCourseChoose) {
            System.out.println("Enter a course name or exit: ");

            String getCourse = scnr.nextLine();

            if (getCourse.equalsIgnoreCase("exit")) {
                exitCourseChoose = true;
                break;
            }

            boolean foundCourse = false;

            //Dummy course early initilization to appease IDE.
            Course loggedCourse = new Course();

            for (Course course : GlobalData.courseList) {
                if (course.getCourseName().equalsIgnoreCase(getCourse)) {
                    foundCourse = true;
                    loggedCourse = course;
                }
            }

            if (foundCourse) {

                boolean exit = false;

                while (!exit) {

                    System.out.println("Student Interface Home");
                    System.out.println("Options: grade | exit");

                    String getChoice = scnr.nextLine();

                    if (getChoice.equalsIgnoreCase("grade")) {
                        System.out.println("Your overall grade is: " + user.formatGrade());

                        for (Assignment assignment : GlobalData.assignmentList) {
                            if (assignment.getCourse().equals(loggedCourse.getCourseName())) {
                                System.out.print(assignment.getName() + ": " + user.getStudentAssignments().get(assignment.getName()));
                            }
                        }
                    }

                    else if (getChoice.equalsIgnoreCase("exit")) {
                        exit = true;
                        break;
                    }
                    else {
                        System.out.println("Invalid option; please make sure you spelled the command correctly!");
                    }
                }
            } else {
                System.out.println("Could not find course \"" + getCourse + "\"!");
                System.out.println("Available Courses:");
                for (Course printedCourse : GlobalData.courseList) {
                    if (printedCourse.getStudentRoster().contains(user)) {
                        System.out.println(printedCourse);
                    }
                }
            }
        }
    }
}
