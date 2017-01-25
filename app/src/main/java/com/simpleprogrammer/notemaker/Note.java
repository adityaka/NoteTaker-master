package com.simpleprogrammer.notemaker;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by siddharthak on 19-01-2017.
 */

public class Note implements Serializable  {
    public Note(String title, String note, Date date) {
        super();
        this.title = title;
        this.note = note;
        this.date = date;

    }
    private String title;
    private String note;
    private Date date;
    public String getTitle() {
            return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date){
        this.date = date;
    }

}
