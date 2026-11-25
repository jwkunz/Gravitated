plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

// One version for the whole project: the VERSION file at the repository root.
val projectVersion = rootProject.file("../VERSION").readText().trim()
val versionParts = projectVersion.split(".").map { it.toInt() }
val androidVersionCode = versionParts[0] * 10_000 + versionParts[1] * 100 + versionParts[2]

android {
    namespace = "com.example.gravitated"
    compileSdk = 36

    sourceSets {
        getByName("main") {
            assets.srcDir("build/generated/web-assets")
        }
    }

    defaultConfig {
        applicationId = "com.example.gravitated"
        minSdk = 24
        targetSdk = 36
        versionCode = androidVersionCode
        versionName = projectVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

// Bundles the web app (../web/gravitated.html) as the app's index.html. There is no web build step:
// the page is a single hand-written HTML file.
val copyWeb by tasks.registering(Copy::class) {
    from(rootProject.file("../web/gravitated.html")) { rename { "index.html" } }
    into(layout.buildDirectory.dir("generated/web-assets"))
}

tasks.named("preBuild") { dependsOn(copyWeb) }

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.webkit:webkit:1.13.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
