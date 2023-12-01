package com.cityapi.cityapi.services;

import com.cityapi.cityapi.PostgreConnection;
import com.cityapi.cityapi.models.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserService {
    private final Connection con = PostgreConnection.getConnection();
    private final String getUserSqlStatement = "select * from users where id=?;";

    public User getUser(Integer id) {

        User user = null;
        try(PreparedStatement preparedStatement = con.prepareStatement(getUserSqlStatement)){
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()){
                user = new User(
                        result.getInt(User.Column.ID.ordinal()),
                        result.getString(User.Column.USERNAME.ordinal()),
                        result.getString(User.Column.PASSWORD.ordinal()),
                        result.getString(User.Column.EMAIL.ordinal())
                );
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }
}
