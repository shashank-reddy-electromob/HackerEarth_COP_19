package org.electromob.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class screen3 extends AppCompatActivity {

    private CardView telangana,andhra,tamilnadu,maharasthra,delhi;
    private Button predict;
    private ImageView chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        telangana = findViewById(R.id.telangana);
        andhra = findViewById(R.id.andhra);
        tamilnadu = findViewById(R.id.tamilnadu);
        maharasthra = findViewById(R.id.maharashtra);
        delhi = findViewById(R.id.delhi);
        predict = findViewById(R.id.predict);
        chat = findViewById(R.id.chat);

        telangana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Telangana",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),screen6.class);
                intent.putExtra("name","Telangana");
                intent.putExtra("cases","473");
                startActivity(intent);
            }
        });

        andhra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),screen6.class);
                intent.putExtra("name","Andhra Pradesh");
                intent.putExtra("cases","363");
                startActivity(intent);
            }
        });

        tamilnadu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),screen6.class);
                intent.putExtra("name","Tamil Nadu");
                intent.putExtra("cases","834");
                startActivity(intent);
            }
        });

        maharasthra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),screen6.class);
                intent.putExtra("name","Maharashtra");
                intent.putExtra("cases","1364");
                startActivity(intent);
            }
        });

        delhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),screen6.class);
                intent.putExtra("name","Delhi");
                intent.putExtra("cases","898");
                startActivity(intent);
            }
        });

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Machine learning model is under development please retry later",Toast.LENGTH_LONG).show();
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"All our Development members are out of reach..",Toast.LENGTH_LONG).show();
            }
        });

    }
}
