package com.example.mock;

public class MLModel {
    private long id;
    private String name;
    private String explain;
    private Tag[] tags;
    private Thesis[] theses;
    private String[] presentations;

    public MLModel(long id, String name, String explain, Tag[] tags, Thesis[] theses, String[] presentations) {
        this.id = id;
        this.name = name;
        this.explain = explain;
        this.tags = tags;
        this.theses = theses;
        this.presentations = presentations;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getExplain() { return explain; }
    public Tag[] getTags() { return tags; }
    public Thesis[] getTheses() { return theses; }
    public String[] getPresentations() { return presentations; }
}
