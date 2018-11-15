package com.example.a1738253.tp2_tasksapp.Fragment;

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
    private TextView mTitle;
    private TextView mDescription;
    private Spinner mType;
    private SeekBar mStatut;
    private TextView mStatutLabel;
    private Switch mArchive;
    private RadioGroup mNotification;
    private ImageButton mCloseBtn;
    private Button mSaveButton;
    private TaskLog mTaskLog;

    /**Properties used to manage the date*/
    private Calendar mDate;
    private Button mPickDateBtn;
    private DatePickerDialog mDatePicker;

    //Task that will hold the details for displaying to the user
    private Task mTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);


        mStatut = view.findViewById(R.id.task_detail_statusInput);
        mStatut.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch(seekBar.getProgress()){
                    case 0:
                        mStatutLabel.setText(Task.Statut.NonComplété.toString());
                        break;
                    case 1:
                        mStatutLabel.setText(Task.Statut.EnCours.toString());
                        break;
                    case 2:
                        mStatutLabel.setText(Task.Statut.Complété.toString());
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
        //Clicck listener for the datepicker
        mPickDateBtn = view.findViewById(R.id.task_detail_dateInput);
        mPickDateBtn.setOnClickListener(view1 -> mDatePicker.show());

        //Click Listener for the close button
        mCloseBtn = view.findViewById(R.id.task_detail_closeBtn);
        mCloseBtn.setOnClickListener(view1 -> {
            CloseForm();
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.frame_home, new TaskListFragment());
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            trans.addToBackStack(null);
            trans.commit();
        });

        //Click listener for the save button
        mSaveButton = view.findViewById(R.id.task_detail_saveBtn);
        mSaveButton.setOnClickListener(view12 -> SubmitForm());

        //We select all the element from the view.
        mTitle = view.findViewById(R.id.task_detail_titleInput);
        mDescription = view.findViewById(R.id.task_detail_descInput);
        mType = view.findViewById(R.id.task_detail_categorieInput);
        mStatutLabel = view.findViewById(R.id.task_detail_statusLabel);
        mArchive = view.findViewById(R.id.task_detail_archiveInput);
        mNotification = view.findViewById(R.id.task_detail_notifRadio);

        //We intialise the form with the data passed as an bundle argument to the fragment.
        Bundle bundle = getArguments();
        if(bundle != null)
            mTask = (Task) bundle.getSerializable("TASK_DETAIL"); //Intialize the task property with the one passed in the bundle

        //We initialise all the form input with the task that was passed as a bundle to the fragment
        if(mTask != null)
        {
            mTitle.setText(mTask.getTitre());
            mDescription.setText(mTask.getDescription());
            mType.setSelection(mTask.getType().getValue());
            mArchive.setChecked(mTask.isArchive());



            //Intialise the right radio button
            switch (mTask.getNotification()){
                case LeJour: mNotification.check(R.id.task_detail_notifInput1); break;
                case JourAvant: mNotification.check(R.id.task_detail_notifInput2); break;
                case SemaineAvant: mNotification.check(R.id.task_detail_notifInput3); break;
                case Aucune: mNotification.check(R.id.task_detail_notifInput4); break;
            }

            //Set the status bar to the right value
            switch(mTask.getStatut()){
                case NonComplété: mStatut.setProgress(0);break;
                case EnCours: mStatut.setProgress(1); break;
                case Complété: mStatut.setProgress(2); break;
            }

            //Initialization of the date input
            mDate = mTask.getDate();
            mDatePicker = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
                mDate.set(i,i1,i2);
                mPickDateBtn.setText(mDate.get(Calendar.YEAR)+"-"+mDate.get(Calendar.MONTH)+"-"+mDate.get(Calendar.DAY_OF_MONTH));
            }, mDate.get(Calendar.YEAR), mDate.get(Calendar.MONTH), mDate.get(Calendar.DAY_OF_MONTH));
            int Year = mDate.get(Calendar.YEAR);
            int Month = mDate.get(Calendar.MONTH);
            int Day = mDate.get(Calendar.DAY_OF_MONTH);
            mPickDateBtn.setText(Year + "-" + Month + "-" + Day);
        }

        return view;
    }

    /** Method called to save the information inside the taskLog
     */
    public void SubmitForm()
    {
        //Verification before submitting to make sure the task is not null, otherwise we cant submit the form.
        if(mTask == null)
            throw new NullPointerException();

        String title = mTitle.getText().toString();
        String description = mDescription.getText().toString();
        Task.Type type = Task.Type.valueOf(mType.getSelectedItemPosition());
        boolean archive = mArchive.isChecked();


        Task.Statut statut;
        switch(mStatut.getProgress()){
            case 1: statut = Task.Statut.EnCours; break;
            case 2: statut = Task.Statut.Complété; break;
            default: statut = Task.Statut.NonComplété; break;
        }
        Task.Notification notification;
        switch(mNotification.getCheckedRadioButtonId()){
            case R.id.task_detail_notifInput1: notification = Task.Notification.LeJour; break;
            case R.id.task_detail_notifInput2: notification = Task.Notification.JourAvant; break;
            case R.id.task_detail_notifInput3: notification = Task.Notification.SemaineAvant; break;
            default: notification = Task.Notification.Aucune; break;
        }

        boolean estOk = true;
        //Verification
        Calendar today = Calendar.getInstance();
        if(!Entre(title, 20,3))
            estOk = false;
        if(!Entre(description,150,10))
            estOk = false;
        if(!(mDate.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)) || mDate.after(today))
            estOk = false;
        if(!(title.charAt(0) == Character.toUpperCase(title.charAt(0))))
            estOk = false;

        if(estOk){
            mTaskLog = TaskLog.GetInstance();
            Task newTask = new Task(title, description, type,mDate,statut,archive,notification);
            if(mTaskLog.UpdateTask(mTask.getId() , newTask)){
                CloseForm();
                Toast.makeText(getContext(), "Modifications Enregistrées", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "La verification à échoué", Toast.LENGTH_SHORT).show();
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
