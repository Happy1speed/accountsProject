import java.util.ArrayList;


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

    @Override
    public void save() {

    }
}
