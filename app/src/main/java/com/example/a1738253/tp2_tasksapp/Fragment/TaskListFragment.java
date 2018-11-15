package com.example.a1738253.tp2_tasksapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.a1738253.tp2_tasksapp.Adapter.RecyclerItemClickListener;
import com.example.a1738253.tp2_tasksapp.Adapter.TaskRecyclerViewAdapter;
import com.example.a1738253.tp2_tasksapp.Model.TaskLog;
import com.example.a1738253.tp2_tasksapp.R;

/** Class that holds the fragment for interacting with the
 *  recycler view list item. Implements the OnRecyclerCLickListener for
 *  triggering the right action when interacting with the list.
 */
public class TaskListFragment extends Fragment implements RecyclerItemClickListener.OnRecyclerClickListener{

    private TaskLog mTaskLog;                                   //Holds the Tasklog instance for manipulating the list
    private RecyclerView mTaskRecyclerView;                     //Holds the recycler view contain inside the fragment
    private TaskRecyclerViewAdapter mTaskRecyclerViewAdapter;   //Adapter for setting up the recycler view
    private ImageButton mBtnAddTaks;                            //Button to create a new task using a fragment

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        //Intialise le Tasklog pour pouvoir interagir avec les taches
        mTaskLog = TaskLog.GetInstance();
        //Initialise the recycler view
        mTaskRecyclerView = view.findViewById(R.id.TaskRecyclerView);
        //Set the layout manager for the layout view
        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //Might need to acess context from activity
        //Set the event listener when a item in the recycler view is taped
        mTaskRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mTaskRecyclerView, this));
        //Set the adapater for the recycler view
        mTaskRecyclerViewAdapter = new TaskRecyclerViewAdapter(mTaskLog.getmTaskList());
        mTaskRecyclerView.setAdapter(mTaskRecyclerViewAdapter);

        //Btn used to add the fragment_task_create to the first page in the view pager
        mBtnAddTaks = view.findViewById(R.id.BtnAddTask);
        mBtnAddTaks.setOnClickListener(view1 -> {
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.frame_home, new TaskCreateFragment());
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            trans.addToBackStack(null);
            trans.commit();
        });
        return view;
    }

    /** Listener Method when the user performs a click on a item inside the recycler view
     * @param view  View that triggered the event
     * @param position position of the item within the recycler list
     */
    @Override
    public void onItemClick(View view, int position) {

        //Intialization of the bundle that will be passed to the fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("TASK_DETAIL", mTaskRecyclerViewAdapter.GetTask(position)); //**Should not encode string

        //Initialization of the fragment for displaying details
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(bundle);

        //Fragment transactino to add the fragment_task_detail on top of the home fragment. Need to make this less dependent***
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.frame_home, fragment);
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);
        trans.commit();
    }

    /** Listener Method triggered when the user perform a long click on an item inside the list
     * @param view View that triggered the event
     * @param position position of the item clicked inside the recycler view
     */
    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(getContext(), "Task Long Tap Position : " + String.valueOf(position), Toast.LENGTH_SHORT).show();
    }
}
