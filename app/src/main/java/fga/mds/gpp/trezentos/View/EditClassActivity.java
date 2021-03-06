package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class EditClassActivity extends AppCompatActivity implements View.OnClickListener {

    private UserClass userClass;
    private TextView className;
    private TextView instituition;
    private TextView classPassword;
    private TextView cutGrade;
    private TextView groupsSize;
    private TextView addition;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);

        initToolbar();
        recoverLastIntent();
        initFields();
        initFillFields();

    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Informações Sobre a Sala");
    }

    private void recoverLastIntent(){
        Intent intent = getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");
    }

    private void initFields(){
        className = (TextView) findViewById(R.id.text_view_class_name_x);
        instituition = (TextView) findViewById(R.id.text_view_institution_x);
        classPassword = (TextView) findViewById(R.id.text_view_class_password_x);
        cutGrade = (TextView) findViewById(R.id.text_view_cut_grade_x);
        groupsSize = (TextView) findViewById(R.id.text_view_size_groups_x);
        addition = (TextView) findViewById(R.id.text_view_addition_x);
        backButton = (Button) findViewById(R.id.button_save);
        backButton.setOnClickListener(this);
    }

    private void initFillFields(){
        className.setText(userClass.getClassName());
        instituition.setText(userClass.getInstitution());
        classPassword.setText(userClass.getPassword());
        cutGrade.setText(String.valueOf(userClass.getCutOff()));
        groupsSize.setText(String.valueOf(userClass.getSizeGroups()));
        addition.setText(String.valueOf(userClass.getAddition()));
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}