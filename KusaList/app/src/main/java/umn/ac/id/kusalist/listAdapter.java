package umn.ac.id.kusalist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
public class listAdapter extends RecyclerView.Adapter<listAdapter.IniViewHolder> {
    private LayoutInflater inflater;
    private LinkedList<NoteArray> daftarNama;
    private Context mContext;


    listAdapter(Context context, LinkedList<NoteArray> list){
        inflater = LayoutInflater.from(context);
        daftarNama = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public IniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.itemtemplate, parent, false);
        return new IniViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull IniViewHolder holder, int position) {
        holder.tvRv.setText(daftarNama.get(position).getTitle());
        holder.chkbox.setChecked(daftarNama.get(position).isCheckbox());
    }

    @Override
    public int getItemCount() {
        return daftarNama.size();
    }
    public class IniViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvRv;
        listAdapter iniAdapter;
        Button test;
        CheckBox chkbox;

        private int mPosisi;
        private NoteArray mSumberAudio; //PLACEHOLDER ARRAY, NOT FOR FINAL USE!
        public IniViewHolder(@NonNull View itemView, listAdapter adapter) {
            super(itemView);
            tvRv = itemView.findViewById(R.id.tvRv);
            iniAdapter = adapter;
            test = itemView.findViewById(R.id.button3);
            chkbox = itemView.findViewById(R.id.checkBox);
            test.setOnClickListener(view -> {
                String testc = daftarNama.get(mPosisi).getTitle();
                Toast.makeText(mContext, testc, Toast.LENGTH_LONG).show();
                daftarNama.remove(mPosisi);//remove the item from list
                notifyItemRemoved(mPosisi);//refresh recyclerview
            });
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            mPosisi = getLayoutPosition();
            mSumberAudio = daftarNama.get(mPosisi);
            Intent intentSatu = new Intent(mContext, NoteViewer.class);
            intentSatu.putExtra("extras", mSumberAudio);
            mContext.startActivity(intentSatu);
        }
    }
}