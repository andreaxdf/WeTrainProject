package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries;
import engeneering.NotificationFactorySingleton;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import exception.ElementNotFoundException;
import exception.UserNotFoundException;
import model.Course;
import model.User;
import model.notification.Notification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public void saveNotification(Notification notification) throws SQLException, DBUnreachableException {
        saveNotification(
                notification.getType().ordinal(),
                notification.getDescription(),
                notification.getNotificationDate(),
                notification.getSender().getFiscalCode(),
                notification.getReceiver().getFiscalCode()
        );
    }

    public void saveNotification(int type, String info, LocalDateTime dateTime, String sender, String receiver) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.INSERT_NOTIFICATION_QUERY)) {
            Queries.insertNotification(preparedStatement, type, info, dateTime, sender, receiver);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Notification> loadAllNotifications(User user) throws SQLException, DBUnreachableException, ElementNotFoundException, UserNotFoundException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.LOAD_ALL_NOTIFICATIONS_QUERY); ResultSet rs = Queries.loadAllNotifications(preparedStatement, user)){
            List<Notification> myList = new ArrayList<>();
            while(rs.next()){
                myList.add(NotificationFactorySingleton.getInstance().createNotification(
                        rs.getInt("idNotification"),
                        rs.getInt("Type"),
                        rs.getString("Info"),
                        rs.getTimestamp("NotificationDate").toLocalDateTime(),
                        new UserDAO().loadUser(rs.getString("sender")),
                        user)
                );
            }
            return myList;
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void deleteNotification(int idNotification) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.DELETE_NOTIFICATION_QUERY)) {
            Queries.deleteNotification(preparedStatement, idNotification);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void sendCourseNotification(Course course, Notification notification) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.LOAD_SUBSCRIBED_QUERY); ResultSet rs = Queries.loadSubscribed(preparedStatement, course.getId())) {
            while(rs.next()) {
                new NotificationDAO().saveNotification(
                        notification.getType().ordinal(),
                        notification.getDescription(),
                        notification.getNotificationDate(),
                        notification.getSender().getFiscalCode(),
                        rs.getString("Athlete")
                );
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
