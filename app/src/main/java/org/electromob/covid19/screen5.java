package org.electromob.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class screen5 extends AppCompatActivity {

    private Button logout;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private int score=0;
    private String name123,weight123,place123,height123,number123,latitude,longitude;
    private TextView name,weight,height,place,number,risk,remedies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen5);

        logout = findViewById(R.id.logout);
        name = findViewById(R.id.nameread);
        weight = findViewById(R.id.weightread);
        height = findViewById(R.id.heightread);
        place = findViewById(R.id.placeread);
        number = findViewById(R.id.numberread);
        risk = findViewById(R.id.heading5);
        remedies = findViewById(R.id.heading3);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Users").child(mAuth.getUid());
        reference.keepSynced(true);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    name123 = dataSnapshot.child("name12").getValue().toString();
                    weight123 = dataSnapshot.child("weight12").getValue().toString();
                    height123 = dataSnapshot.child("height12").getValue().toString();
                    number123 = dataSnapshot.child("phone_number12").getValue().toString();
                    place123 = dataSnapshot.child("place12").getValue().toString();
                    score = Integer.valueOf(dataSnapshot.child("score12").getValue().toString());
                    latitude = dataSnapshot.child("lattitude12").getValue().toString();
                    longitude = dataSnapshot.child("longitude12").getValue().toString();
                }
                if (score == 0){
                    risk.setText("You have LOW risk of being effected with Covid-19");
                    remedies.setText("-> Regularly clean your hands thoroughly with an alcohol based hand rub or with soap and water.\n-> Maintain social distance.\n-> Avoid touching eyes nose and mouth with your hands.\n-> Keep uptodate on the covid-19 hotspots and avoid travelling in those places.");
                } else if (score >= 1 && score <= 12){
                    risk.setText("You have MEDIUM risk of being effected with Covid-19");
                    remedies.setText("-> Take Proper rest and have a good sleep.\n-> Keep yourself warm.\n-> Drink plenty of liquids.\n-> Use a room humidifier or take a hot shower to help the ease of sore throat and cough.");
                } else if (score >= 13 && score <= 48){
                    risk.setText("You have HIGH risk of being effected with Covid-19");
                    remedies.setText("Officials will contact you soon!!!\nLatitude:" + latitude +"\nLongitiude: "+ longitude  + "\nAnd maintain social distancing with family by staying in a seperate room.");
                }

                name.setText(name123);
                weight.setText(weight123);
                height.setText(height123);
                number.setText(number123);
                place.setText(place123);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),screen1.class));
                finish();
            }
        });

    }
}