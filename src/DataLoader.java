import java.io.*;

public class DataLoader {


    public static void load() {

        String currentDir = System.getProperty("user.dir");

        String studentFolderDir = currentDir + "/students/";

        File studFolder = new File(studentFolderDir);

        studFolder.mkdirs();

        File[] studentFiles = studFolder.listFiles();


        if (studentFiles != null) {
            //If files are there to be read:

            for (File currentFile : studentFiles) {

                //Iterate through available files:

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(studFolder + "\\" + currentFile.getName()))) {
                    //Username is the file name
                    String[] removeFileExtension = currentFile.getName().split("\\.");
                    String grabUserName = removeFileExtension[0];

                    //First 3 items are in this order:
                    String grabEmail = bufferedReader.readLine();
                    String grabPassword = bufferedReader.readLine();
                    double grabGrade = Double.parseDouble(bufferedReader.readLine());

                    //Early object instance
                    StudentAccount studentAccount = new StudentAccount(grabUserName, grabEmail, grabPassword, grabGrade);

                    //Read and construct assignments HashMap from file.
                    for (String line : bufferedReader.lines().toList()) {

                        //Split based on , split indicator.
                        String[] parts = line.split(",");

                        //Add assignment score to the student account. [0] is the first half, [1] is the other half.
                        studentAccount.setKeyValueGrade(parts[0], Double.parseDouble(parts[1]));
                    }

                    //add this student... NOW
                    GlobalData.studentList.add(studentAccount);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    //System.out.print("Couldn't open file.");
                }
            }
        }





        String teacherFolderDir = currentDir + "/teachers/";

        File teachFolder = new File(teacherFolderDir);

        teachFolder.mkdirs();

        File[] teacherFiles = teachFolder.listFiles();


        if (teacherFiles != null) {
            //If files are there to be read:

            for (File currentFile : teacherFiles) {

                //Iterate through available files:

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(teachFolder + "\\" + currentFile.getName()))) {
                    //Username is the file name
                    String[] removeFileExtension = currentFile.getName().split("\\.");
                    String grabUserName = removeFileExtension[0];

                    //First 2 items are in this order:
                    String grabEmail = bufferedReader.readLine();
                    String grabPassword = bufferedReader.readLine();

                    //Early object instance
                    TeacherAccount teacherAccount = new TeacherAccount(grabUserName, grabEmail, grabPassword);

                    //should be uncommented later for making dynamic amounts of courses.
//                    for (String line : bufferedReader.lines().toList()) {
//
//                        //Split based on , split indicator.
//                        String[] parts = line.split(",");
//
//
//                        teacherAccount.addCourse();
//                    }


                    GlobalData.teacherList.add(teacherAccount);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }





        String assignmentsFolderDir = currentDir + "/assignments/";

        File assignmentsFolder = new File(assignmentsFolderDir);

        assignmentsFolder.mkdirs();

        File[] assignmentFiles = assignmentsFolder.listFiles();


        if (assignmentFiles != null) {
            //If files are there to be read:

            for (File currentFile : assignmentFiles) {

                //Iterate through available files:

                System.out.println(assignmentsFolder + "/" + currentFile.getName());

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(assignmentsFolder + "\\" + currentFile.getName()))) {

                    //First 3 items are in this order:
                    String grabAssignmentName = bufferedReader.readLine();

                    double grabMaxGrade = Double.parseDouble(bufferedReader.readLine());

                    String grabDate = bufferedReader.readLine();

                    //Early object instance
                    Assignment assignment = new Assignment(grabMaxGrade, grabAssignmentName, grabDate);

                    GlobalData.assignmentList.add(assignment);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }




        String currentCourseDir = System.getProperty("user.dir");

        String courseFolderDir = currentCourseDir + "/courses/";

        File courFolder = new File(courseFolderDir);

        courFolder.mkdirs();

        File[] courseFiles = courFolder.listFiles();


        if (courseFiles != null) {
            //If files are there to be read:

            for (File currentFile : courseFiles) {

                //Iterate through available files:

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(courFolder + "\\" + currentFile.getName()))) {
                    //Username is the file name
                    String[] removeFileExtension = currentFile.getName().split("\\.");
                    String grabCourseName = removeFileExtension[0];

                    //First 3 items are in this order:
                    String grabEmail = bufferedReader.readLine();
                    String grabInstructorInfo = bufferedReader.readLine();
                    String grabRoomNumber = bufferedReader.readLine();
                    int grabCourseCode = Integer.getInteger(bufferedReader.readLine());
                    int grabCredits = Integer.getInteger(bufferedReader.readLine());

                    //Early object instance
                    Course course = new Course(grabCourseName, grabEmail, grabInstructorInfo, grabRoomNumber, grabCourseCode, grabCredits);

                    //Read and construct assignments HashMap from file.
                    for (String line : bufferedReader.lines().toList()) {
                        for (StudentAccount studentAccount : GlobalData.studentList) {
                            if (studentAccount.getUsername().equals(line)) {
                                course.addStudentToRoster(studentAccount);
                            }
                        }

                    }

                    GlobalData.courseList.add(course);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    //System.out.print("Couldn't open file.");
                }
            }
        }





        //Ensure account list has both lists worth of data.
        GlobalData.accountList.addAll(GlobalData.studentList);
        GlobalData.accountList.addAll(GlobalData.teacherList);


        //test to see if they are loaded correctly:

//        for (StudentAccount studentAccount :  GlobalData.studentList) {
//            System.out.println(studentAccount.getUsername());
//            System.out.println(studentAccount.getEmail());
//            System.out.println(studentAccount.getPassword());
//            for (Map.Entry<String, Double> entry : studentAccount.getStudentAssignments().entrySet()) {
//                System.out.print(entry.getKey());
//                System.out.print(" : ");
//                System.out.print(entry.getValue());
//                System.out.println();
//            }
//        }
//
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//        for (Assignment assignment :  GlobalData.assignmentList) {
//            System.out.println(assignment.getName());
//            System.out.println(assignment.getDate());
//            System.out.println(assignment.getMaxPoints());
//        }

    }
}
