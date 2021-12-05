package umn.ac.id.kusalist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference notesRef;
    FirebaseAuth mAuth;
    RecyclerView iniRv;
    NoteAdapter noteAdapter;
    ArrayList<Note> notes = new ArrayList<>();
    final Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        else
        {
            notesRef = database.getReference("users/"+user.getUid()+"/notes");

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

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}