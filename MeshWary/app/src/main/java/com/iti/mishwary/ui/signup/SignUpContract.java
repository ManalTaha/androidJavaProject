package com.iti.mishwary.ui.signup;

import com.iti.mishwary.Models.User;

public interface SignUpContract  {
    public interface SignupView{
        void goToLogin(User user);
        void showError();
        void registerUser(User user);
    }
    public interface SignupPresenter{
        void addUser(User user);
        void checkMail(User user);
    }
}
