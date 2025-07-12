# client/Dockerfile
FROM node:18-alpine
WORKDIR /app
# パッケージ定義を先にコピーして依存解決
COPY package*.json ./
RUN npm install  # 依存ライブラリインストール
# 残りのソースコードをコピー
COPY . .
# Next.jsアプリをビルド（静的ファイルとサーバーエントリを生成）
RUN npm run build
# ポート開放（Next.jsデフォルトポート）
EXPOSE 8081
# プロダクションモードでNext.jsアプリケーションを起動
CMD ["npm", "run", "start"]
