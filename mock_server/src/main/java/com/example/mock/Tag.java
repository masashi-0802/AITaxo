package com.example.mock;

public class Tag {
    private long id;
    private String name;
    private String fullName;
    private Thesis firstThesis;

    public Tag(long id, String name, String fullName, Thesis firstThesis) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.firstThesis = firstThesis;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getFullName() { return fullName; }
    public Thesis getFirstThesis() { return firstThesis; }
}
