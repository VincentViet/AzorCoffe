package com.azor.login;

import java.sql.SQLException;

public interface IPresenter {
    void loginValidate(String identify, String password) throws SQLException;
}
