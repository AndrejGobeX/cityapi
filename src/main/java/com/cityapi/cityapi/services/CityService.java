package com.cityapi.cityapi.services;

import com.cityapi.cityapi.PostgreConnection;
import com.cityapi.cityapi.models.City;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CityService {
    private final Connection con = PostgreConnection.getConnection();
    private final String getCityByIdSqlStatement = "select * from cities where id=?;";
    private final String getCityByNameSqlStatement = "select * from cities where upper(name)=upper(?);";

    private City getCity(ResultSet result) throws SQLException{
        if (result.next()){
            return new City(
                    result.getInt(City.Column.ID.ordinal()),
                    result.getString(City.Column.NAME.ordinal()),
                    result.getString(City.Column.COUNTRY.ordinal()),
                    result.getString(City.Column.STATE_REGION.ordinal()),
                    result.getInt(City.Column.POPULATION.ordinal())
            );
        }
        return null;
    }

    public City getCity(Integer id) {
        City city = null;
        try(PreparedStatement preparedStatement = con.prepareStatement(getCityByIdSqlStatement)){
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            city = getCity(result);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return city;
    }

    public City getCity(String name) {
        City city = null;
        try(PreparedStatement preparedStatement = con.prepareStatement(getCityByNameSqlStatement)){
            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();
            city = getCity(result);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return city;
    }
}
