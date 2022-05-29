package com.lemzeeyyy.quizapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lemzeeyyy.quizapplication.DifficultyActivity;
import com.lemzeeyyy.quizapplication.R;
import com.lemzeeyyy.quizapplication.SplashActivity;
import com.lemzeeyyy.quizapplication.model.CourseModel;

import java.util.List;
import java.util.Random;

public class CourseAdapter extends BaseAdapter {
    private Context context;
    private List<CourseModel> catList;

    public CourseAdapter(List<CourseModel> catList, Context context) {
        this.catList = catList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return catList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        if(view == null) {
             view1 = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.course_item_layout,viewGroup,false);
        }else
            view1 = view;

        view1.setOnClickListener(view2 -> {
            SplashActivity.selectedCourseIndex = i;
            Intent intent = new Intent(viewGroup.getContext(), DifficultyActivity.class);
//            intent.putExtra("COURSES",catList.get(i).getCourseName());
//            intent.putExtra("CATEGORY_ID",i+1);
            viewGroup.getContext().startActivity(intent);
        });

        ((TextView) view1.findViewById(R.id.courseNameID)).setText(catList.get(i).getCourseName());
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255));
        view1.setBackgroundColor(color);

        return view1;
    }
}
