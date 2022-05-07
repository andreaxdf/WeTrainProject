package viewone.engeneering;

import database.dao_classes.CourseDAO;
import exception.DBConnectionFailedException;
import model.Course;
import model.User;
import model.notification.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static model.notification.NotificationEnum.*;

public class NotificationFactorySingleton {
    private static final NotificationFactorySingleton instance = new NotificationFactorySingleton();

    private NotificationFactorySingleton() {}

    public static NotificationFactorySingleton getInstance() {
        return instance;
    }

    public Notification createCourseCommunicationNotification(User sender, Course course, String text) {
        return new CommunicationNotification(
                sender,
                null,
                text,
                course);
    }

    public Notification createRejectedRequestNotification(User sender) {
        return new RejectedRequestNotification(
                sender,
                null
        );
    }

    public Notification createCourseModifiedNotification(User sender, User receiver, Course course) {
        return new CourseModifiedNotification(
                sender,
                receiver,
                course
        );
    }

    public Notification createSubscribeToTrainerNotification(User sender, User receiver, int subscribers) {
        return new SubscriptionToTrainerNotification(
                sender,
                receiver,
                subscribers
        );
    }

    public Notification createSubscribeToCourseNotification(User sender, User receiver, Course course, int subscribers) {
        return new SubscriptionToCourseNotification(
                sender,
                receiver,
                course,
                subscribers
        );
    }

    public Notification createWorkoutPlanReadyNotification(User sender, User receiver) {
        return new WorkoutPlanReadyNotification(
                sender,
                receiver
        );
    }

    public Notification createNotification(int idNotification, int type, String info, LocalDateTime dateTime, User sender, User receiver) throws DBConnectionFailedException, SQLException {
        NotificationEnum type1 = NotificationEnum.of(type);
        String[] params = info.split("-");
        if(type1 == SUBSCRIPTIONTOTRAINER) {
            return new SubscriptionToTrainerNotification(
                    idNotification,
                    sender,
                    receiver,
                    Integer.parseInt(params[0]),
                    dateTime
            );
        } else if (type1 == SUBSCRIPTIONTOCOURSE) {
            return new SubscriptionToCourseNotification(
                    idNotification,
                    sender,
                    receiver,
                    Integer.parseInt(params[1]),
                    new CourseDAO().loadCourse(Integer.parseInt(params[0])),
                    dateTime
            );
        } else if (type1 == REJECTEDREQUEST) {
            return new RejectedRequestNotification(
                    idNotification,
                    sender,
                    receiver,
                    dateTime
            );
        } else if (type1 == WORKOUTPLANREADY) {
            return new WorkoutPlanReadyNotification(
                    idNotification,
                    sender,
                    receiver,
                    dateTime
            );
        } else if (type1 == COMMUNICATION) {
            return new CommunicationNotification(
                    idNotification,
                    sender,
                    receiver,
                    params[1],
                    new CourseDAO().loadCourse(Integer.parseInt(params[0])),
                    dateTime
            );
        } else if (type1 == COURSEMODIFIED) {
            return new CourseModifiedNotification(
                    idNotification,
                    sender,
                    receiver,
                    new CourseDAO().loadCourse(Integer.parseInt(params[0])),
                    dateTime
            );
        } else if(type1 == EMAILRECEIVED) {
            return new EmailReceivedNotification(
                    idNotification,
                    sender,
                    receiver,
                    dateTime
            );
        }
        throw new RuntimeException();
    }
}
