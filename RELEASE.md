# Release Guide

## Versioning

Use Semantic Versioning: `MAJOR.MINOR.PATCH`.

## Pre-release Checklist

1. `./gradlew clean test` passes.
2. `./gradlew packageApp` succeeds.
3. `build/distributions/wordguess.zip` and `build/libs/wordguess-all.jar` are generated.
4. On Windows, `./gradlew.bat packageWindowsExeZip` succeeds and `build/native/windows/WordGuess-windows.zip` is generated.
5. `CHANGELOG.md` is updated.

## Tagging

Create an annotated tag:

```bash
git tag -a v0.1.0 -m "WordGuess v0.1.0"
git push origin v0.1.0
```

Pushing a `v*` tag triggers `.github/workflows/release.yml`, which builds the application and publishes the packaged artifacts to GitHub Releases automatically.

## GitHub Release Notes

Recommended sections:

- Highlights
- Fixes
- Known issues
- Checksums/artifacts

Recommended download wording:

- Windows users: download `WordGuess-windows.zip` and run `WordGuess.exe`
- Cross-platform Java users: download `wordguess-all.jar`

## Release Template

The repository includes a GitHub release template at `.github/release.yml` so new releases start with consistent notes and categories.
