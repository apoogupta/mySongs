package com.example.mymusic;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Show_Lyrics extends AppCompatActivity {
    public DBhelper db;
    public  TextView showlyrics,description;
    public Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_lyrics);
        Bundle extras = getIntent().getExtras();
        String title=extras.getString("title");
        db=new DBhelper(this);
        Cursor cursor=db.ShowLyrics(title);
        showlyrics=(TextView)findViewById(R.id.tv);
        description=(TextView)findViewById(R.id.desc);
        delete=(Button)findViewById(R.id.delete);

        if(cursor.getCount()==0)
            Toast.makeText(this,"no data to show",Toast.LENGTH_SHORT).show();
        else
        {
           cursor.moveToFirst();
          showlyrics.setText(cursor.getString(2));
          description.setText(cursor.getString(1));



            }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean deleted= db.DeleteSong(title);
               if(deleted)
               {
                   Intent intent=new Intent(Show_Lyrics.this,ShowSongNames.class);
                   Toast.makeText(Show_Lyrics.this,"DELETED",Toast.LENGTH_SHORT).show();
                   startActivity(intent);

               }
               else
               {
                   Toast.makeText(Show_Lyrics.this,"not deleted",Toast.LENGTH_SHORT).show();

               }
            }
        });


        }






}
