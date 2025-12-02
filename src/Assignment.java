
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Assignment extends Saveable {

    // Fields
    private double maxPoints;
    private String name;
    private String date;
    private String course;

    // Constructor
    public Assignment(double maxPoints,
                      String name,
                      String date, String course) {
        this.maxPoints = maxPoints;
        this.name = name;
        this.date = date;
        this.course = course;
    }

    // Getters
    public double getMaxPoints() {
        return this.maxPoints;
    }
    public String getName() {
        return this.name;
    }
    public String getDate() {
        return this.date;
    }
    public String getCourse() {
        return this.course;
    }

    // Setters
    public void setMaxPoints(double maxPoints) {
        this.maxPoints = maxPoints;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // @override save
    @Override
    public void save() {

        //Implemented folder for assignments

        //Absolute system file path
        String currentDir = System.getProperty("user.dir");

        //Absolute system file path + student folder
        String assignmentFolderDir = currentDir + "/assignments/";

        File assignFolder = new File(assignmentFolderDir);

        //Generate folder
        assignFolder.mkdirs();

        String currentFileDir = assignmentFolderDir + this.name + ".txt";

        File assignFile = new File(currentFileDir);

        if (!assignFile.exists()) {
            try {
                assignFile.createNewFile();
            }
            catch (IOException e) {
                System.out.println("FAILED TO CREATE FILE");
            }
        }


        String pointString = this.maxPoints + "";

        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(currentFileDir))) {
            buffWrite.write(this.name);
            buffWrite.newLine();
            buffWrite.write(pointString);
            buffWrite.newLine();
            buffWrite.write(this.date);
            buffWrite.flush();
            buffWrite.newLine();
            buffWrite.write(this.course);
            buffWrite.flush();
        }
        catch(IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
        /*
            Writes the following text to a file in order:
            Assignment name
            Maximum points
            Assignment date
            ---
            fileName is name(in lower case).txt
        */
    }
}

