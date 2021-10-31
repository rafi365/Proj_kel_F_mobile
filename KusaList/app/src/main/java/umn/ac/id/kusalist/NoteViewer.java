package umn.ac.id.kusalist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class NoteViewer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_viewer);
        Intent intent = getIntent();
        EditText title;
        EditText bodytext;
        String nama = intent.getStringExtra("extras");

        title = (EditText) findViewById(R.id.Title);
        bodytext = (EditText) findViewById(R.id.BodyText);
        title.setText(nama);

        Toast.makeText(this, title.getText().toString(), Toast.LENGTH_LONG).show();
        bodytext.setText("EXAMPLE TEXT \nTESTING MULTILINES");
        getSupportActionBar().setTitle(nama);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        //save stuff to db here
    }
}