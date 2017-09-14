package data.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Grzesiek on 2017-08-10.
 */

public class NoteRealm extends RealmObject {

    @PrimaryKey
    private int id;

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    private String noteText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
