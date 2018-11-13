package com.example.a1738253.tp2_tasksapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.a1738253.tp2_tasksapp.Model.Task;
import com.example.a1738253.tp2_tasksapp.R;
import static com.example.a1738253.tp2_tasksapp.Activity.MainActivity.TASK_TRANSFER;

public class TaskDetailActivity extends AppCompatActivity {

    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_task_detail);

        mTitle = findViewById(R.id.task_detail_title);

        Intent intent = getIntent();
        Task task = (Task) intent.getSerializableExtra(TASK_TRANSFER);

        if(task != null)
        {
            mTitle.setText(task.getTitre());
        }
    }
}
