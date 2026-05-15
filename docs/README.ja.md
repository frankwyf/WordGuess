# WordGuess（日本語ドキュメント）

[![CI](https://github.com/frankwyf/WordGuess/actions/workflows/ci.yml/badge.svg)](https://github.com/frankwyf/WordGuess/actions/workflows/ci.yml)
[![Release](https://github.com/frankwyf/WordGuess/actions/workflows/release.yml/badge.svg)](https://github.com/frankwyf/WordGuess/actions/workflows/release.yml)

WordGuess は、Wordle に着想を得たターミナルベースの単語推測ゲームです。

このプロジェクトは、オープンソースのポートフォリオ用ゲームとして公開されています。

## 必要環境

- JDK 26
- Gradle Wrapper（リポジトリに `gradlew` / `gradlew.bat` を同梱）

## クイックスタート（Windows PowerShell）

1. Java 環境変数を設定

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-26.0.1'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
java -version
```

2. テスト実行

```powershell
.\gradlew.bat clean test
```

3. ゲーム起動

```powershell
.\gradlew.bat run
```

4. 固定ゲーム番号で起動（デモ向け）

```powershell
.\gradlew.bat runFixed
```

5. リリース成果物をビルド

```powershell
.\gradlew.bat packageApp
```

`v0.1.0` のようなタグを push すると、GitHub Release の自動公開パイプラインが実行されます。

## VS Code での利用

- `.vscode/tasks.json` のタスクをそのまま実行できます。
- 初回の `gradlew` 実行時は依存関係のダウンロードに時間がかかる場合があります。必要に応じてプロキシやミラーを設定してください。

## ディレクトリ構成

- ソースコード: `src/main`
- テスト: `src/test`
- 単語データ: `data/words.txt`
- GitHub 自動化: `.github/workflows`

## 自動化

- CI は push と pull request ごとにビルド、テスト、パッケージングを実行します。
- Release ワークフローはバージョンタグ push 時に成果物を公開します。
- Dependabot は GitHub Actions の依存関係更新を追跡します。

## コントリビューション

- ガイドライン: `CONTRIBUTING.md`
- ライセンス: `LICENSE`
