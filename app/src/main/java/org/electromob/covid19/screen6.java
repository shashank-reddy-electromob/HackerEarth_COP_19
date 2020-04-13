package org.electromob.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class screen6 extends AppCompatActivity {

    private ImageView graph,call,chat,alert;
    private TextView title,cases;
    private String tittlename,activecases;
    private FirebaseStorage firebaseStorage;
    private StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen6);

        graph = findViewById(R.id.imageView3);
        title = findViewById(R.id.statestats);
        cases = findViewById(R.id.casesread);
        call = findViewById(R.id.call);
        chat = findViewById(R.id.chat1);
        alert = findViewById(R.id.emergency);

        Intent intent = getIntent();

        tittlename = intent.getStringExtra("name");
        activecases = intent.getStringExtra("cases");

        title.setText(tittlename + "'s  " +"Statistics");
        cases.setText(activecases);

        firebaseStorage = FirebaseStorage.getInstance();
        try {
            final StorageReference storageReference = firebaseStorage.getReference("Graphs");
            storageReference.child(tittlename + ".JPG")
                    .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(graph);
                }
            });
            graph.setVisibility(View.VISIBLE);
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Service not available right now",Toast.LENGTH_SHORT).show();
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Service not available right now",Toast.LENGTH_SHORT).show();
            }
        });

        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Service not available right now",Toast.LENGTH_SHORT).show();
            }
        });

    }
}

