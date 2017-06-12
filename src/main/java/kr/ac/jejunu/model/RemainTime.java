package kr.ac.jejunu.model;

/**
 * Created by Boobby on 17-6-11.
 */
public class RemainTime {
    private Integer year;
    private Integer month;
    private Integer date;
    private Integer hour;
    private Integer minute;

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDate() {
        return date;
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public RemainTime(Integer year, Integer month, Integer date, Integer hour, Integer minute) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {
        String string = "";

        if (year > 0) string = string + year + "y ";
        if (month > 0) string = string + month + "M ";
        if (date > 0) string = string + date + "d ";
        if (hour > 0) string = string + hour + "H ";
        if (minute > 0) string = string + minute + "m";

        return string;
    }
}
