# ビルドステージ
FROM gradle:8.5-jdk17 AS build

# 作業ディレクトリを設定
WORKDIR /app

# Gradleのキャッシュ改善のため、まずは依存関係のファイルのみをコピー
COPY gradle gradle
COPY gradlew .
COPY gradlew.bat .
COPY settings.gradle .
COPY build.gradle .

# Gradlewに実行権限を付与
RUN chmod +x ./gradlew

# 依存関係をダウンロード
RUN ./gradlew dependencies --no-daemon

# ソースコードをコピー
COPY src src

# アプリケーションをビルド
RUN ./gradlew build -x test --no-daemon

# 実行ステージ
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# ビルドステージからJARファイルをコピー
COPY --from=build /app/build/libs/*.jar app.jar

# ポート8080を公開
EXPOSE 8080

# アプリケーションを実行
ENTRYPOINT ["java", "-jar", "app.jar"]