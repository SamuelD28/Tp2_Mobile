package com.example.a1738253.tp2_tasksapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.a1738253.tp2_tasksapp.Model.Task;
import com.example.a1738253.tp2_tasksapp.R;

public class TaskCreateActivity extends AppCompatActivity {

    private  static  final String ARG_TASK_ID = "task_id";
    private String[] elements = new String[]{"École", "Travail", "Personnel", "Autre"};
    private  static final String DIALOG_DATE = "DialogTag";
    private static final int REQUEST_DATE = 0;
//    private ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.fragment_task_create, elements);  Cause un bug quand on essaie douvrir lactivity
    private Task mTask;
    private EditText mTitre;
    private EditText mDescription;
    private TextView mTypeTV;
    private Spinner mType;
    private SeekBar mStatut;
    private RadioGroup mNotif;
    private Button mDateButton;
    private Switch mArchive;


    private ImageButton mCloseBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_task_create);
        Intent intent = getIntent();

        mCloseBtn = findViewById(R.id.task_create_closeBtn);
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mTitre = (EditText) findViewById(R.id.task_create_titleInput);
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

        mDescription = (EditText) findViewById(R.id.task_create_descInput);
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

//        mTypeTV = (TextView) findViewById(R.id.text_view);
        mType = (Spinner) findViewById(R.id.task_create_categorieInput);
        mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
//                mTypeTV.setText("Spinner selected : " + parent.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
