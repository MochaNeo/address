# ビルドステージ
FROM gradle:8.5-jdk17 AS build

# 作業ディレクトリを設定
WORKDIR /app

# Gradleファイルをコピー
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# 依存関係をダウンロード
RUN gradle dependencies

# ソースコードをコピー
COPY src ./src

# アプリケーションをビルド
RUN gradle build -x test

# 実行ステージ
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# ビルドステージからJARファイルをコピー
COPY --from=build /app/build/libs/*.jar app.jar

# ポート8080を公開
EXPOSE 8080

# アプリケーションを実行
ENTRYPOINT ["java", "-jar", "app.jar"]