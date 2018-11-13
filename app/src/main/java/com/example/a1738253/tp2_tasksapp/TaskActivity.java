package com.example.a1738253.tp2_tasksapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a1738253.tp2_tasksapp.Fragment.TaskFragment;
import com.example.a1738253.tp2_tasksapp.Model.Task;
import com.example.a1738253.tp2_tasksapp.Model.TaskLog;

import java.util.List;
import java.util.UUID;

public class TaskActivity extends AppCompatActivity {

    private static  final  String EXTRA_TASK_ID = "com.cstjean.a1738253.tp2_tasksapp";


    private ViewPager mViewPager;
    private List<Task> mTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_pager);

        UUID taskID = (UUID)getIntent().getSerializableExtra(TaskActivity.EXTRA_TASK_ID);

        mTasks = TaskLog.get().getTasks();

        mViewPager = (ViewPager) findViewById(R.id.task_view_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                Task task = mTasks.get(i);
                return TaskFragment.newInstance(task.getId());
            }

            @Override
            public int getCount() {
               return mTasks.size();
            }
        });

        for (int i=0; i < mTasks.size(); ++i){
            if (mTasks.get(i).equals(taskID)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }



    }
    public  static Intent newIntent(Context context, UUID task_id) {

        Intent intent =  new Intent(context, TaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID, task_id);
        return  intent;
    }
}
