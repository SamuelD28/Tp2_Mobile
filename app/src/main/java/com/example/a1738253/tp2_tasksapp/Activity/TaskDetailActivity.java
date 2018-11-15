package com.example.a1738253.tp2_tasksapp.Activity;

import android.support.v4.app.Fragment;
import com.example.a1738253.tp2_tasksapp.Fragment.TaskDetailFragment;

public class TaskDetailActivity extends SingleFragementActivity {

    @Override
    public Fragment createFragment() {
        return new TaskDetailFragment();
    }
}
