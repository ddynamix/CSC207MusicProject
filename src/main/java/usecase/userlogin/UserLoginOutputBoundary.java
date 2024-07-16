package usecase.userlogin;


public interface UserLoginOutputBoundary {
    void prepareLoginSuccessView(UserLoginOutputData user);

    void prepareLoginFailView(String error);
}
