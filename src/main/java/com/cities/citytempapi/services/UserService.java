package com.cities.citytempapi.services;

import com.cities.citytempapi.PostgreConnection;
import com.cities.citytempapi.models.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserService {
    private final Connection con = PostgreConnection.getConnection();
    private final String getUserByIdSqlStatement = "select * from users where id=?;";
    private final String getUserByUsernameSqlStatement = "select * from users where username=?;";

    public User getUser(Integer id) {

        User user = null;
        try(PreparedStatement preparedStatement = con.prepareStatement(getUserByIdSqlStatement)){
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

    public User getUser(String username) {

        User user = null;
        try(PreparedStatement preparedStatement = con.prepareStatement(getUserByUsernameSqlStatement)){
            preparedStatement.setString(1, username);
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
