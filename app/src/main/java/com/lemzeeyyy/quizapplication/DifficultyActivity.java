package com.lemzeeyyy.quizapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.lemzeeyyy.quizapplication.adapter.DifficultyAdapter;

public class DifficultyActivity extends AppCompatActivity {
    private TextView difficulty;
    private GridView diffGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        Toolbar toolbar = findViewById(R.id.setToolbarId);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("COURSES");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        difficulty = findViewById(R.id.diffText);
        diffGrid = findViewById(R.id.diffGridViewID);
        DifficultyAdapter adapter = new DifficultyAdapter(4);
        diffGrid.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            DifficultyActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}