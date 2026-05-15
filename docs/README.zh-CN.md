# WordGuess（中文说明）

[![CI](https://github.com/frankwyf/WordGuess/actions/workflows/ci.yml/badge.svg)](https://github.com/frankwyf/WordGuess/actions/workflows/ci.yml)
[![Release](https://github.com/frankwyf/WordGuess/actions/workflows/release.yml/badge.svg)](https://github.com/frankwyf/WordGuess/actions/workflows/release.yml)

WordGuess 是一个基于终端的猜词小游戏，灵感来自 Wordle。

这个项目是一个开源猜词游戏，并用于个人作品集展示。

## 环境要求

- JDK 26
- Gradle Wrapper（仓库内已提供 `gradlew` / `gradlew.bat`）

## 快速开始（Windows PowerShell）

1. 设置 Java 环境变量

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-26.0.1'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
java -version
```

2. 运行测试

```powershell
.\gradlew.bat clean test
```

3. 启动游戏

```powershell
.\gradlew.bat run
```

4. 运行固定关卡（便于演示）

```powershell
.\gradlew.bat runFixed
```

5. 构建发布产物

```powershell
.\gradlew.bat packageApp
```

6. 构建 Windows 原生可双击版本

```powershell
.\gradlew.bat packageWindowsExeZip
```

推送类似 `v0.1.0` 的 Git 标签后，会自动触发 GitHub Release 发布流程。

## 下载建议

- Windows 用户优先下载 `WordGuess-windows.zip`
- 解压后直接双击 `WordGuess.exe`
- 如果你更希望用通用 Java 方式运行，再选择 `wordguess-all.jar`

## VS Code 使用建议

- 可直接运行 `.vscode/tasks.json` 中预设任务。
- 若 `gradlew` 首次下载依赖较慢，请耐心等待，或配置代理/镜像。

## 目录说明

- 源码：`src/main`
- 测试：`src/test`
- 词库：`data/words.txt`
- GitHub 自动化：`.github/workflows`

## 自动化

- CI 会在 push 和 pull request 时自动执行构建、测试和打包。
- Release 流水线会在推送版本标签时自动发布产物，包括 Windows 原生包。
- Dependabot 会自动检查 GitHub Actions 依赖更新。

## 开源协作

- 贡献指南：`CONTRIBUTING.md`
- 许可证：`LICENSE`
