package com.example.mock;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MockController {

    @GetMapping("/mlmodels")
    public Flux<Object> getMlModels() {
        List<MLModel> models = Arrays.asList(
            new MLModel(
                1L,
                "GRU",
                "コンパクトなLSTM",
                new Tag[]{
                    new Tag(1L, "RNN", null, null)
                },
                new Thesis[]{
                    new Thesis(1L, "10.1000/xyz123", "Efficient GRU", "Author A, Author B")
                },
                new String[]{}
            ),
            new MLModel(
                2L,
                "Transformer",
                "Attentionによるシーケンス処理",
                new Tag[]{
                    new Tag(2L, "Attention", null, null),
                    new Tag(3L, "NLP", null, null)
                },
                new Thesis[]{
                    new Thesis(2L, "10.1000/xyz456", "Attention is All You Need", "Vaswani et al.")
                },
                new String[]{"ICLR2023"}
            ),
            new MLModel(
                3L,
                "CNN",
                "画像認識に特化したネットワーク",
                new Tag[]{
                    new Tag(4L, "Vision", null, null)
                },
                new Thesis[]{
                    new Thesis(3L, "10.1000/xyz789", "Convolutional Networks", "LeCun et al.")
                },
                new String[]{"CVPR2022"}
            )
        );

        return Flux.fromArray(models.toArray());
    }

    private class MLModel {
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

    private class Thesis {
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

    private class Tag {
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
}
