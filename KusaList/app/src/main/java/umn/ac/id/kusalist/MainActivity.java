package umn.ac.id.kusalist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    RecyclerView iniRv;
    listAdapter rvAdapter;
    LinkedList<NoteArray> daftarNama = new LinkedList<>();
    final Context c = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("KusaList");

//        for(int i = 0 ; i < 10 ; i++){
//            daftarNama.add(new NoteArray("example"+ (i+1),"this is some text and stuff\n hellow world!!!\n number :"+(i+1)+"\n checkbox : "+((i % 2) == 0),null,(((i % 2) == 0))));
//        }

        iniRv = findViewById(R.id.iniRv);
        rvAdapter = new listAdapter(this, daftarNama);
        iniRv.setAdapter(rvAdapter);
        iniRv.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
                View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
                alertDialogBuilderUserInput.setView(mView);

                final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.titleInputDialog);
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                // ToDo get user input here
                                if( userInputDialogEditText.getText().toString().isEmpty()){
                                    Toast.makeText(c, "List title cannot be empty!", Toast.LENGTH_LONG).show();
                                }else{
                                    daftarNama.add(new NoteArray(userInputDialogEditText.getText().toString(),"this is some text and stuff\n hellow world!!!",null,false));
                                    rvAdapter.notifyDataSetChanged();
                                }
                            }
                        })

                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });
    }
    public void refresher(){
        iniRv.setAdapter(rvAdapter);
    }

}