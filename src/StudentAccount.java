import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentAccount extends BaseAccount {

    //Overall grade, must be updated to reflect student assignments when an assignment is changed.
    private double studentGrade;


    private HashMap<String, Double> studentAssignments;


    public StudentAccount(String userName, String email, String password, double studentGrade) {
        super(userName, email, password);

        this.studentGrade = studentGrade;
    }

    public double getStudentGrade() {
        return this.studentGrade;
    }

    public void setStudentGrade(double newGrade) {
        this.studentGrade = newGrade;
    }

    public HashMap<String, Double> getStudentAssignments() {
        return studentAssignments;
    }

    public void setKeyValueGrade(String key, double setGrade) {
        if (this.studentAssignments.containsKey(key)) {
            this.studentAssignments.put(key, setGrade);
        }
    }

    @Override
    public char formatGrade() {
        if (this.studentGrade < 60) {
            return 'f';
        }
        else if (this.studentGrade < 70) {
            return 'd';
        }
        else if (this.studentGrade < 80) {
            return 'c';
        }
        else if (this.studentGrade < 90) {
            return 'b';
        }
        else {
            return 'a';
        }
    }

    @Override
    public void save() {
        //Absolute system file path
        String currentDir = System.getProperty("user.dir");

        //Absolute system file path + student folder
        String studentFolderDir = currentDir + "/students/";

        File studFolder = new File(studentFolderDir);

        //Generate folder
        studFolder.mkdirs();

        String currentFileDir = studentFolderDir + this.getStudentName() + ".txt";

        File studFile = new File(currentFileDir);

        if (!studFile.exists()) {
            try {
                studFile.createNewFile();
            }
            catch (IOException e) {
                System.out.println("FAILED TO CREATE FILE");
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter( currentFileDir))) {
            bufferedWriter.write(this.getEmail());
            bufferedWriter.newLine();
            bufferedWriter.write(this.getPassword());
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(this.getStudentGrade()));
            bufferedWriter.newLine();


            //Hash map encoding. , = split next item. end of line means entry end.
            for (Map.Entry<String, Double> entry : this.getStudentAssignments().entrySet()) {
                bufferedWriter.write(entry.getKey() + "," + entry.getValue());
                bufferedWriter.newLine();
            }
            // token for end of hash map.
            bufferedWriter.write("***");

            bufferedWriter.flush();
        }
        catch (IOException e) {
            System.out.println("WRITER EXCEPTION");
        }
    }

}
