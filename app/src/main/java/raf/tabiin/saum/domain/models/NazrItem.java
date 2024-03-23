package raf.tabiin.saum.domain.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "nazr")
public class NazrItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "titleNazr")
    public String titleNazr;

    @ColumnInfo(name = "textNazr")
    public String textNazr;

    @ColumnInfo(name = "day1")
    public boolean day1;

    @ColumnInfo(name = "day2")
    public boolean day2;

    @ColumnInfo(name = "day3")
    public boolean day3;

    @ColumnInfo(name = "progress")
    public int progress;

    @ColumnInfo(name = "completed")
    public boolean completed;

    //@Ignore
    public NazrItem(String titleNazr, String textNazr) {
        this.titleNazr = titleNazr;
        this.textNazr = textNazr;
    }

    public NazrItem(int id, String titleNazr, String textNazr, int progress, boolean done) {
        this.titleNazr = titleNazr;
        this.textNazr = textNazr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleNazr() {
        return titleNazr;
    }

    public void setTitleNazr(String titleNazr) {
        this.titleNazr = titleNazr;
    }

    public String getTextNazr() {
        return textNazr;
    }

    public void setTextNazr(String textNazr) {
        this.textNazr = textNazr;
    }

    public boolean isDay1() {
        return day1;
    }

    public void setDay1(boolean day1) {
        this.day1 = day1;
    }

    public boolean isDay2() {
        return day2;
    }

    public void setDay2(boolean day2) {
        this.day2 = day2;
    }

    public boolean isDay3() {
        return day3;
    }

    public void setDay3(boolean day3) {
        this.day3 = day3;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
