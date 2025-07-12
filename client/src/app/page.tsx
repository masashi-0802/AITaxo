"use client";
import { useState, useEffect } from "react";

// 型定義（TypeScript）: APIからのJSON構造に合わせる
type Tag = { id: number; name: string; };
type Thesis = { id: number; title: string; };
type MLModel = { 
  id: number;
  name: string;
  explain: string;
  tags: Tag[];
  theses: Thesis[];
  presentations: string[];
};

export default function ModelListPage() {
  const [models, setModels] = useState<MLModel[]>([]);
  const [name, setName] = useState("");
  const [desc, setDesc] = useState("");

  // コンポーネント初期表示時にモデル一覧を取得
  useEffect(() => {
    fetch("http://localhost:8081/mlmodels")  // Spring BootのAPIを呼ぶ
      .then(res => res.json())
      .then(data => setModels(data))
      .catch(err => console.error(err));
  }, []);

  // 「追加」ボタン押下時のハンドラ
  const handleAdd = async () => {
    // 新規モデルをPOSTで作成
    await fetch("http://localhost:8080/api/models", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ 
        name: name, 
        description: desc 
        // 本来タグや論文も選択可能にし、IDリストを含める
      })
    });
    // 登録後、一覧を再取得して更新
    const res = await fetch("http://localhost:8080/api/models");
    const data = await res.json();
    setModels(data);
    // フォームをクリア
    setName("");
    setDesc("");
  };

  return (
    <div>
      <h1>MLモデル一覧</h1>
      {/* 新規モデル追加フォーム */}
      <div style={{ marginBottom: "1rem" }}>
        <input 
          value={name} 
          onChange={e => setName(e.target.value)} 
          placeholder="モデル名" 
        />
        <input 
          value={desc} 
          onChange={e => setDesc(e.target.value)} 
          placeholder="説明" 
        />
        <button onClick={handleAdd}>追加</button>
      </div>
      {/* モデルの一覧表示 */}
      <ul>
        {models.map(model => (
          <li key={model.id}>
            <h3>{model.name}</h3>
            <p>{model.explain}</p>
            <p>{model.tags.map(t => t.name).join(", ")}</p>
            <p>{model.theses.map(t => t.title).join(", ")}</p>
            <p>{model.presentations.join(", ")}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}
