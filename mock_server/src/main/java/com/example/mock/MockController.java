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
        Thesis gruThesis = new Thesis(1L, "10.1000/xyz123", "Efficient GRU", "Author A, Author B");
        Thesis transThesis = new Thesis(2L, "10.1000/xyz456", "Attention is All You Need", "Vaswani et al.");
        List<MLModel> models = Arrays.asList(
            new MLModel(
                1L,
                "GRU",
                "Gated Recurrent Unit",
                "コンパクトなLSTM",
                new Tag[]{
                    new Tag(1L, "RNN", "Recurrent Neural Network", gruThesis)
                },
                new Thesis[]{ gruThesis },
                new String[]{}
            ),
            new MLModel(
                2L,
                "Transformer",
                "",
                "Attentionによるシーケンス処理",
                new Tag[]{
                    new Tag(2L, "Attention", "", transThesis),
                    new Tag(3L, "NLP", "Natural Language Processing", transThesis)
                },
                new Thesis[]{ transThesis },
                new String[]{"ICLR2023"}
            ),
            new MLModel(
                3L,
                "CNN",
                "Convolutional Neural Network",
                "画像認識に特化したネットワーク",
                new Tag[]{
                    new Tag(4L, "Vision", "", gruThesis)
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
        Thesis gruThesis = new Thesis(1L, "10.1000/xyz123", "Efficient GRU", "Author A, Author B");
        List<Tag> tags = Arrays.asList(
            new Tag[]{
                new Tag(1L, "RNN", "Recurrent Neural Network", gruThesis),
                new Tag(2L, "Attention", "", gruThesis),
                new Tag(3L, "NLP", "Natural Language Processing", gruThesis),
            }
        );

        return Flux.fromArray(tags.toArray());
    }
}
