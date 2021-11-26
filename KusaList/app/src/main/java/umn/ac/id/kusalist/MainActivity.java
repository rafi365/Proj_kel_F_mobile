package umn.ac.id.kusalist;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference notesRef = database.getReference("notes");

    RecyclerView iniRv;
    NoteAdapter noteAdapter;
    ArrayList<Note> notes = new ArrayList<>();
    final Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("KusaList");

        iniRv = findViewById(R.id.iniRv);
        noteAdapter = new NoteAdapter(this, notes);
        iniRv.setAdapter(noteAdapter);
        iniRv.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
            View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
            AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
            alertDialogBuilderUserInput.setView(mView);

            final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.titleInputDialog);
            alertDialogBuilderUserInput
                    .setCancelable(false)
                    .setPositiveButton("ok", (dialogBox, id) -> {
                        if( userInputDialogEditText.getText().toString().isEmpty()){
                            Toast.makeText(c, "List title cannot be empty!", Toast.LENGTH_LONG).show();
                        } else{
                            String idNote = System.currentTimeMillis()+"";
                            Note note = new Note(
                                    idNote,
                                    userInputDialogEditText.getText().toString(),
                                    "",
                                    null,
                                    false
                            );
                            notesRef.child(idNote).setValue(note);
                        }
                    })

                    .setNegativeButton("Cancel",
                            (dialogBox, id) -> dialogBox.cancel()
                    );

            AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
            alertDialogAndroid.show();
        });

        notesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notes.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    notes.add(data.getValue(Note.class));
                }
                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}