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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1738253.tp2_tasksapp.Model.Task;
import com.example.a1738253.tp2_tasksapp.Model.TaskLog;
import com.example.a1738253.tp2_tasksapp.R;
import java.util.Calendar;

import static com.example.a1738253.tp2_tasksapp.Utils.Validation.Entre;

/** Class that holds the fragment for displaying details about a task
 *  Implement the fragment class.
 */
public class TaskDetailFragment extends Fragment{

    /**Properties for the class**/
    private TextView TitleInput;
    private TextView DescriptionInput;
    private Spinner TypeInput;
    private SeekBar StatusInput;
    private TextView StatutLabel;
    private Switch ArchiveInput;
    private RadioGroup NotificationInput;
    private ImageButton CloseBtn;
    private Button SaveBtn;
    private Button DeleteBtn;
    private DatePickerDialog DatePicker;
    private Button OpenDatePickerBtn;

    private Calendar mDate;
    private TaskLog mTaskLog;
    private Task mTask;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);

        //We intialise the form with the data passed as an bundle argument to the fragment.
        Bundle bundle = getArguments();
        if(bundle == null)
            throw new IllegalStateException("No Task was found");
        else
            mTask = (Task) bundle.getSerializable("TASK_DETAIL"); //Intialize the task property with the one passed in the bundle

        StatusInput = view.findViewById(R.id.task_detail_statusInput);
        StatusInput.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch(seekBar.getProgress()){
                    case 0:
                        StatutLabel.setText(Task.Statut.NonComplete.toString());
                        break;
                    case 1:
                        StatutLabel.setText(Task.Statut.EnCours.toString());
                        break;
                    case 2:
                        StatutLabel.setText(Task.Statut.Complete.toString());
                        break;
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        StatutLabel = view.findViewById(R.id.task_detail_statusLabel);
        //Set the status bar to the right value
        switch(mTask.getStatut()){
            case NonComplete: StatusInput.setProgress(0);break;
            case EnCours: StatusInput.setProgress(1); break;
            case Complete: StatusInput.setProgress(2); break;
        }

        //Click listener for the datepicker
        OpenDatePickerBtn = view.findViewById(R.id.task_detail_dateInput);
        OpenDatePickerBtn.setOnClickListener(view1 -> DatePicker.show());
        mDate = mTask.getDate();
        DatePicker = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
            mDate.set(i,i1,i2);
            OpenDatePickerBtn.setText(mDate.get(Calendar.YEAR)+"-"+mDate.get(Calendar.MONTH)+"-"+mDate.get(Calendar.DAY_OF_MONTH));
        }, mDate.get(Calendar.YEAR), mDate.get(Calendar.MONTH), mDate.get(Calendar.DAY_OF_MONTH));
        int Year = mDate.get(Calendar.YEAR);
        int Month = mDate.get(Calendar.MONTH);
        int Day = mDate.get(Calendar.DAY_OF_MONTH);
        OpenDatePickerBtn.setText(Year + "-" + Month + "-" + Day);


        //Click Listener for the close button
        CloseBtn = view.findViewById(R.id.task_detail_closeBtn);
        CloseBtn.setOnClickListener(view1 -> {
            CloseForm();
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.frame_home, new TaskListFragment());
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            trans.commit();
        });

        //Click listener for the save button
        SaveBtn = view.findViewById(R.id.task_detail_saveBtn);
        SaveBtn.setOnClickListener(view12 -> SubmitForm());

        DeleteBtn = view.findViewById(R.id.task_detail_deleteBtn);
        DeleteBtn.setOnClickListener(view13 -> DeleteTask());

        //We select all the element from the view.
        TitleInput = view.findViewById(R.id.task_detail_titleInput);
        TitleInput.setText(mTask.getTitre());

        DescriptionInput = view.findViewById(R.id.task_detail_descInput);
        DescriptionInput.setText(mTask.getDescription());

        TypeInput = view.findViewById(R.id.task_detail_categorieInput);
        TypeInput.setSelection(mTask.getType().getValue());

        ArchiveInput = view.findViewById(R.id.task_detail_archiveInput);
        ArchiveInput.setChecked(mTask.isArchive());

        NotificationInput = view.findViewById(R.id.task_detail_notifRadio);
        //Intialise the right radio button
        switch (mTask.getNotification()){
            case LeJour: NotificationInput.check(R.id.task_detail_notifInput1); break;
            case JourAvant: NotificationInput.check(R.id.task_detail_notifInput2); break;
            case SemaineAvant: NotificationInput.check(R.id.task_detail_notifInput3); break;
            case Aucune: NotificationInput.check(R.id.task_detail_notifInput4); break;
        }
        return view;
    }

    /** Method called to save the information inside the taskLog
     */
    public void SubmitForm()
    {
        String title = TitleInput.getText().toString();
        String description = DescriptionInput.getText().toString();
        Task.Type type = Task.Type.valueOf(TypeInput.getSelectedItemPosition());
        boolean archive = ArchiveInput.isChecked();

        Task.Statut statut;
        switch(StatusInput.getProgress()){
            case 1: statut = Task.Statut.EnCours; break;
            case 2: statut = Task.Statut.Complete; break;
            default: statut = Task.Statut.NonComplete; break;
        }

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
            if(mTaskLog.UpdateTask(mTask.getId() , newTask)){
                CloseForm();
                Toast.makeText(getContext(), "Modifications Enregistrées", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), erreurMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private void DeleteTask()
    {
        mTaskLog = TaskLog.GetInstance();
        
        if(mTaskLog.DeleteTask(mTask.getId())){
            CloseForm();
            Toast.makeText(getContext(), "Suppresion effectuée", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Suppresion à échouè", Toast.LENGTH_SHORT).show();
        }
    }

    private void CloseForm()
    {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.frame_home, new TaskListFragment());
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        trans.commit();
    }
}
