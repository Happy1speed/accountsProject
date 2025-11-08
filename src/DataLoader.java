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

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(studFolder + currentFile.getName()))) {
                    //Username is the file name
                    String grabUserName = currentFile.getName();

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
                    System.out.print("Couldn't open file.");
                }
            }
        }





        //Todo Must correct this when putting project together, for now it is assumed that teachers is the right folder.
        String teacherFolderDir = currentDir + "/teachers/";

        File teacherFolder = new File(teacherFolderDir);

        teacherFolder.mkdirs();





        //Todo Must correct this when putting project together, for now it is assumed that assignments is the right folder.
        String assignmentsFolderDir = currentDir + "/assignments/";

        File assignmentsFolder = new File(assignmentsFolderDir);

        assignmentsFolder.mkdirs();

    }
}
