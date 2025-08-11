package com.example.aitaxo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.aitaxo.dto.MLModelDto;
import com.example.aitaxo.model.Tag;
import com.example.aitaxo.repository.MLModelRepository;
import com.example.aitaxo.repository.TagRepository;
import com.example.aitaxo.service.MLModelService;
import com.example.aitaxo.repository.PaperRepository;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@RestController
// @RequestMapping("/mlmodels")
// @CrossOrigin(origins = "http://131.113.48.144:8081")  // フロントエンドからのアクセスを許可
@CrossOrigin(origins = "http://localhost:3000")
public class MLModelController {

    @Autowired
    private MLModelRepository modelRepo;
    @Autowired
    private TagRepository tagRepo;
    @Autowired
    private PaperRepository paperRepo;

    private final MLModelService service;
    
    public MLModelController(MLModelService service) {
        this.service = service;
    }
    
    // (1) 全MLモデルの取得 + フィルタ（タグ名 or モデル名による絞り込み）
    @GetMapping("/mlmodels")
    public Flux<MLModelDto> getModels(
        @RequestParam(required=false) String tag,
        @RequestParam(required=false) String name
    ) {
        List<MLModelDto> dtos = getDtos(tag, name);
        return Flux.defer(() -> Flux.fromIterable(dtos))
                   .subscribeOn(Schedulers.boundedElastic());
    }

    private List<MLModelDto> getDtos(String tag, String name) {
        if (tag != null && !tag.isEmpty()) {
            return service.findByTagName(tag);
        } else if (name != null && !name.isEmpty()) {
            return service.findByName(name);
        } else {
            return service.findAll();
        }
    };

    // (2) 新規MLモデルの追加
    // @PostMapping("/create/mlmodels")
    // public MLModel createModel(@RequestBody CreateModelRequest req) {
    //     // リクエストDTOにはモデル名・説明・関連タグIDs・関連論文IDs・URLリスト等が含まれる
    //     MLModel model = new MLModel();
    //     model.setName(req.getName());
    //     model.setExplain(req.getExplain());
    //     // 関連タグ・論文をIDリストから取得して設定
    //     if (req.getTagIds() != null) {
    //         model.setTags(tagRepo.findAllById(req.getTagIds()));
    //     }
    //     if (req.getThesesIds() != null) {
    //         model.setTheses(paperRepo.findAllById(req.getThesisIds()));
    //     }
    //     model.setPresentations(req.getPresentations());
    //     // 保存（save後、生成されたIDや関連も含めて返す）
    //     return modelRepo.save(model);
    // }

    // (3) MLモデルの削除
    @DeleteMapping("/remove/mlmodel/{id}")
    public void deleteModel(@PathVariable Long id) {
        modelRepo.deleteById(id);
    }

}
