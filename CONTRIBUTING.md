# Contributing to WordGuess

Thanks for your interest in contributing.

## Ways to Contribute

- Report bugs
- Propose features
- Improve documentation
- Submit code fixes and tests

## Development Setup

1. Ensure JDK 26 is installed.
2. Clone the repository and enter the project directory.
3. On Windows PowerShell, set Java environment variables:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-26.0.1'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
```

4. Run checks:

```powershell
.\gradlew.bat clean test
.\gradlew.bat style
```

## Coding Guidelines

- Keep changes focused and minimal.
- Follow existing package structure and naming conventions.
- Add or update tests for behavior changes.
- Avoid unrelated refactors in the same pull request.

## Pull Request Checklist

- Build passes locally.
- Tests pass locally.
- New behavior is covered by tests.
- Documentation is updated when needed.

## Commit Messages

Use clear, concise commit messages. Example:

- `fix(game): handle invalid guess length`
- `docs(readme): add Japanese quick start`

## Code of Conduct

Please be respectful and constructive in all discussions.
