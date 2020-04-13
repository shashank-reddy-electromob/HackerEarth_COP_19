package org.electromob.covid19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class screen2 extends AppCompatActivity {

    private Button submit;
    private Integer score = 0;
    private String name,phone,lattitude,longitude,place,age1,gender1,caugh,cold,fever,breath,cj,travel,height1,weight1,score1;
    private EditText Living_place,age,gender,height,weight;
    private RadioButton caugh1,cough2,cough3;
    private RadioButton cold1,cold2,cold3;
    private RadioButton fever1,fever2;
    private RadioButton breath1,breath2,breath3;
    private RadioButton cj1,cj2;
    private RadioButton travel1,travel2,travel3;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(screen2.this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    123);

        }

        submit = findViewById(R.id.submit);
        Living_place = findViewById(R.id.editText4);
        age = findViewById(R.id.editText);
        gender = findViewById(R.id.editText1);
        caugh1 = findViewById(R.id.checkBox);
        cough2 = findViewById(R.id.checkBox1);
        cough3 = findViewById(R.id.checkBox2);
        cold1 = findViewById(R.id.checkBox3);
        cold2 = findViewById(R.id.checkBox4);
        cold3 = findViewById(R.id.checkBox5);
        fever1 = findViewById(R.id.checkBox6);
        fever2 = findViewById(R.id.checkBox7);
        breath1 = findViewById(R.id.checkBox8);
        breath2 = findViewById(R.id.checkBox9);
        breath3 = findViewById(R.id.checkBox10);
        cj1 = findViewById(R.id.checkBox11);
        cj2 = findViewById(R.id.checkBox12);
        travel1 = findViewById(R.id.checkBox13);
        travel2 = findViewById(R.id.checkBox14);
        travel3 = findViewById(R.id.checkBox15);
        height = findViewById(R.id.edheight);
        weight = findViewById(R.id.edweight);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Users");
        user = mAuth.getCurrentUser();

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("number");
        lattitude = intent.getStringExtra("lattitude");
        longitude = intent.getStringExtra("longitude");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                place = Living_place.getText().toString();
                age1 = age.getText().toString();
                gender1 = gender.getText().toString();
                height1 = height.getText().toString();
                weight1 = weight.getText().toString();

                if (caugh1.isChecked()){caugh = "Mild"; score = score + 3;
                } else if (cough2.isChecked()){caugh = "Severe"; score = score + 7;
                } else if (cough3.isChecked()){caugh = "None"; score = score + 0;
                } else {caugh = " ";}

                if (cold1.isChecked()){cold = "Mild"; score = score + 3;
                } else if (cold2.isChecked()){cold = "Severe"; score = score + 7;
                } else if (cold3.isChecked()){cold = "None"; score = score + 0;
                } else {cold = " ";}

                if (fever1.isChecked()){fever = "Normal"; score = score + 0;
                } else if (fever2.isChecked()){fever = "High"; score = score + 10;
                } else {fever = " ";}

                if (breath1.isChecked()){breath = "Mild"; score = score + 3;
                } else if (breath2.isChecked()){breath = "Severe"; score = score + 7;
                } else if (breath3.isChecked()){breath = "None"; score = score + 0;
                } else {breath = " ";}

                if (cj1.isChecked()){cj = "Yes"; score = score + 10;
                } else if (cj2.isChecked()){cj = "No"; score = score + 0;
                } else {cj = " ";}

                if (travel1.isChecked()){travel = "International"; score = score + 7;
                } else if (travel2.isChecked()){travel = "Interstate"; score = score + 3;
                } else if (travel3.isChecked()){travel = "None"; score = score + 0;
                } else {travel = " ";}

                score1 = String.valueOf(score);

                reference = reference.child(mAuth.getUid());

                if(!place.isEmpty() && !age1.isEmpty() && !gender1.isEmpty() && !caugh.isEmpty() && !cold.isEmpty() && !fever.isEmpty() && !breath.isEmpty() && !cj.isEmpty() && !travel.isEmpty() && !height1.isEmpty() && !weight1.isEmpty())
                {
                    UserInfo userInfo = new UserInfo(name,phone,lattitude,longitude,place,age1,gender1,caugh,cold,fever,breath,cj,travel,height1,weight1,score1);
                    reference.setValue(userInfo);
                    Toast.makeText(getApplicationContext(),"Data taken...",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),screen2_1.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}



