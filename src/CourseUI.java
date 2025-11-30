import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseUI {

    public static int intErrorCatch(Scanner scnr) {
        boolean needInput = true;
        int n = 0;
        while (needInput) {
            try {
                n = scnr.nextInt();
                needInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Not a valid number, please try again...");
                scnr.next();
            }
        }
        return n;
    }

    public static void createCourse(Scanner scnr, TeacherAccount teacherAccount) {
        boolean finalized = false;
        System.out.println("Changes to the course may be made at the end if there are any entry errors...");

        System.out.println("Enter the course name: ");
        String courseName = scnr.nextLine();

        System.out.println("Enter the meeting time(s): ");
        String meetingTimes = scnr.nextLine();

        System.out.println("Enter the room number: ");
        String roomNumber = scnr.nextLine();

        System.out.println("Enter the instructor information: ");
        String instructorInfo = scnr.nextLine();

        System.out.println("Enter the course code: ");
        int courseCode = intErrorCatch(scnr);

        System.out.println("Enter the number of eligible credits: ");
        int credits = intErrorCatch(scnr);

        Course myCourse = new Course(courseName, meetingTimes, roomNumber, instructorInfo, courseCode, credits);

        System.out.println("Course successfully created! Here's the course information: ");
        myCourse.displayInfo();

        while (finalized == false) {
            System.out.println("Is there anything else that you would like to change?");
            System.out.println("Yes | No");
            String input;
            input = scnr.nextLine().toLowerCase();
            if (input.equals("yes")) {
                System.out.println("Which would you like to change?");
                System.out.println("Name | Time | Number | Info | Code | Credit | Exit");
                boolean validInput = false;
                while (validInput == false) {
                    input = scnr.nextLine().toLowerCase();
                    if (input.equals("name")) {
                        System.out.println("Enter a new course name: ");
                        myCourse.setCourseName(scnr.nextLine());
                        validInput = true;
                    }
                    else if (input.equals("time")) {
                        System.out.println("Enter a new meeting time(s): ");
                        myCourse.setMeetingTimes(scnr.nextLine());
                        validInput = true;
                    }
                    else if (input.equals("number")) {
                        System.out.println("Enter a new room number: ");
                        myCourse.setRoomNumber(scnr.nextLine());
                        validInput = true;
                    }
                    else if (input.equals("info")) {
                        System.out.println("Enter new instructor information: ");
                        myCourse.setInstructorInfo(scnr.nextLine());
                        validInput = true;
                    }
                    else if (input.equals("code")) {
                        System.out.println("Enter a new course code: ");
                        myCourse.setCourseCode(intErrorCatch(scnr));
                        validInput = true;
                    }
                    else if (input.equals("credit")) {
                        System.out.println("Enter a new number of credits: ");
                        myCourse.setCredits(intErrorCatch(scnr));
                        validInput = true;
                    }
                    else if (input.equals("exit")) {
                        validInput = true;
                        continue;
                    }
                    else {
                        System.out.println("Please respond with \"name\", \"time\", \"number\", \"info\", \"code\", \"credit\", or \"exit\"");
                    }
                }
            }
            else if (input.equals("no")) {
                finalized = true;
            }
            else {
                System.out.println("Please respond with \"yes\" or \"no\"");
            }
        }
        teacherAccount.addCourse(myCourse);
        GlobalData.saveableList.add(myCourse);
        myCourse.save();
    }
}

