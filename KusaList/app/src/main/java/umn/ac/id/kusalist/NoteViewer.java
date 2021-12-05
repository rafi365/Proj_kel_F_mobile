package umn.ac.id.kusalist;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoteViewer extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    DatabaseReference notesRef = database.getReference("users/"+user.getUid()+"/notes");
    EditText etTitle;
    EditText etBody;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_viewer);

        Bundle b = getIntent().getExtras();
        Note note = b.getParcelable("note");

        etTitle = findViewById(R.id.et_title);
        etBody = findViewById(R.id.et_body);
        saveButton = findViewById(R.id.bt_save);
        etTitle.setText(note.getTitle());

        etBody.setText(note.getBody());
        getSupportActionBar().setTitle(note.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveButton.setOnClickListener(view -> {
            note.setTitle(etTitle.getText().toString());
            note.setBody(etBody.getText().toString());
            notesRef.child(note.getId()).setValue(note);
            finish();
        });
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