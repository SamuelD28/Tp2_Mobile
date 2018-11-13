package com.example.a1738253.tp2_tasksapp.Fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a1738253.tp2_tasksapp.Model.Task;
import com.example.a1738253.tp2_tasksapp.R;
import java.util.List;

class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder>{
    private List<Task> mTaskListe;
    private Context mContext;

    public TaskRecyclerViewAdapter(List<Task> taskListe, Context context) {
        this.mTaskListe = taskListe;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_card, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        if((mTaskListe == null) || (mTaskListe.size() == 0)) {
            //Do something if nothing is placed here
        } else {
            Task task = mTaskListe.get(position);
            holder.mTitre.setText(task.getTitre());
            holder.mType.setText(task.getType().toString());
        }
    }

    @Override
    public int getItemCount() {
        return ((mTaskListe != null) && (mTaskListe.size() != 0)? mTaskListe.size(): 0);
    }

    void LoadNewTasks(List<Task> newTaskListe)
    {
        mTaskListe = newTaskListe;
        notifyDataSetChanged();
    }

    public Task GetTask(int position)
    {
        return ((mTaskListe != null) && (mTaskListe.size() != 0)? mTaskListe.get(position): null);
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitre = null;
        private TextView mType = null;
        private TextView mDate = null;

        public TaskViewHolder(View itemView){
            super(itemView);
            this.mTitre = itemView.findViewById(R.id.TaskTitre);
            this.mType = itemView.findViewById(R.id.TaskCategorie);
            this.mDate = itemView.findViewById(R.id.TaskDate);
        }
    }

}
