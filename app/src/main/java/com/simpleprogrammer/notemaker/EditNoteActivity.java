package com.simpleprogrammer.notemaker;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EditNoteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
            private boolean isInEditMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        final Button saveButton = (Button)findViewById(R.id.saveButton);
        final EditText titleEditText = (EditText)findViewById(R.id.titleEditText);
        final EditText noteEditText = (EditText)findViewById(R.id.noteEdittext);
        final TextView dateTextView = (TextView)findViewById(R.id.dateTextView);

        Serializable extra = getIntent().getSerializableExtra("Note");
        if(extra != null)
        {
            Note note = (Note)extra;
            titleEditText.setText(note.getTitle());
            noteEditText.setText(note.getNote());

           //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
           //String date = dateFormat.format(note.getDate());

            //dateTextView.setText(date);

        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveButton.setText("Clicked"); //just to see if SAVE Button is working or not

                //EditText dateEditText = (EditText)findViewById(R.id.dateEditText); //extra for learning and testing

                if(isInEditMode)
                {
                    //passing the note intent
                    isInEditMode = false;
                    saveButton.setText("Edit");
                    titleEditText.setEnabled(false);
                    noteEditText.setEnabled(false);

                    TextView dateTextView = (TextView)findViewById(R.id.dateTextView);
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String date = dateFormat.format(Calendar.getInstance().getTime());
                    dateTextView.setText(date);


                    Intent returnIntent = new Intent();
                    Note note = new Note(titleEditText.getText().toString(),
                            noteEditText.getText().toString(),
                            Calendar.getInstance().getTime());
                    returnIntent.putExtra("Note", note);
                    setResult(RESULT_OK, returnIntent);
                    finish();

                }
                else
                {
                    isInEditMode = true;
                    saveButton.setText("Save");
                    titleEditText.setEnabled(true);
                    noteEditText.setEnabled(true);

                }
                //dateEditText.setEnabled(false); //extra for learning and testing
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

       // } else if (id == R.id.nav_share) {

        //} else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
