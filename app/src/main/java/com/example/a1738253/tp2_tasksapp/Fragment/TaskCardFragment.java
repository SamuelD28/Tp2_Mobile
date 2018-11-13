package com.example.a1738253.tp2_tasksapp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a1738253.tp2_tasksapp.Model.Task;
import com.example.a1738253.tp2_tasksapp.R;

public class TaskCardFragment extends Fragment {

    private TextView TitreTextView;
    private TextView TypeTextView;
    private TextView DateTextView;

    private Task Task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.task_card_fragment, container, false);
        TitreTextView = view.findViewById(R.id.TaskTitre);
        TypeTextView = view.findViewById(R.id.TaskCategorie);
        DateTextView = view.findViewById(R.id.TaskDate);

        DateTextView.setText("2018-02-12"); //Erreur quand on utilise les methode getter sur la date
        TitreTextView.setText(Task.getTitre());
        TypeTextView.setText(Task.getType().toString());

        return view;
    }

    public void SetTask(Task task)
    {
        if(task == null)
            throw new NullPointerException();

        Task = task;
    }

}
