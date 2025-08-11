"use client";
import { useState, useEffect } from "react";
import Image from "next/image";
import Link from 'next/link';

import editImage from "@/../public/edit.png";
import removeImage from "@/../public/remove.png";

import { MLModel } from "./types";
import SearchForm from "./components/searchForm";
import { url } from "./consts";

export default function ModelListPage() {
  const [models, setModels] = useState<MLModel[]>([]);
  const [name, setName] = useState("");
  const [desc, setDesc] = useState("");

  // コンポーネント初期表示時にモデル一覧を取得
  useEffect(() => {
    fetch(`${url}/mlmodels`)  // Spring BootのAPIを呼ぶ
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
      <SearchForm setModels={setModels}/>
      {/* モデルの一覧表示 */}
      <ul className="grid grid-cols-2 md:grid-cols-1 gap-5">
        { 
        Array.isArray(models) ? 
          models.map(model => (
            <li key={model.id} className="flex justify-between px-4 py-4 border rounded-2xl">
              <div className="flex-1 pr-4">
                <h3 className="text-xl font-bold">{model.name}</h3>
                <p>{model.explain}</p>
                <p>タグ: {model.tags.join(", ")}</p>
                <p>論文: {model.theses.map(t => t.title).join(", ")}</p>
                {/* {
                <p>資料: {model.presentations.join(", ")}</p>
                } */}
              </div>

              <Link href="/edit/mlmodel">
                <Image src={editImage} alt="edit" width="64" height="64" />
              </Link>
              <Link href="/remove/mlmodel">
                <Image src={removeImage} alt="remove" width="64" height="64" />
              </Link>
            </li>
          ))
        :
        <></>
        }
      </ul>
    </div>
  );
}
