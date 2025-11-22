import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Still need to extend BaseAccount
public class TeacherAccount extends BaseAccount {
    //I highkey doubt this is right but trying to make a teacher account that can own multiple courses
private ArrayList<Course> ownedCourses;


    // Default constructor, I want to do the superclass thing but need clarificiation
    public TeacherAccount(String username,
                          String email,
                          String password) {
        super(username, email, password);

        ownedCourses = new ArrayList<>();

        //todo Remove when the time comes to add multi-course support
        addCourse(Main.course);
    }


    // Method to add a course
    public void addCourse(Course course) {
        ownedCourses.add(course);
    }


    // Display courses
    public ArrayList<Course> getCoursesTaught() {
        return ownedCourses;
    }


    public void formatGrade() {
        for (Course course : this.getCoursesTaught()) {
            for (StudentAccount studentAccount : course.getStudentRoster()) {
                System.out.print(studentAccount.getUsername());
                System.out.print("s overall grade: ");
                System.out.println(studentAccount.formatGrade());
            }
        }
    }

    //Please check this too
    @Override
    public void save() {
        //Absolute system file path
        String currentDir = System.getProperty("user.dir");


        //Absolute system file path + teacher folder
        String teachFolderDir = currentDir + "/teachers/";


        File teachFolder = new File(teachFolderDir);


        //Generate folder
        teachFolder.mkdirs();


        String currentFileDir = teachFolderDir + this.getUsername() + ".txt";


        File teachFile = new File(currentFileDir);


        if (!teachFile.exists()) {
            try {
                teachFile.createNewFile();
            }
            catch (IOException e) {
                System.out.println("FAILED TO CREATE FILE");
            }
        }


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(currentFileDir))) {
            bufferedWriter.write(this.getEmail());
            bufferedWriter.newLine();
            bufferedWriter.write(this.getPassword());
            bufferedWriter.newLine();

            for (Course thisCourse : this.getCoursesTaught()){
                bufferedWriter.write(thisCourse.getCourseName());
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
        }
        catch (IOException e) {
            System.out.println("WRITER EXCEPTION");
        }
    }

}
