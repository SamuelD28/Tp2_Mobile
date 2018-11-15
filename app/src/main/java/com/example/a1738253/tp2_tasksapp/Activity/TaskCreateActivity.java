package com.example.a1738253.tp2_tasksapp.Activity;
import android.support.v4.app.Fragment;
import com.example.a1738253.tp2_tasksapp.Fragment.TaskCreateFragment;

public class TaskCreateActivity extends SingleFragementActivity {

    @Override
    public Fragment createFragment() {
        return new TaskCreateFragment();
    }
}
