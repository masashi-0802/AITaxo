// server/src/main/java/com/example/aitaxo/controller/MLModelController.java
package com.example.aitaxo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.aitaxo.model.MLModel;
import com.example.aitaxo.repository.MLModelRepository;
import com.example.aitaxo.repository.TagRepository;
import com.example.aitaxo.repository.PaperRepository;

@RestController
@RequestMapping("/mlmodels")
@CrossOrigin(origins = "http://131.113.48.144:8081")  // フロントエンドからのアクセスを許可
public class MLModelController {

    @Autowired
    private MLModelRepository modelRepo;
    @Autowired
    private TagRepository tagRepo;
    @Autowired
    private PaperRepository paperRepo;

    // (1) 全MLモデルの取得 + フィルタ（タグ名 or モデル名による絞り込み）
    @GetMapping
    public Flux<MLModel> getModels(@RequestParam(required=false) String tag,
                                   @RequestParam(required=false) String name) {
        if (tag != null && !tag.isEmpty()) {
           List<MLModel> filtered = modelRepo.findByTags_Name(tag);
           return filtered.isEmpty() ? Flux.empty() : Flux.fromIterable(filtered);
        }

        if (name != null && !name.isEmpty()) {
           List<MLModel> filtered = modelRepo.findByNameContaining(name);
           return filtered.isEmpty() ? Flux.empty() : Flux.fromIterable(filtered);
        }
        // フィルタなし：全件返す
        return Flux.fromIterable(modelRepo.findAll());

    }

    // (2) 新規MLモデルの追加
    @PostMapping("/create/mlmodels")
    public MLModel createModel(@RequestBody CreateModelRequest req) {
        // リクエストDTOにはモデル名・説明・関連タグIDs・関連論文IDs・URLリスト等が含まれる
        MLModel model = new MLModel();
        model.setName(req.getName());
        model.setExplain(req.getExplain());
        // 関連タグ・論文をIDリストから取得して設定
        if (req.getTagIds() != null) {
            model.setTags(tagRepo.findAllById(req.getTagIds()));
        }
        if (req.getThesesIds() != null) {
            model.setTheses(paperRepo.findAllById(req.getThesisIds()));
        }
        model.setPresentations(req.getPresentations());
        // 保存（save後、生成されたIDや関連も含めて返す）
        return modelRepo.save(model);
    }

    // (3) MLモデルの削除
    @DeleteMapping("/remove/mlmodel/{id}")
    public void deleteModel(@PathVariable Long id) {
        modelRepo.deleteById(id);
    }

}
