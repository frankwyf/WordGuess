# WordGuess (English Docs)

WordGuess is a terminal-based word guessing game inspired by Wordle.

The project is maintained as an open-source portfolio project.

## Requirements

- JDK 26
- Gradle Wrapper (`gradlew` / `gradlew.bat` included)

## Quick Start (Windows PowerShell)

1. Set Java environment variables

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-26.0.1'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
java -version
```

2. Run tests

```powershell
.\gradlew.bat clean test
```

3. Start the game

```powershell
.\gradlew.bat run
```

4. Run with a fixed game number (good for demos)

```powershell
.\gradlew.bat runFixed
```

## VS Code Tips

- You can run predefined tasks from `.vscode/tasks.json`.
- The first `gradlew` run may take time due to dependency downloads. Configure a proxy or mirror if your network is restricted.

## Project Layout

- Source code: `src/main/java/wordguess`
- Unit tests: `src/test/java/wordguess`
- Word data: `data/words.txt`

## Contributing

- Guide: `CONTRIBUTING.md`
- License: `LICENSE`
