// 型定義（TypeScript）: APIからのJSON構造に合わせる
export type Tag = {
    id: number;
    name: string;
    fullName: string;
    firstThesis: Thesis;
    mlmodel: MLModel;
};
export type Thesis = { id: number; title: string; };
export type MLModel = { 
  id: number;
  name: string;
  fullName: string;
  explain: string;
  tags: Tag[];
  theses: Thesis[];
  presentations: string[];
};
