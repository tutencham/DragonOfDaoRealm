package data.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import data.pojo.Note;
import data.realm.NoteRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Grzesiek on 2017-08-10.
 */

public class NoteDao {


    private Realm realm;
    private RealmConfiguration realmConfig;


    public NoteDao(Context context){
        realmConfig= new RealmConfiguration.Builder(context).build();
        realm = Realm.getInstance(realmConfig);


    }

    public void close(){
        realm.close();
    }

    public void insertNote(final Note note){
        realm.beginTransaction();

        NoteRealm noteRealm =realm.createObject(NoteRealm.class);
        noteRealm.setId(generateId());
        noteRealm.setNoteText(note.getNoteText());

        realm.commitTransaction();
    }

    public Note getNotebyId(final int id){
        NoteRealm noteRealm =realm.where(NoteRealm.class).equalTo("id",id).findFirst();
        return new NoteMapper().fromRealm(noteRealm);

    }
    public void updateNote(final Note note){
        NoteRealm noteRealm =realm.where(NoteRealm.class).equalTo("id",note.getId()).findFirst();
        realm.beginTransaction();
        noteRealm.setNoteText(note.getNoteText());
        realm.commitTransaction();
    }

    public void deleteNoteById(final long id){
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm){
                realm.where(NoteRealm.class).equalTo("id", id).findFirst().deleteFromRealm();
            }




        });
    }
    public List<Note> getAllNotes(){
        List<Note> notes = new ArrayList<>();
        NoteMapper mapper = new NoteMapper();
        RealmResults<NoteRealm> all = realm.where(NoteRealm.class).findAll();
        for (NoteRealm noteRealm :all){
            notes.add(mapper.fromRealm(noteRealm));
        }
        return notes;
    }

    public List<Note> getNotesLike(String text) {
        List<Note> notes = new ArrayList<>();
        NoteMapper mapper = new NoteMapper();


        RealmResults<NoteRealm> all = realm.where(NoteRealm.class)
                .contains("noteText", text)
                .findAll();

        for (NoteRealm noteRealm : all) {
            notes.add(mapper.fromRealm(noteRealm));


        }
        return notes;
    }
    public List<NoteRealm> getRawNotes(){
        return realm.where(NoteRealm.class).findAllSorted("noteText");

    }


    public void deleteAllNotes() {
        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                realm.where(NoteRealm.class)
                        .findAll()
                        .deleteAllFromRealm();
            }

        });
    }
    public void deleteNullAllNotes(){
        realm.executeTransaction(new Realm.Transaction(){

            @Override
            public void execute(Realm realm){
                realm.where(NoteRealm.class)
                        .isNull("noteText")
                        .findAll()
                        .deleteAllFromRealm();
            }

        });



    }
    private int generateId() {

        return realm.where(NoteRealm.class).max("id").intValue() +1;
    }


}
