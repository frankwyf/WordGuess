# WordGuess

A terminal-based word guessing game inspired by Wordle.

This project is maintained as an open-source portfolio game.

## Language Docs

- Chinese (Simplified): [docs/README.zh-CN.md](docs/README.zh-CN.md)
- Japanese: [docs/README.ja.md](docs/README.ja.md)
- English: [docs/README.en.md](docs/README.en.md)

## Quick Start

### Requirements

- JDK 26
- Gradle Wrapper (`gradlew` / `gradlew.bat` included in repository)

The wrapper is configured to Gradle 9.4.0, which supports running on Java 26.

### Configure Java (Windows PowerShell)

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-26.0.1'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
java -version
```

### Build and Test

```powershell
.\gradlew.bat clean test
```

### Run the Game

```powershell
.\gradlew.bat run
```

Run fixed game number (helpful for demo/testing):

```powershell
.\gradlew.bat runFixed
```

### Package for Release

Build all release artifacts (standard distributions + runnable uber JAR):

```powershell
.\gradlew.bat packageApp
```

Artifacts:

- Standard app bundle (recommended): `build/distributions/wordguess.zip`
- Self-contained JAR: `build/libs/wordguess-all.jar`

Run packaged JAR on Java 26:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-26.0.1'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
java -jar .\build\libs\wordguess-all.jar 12
```

## Open Source

- Contribution guide: [CONTRIBUTING.md](CONTRIBUTING.md)
- License: [LICENSE](LICENSE)
- Security policy: [SECURITY.md](SECURITY.md)
- Code of conduct: [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md)
- Changelog: [CHANGELOG.md](CHANGELOG.md)
- Release guide: [RELEASE.md](RELEASE.md)
- GitHub topics and labels: [GITHUB_TOPICS.md](GITHUB_TOPICS.md)

## Project Structure

- Source code: `src/main/java/wordguess`
- Unit tests: `src/test/java/wordguess`
- Word data: `data/words.txt`
