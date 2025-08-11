// 型定義（TypeScript）: APIからのJSON構造に合わせる
export type Tag = {
    id: number;
    name: string;
    fullName: string;
    firstThesisId: number,
    firstThesisTitle: string,
    mlmodel: MLModel;
};
export type Thesis = {
    id: number;
    doi: string;
    title: string;
    authors: string;
};
export type MLModel = { 
  id: number;
  name: string;
  fullName: string;
  explain: string;
  tags: Tag[];
  theses: Thesis[];
  presentations: string[];
};
