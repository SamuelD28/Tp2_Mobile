package com.example.a1738253.tp2_tasksapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a1738253.tp2_tasksapp.Model.Task;
import com.example.a1738253.tp2_tasksapp.R;
import static com.example.a1738253.tp2_tasksapp.Activity.MainActivity.TASK_TRANSFER;

public class TaskDetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mDescription;
    private Spinner mType;
    private ImageButton mCloseBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_task_detail);

        mCloseBtn = findViewById(R.id.task_detail_closeBtn);
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mTitle = findViewById(R.id.task_detail_titleInput);

        Intent intent = getIntent();
        Task task = (Task) intent.getSerializableExtra(TASK_TRANSFER);

        if(task != null)
        {
            mTitle.setText(task.getTitre());
        }
    }
}
