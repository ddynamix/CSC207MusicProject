package usecase;

public abstract class UserSignupInputData {
    private final String username;
    private final String password;
    private final String repeatPass;
    private final String email;

    public UserSignupInputData(String username, String password, String repeatPass, String email) {
        this.username = username;
        this.password = password;
        this.repeatPass = repeatPass;
        this.email = email;
    }

    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getRepeatPass() {return repeatPass;}
    public String getEmail() {return email;}
}
