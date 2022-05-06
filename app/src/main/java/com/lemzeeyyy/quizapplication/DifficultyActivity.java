package com.lemzeeyyy.quizapplication;

import static com.lemzeeyyy.quizapplication.SplashActivity.catList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lemzeeyyy.quizapplication.adapter.DifficultyAdapter;

public class DifficultyActivity extends AppCompatActivity {
    private TextView difficulty;
    private GridView diffGrid;
    private FirebaseFirestore firestore;
    private int category_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        Toolbar toolbar = findViewById(R.id.setToolbarId);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("COURSES");
        category_id = getIntent().getIntExtra("CATEGORY_ID",1);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        difficulty = findViewById(R.id.diffText);
        diffGrid = findViewById(R.id.diffGridViewID);
        firestore = FirebaseFirestore.getInstance();
        loadDifficulty();
        DifficultyAdapter adapter = new DifficultyAdapter(4);
        diffGrid.setAdapter(adapter);

    }

    private void loadDifficulty() {
        firestore.collection("QUIZ").document("CAT"+String.valueOf(category_id))
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc = task.getResult();
                    if(doc.exists()){
                        long count = (long)doc.get("DIFFICULTY");

                    }else {
                        Toast.makeText(SplashActivity.this, "no category document", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    Toast.makeText(SplashActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            DifficultyActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}