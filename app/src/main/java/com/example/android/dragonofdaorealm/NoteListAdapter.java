package com.example.android.dragonofdaorealm;


import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.socks.library.KLog;

import java.util.List;


import data.db.NoteDao;
import data.db.Test;
import data.pojo.Note;
import data.realm.NoteRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Grzesiek on 2017-09-01.
 */

public class NoteListAdapter extends RecyclerView.Adapter {

    public NoteListAdapter(List<Note> noteList, Context contextview) {
        this.noteList = noteList;
        this.contextview = contextview;

    }

    private List<Note> noteList;
    private Context contextview;














    @Override
    public RecyclerView.ViewHolder


    onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(contextview);
        View view = inflater.inflate(R.layout.note_layout,parent, false );
        NoteListHolder noteListHolder = new NoteListHolder(view);

        return noteListHolder;
    }
    public void delete (final int position){



        NoteDao.getInstance(contextview).deleteNoteById(position);







//        RealmConfiguration realmConfig= new RealmConfiguration.Builder(contextview).build();
//        Realm.getInstance(realmConfig).executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                NoteRealm noteRealm =realm.where(NoteRealm.class).equalTo("id", noteList.get(position).getId()).findFirst();
//                noteRealm.deleteFromRealm();
//            }
//        });
//        noteList.remove(position);
//        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,  int position) {


        NoteListHolder myHolder = (NoteListHolder) holder;

       myHolder.noteText.setText(noteList.get(position).getNoteText());







    }






    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


         TextView noteText;


        public NoteListHolder(View itemView) {
            super(itemView);
           noteText= (TextView) itemView.findViewById(R.id.note_layout);
            noteText.setOnClickListener(this);


        }




        @Override
        public void onClick(View v) {
            KLog.e("cos sie zjebalo");
            Toast.makeText(contextview, "Wyjebales cos "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
            delete(getAdapterPosition());


        }
    }



}
