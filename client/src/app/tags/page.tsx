"use client";
import { useState, useEffect } from "react";
import Image from "next/image";
import Link from 'next/link';

import editImage from "@/../public/edit.png";
import removeImage from "@/../public/remove.png";

import { Tag } from "../types";
import { url } from "@/app/consts";

export default function TagListPage() {
  const [tags, setTags] = useState<Tag[]>([]);

  // コンポーネント初期表示時にタグ一覧を取得
  useEffect(() => {
    fetch(`${url}/tags`)
      .then(res => res.json())
      .then(data => setTags(data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h1>タグ一覧</h1>
      <ul className="grid grid-cols-2 md:grid-cols-1 gap-5">
        {tags.map(tag => (
          <li key={tag.id} className="flex justify-between px-4 py-4 border rounded-2xl">
            <div className="flex-1 pr-4">
              <h3 className="text-xl font-bold">{tag.name}</h3>
              <p>初出論文: {tag.firstThesisTitle}</p>
            </div>

            <Link href="/edit/tag">
              <Image src={editImage} alt="edit" width="64" height="64" />
            </Link>
            <Link href="/remove/tag">
              <Image src={removeImage} alt="remove" width="64" height="64" />
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}
