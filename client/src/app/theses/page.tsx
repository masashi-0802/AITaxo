"use client";
import { useState, useEffect } from "react";
import Image from "next/image";
import Link from 'next/link';

import editImage from "@/../public/edit.png";
import removeImage from "@/../public/remove.png";

import { Thesis } from "../types";
import { url } from "@/app/consts";

export default function ThesisListPage() {
  const [theses, setTheses] = useState<Thesis[]>([]);

  // コンポーネント初期表示時に論文一覧を取得
  useEffect(() => {
    fetch(`${url}/theses`)
      .then(res => res.json())
      .then(data => setTheses(data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h1>論文一覧</h1>
      <ul className="grid grid-cols-2 md:grid-cols-1 gap-5">
        {theses.map(thesis => (
          <li key={thesis.id} className="flex justify-between px-4 py-4 border rounded-2xl">
            <div className="flex-1 pr-4">
              <h3 className="text-xl font-bold">{thesis.title}</h3>
              <p>doi: {thesis.doi}</p>
              <p>authors: {thesis.authors}</p>
            </div>

            <Link href="/edit/thesis">
              <Image src={editImage} alt="edit" width="64" height="64" />
            </Link>
            <Link href="/remove/thesis">
              <Image src={removeImage} alt="remove" width="64" height="64" />
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}
