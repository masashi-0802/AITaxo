-- ===== Thesis 初期データ =====
INSERT INTO thesis (doi, title, authors) VALUES
('10.1000/cv001', 'Deep Learning for Computer Vision', 'Alice, Bob'),
('10.1000/nlp001', 'Natural Language Processing with Transformers', 'Charlie, Dave'),
('10.1000/ml001', 'Efficient Gradient Boosting Methods', 'Eve, Frank');

-- ===== Tag 初期データ =====
INSERT INTO tag (name, full_name, first_thesis_id) VALUES
('CV',  'Computer Vision',             0),
('NLP', 'Natural Language Processing', 1),
('ML',  'Machine Learning',            2);

-- ===== MLModel 初期データ =====
INSERT INTO ml_model (name, full_name, explain) VALUES
('ResNet50',  'Residual Network 50 Layers',              'Image classification model'),
('BERT-base', 'Bidirectional Encoder Representations',   'Language representation model'),
('LightGBM',  'Light Gradient Boosting Machine',         'Efficient gradient boosting framework');

-- ===== 中間テーブル: MLModel-Tag =====
-- ResNet50 -> CV, ML
INSERT INTO mlmodel_tag (mlmodel_id, tag_id) VALUES (0, 0), (0, 2);
-- BERT-base -> NLP, ML
INSERT INTO mlmodel_tag (mlmodel_id, tag_id) VALUES (1, 1), (1, 2);
-- LightGBM -> ML
INSERT INTO mlmodel_tag (mlmodel_id, tag_id) VALUES (2, 2);

-- ===== 中間テーブル: MLModel-Thesis =====
-- ResNet50 -> Thesis 1
INSERT INTO mlmodel_thesis (mlmodel_id, thesis_id) VALUES (0, 0);
INSERT INTO mlmodel_thesis (mlmodel_id, thesis_id) VALUES (0, 1);
-- BERT-base -> Thesis 2
INSERT INTO mlmodel_thesis (mlmodel_id, thesis_id) VALUES (1, 1);
-- LightGBM -> Thesis 3
INSERT INTO mlmodel_thesis (mlmodel_id, thesis_id) VALUES (2, 2);
