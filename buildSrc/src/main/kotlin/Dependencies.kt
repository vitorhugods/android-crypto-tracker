
object Sdk {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 30
    const val COMPILE_SDK_VERSION = 30
}

object Versions {
    const val ANDROIDX_TEST_EXT = "1.1.1"
    const val ANDROIDX_TEST = "1.2.0"
    const val ANDROID_LIFECYCLE = "2.2.0"
    const val ANDROID_ARCH = "2.1.0"
    const val APPCOMPAT = "1.2.0"
    const val AVATAR_VIEW = "1.1.0"
    const val CONSTRAINT_LAYOUT = "2.0.4"
    const val CORE_KTX = "1.3.2"
    const val COROUTINES = "1.4.1"
    const val ESPRESSO_CORE = "3.2.0"
    const val FLEXBOX_LAYOUT = "2.0.1"
    const val FRAGMENT = "1.2.5"
    const val JUNIT = "4.13"
    const val KOIN = "2.2.1"
    const val KOTLINX_SERIALIZATION = "1.0.1"
    const val KLUENT = "1.64"
    const val KTLINT = "0.39.0"
    const val LIVE_DATA_TESTING = "1.1.2"
    const val MATERIAL_COMPONENTS = "1.2.1"
    const val OKHTTP = "4.9.0"
    const val PICASSO = "2.71828"
    const val RECYCLERVIEW = "1.1.0"
    const val RETROFIT = "2.9.0"
    const val RETROFIT_KOTLINX_SERIALIZATION = "0.8.0"
    const val ROBOLECTRIC = "4.4"
}

object BuildPluginsVersion {
    const val AGP = "4.1.1"
    const val DETEKT = "1.14.2"
    const val KOTLIN = "1.4.10"
    const val KTLINT = "9.4.1"
    const val VERSIONS_PLUGIN = "0.36.0"
}

object LibArchitecture {
    const val FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT}"
    const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.ANDROID_LIFECYCLE}"
    const val VIEW_MODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ANDROID_LIFECYCLE}"
}

object LibCoroutines {
    const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
}

object LibDI {
    const val KOIN = "org.koin:koin-android:${Versions.KOIN}"
    const val KOIN_VIEW_MODEL = "org.koin:koin-android-viewmodel:${Versions.KOIN}"
    const val KOIN_FRAGMENT = "org.koin:koin-androidx-fragment:${Versions.KOIN}"
}

object LibHttp {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
}

object LibSerialization {
    const val KOTLINX =
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.KOTLINX_SERIALIZATION}"
    const val RETROFIT_KOTLINX =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.RETROFIT_KOTLINX_SERIALIZATION}"
}

object LibSupport {
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val ANDROIDX_CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
}

object LibTesting {
    const val ANDROID_CORE_TESTING = "androidx.arch.core:core-testing:${Versions.ANDROID_ARCH}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val KLUENT = "org.amshove.kluent:kluent-android:${Versions.KLUENT}"
    const val LIVE_DATA_TESTING = "com.jraska.livedata:testing-ktx:${Versions.LIVE_DATA_TESTING}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}"
}

object LibUI {
    const val AVATAR_VIEW = "xyz.schwaab:avvylib:${Versions.AVATAR_VIEW}"
    const val FLEXBOX_LAYOUT = "com.google.android:flexbox:${Versions.FLEXBOX_LAYOUT}"
    const val MATERIAL_COMPONENTS = "com.google.android.material:material:${Versions.MATERIAL_COMPONENTS}"
    const val PICASSO = "com.squareup.picasso:picasso:${Versions.PICASSO}"
    const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLERVIEW}"
}

object LibAndroidTesting {
    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}
