package umn.ac.id.kusalist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    RecyclerView iniRv;
    listAdapter rvAdapter;
    LinkedList<NoteArray> daftarNama = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("KusaList");

        for(int i = 0 ; i < 10 ; i++){
            daftarNama.add(new NoteArray("example"+ (i+1),"this is some text and stuff\n hellow world!!!\n number :"+(i+1)+"\n checkbox : "+((i % 2) == 0),null,(((i % 2) == 0))));
        }

        iniRv = findViewById(R.id.iniRv);
        rvAdapter = new listAdapter(this, daftarNama);
        iniRv.setAdapter(rvAdapter);
        iniRv.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void refresher(){
        iniRv.setAdapter(rvAdapter);
    }

}