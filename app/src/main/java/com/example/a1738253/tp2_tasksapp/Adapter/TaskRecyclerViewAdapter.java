package com.example.a1738253.tp2_tasksapp.Adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a1738253.tp2_tasksapp.Model.Task;
import com.example.a1738253.tp2_tasksapp.R;

import java.util.Calendar;
import java.util.List;

/** Class used to instantiate a new recycler view to be used for displaying tasks.
 * We dont use TaskLog taskList directly in here because i want to make this class more independant.
 */
public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder>{

    private List<Task> mTaskListe; //Property that holds the task list.

    /** Constructor for the Recycler View adapter. We need a list argument to instantiate the class
     * @param taskListe List to instantiate the recycler view with.
     */
    public TaskRecyclerViewAdapter(List<Task> taskListe) {
        this.mTaskListe = taskListe;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_card, parent, false);
        return new TaskViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        if((mTaskListe == null) || (mTaskListe.size() == 0)) {
            //If nothing is ccontained inside the tasklist, we display a default card to the user
            holder.mTitre.setText("Aucune Cartes Disponible");
            holder.mTaskContainer.setBackgroundResource(R.drawable.bg_task_inactif);
        } else {
            Task task = mTaskListe.get(position); //We retrieve the corresponding task that was clicked inside the recycler view

            holder.mTitre.setText(task.getTitre());
            holder.mType.setText(task.getType().toString());

            //Logic to display different text based on the amount of day left
            String dateString = "";
            int dayLeft = task.getDate().get(Calendar.DAY_OF_MONTH) - Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            switch (dayLeft){
                case 0: dateString="Aujourdh'ui"; break;
                case 1: dateString="Demain"; break;
                default: dateString="Dans " + String.valueOf(dayLeft) + " jours"; break;
            }
            holder.mDate.setText(dateString);

            //We changed the background drawable based on the status of task.
            switch(task.getStatut()){
                case NonComplete: holder.mTaskContainer.setBackgroundResource(R.drawable.bg_task_missed); break;
                case EnCours: holder.mTaskContainer.setBackgroundResource(R.drawable.bg_task_ongoing); break;
                case Complete: holder.mTaskContainer.setBackgroundResource(R.drawable.bg_task_done); break;
            }
        }
    }

    /** Methos that returns the number of task inside the tasklist
     * @return Number of tasks
     */
    @Override
    public int getItemCount() {
        return ((mTaskListe != null) && (mTaskListe.size() != 0)? mTaskListe.size(): 0);
    }

    /** Method that return a task based on the position passed as a parameter
     * @param position Position of the task
     * @return Task found inside the task list
     */
    public Task GetTask(int position)
    {
        return ((mTaskListe != null) && (mTaskListe.size() != 0)? mTaskListe.get(position): null);
    }

    /** Class used by the recycler view to create new list item.
     *  Uses the fragment_task_card.
     */
    static class TaskViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitre = null;
        private TextView mType = null;
        private TextView mDate = null;
        private LinearLayout mTaskContainer = null;

        /** Constructor for the ViewHolder. We select item from the view here.
         * @param itemView The created view
         */
        public TaskViewHolder(View itemView){
            super(itemView);
            this.mTitre = itemView.findViewById(R.id.TaskTitre);
            this.mType = itemView.findViewById(R.id.TaskCategorie);
            this.mDate = itemView.findViewById(R.id.TaskDate);
            this.mTaskContainer = itemView.findViewById(R.id.Task);
        }
    }

}
