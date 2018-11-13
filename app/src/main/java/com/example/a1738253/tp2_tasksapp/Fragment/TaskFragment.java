package com.example.a1738253.tp2_tasksapp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.a1738253.tp2_tasksapp.Model.Task;

import java.util.UUID;

public class TaskFragment extends Fragment{

    private  static  final String ARG_TASK_ID = "task_id";
    private String[] elements = new String[]{"École", "Travail", "Personnel", "Autre"};
    private  static final String DIALOG_DATE = "DialogTag";

    private static final int REQUEST_DATE = 0;

    private ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.new_task_fragment, elements);

    private Task mTask;
    private EditText mTitre;
    private EditText mDescription;
    private TextView mTypeTV;
    private Spinner mType;
    private SeekBar mStatut;
    private RadioGroup mNotif;
    private Button mDateButton;
    private Switch mArchive;


    public  static TaskFragment newInstance(UUID taskId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, taskId);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);

        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_task_fragment, container, false);

        mTitre = (EditText) v.findViewById(R.id.task_title);
        mTitre.setText(mTask.getTitre());
        mTitre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mTask.setTitre(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDescription = (EditText) v.findViewById(R.id.task_description);
        mDescription.setText(mTask.getDescription());
        mDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mTask.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mTypeTV = (TextView) v.findViewById(R.id.text_view);
        mType = (Spinner) v.findViewById(R.id.spinner);
        mType.setAdapter(adapter);

        mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                mTypeTV.setText("Spinner selected : " + parent.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }
}
