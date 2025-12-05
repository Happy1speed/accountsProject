import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ExportGrade {
    public static void export(StudentAccount student) {

        //Absolute system file path
        String currentDir = System.getProperty("user.dir");

        //Absolute system file path + student folder
        String studentFolderDir = currentDir + "/exportedGrades/";

        File studFolder = new File(studentFolderDir);

        //Generate folder
        studFolder.mkdirs();

        String currentFileDir = studentFolderDir + student.getUsername() + ".txt";

        File studFile = new File(currentFileDir);

        if (!studFile.exists()) {
            try {
                studFile.createNewFile();
            }
            catch (IOException e) {
                System.out.println("FAILED TO CREATE FILE");
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(currentFileDir))) {

            //Hash map encoding. , = split next item. end of line means entry end.
            if (student.getStudentAssignments() != null && !student.getStudentAssignments().isEmpty()) {
                for (Map.Entry<String, Double> entry : student.getStudentAssignments().entrySet()) {
                    bufferedWriter.write(entry.getKey() + " | Grade: " + entry.getValue());
                    bufferedWriter.newLine();
                }
            }

            bufferedWriter.flush();
        }
        catch (IOException e) {
            System.out.println("WRITER EXCEPTION");
        }

        //Prints assignment list
        System.out.println(student.getUsername() + "'s assignments and corresponding grades: ");
        for(Map.Entry<String, Double> entry : student.getStudentAssignments().entrySet()) {
            System.out.println(entry.getKey() + " | Grade: " + entry.getValue());
        }
    }

}
