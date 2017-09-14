package data.pojo;

/**
 * Created by Grzesiek on 2017-08-10.
 */

public class Note {

    private Integer id;
    private  String noteText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    @Override
    public String toString(){
        return noteText;
    }

}
