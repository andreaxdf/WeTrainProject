package controller;

import database.dao_classes.CourseDAO;
import database.dao_classes.TrainerDAO;
import exception.DBUnreachableException;
import exception.invalid_data_exception.EmptyFieldsException;
import exception.invalid_data_exception.InvalidTimeException;
import model.Course;
import model.Lesson;
import model.Trainer;
import viewone.bean.CommunicationBean;
import viewone.bean.CourseBean;
import viewone.bean.LessonBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageCoursesController extends CourseManagementController{

    private final LoginController loginController = new LoginController();
    private final NotificationsController notificationsController = new NotificationsController();

    public void createCourse(CourseBean bean) throws SQLException, DBUnreachableException, InvalidTimeException {
        Trainer trainer = new TrainerDAO().loadTrainer(bean.getOwner());
        Course course = new Course(
                bean.getName(),
                bean.getDescription(),
                bean.getFitnessLevel(),
                trainer,
                bean.getEquipment(),
                getLessonFromBean(bean.getLessonBeanList()));
        new CourseDAO().saveCourse(course);
    }

    private List<Lesson> getLessonFromBean(List<LessonBean> lessonBeanList) throws InvalidTimeException {
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
                    bean.getLessonEndTime()
            );
            list.add(lesson);
        }
        return list;
    }

    public List<CourseBean> getCourseList() throws DBUnreachableException, SQLException {
        List<Course> courseList = new CourseDAO().loadAllCoursesTrainer((Trainer) loginController.getLoggedUser());
        return getCourseBeanList(courseList);
    }

    public void deleteCourse(CourseBean courseBean) throws DBUnreachableException, SQLException {
        new CourseDAO().deleteCourse(courseBean.getId());
    }

    public void modifyCourse(CourseBean courseBean, int id) throws SQLException, DBUnreachableException, EmptyFieldsException, InvalidTimeException {
        new CourseDAO().modifyCourse(
                id,
                new Course(
                        courseBean.getName(),
                        courseBean.getDescription(),
                        courseBean.getFitnessLevel(),
                        (Trainer) loginController.getLoggedUser(),
                        courseBean.getEquipment(),
                        getLessonFromBean(courseBean.getLessonBeanList())
                ));
        notificationsController.sendCourseCommunicationNotification(
                new CommunicationBean(
                        """
                        ATTENTION!
                        The trainer %s modified the course %s.
                        Be sure to check the modification!
                        """,
                        courseBean
                )
        );
    }
}