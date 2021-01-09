version = LibraryAndroidCoordinates.LIBRARY_VERSION

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
    id("maven-publish")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)

        versionCode = LibraryAndroidCoordinates.LIBRARY_VERSION_CODE
        versionName = LibraryAndroidCoordinates.LIBRARY_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
    }

    lintOptions {
        isAbortOnError = true
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    coreLibraryDesugaring(LibDesugar.CORE_DESUGAR)

    implementation(LibArchitecture.LIVE_DATA)
    implementation(LibArchitecture.VIEW_MODEL)

    implementation(LibSupport.ANDROIDX_APPCOMPAT)
    implementation(LibSupport.ANDROIDX_CORE_KTX)

    implementation(LibDI.KOIN)
    implementation(LibDI.KOIN_FRAGMENT)
    implementation(LibDI.KOIN_VIEW_MODEL)

    implementation(LibReactive.RX_KOTLIN)

    implementation(LibTesting.JUNIT)
    implementation(LibTesting.ANDROID_CORE_TESTING)
    implementation(LibTesting.KLUENT)
    implementation(LibTesting.LIVE_DATA_TESTING)

    androidTestImplementation(LibAndroidTesting.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(LibAndroidTesting.ANDROIDX_TEST_EXT_JUNIT)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
            }
        }
    }
}