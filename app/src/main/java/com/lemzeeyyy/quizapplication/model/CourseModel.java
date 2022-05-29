package com.lemzeeyyy.quizapplication.model;

import java.util.List;

public class CourseModel {
    private String courseName;
    private String courseId;

    public CourseModel(String courseName, String courseId) {
        this.courseName = courseName;
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
