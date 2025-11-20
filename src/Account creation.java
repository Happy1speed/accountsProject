public class AccountCreation extends Saveable() {
    
    public static void createAccount(String userName, String email, String password, boolean isTeacher) {
        if(isTeacher == true) {
            TeacherAccount teacher = new TeacherAccount(String userName, String email, String password);
            teacher.save();
            GlobalData.teacherList.add(teacher);
        }
        else {
            StudentAccount student = new StudentAccount(String userName, String email, String password);
            student.save();
            GlobalData.studentList.add(student);
        }
        
    }
}
