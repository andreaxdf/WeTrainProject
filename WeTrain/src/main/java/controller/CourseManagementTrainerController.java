package controller;

import database.dao_classes.CourseDAO;
import database.dao_classes.NotificationDAO;
import database.dao_classes.TrainerDAO;
import exception.DBConnectionFailedException;
import exception.InvalidTimeException;
import model.Course;
import model.Lesson;
import model.Trainer;
import model.notification.CommunicationNotification;
import model.notification.Notification;
import viewone.bean.CommunicationBean;
import viewone.bean.CourseBean;
import viewone.bean.LessonBean;
import viewone.engeneering.NotificationFactorySingleton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseManagementTrainerController extends CourseManagementController{

    private final LoginController loginController = new LoginController();

    public void createCourse(CourseBean bean) throws SQLException, DBConnectionFailedException, InvalidTimeException {
        Trainer trainer = new TrainerDAO().loadTrainer(bean.getOwner());
        Course course = new Course(
                bean.getName(),
                bean.getDescription(),
                bean.getFitnessLevel(),
                trainer,
                bean.getEquipment());
        if(bean.getLessonBeanList() != null){
            course.addAllLessons(setLesson(bean.getLessonBeanList()));
        }
        new CourseDAO().saveCourse(course);
    }

    private List<Lesson> setLesson(List<LessonBean> lessonBeanList) throws InvalidTimeException {
        List<Lesson> list = new ArrayList<>();
        for(LessonBean bean: lessonBeanList) {
            boolean cond1 = bean.getLessonStartTime().getHour() > bean.getLessonEndTime().getHour();
            boolean cond2 = bean.getLessonStartTime().getHour() == bean.getLessonEndTime().getHour();
            boolean cond3 = bean.getLessonStartTime().getMinute() > bean.getLessonEndTime().getMinute();
            if(cond1 || (cond2 && cond3)) {
                throw new InvalidTimeException();
            }
            Lesson lesson = new Lesson(
                    bean.getLessonDay(),
                    bean.getLessonStartTime(),
                    bean.getLessonEndTime());
            list.add(lesson);
        }
        return list;
    }

    public List<CourseBean> getCourseList() throws DBConnectionFailedException, SQLException {
        List<Course> courseList = new CourseDAO().loadAllCoursesTrainer((Trainer) loginController.getLoggedUser());
        return getCourseBeanList(courseList);
    }

    public void deleteCourse(CourseBean courseBean) throws DBConnectionFailedException, SQLException {
        new CourseDAO().deleteCourse(courseBean.getId());
    }

    public void modifyCourse(CourseBean courseBean, int id) throws SQLException, DBConnectionFailedException {
        new CourseDAO().modifyCourse(
                id,
                new Course(
                        courseBean.getName(),
                        courseBean.getDescription(),
                        courseBean.getFitnessLevel(),
                        (Trainer) loginController.getLoggedUser(),
                        courseBean.getEquipment()
                ));
    }

    public void sendCourseCommunication(CommunicationBean bean) throws SQLException, DBConnectionFailedException {
        Course course = new CourseDAO().loadCourse(bean.getCourseBean().getId());
        new NotificationDAO().sendCourseNotification(
                course,
                (Trainer) loginController.getLoggedUser(),
                bean.getText());
    }
}
