public abstract class BaseAccount extends Saveable {

    //Added fields since they were missing

    private String username;
    private String email;
    private String password;


    public BaseAccount(String username,
                       String email,
                       String password) {
        //Assign new fields
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //Added setters and getters because they were missing

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //todo: Might need reworking in the future

    public abstract char formatGrade(StudentAccount student);

    public abstract char formatGrade();
}

