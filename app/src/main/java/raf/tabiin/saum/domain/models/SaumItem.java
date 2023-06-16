package raf.tabiin.saum.domain.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "saums")
public class SaumItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "day")
    public String day;

    @ColumnInfo(name = "month")
    public String month;

    @ColumnInfo(name = "progress")
    public int progress;

    @ColumnInfo(name = "completed")
    public boolean completed;


    @Ignore
    public SaumItem(int id, String day, String month, int progress, boolean completed) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.progress = progress;
        this.completed = completed;
    }



    public SaumItem(String day, String month, int progress, boolean completed) {
        this.day = day;
        this.month = month;
        this.progress = progress;
        this.completed = completed;
    }





    /*
    public SaumItem(String day, String month) {
        this.day = day;
        this.month = month;
    }

     */

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = String.valueOf(month);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "CounterItem{" +
                "id=" + id +
                ", title='" + day + '\'' +
                ", target=" + month +
                ", progress=" + progress +
                ", completed=" + completed +
                '}';
    }
}
