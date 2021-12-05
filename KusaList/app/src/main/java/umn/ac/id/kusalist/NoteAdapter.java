package umn.ac.id.kusalist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.IniViewHolder> {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    DatabaseReference notesRef = database.getReference("users/"+user.getUid()+"/notes");
    private LayoutInflater inflater;
    private ArrayList<Note> daftarNama;
    private Context mContext;


    NoteAdapter(Context context, ArrayList<Note> list){
        inflater = LayoutInflater.from(context);
        daftarNama = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public IniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_note, parent, false);
        return new IniViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull IniViewHolder holder, int position) {
        holder.tvRv.setText(daftarNama.get(position).getTitle());
        holder.checkbtn.setChecked(daftarNama.get(position).isCheckbox());
    }

    @Override
    public int getItemCount() {
        return daftarNama.size();
    }
    public class IniViewHolder extends RecyclerView.ViewHolder {
        TextView tvRv;
        ImageButton btRemove;
        NoteAdapter iniAdapter;
        CheckBox checkbtn;

        public IniViewHolder(@NonNull View itemView, NoteAdapter adapter) {
            super(itemView);
            tvRv = itemView.findViewById(R.id.tv_title);
            btRemove = itemView.findViewById(R.id.bt_remove);
            iniAdapter = adapter;
            checkbtn = itemView.findViewById(R.id.checkBox);

            itemView.setOnClickListener(view -> {
                Intent intentSatu = new Intent(mContext, NoteViewer.class);
                intentSatu.putExtra("note", daftarNama.get(getAdapterPosition()));
                mContext.startActivity(intentSatu);
            });

            btRemove.setOnClickListener(view -> {
                notesRef.child(daftarNama.get(getAdapterPosition()).getId()).removeValue();
                Toast.makeText(mContext,"Data berhasil dihapus",Toast.LENGTH_SHORT).show();
            });
            checkbtn.setOnClickListener(view -> {
                Note temp = daftarNama.get(getAdapterPosition());
                temp.setCheckbox(checkbtn.isChecked());
                notesRef.child(daftarNama.get(getAdapterPosition()).getId()).setValue(temp);
            });
        }
    }
}