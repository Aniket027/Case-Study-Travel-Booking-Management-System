package com.travelbooking.dao;

import com.travelbooking.model.Destination;
import com.travelbooking.util.DBConnectionUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DestinationDAO {

    public void addDestination(Destination destination) {
        String query = "INSERT INTO Destination (name, location, description, price_per_day) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, destination.getName());
            preparedStatement.setString(2, destination.getLocation());
            preparedStatement.setString(3, destination.getDescription());
            preparedStatement.setBigDecimal(4, destination.getPricePerDay());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Destination getDestination(int destinationId) {
        Destination destination = null;
        String query = "SELECT * FROM Destination WHERE destination_id = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, destinationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                destination = new Destination();
                destination.setDestinationId(resultSet.getInt("destination_id"));
                destination.setName(resultSet.getString("name"));
                destination.setLocation(resultSet.getString("location"));
                destination.setDescription(resultSet.getString("description"));
                destination.setPricePerDay(resultSet.getBigDecimal("price_per_day"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destination;
    }

    public void updateDestination(Destination destination) {
        String query = "UPDATE Destination SET name = ?, location = ?, description = ?, price_per_day = ? WHERE destination_id = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, destination.getName());
            preparedStatement.setString(2, destination.getLocation());
            preparedStatement.setString(3, destination.getDescription());
            preparedStatement.setBigDecimal(4, destination.getPricePerDay());
            preparedStatement.setInt(5, destination.getDestinationId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDestination(int destinationId) {
        String query = "DELETE FROM Destination WHERE destination_id = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, destinationId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getPricePerDay(int destinationId) {
        String query = "SELECT price_per_day FROM destination WHERE destination_id = ?";
        BigDecimal pricePerDay = null;

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, destinationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                pricePerDay = resultSet.getBigDecimal("price_per_day");
            } else {
                // Handle destination not found scenario
                // For example, throw a custom exception or return a default value
                throw new RuntimeException("Destination not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pricePerDay;
    }

}
