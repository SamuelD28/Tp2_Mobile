package com.example.a1738253.tp2_tasksapp.Model;

import java.util.List;
import java.util.UUID;

public class TaskLog {

    private  static TaskLog sTaskLog;

    private List<Task> mTasks;

    private TaskLog() {
        //Création de taches pour tests
    }

    public static  TaskLog get(){
        if (sTaskLog == null){
            sTaskLog = new TaskLog();
        }
        return  sTaskLog;
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public Task getTask(UUID id)
    {
        for(Task t : mTasks){
            if (t.getId().equals(id)){
                return t;
            }
        }

        return null;
    }

    public void AddTask(Task t){
        mTasks.add(t);
    }
}
