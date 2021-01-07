plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)
    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)

        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "xyz.schwaab.template.TestAppJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    dataBinding {
        isEnabled = true
    }
    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

repositories {
    maven("https://jitpack.io")
    maven("https://dl.bintray.com/vitorhugods/AvatarView")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(project(Modules.Android.IMAGE_DISPLAY))
    implementation(project(Modules.JVM.LOCALE))

    implementation(LibSupport.ANDROIDX_APPCOMPAT)
    implementation(LibSupport.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(LibSupport.ANDROIDX_CORE_KTX)

    implementation(LibArchitecture.LIVE_DATA)
    implementation(LibArchitecture.VIEW_MODEL)

    implementation(LibCoroutines.CORE)
    implementation(LibCoroutines.ANDROID)

    implementation(LibDI.KOIN)
    implementation(LibDI.KOIN_VIEW_MODEL)
    implementation(LibDI.KOIN_FRAGMENT)

    implementation(LibHttp.OKHTTP)
    implementation(LibHttp.OKHTTP_LOGGING)

    implementation(LibUI.AVATAR_VIEW)
    implementation(LibUI.FLEXBOX_LAYOUT)
    implementation(LibUI.MATERIAL_COMPONENTS)
    implementation(LibUI.PICASSO)
    implementation(LibUI.RECYCLERVIEW)

    testImplementation(LibTesting.ANDROID_CORE_TESTING)
    testImplementation(LibTesting.COROUTINES)
    testImplementation(LibTesting.JUNIT)
    testImplementation(LibTesting.KLUENT)
    testImplementation(LibTesting.ROBOLECTRIC)
    testImplementation(LibTesting.LIVE_DATA_TESTING)

    androidTestImplementation(LibTesting.KLUENT)
    androidTestImplementation(LibAndroidTesting.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(LibAndroidTesting.ANDROIDX_TEST_RULES)
    androidTestImplementation(LibAndroidTesting.ESPRESSO_CORE)
}