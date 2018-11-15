package com.example.a1738253.tp2_tasksapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1738253.tp2_tasksapp.R;

/** Fragment for the first view pager inside Main Activity.
 *  This fragment is home to TaskListFragment, TaskCreateFragment and TaskDetail Fragment. I need to reduce the dependency between these fragment**
 */
public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_home, container, false); //View Inflater

        FragmentTransaction transaction = getFragmentManager().beginTransaction(); //Fragment transactino to add the fragment inside the home view pager.
        transaction.replace(R.id.frame_home, new TaskListFragment()); //The default fragment for the home fragment is the task list
        transaction.commit();

        return view;
    }
}
