package com.lemzeeyyy.quizapplication;

import static com.lemzeeyyy.quizapplication.SplashActivity.catList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.lemzeeyyy.quizapplication.adapter.CourseAdapter;


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

        CourseAdapter adapter = new CourseAdapter(catList,CourseActivity.this);
        courseGrid.setAdapter(adapter);

    }
}