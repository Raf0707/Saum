package raf.tabiin.saum.domain.models;

public class KaffaratDay {
    private String day;
    private boolean isChecked;

    public KaffaratDay(String day, boolean isChecked) {
        this.day = day;
        this.isChecked = isChecked;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
