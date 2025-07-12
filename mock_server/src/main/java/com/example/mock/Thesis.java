package com.example.mock;

public class Thesis {
    private long id;
    private String doi;
    private String title;
    private String authors;

    public Thesis(long id, String doi, String title, String authors) {
        this.id = id;
        this.doi = doi;
        this.title = title;
        this.authors = authors;
    }

    public long getId() { return id; }
    public String getDoi() { return doi; }
    public String getTitle() { return title; }
    public String getAuthors() { return authors; }
}
