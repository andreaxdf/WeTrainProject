package model;

import java.sql.Time;

public class Lesson {
    private int id;
    private String lessonDay;
    private Time lessonStartTime;
    private Time lessonEndTime;
    private Course course;

    public Lesson(int id, Course course, String lessonDay, Time lessonStartTime, Time lessonEndTime){
        this.id = id;
        this.course = course;
        this.lessonDay = lessonDay;
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
    }

    public int getId() {
        return id;
    }

    public String getLessonDay() {
        return lessonDay;
    }

    public Course getCourse() {
        return course;
    }

    public Time getLessonStartTime() {
        return lessonStartTime;
    }

    public void setLessonStartTime(Time lessonStartTime) {
        this.lessonStartTime = lessonStartTime;
    }

    public Time getLessonEndTime() {
        return lessonEndTime;
    }

    public void setLessonEndTime(Time lessonEndTime) {
        this.lessonEndTime = lessonEndTime;
    }
}
