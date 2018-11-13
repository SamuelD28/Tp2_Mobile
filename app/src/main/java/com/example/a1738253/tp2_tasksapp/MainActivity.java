package com.example.a1738253.tp2_tasksapp;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.a1738253.tp2_tasksapp.Fragment.HomeFragment;
import com.example.a1738253.tp2_tasksapp.Model.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> TaskList = new ArrayList<>();
    private FragmentManager FragManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragManager = getFragmentManager();
        TaskDataSeed();
        InsertHomeFragment();
    }

    //Methode pour generer des fausse données
    private void TaskDataSeed()
    {
        Task taskOne = new Task("Aller à l'épicerie", Task.Type.PERSONNEL);
        Task taskTwo = new Task("Gym Bicep/Tricep", Task.Type.PERSONNEL);
        Task taskThree = new Task("Travaux Structure de donnée", Task.Type.ECOLE);
        Task taskFour = new Task("Souper Fête William", Task.Type.AUTRE);
        Task taskFive = new Task("Changement Huile", Task.Type.AUTRE);
        Task taskSix = new Task("Course Matinale", Task.Type.TRAVAIL);

        TaskList.add(taskOne);
        TaskList.add(taskTwo);
        TaskList.add(taskThree);
        TaskList.add(taskFour);
        TaskList.add(taskFive);
        TaskList.add(taskSix);
    }

    private void InsertHomeFragment()
    {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.SetTaskList(TaskList);
        FragManager.beginTransaction().add(R.id.FragmentContainer, homeFragment).commit();
    }
}
