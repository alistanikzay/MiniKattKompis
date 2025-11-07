package org.example.minikattkompis.model;

import java.time.LocalDate;

public class Reminder {

    private Long id;
    private String catName;
    private String description;
    private LocalDate date;

    public Reminder() {}

    public Reminder(Long id, String catName, String description, LocalDate date) {
        this.id = id;
        this.catName = catName;
        this.description = description;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
