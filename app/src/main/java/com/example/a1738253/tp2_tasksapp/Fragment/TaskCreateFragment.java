package com.example.a1738253.tp2_tasksapp.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1738253.tp2_tasksapp.Model.Task;
import com.example.a1738253.tp2_tasksapp.Model.TaskLog;
import com.example.a1738253.tp2_tasksapp.R;

import java.util.Calendar;

import static com.example.a1738253.tp2_tasksapp.Utils.Validation.Entre;

public class TaskCreateFragment extends Fragment {

    /**Properties for the class**/
    private TextView TitleInput;
    private TextView DescriptionInput;
    private Spinner TypeInput;
    private RadioGroup NotificationInput;
    private ImageButton CloseBtn;
    private Button AddBtn;
    private DatePickerDialog DatePicker;
    private Button OpenDatePickerBtn;

    private Calendar mDate;
    private TaskLog mTaskLog;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_task_create, container, false);

        mDate = Calendar.getInstance();

        //Click listener for the datepicker
        OpenDatePickerBtn = view.findViewById(R.id.task_create_dateInput);
        OpenDatePickerBtn.setOnClickListener(view1 -> DatePicker.show());

        DatePicker = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
            mDate.set(i,i1,i2);
            OpenDatePickerBtn.setText(mDate.get(Calendar.YEAR)+"-"+mDate.get(Calendar.MONTH)+"-"+mDate.get(Calendar.DAY_OF_MONTH));
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));



        //Click Listener for the close button
        CloseBtn = view.findViewById(R.id.task_create_closeBtn);
        CloseBtn.setOnClickListener(view1 -> {
            CloseForm();
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.frame_home, new TaskListFragment());
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            trans.addToBackStack(null);
            trans.commit();
        });

        //Click listener for the save button
        AddBtn = view.findViewById(R.id.task_create_addbtn);
        AddBtn.setOnClickListener(view12 -> SubmitForm());

        TitleInput = view.findViewById(R.id.task_create_titleInput);
        DescriptionInput = view.findViewById(R.id.task_create_descInput);
        TypeInput = view.findViewById(R.id.task_create_categorieInput);
        NotificationInput = view.findViewById(R.id.task_create_radioGroup);

        return view;
    }

    /** Method called to save the information inside the taskLog
     */
    public void SubmitForm()
    {
        String title = TitleInput.getText().toString();
        String description = DescriptionInput.getText().toString();
        Task.Type type = Task.Type.valueOf(TypeInput.getSelectedItemPosition());
        boolean archive = false;
        Task.Statut statut = Task.Statut.NonComplete;
        Task.Notification notification;
        switch(NotificationInput.getCheckedRadioButtonId()){
            case R.id.task_detail_notifInput1: notification = Task.Notification.LeJour; break;
            case R.id.task_detail_notifInput2: notification = Task.Notification.JourAvant; break;
            case R.id.task_detail_notifInput3: notification = Task.Notification.SemaineAvant; break;
            default: notification = Task.Notification.Aucune; break;
        }

        boolean estOk = true;
        String erreurMessage = "";
        Calendar today = Calendar.getInstance();

        if(!Entre(title, 40,3)){
            erreurMessage = "Le titre doit être entre 3 et 40";
            estOk = false;
        }
        if(!Entre(description,150,10)){
            erreurMessage = "La description doit être entre 10 et 150";
            estOk = false;
        }
        if(!mDate.after(today) && mDate.get(Calendar.DAY_OF_MONTH) != today.get(Calendar.DAY_OF_MONTH)){
            erreurMessage = "La date ne doit pas être antérieur";
            estOk = false;
        }
        if(!(title.charAt(0) == Character.toUpperCase(title.charAt(0)))){
            erreurMessage = "La première lettre du tire doit être majuscule";
            estOk = false;
        }

        if(estOk){
            mTaskLog = TaskLog.GetInstance();
            Task newTask = new Task(title, description, type,mDate,statut,archive,notification);
            if(mTaskLog.AddTask(newTask)){
                CloseForm();
                Toast.makeText(getContext(), "Tâche ajoutée", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), erreurMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private void CloseForm()
    {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.frame_home, new TaskListFragment());
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        trans.addToBackStack(null);
        trans.commit();
    }
}
