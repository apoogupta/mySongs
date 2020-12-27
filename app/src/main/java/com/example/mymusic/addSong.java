package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addSong extends AppCompatActivity {
public EditText title,desc,lyrics;
public DBhelper db;
Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        title=(EditText)findViewById(R.id.titleSong);
        desc=(EditText)findViewById(R.id.description);
        lyrics=(EditText)findViewById(R.id.lyrics);
        Save=(Button)findViewById(R.id.SaveSong);
        db=new DBhelper(this);
       Save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               insertDetails();

           }
       });

    }

    private void insertDetails() {

        String s1=title.getText().toString();
        String s2=desc.getText().toString();
        String s3=lyrics.getText().toString();
        if(db.insertSong(s1,s2,s3))
        {
            Toast.makeText(this, "done",
                    Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "not done",
                    Toast.LENGTH_SHORT).show();
        }

    }

}