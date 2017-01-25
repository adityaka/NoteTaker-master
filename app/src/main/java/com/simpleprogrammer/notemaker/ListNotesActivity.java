package com.simpleprogrammer.notemaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListNotesActivity extends AppCompatActivity {
    private List<Note> notes = new ArrayList<Note>();
    private ListView notesListView;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Serializable extra = data.getSerializableExtra("Note");
        if(extra != null)
        {
            Note newNote = (Note)extra;
            notes.add(newNote);
            populatelist();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        notesListView = (ListView)findViewById(R.id.notesListView);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemNumber,
                                    long id) {
                Intent editNoteIntent = new Intent(view.getContext(), EditNoteActivity.class);
                editNoteIntent.putExtra("Note", notes.get(itemNumber));
                startActivity(editNoteIntent);
            }
        });

        notes.add(new Note("First Note", "Blah blah", new Date()));
        notes.add(new Note("Second Note", "Blah blah", new Date()));
        notes.add(new Note("Third Note", "Blah blah", new Date()));
        notes.add(new Note("Fourth Note", "Blah blah", new Date()));
        notes.add(new Note("Fifth Note", "Blah blah", new Date()));
        //notes.add(new Note("Sixth Note", "Blah blah", new Date()));
        //notes.add(new Note("Seventh Note", "Blah blah", new Date()));

        populatelist();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action  bar if it is present.

        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    //TO MAKE THE ADD_NOTE WORK (ONOPTIONS ITEM)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //notes.add(new Note("Added Note", "Blah", new Date()));
        //populatelist();

        Intent editNoteIntent = new Intent(this, EditNoteActivity.class);
        //startActivity(editNoteIntent);
        startActivityForResult(editNoteIntent, 1);
        return true;
    }

    private void populatelist() {

        List<String> values = new ArrayList<String>();

        for(Note note : notes)
        {
            values.add(note.getTitle());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        notesListView.setAdapter(adapter);
    }
}