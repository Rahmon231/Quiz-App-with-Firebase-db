package com.lemzeeyyy.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.lemzeeyyy.quizapplication.adapter.CourseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {
    private GridView courseGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        courseGrid = findViewById(R.id.courseGridviewID);
        Toolbar toolbar = findViewById(R.id.toolBarId);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<String> catList = new ArrayList<>();
        catList.add("Course 1");
        catList.add("Course 2");
        catList.add("Course 3");
        catList.add("Course 4");
        CourseAdapter adapter = new CourseAdapter(catList);
        courseGrid.setAdapter(adapter);

    }
}