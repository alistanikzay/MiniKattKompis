package org.example.minikattkompis.model;

public class Recommendation {

    private Long id;
    private String text;
    private String category;

    public Recommendation() {
    }

    public Recommendation(Long id, String text, String category) {
        this.id = id;
        this.text = text;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
