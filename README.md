# Gravitated

Android app wrapper for the `gravitated.html` web simulation.

This project packages the web app in [`app/src/web/gravitated.html`](/home/jwkunz/proj_android/gravitated/app/src/web/gravitated.html) and serves it through an Android `WebView` using `WebViewAssetLoader`.

## Current Features

- N-body gravity simulation with up to 12 configurable bodies
- Inelastic body merging on collision
- Per-body angular momentum with visible rotating surface texture
- Dynamic mobile-friendly controls sheet
- Adaptive viewport with centroid tracking and auto-fit scaling
- Branded Android launcher icon

## Project Layout

- `app/src/web/gravitated.html`
  The web simulation UI, rendering, and physics logic.
- `app/src/main/java/com/example/gravitated/MainActivity.kt`
  Android activity hosting the `WebView`.
- `app/src/main/res/`
  Android resources including launcher icons and theme files.

## Build

Requirements:

- Android SDK
- JDK 11 or newer

Build a debug APK:

```bash
./gradlew assembleDebug
```

Install to a connected device:

```bash
./gradlew installDebug
```

## Notes

- The web assets are packaged via the app module `sourceSets` configuration.
- The app loads the simulation from:

```text
https://appassets.androidplatform.net/assets/gravitated.html
```

- `local.properties` is intentionally ignored and should not be committed.
