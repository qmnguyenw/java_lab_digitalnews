/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
/**
 *
 * @author Admin
 */
public class MyException extends Exception {
    String message;

    public MyException(Exception e) {
        if (e instanceof SQLException) {
            message = "Database Connection Error";
        } else if (e instanceof NullPointerException) {
            message = "NullPointerException";
        } else {
            message = e.getMessage();
        }
    }

    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
