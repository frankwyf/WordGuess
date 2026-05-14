# Release Guide

## Versioning

Use Semantic Versioning: `MAJOR.MINOR.PATCH`.

## Pre-release Checklist

1. `./gradlew clean test` passes.
2. `./gradlew packageApp` succeeds.
3. `build/distributions/wordguess.zip` and `build/libs/wordguess-all.jar` are generated.
4. `CHANGELOG.md` is updated.

## Tagging

Create an annotated tag:

```bash
git tag -a v0.1.0 -m "WordGuess v0.1.0"
git push origin v0.1.0
```

## GitHub Release Notes

Recommended sections:

- Highlights
- Fixes
- Known issues
- Checksums/artifacts
