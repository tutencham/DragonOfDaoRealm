package data.db;

import data.pojo.Note;
import data.realm.NoteRealm;

/**
 * Created by Grzesiek on 2017-08-10.
 */

public class NoteMapper {

    Note  fromRealm(NoteRealm noteRealm){

        Note note = new Note();
        note.setId(noteRealm.getId());
        note.setNoteText(noteRealm.getNoteText());
        return note;
    }
}
