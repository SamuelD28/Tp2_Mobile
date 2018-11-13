package com.example.a1738253.tp2_tasksapp.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.a1738253.tp2_tasksapp.Activity.TaskDetailActivity;
import com.example.a1738253.tp2_tasksapp.Activity.TaskCreateActivity;
import com.example.a1738253.tp2_tasksapp.Adapter.RecyclerItemClickListener;
import com.example.a1738253.tp2_tasksapp.Adapter.TaskRecyclerViewAdapter;
import com.example.a1738253.tp2_tasksapp.Model.Task;
import com.example.a1738253.tp2_tasksapp.R;

import java.util.ArrayList;

import static com.example.a1738253.tp2_tasksapp.Activity.MainActivity.TASK_TRANSFER;

public class HomeFragment extends Fragment implements RecyclerItemClickListener.OnRecyclerClickListener{

    private ArrayList<Task> mTaskList = new ArrayList<>();
    private RecyclerView mTaskRecyclerView;
    private TaskRecyclerViewAdapter mTaskRecyclerViewAdapter;
    private ImageButton mBtnAddTaks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        //Initialise the recycler view
        mTaskRecyclerView = view.findViewById(R.id.TaskRecyclerView);
        //Set the layout manager for the layout view
        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //Might need to acess context from activity
        //Set the event listener when a item in the recycler view is taped
        mTaskRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mTaskRecyclerView, this));
        //Set the adapater for the recycler view
        mTaskRecyclerViewAdapter = new TaskRecyclerViewAdapter(mTaskList, getContext()); //Might need to access context from activity
        mTaskRecyclerView.setAdapter(mTaskRecyclerViewAdapter);

        mBtnAddTaks = view.findViewById(R.id.BtnAddTask);
        mBtnAddTaks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(getContext(), TaskCreateActivity.class);
            startActivity(intent);
            }
        });
        return view;
    }

    /** Method used to pass down data to the Fragment via MainActivity. Might remove for better encapsulation**
     * @param taskList ArrayList used to initialised the List
     */
    public void SetTaskList(ArrayList<Task> taskList)
    {
        if (taskList == null)
            throw new NullPointerException();

        mTaskList = taskList;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "Task Tap Position : " + String.valueOf(position), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), TaskDetailActivity.class);
        intent.putExtra(TASK_TRANSFER, mTaskRecyclerViewAdapter.GetTask(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(getContext(), "Task Long Tap Position : " + String.valueOf(position), Toast.LENGTH_SHORT).show();
    }
}
