package com.example.medappjam;

//Input date based on day of the year rather than day of the month
public class InputDate {
    private int day;
    private int year;

    public InputDate(int day, int year) {
        this.day = day;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
