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
            new MLModel("GRU", "コンパクトなLSTM", new String[]{"RNN"}, new String[]{"A", "B"}, new String[]{}),
            new MLModel("Transformer", "Attentionによるシーケンス処理", new String[]{"Attention", "NLP"}, new String[]{"Vaswani et al., 2017"}, new String[]{"ICLR2023"}),
            new MLModel("CNN", "画像認識に特化したネットワーク", new String[]{"Vision"}, new String[]{"LeCun et al., 1998"}, new String[]{"CVPR2022"})
        );

        return Flux.fromArray(models.toArray());
    }

    private class MLModel {
        private String name;
        private String explain;
        private String[] tags;
        private String[] thesis;
        private String[] presentations;

        public MLModel(String name, String explain, String[] tags, String[] thesis, String[] presentations) {
            this.name = name;
            this.explain = explain;
            this.tags = tags;
            this.thesis = thesis;
            this.presentations = presentations;
        }
    }
}
