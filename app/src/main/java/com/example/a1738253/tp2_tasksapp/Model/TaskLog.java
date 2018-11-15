package com.example.a1738253.tp2_tasksapp.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/** Singleton Class used to manage the task list troughout the application.
 *  It is dependant on the task class which holds the information about one task.
 */
public class TaskLog {
    private ArrayList<Task> mTaskList; //Actual Task list

    private static TaskLog INSTANCE = null; //Holds the instance of the class.


    /** Method used to return the single instance of the class
     * @return The Instance
     */
    public static TaskLog GetInstance(){
        if (INSTANCE == null){
            INSTANCE = new TaskLog();
        }
        return INSTANCE;
    }
    /** Constructor for the class.
     * Creates a new Arraylist and seed the test data.
     */
    private TaskLog() {
        this.mTaskList = new ArrayList<>();
        DataSeed();
    }

    public ArrayList<Task> getmTaskList() {
        return mTaskList;
    }

    public boolean UpdateTask(UUID oldTaskID, Task newTask)
    {
        //Lamda expression to retrieve the old task from the list
        Task oldTask = mTaskList.stream().filter(t -> t.getId() == oldTaskID).findFirst().get();

        if(oldTask == null)
            return false;

        oldTask.UpdateTask(newTask);
        return true;
    }

    /** Dataseed method used to intialise the ArrayList with base data
     */
    private void DataSeed(){
        Task taskOne = new Task("Aller à l'épicerie","Pas oublier qu'il faut aller a lepicerie sinon on vas mourrir de faim",Task.Type.Personnel, Calendar.getInstance(),Task.Statut.NonComplété, false,Task.Notification.LeJour);
        Task taskTwo = new Task("Souper de fête William","Acheter un cadeau pour la fete a william",Task.Type.Travail, Calendar.getInstance(),Task.Statut.Complété, false,Task.Notification.JourAvant);
        Task taskThree = new Task("Changement Huile Garage","Rendez-vous chez le garage pour aller faire changer mon huile",Task.Type.Autre, Calendar.getInstance(),Task.Statut.EnCours, false,Task.Notification.SemaineAvant);
        Task taskFour = new Task("Gym Tricep/Bicep","Pas oublier qu'il faut aller a lepicerie sinon on vas mourrir de faim",Task.Type.Travail, Calendar.getInstance(),Task.Statut.Complété, false,Task.Notification.LeJour);
        Task taskFive = new Task("Yoga Matinale","Pas oublier qu'il faut aller a lepicerie sinon on vas mourrir de faim",Task.Type.École, Calendar.getInstance(),Task.Statut.EnCours, false,Task.Notification.Aucune);
        Task taskSix = new Task("Tp2 Développement Moile","Pas oublier qu'il faut aller a lepicerie sinon on vas mourrir de faim",Task.Type.École, Calendar.getInstance(),Task.Statut.EnCours, false,Task.Notification.SemaineAvant);

        mTaskList.add(taskOne);
        mTaskList.add(taskTwo);
        mTaskList.add(taskThree);
        mTaskList.add(taskFour);
        mTaskList.add(taskFive);
        mTaskList.add(taskSix);
        mTaskList.add(taskOne);
        mTaskList.add(taskTwo);
        mTaskList.add(taskThree);
        mTaskList.add(taskFour);
        mTaskList.add(taskFive);
        mTaskList.add(taskSix);
    }
}
