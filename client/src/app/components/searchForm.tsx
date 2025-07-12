'use client';

import { useEffect, useState } from 'react';

import { Tag } from "@/app/types";
import { url } from '@/app/consts';


export default function SearchForm() {
  const [searchType, setSearchType] = useState<'name' | 'tag'>('name');
  const [name, setName] = useState('');
  const [tag, setTag] = useState('');
  const [tags, setTags] = useState<Tag[]>([]);

  // Tag一覧をサーバから取得
  useEffect(() => {
    if (searchType === 'tag') {
      fetch(`${url}/tags`)
        .then(res => res.json())
        .then(data => setTags(data));
    }
  }, [searchType]);

  const handleSearch = () => {
    const nameParam = searchType === 'name' ? name : 'null';
    const tagParam = searchType === 'tag' ? tag : 'null';

    fetch(`${url}/mlmodels/${nameParam}/${tagParam}`)
      .then(res => res.json())
      .then(data => {
        console.log('検索結果:', data);
        // 必要なら検索結果を状態に保持して表示
      });
  };

  return (
    <div className="p-4 rounded-lg w-full max-w-md space-y-4">
      <div className="flex space-x-4">
        <label className="flex items-center space-x-1">
          <input
            type="radio"
            value="name"
            checked={searchType === 'name'}
            onChange={() => setSearchType('name')}
          />
          <span>Nameで検索</span>
        </label>
        <label className="flex items-center space-x-1">
          <input
            type="radio"
            value="tag"
            checked={searchType === 'tag'}
            onChange={() => setSearchType('tag')}
          />
          <span>Tagで検索</span>
        </label>
      </div>

      {searchType === 'name' && (
        <input
          type="text"
          className="w-full p-2 border rounded"
          placeholder="名前を入力"
          value={name}
          onChange={e => setName(e.target.value)}
        />
      )}

      {searchType === 'tag' && (
        <select
          className="w-full p-2 border rounded"
          value={tag}
          onChange={e => setTag(e.target.value)}
        >
          <option value="">-- タグを選択 --</option>
          {tags.map(t => (
            <option key={t.id} value={t.name}>
              {t.name}
            </option>
          ))}
        </select>
      )}

      <button
        className="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700"
        onClick={handleSearch}
      >
        検索
      </button>
    </div>
  );
}