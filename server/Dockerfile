# server/Dockerfile

# ビルドステージ: Gradleでアプリをビルド
FROM gradle:8.4.0-jdk17 AS build
WORKDIR /app

# Gradle キャッシュを効かせるため、先に build.gradle などだけコピー
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
RUN gradle --no-daemon build || return 0  # 初回キャッシュ作成用（失敗してもOK）

# ソースコードをコピーして再ビルド
COPY src ./src
RUN gradle clean bootJar --no-daemon

# 実行ステージ: 軽量な JDK ランタイム
FROM openjdk:17-jdk-slim
WORKDIR /app

# build/libs 配下にできた JAR ファイルをコピー
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]
