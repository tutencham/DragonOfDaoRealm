package com.example.android.dragonofdaorealm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

import data.db.NoteDao;
import data.pojo.Note;

public class MainActivity extends AppCompatActivity {
    private EditText newNoteText;
    private RecyclerView recyclerView;
    private NoteDao noteDao;
    private NoteListAdapter myAdapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicjalizujemy pola klasy
        newNoteText = (EditText) findViewById(R.id.new_note_text);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        // tworzymy obiekt DAO
        noteDao = new NoteDao(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // wyświetlamy listę notatek
        reloadNotesList();
        myAdapter = new NoteListAdapter(noteDao.getAllNotes(), this);

        recyclerView.setAdapter(myAdapter);

    }


    @Override
    protected void onDestroy() {
        // zamykamy instancję Realma
        noteDao.close();
        super.onDestroy();
    }

    // dodaje nową notatkę do bazy danych i odświeża listę
    public void addNewNote(View view) {
        Note note = new Note();
        String text = newNoteText.getText().toString();

        newNoteText.getText().clear();

        String strext = text;
        if (strext.length() > 0) {

            note.setNoteText(text);
            noteDao.insertNote(note);
            reloadNotesList();
        } else {

            Toast.makeText(MainActivity.this,
                    "write sth", Toast.LENGTH_LONG).show();

        }
        {
            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            );
        }

        // wywołanie metody insertNote() jest takie jak w poprzedniej lekcji,
        // gdzie korzystaliśmy z SQLite
    }

    // usuwa notatkę z bazy i odświeża listę
    public void removeNote(Note note) {
        myAdapter = new NoteListAdapter(noteDao.getAllNotes(),this);
        recyclerView.setAdapter(myAdapter);
        noteDao.deleteNoteById(note.getId());


    }

    // pokazuje listę notatek
    private void reloadNotesList() {
        // pobieramy z bazy danych listę notatek

        myAdapter = new NoteListAdapter(noteDao.getAllNotes(), this);
        recyclerView.setAdapter(myAdapter);


    }

    // usuwa wszystkie notatki z bazy
    public void deleteAll(View view) {
        noteDao.deleteAllNotes();
        reloadNotesList();
    }

    public void deleteNullAll(View view) {
        noteDao.deleteNullAllNotes();
        reloadNotesList();
    }

    public void filterNotes(View view) {
        // pobieramy z bazy danych listę notatek z frazą "filtered"

        myAdapter = new NoteListAdapter(noteDao.getNotesLike("filtered"), this);
        recyclerView.setAdapter(myAdapter);

        // pobiera wyłącznie notatki, które zawierają frazę "filtered"
        // i wyświetla je na ekranie


        // pokazuje listę obiektów typu NoteRealm

    }
}
