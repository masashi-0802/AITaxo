package com.example.mock;

public class Tag {
    private long id;
    private String name;
    private Thesis firstThesis;
    private MLModel mlmodel;

    public Tag(long id, String name, Thesis firstThesis, MLModel mlmodel) {
        this.id = id;
        this.name = name;
        this.firstThesis = firstThesis;
        this.mlmodel = mlmodel;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public Thesis getFirstThesis() { return firstThesis; }
    public MLModel getMlmodel() { return mlmodel; }
}
