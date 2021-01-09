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

    api(LibArchitecture.LIVE_DATA)
    api(LibArchitecture.VIEW_MODEL)

    api(LibSupport.ANDROIDX_APPCOMPAT)
    api(LibSupport.ANDROIDX_CORE_KTX)

    api(LibDI.KOIN)
    api(LibDI.KOIN_FRAGMENT)
    api(LibDI.KOIN_VIEW_MODEL)

    api(LibHttp.OKHTTP)
    api(LibHttp.OKHTTP_LOGGING)

    api(LibUI.MATERIAL_COMPONENTS)
    api(LibUI.RECYCLERVIEW)

    api(LibReactive.RX_KOTLIN)

    testImplementation(LibTesting.JUNIT)
    testImplementation(LibTesting.ANDROID_CORE_TESTING)
    testImplementation(LibTesting.KLUENT)
    testImplementation(LibTesting.LIVE_DATA_TESTING)

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
