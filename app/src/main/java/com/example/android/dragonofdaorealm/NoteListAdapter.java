package com.example.android.dragonofdaorealm;


import android.content.Context;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.socks.library.KLog;

import java.util.List;


import data.db.NoteDao;

import data.pojo.Note;
import data.realm.NoteRealm;


import static android.R.attr.id;


/**
 * Created by Grzesiek on 2017-09-01.
 */

public class NoteListAdapter extends RecyclerView.Adapter {




    public NoteListAdapter(List<Note> noteList,Context contextview) {
        this.noteList = noteList;
        this.contextview = contextview;



    }
    public NoteListAdapter(Context contextview,List<NoteRealm> notesRealm) {

        this.contextview = contextview;
        this.notesRealm= notesRealm;


    }
    private List<NoteRealm> notesRealm;
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



        NoteDao.getInstance(contextview).deleteNoteById(noteList.get(position).getId());

        noteList.remove(position);
        notifyItemRemoved(position);
        noteList.notifyAll();

        //NoteDao.getInstance(contextview).getAllNotes();






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
        switch (holder.getItemViewType()) {

            case 0:
            NoteListHolder myHolder = (NoteListHolder) holder;


            myHolder.noteText.setText(noteList.get(position).getNoteText());
            break;
            case 1:
                NoteListHolder myHolderSec = (NoteListHolder) holder;
              myHolderSec.noteText.setText(notesRealm.get(position).getNoteText());


        }



    }






    @Override
    public int getItemCount() {
        KLog.e(id);


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
            AlertDialog.Builder builder1 = new AlertDialog.Builder(contextview);
            builder1.setMessage("Do You really want to delete this note ?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Toast.makeText(contextview, "You deleted note from position: "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                            delete(getAdapterPosition());


                        }
                    });



            AlertDialog alert11 = builder1.create();
            alert11.show();
            KLog.e("cos sie zjebalo");



        }



    }



}
