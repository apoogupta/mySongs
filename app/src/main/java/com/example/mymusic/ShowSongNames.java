package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class ShowSongNames extends AppCompatActivity {

    public DBhelper db;
    ArrayList<String> listitem;
    ArrayAdapter adapter;
    ListView userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song_names);
        db=new DBhelper(this);
        listitem=new ArrayList<>();
        ShowSongs();
        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
              //  Toast.makeText(ShowSongNames.this,selectedItem, LENGTH_SHORT).show();
               Intent intent =new Intent(ShowSongNames.this,Show_Lyrics.class);
                intent.putExtra("title",selectedItem);
                startActivity(intent);

            }
        });

    }

    private void ShowSongs() {
        userlist=(ListView)findViewById(R.id.lv);
        Cursor cursor=db.ShowSongNames();
        if(cursor.getCount()==0)
            Toast.makeText(this,"no data to show", LENGTH_SHORT).show();
        else
        {
            while(cursor.moveToNext()){
                listitem.add(cursor.getString(0));



            }

            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listitem);
            userlist.setAdapter(adapter);
        }
    }





}