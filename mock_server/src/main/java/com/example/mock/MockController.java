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

    @GetMapping("/tags")
    public Flux<Object> getTags() {
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
}
