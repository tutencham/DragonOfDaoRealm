package com.example.android.dragonofdaorealm;


import android.content.Context;
import android.support.v7.widget.PagerSnapHelper;
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

/**
 * Created by Grzesiek on 2017-09-01.
 */

public class NoteListAdapter extends RecyclerView.Adapter {

    private List<Note> noteList;
    private Context contextview;









    public NoteListAdapter(List<Note> noteList, Context contextview) {
        this.noteList = noteList;
        this.contextview= contextview;


    }




    @Override
    public RecyclerView.ViewHolder


    onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(contextview);
        View view = inflater.inflate(R.layout.note_layout,parent, false );
        NoteListHolder noteListHolder = new NoteListHolder(view);
        return noteListHolder;
    }
    public void delete (int position){
        noteList.remove(position);
        notifyItemRemoved(position);
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
