# WordGuess（中文说明）

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

## VS Code 使用建议

- 可直接运行 `.vscode/tasks.json` 中预设任务。
- 若 `gradlew` 首次下载依赖较慢，请耐心等待，或配置代理/镜像。

## 目录说明

- 源码：`src/main/java/wordguess`
- 测试：`src/test/java/wordguess`
- 词库：`data/words.txt`

## 开源协作

- 贡献指南：`CONTRIBUTING.md`
- 许可证：`LICENSE`
