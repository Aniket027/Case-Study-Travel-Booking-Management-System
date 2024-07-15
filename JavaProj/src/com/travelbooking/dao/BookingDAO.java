package com.travelbooking.dao;

import com.travelbooking.model.Booking;
import com.travelbooking.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDAO {
    public void addBooking(Booking booking) {
        String query = "INSERT INTO Booking (destination_id, customer_id," +
                " booking_date, start_date, end_date, total_cost, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, booking.getDestinationId());
            preparedStatement.setInt(2, booking.getCustomerId());
            preparedStatement.setDate(3, booking.getBookingDate());
            preparedStatement.setDate(4, booking.getStartDate());
            preparedStatement.setDate(5, booking.getEndDate());
            preparedStatement.setBigDecimal(6, booking.getTotalCost());
            preparedStatement.setString(7, booking.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Booking getBooking(int bookingId) {
        Booking booking = null;
        String query = "SELECT * FROM Booking WHERE booking_id = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bookingId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                booking = new Booking();
                booking.setBookingId(resultSet.getInt("booking_id"));
                booking.setDestinationId(resultSet.getInt("destination_id"));
                booking.setCustomerId(resultSet.getInt("customer_id"));
                booking.setBookingDate(resultSet.getDate("booking_date"));
                booking.setStartDate(resultSet.getDate("start_date"));
                booking.setEndDate(resultSet.getDate("end_date"));
                booking.setTotalCost(resultSet.getBigDecimal("total_cost"));
                booking.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    public void updateBooking(Booking booking) {
        String query = "UPDATE Booking SET destination_id = ?, customer_id = ?, booking_date = ?, start_date = ?, end_date = ?, total_cost = ? WHERE booking_id = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, booking.getDestinationId());
            preparedStatement.setInt(2, booking.getCustomerId());
            preparedStatement.setDate(3, booking.getBookingDate());
            preparedStatement.setDate(4, booking.getStartDate());
            preparedStatement.setDate(5, booking.getEndDate());
            preparedStatement.setBigDecimal(6, booking.getTotalCost());
            preparedStatement.setInt(7, booking.getBookingId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelBooking(int bookingId) {
        String query = "UPDATE booking SET status = ? WHERE booking_id = ?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "cancelled");
            preparedStatement.setInt(2, bookingId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
