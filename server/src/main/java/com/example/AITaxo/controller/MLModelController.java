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
    public List<MLModel> getModels(@RequestParam(required=false) String tag,
                                   @RequestParam(required=false) String name) {
        if (tag != null && !tag.isEmpty()) {
            // タグ名でフィルタ
            return modelRepo.findByTags_Name(tag);
        } 
        if (name != null && !name.isEmpty()) {
            // モデル名でフィルタ（部分一致）
            return modelRepo.findByNameContaining(name);
        }
        // フィルタなし：全件返す
        return modelRepo.findAll();
    }

    // (2) 新規MLモデルの登録
    @PostMapping
    public MLModel createModel(@RequestBody CreateModelRequest req) {
        // リクエストDTOにはモデル名・説明・関連タグIDs・関連論文IDs・URLリスト等が含まれる
        MLModel model = new MLModel();
        model.setName(req.getName());
        model.setDescription(req.getDescription());
        // 関連タグ・論文をIDリストから取得して設定
        if (req.getTagIds() != null) {
            model.setTags(tagRepo.findAllById(req.getTagIds()));
        }
        if (req.getPaperIds() != null) {
            model.setPapers(paperRepo.findAllById(req.getPaperIds()));
        }
        model.setPresentationUrls(req.getPresentationUrls());
        // 保存（save後、生成されたIDや関連も含めて返す）
        return modelRepo.save(model);
    }

    // (3) MLモデルの削除
    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        modelRepo.deleteById(id);
    }

    // （必要に応じてPUT/PATCHで更新処理も実装可能）
}
