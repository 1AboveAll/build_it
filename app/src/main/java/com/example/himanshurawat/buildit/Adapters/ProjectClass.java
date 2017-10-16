package com.example.himanshurawat.buildit.Adapters;

import com.example.himanshurawat.buildit.Project;

/**
 * Created by himanshurawat on 15/10/17.
 */

class ProjectClass {

    String name;
    String date;

    public ProjectClass(String name, String date) {
        this.name = name;
        this.date = date;
    }
    public ProjectClass()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
