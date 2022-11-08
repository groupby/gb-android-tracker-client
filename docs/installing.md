# Installing

Instructions for installing dependencies from JitPack differ based on what kind of Gradle project layout you have. These instructions are based on a new Android app created in April 2022 in the latest version of Android Studio at the time, with "basic activity" selected during the wizard.

1. Add the Jitpack Maven repository to your Gradle config by adding `maven {url 'https://www.jitpack.io'}` under `repositories` under `dependencyResolutionManagement`.

```
...
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {url 'https://www.jitpack.io'}
    }
}
...
```

This Gradle config will be in a different place among your project files depending on the project. At time of writing, this was `settings.gradle`.

2. Add the version of the dependency you want to use to the module `build.gradle` file (not the project `build.gradle` file). Versions usable for production start at `1.0.2`. Check GitHub releases to see which is the latest version. It's recommended to use the latest version if using the library for the first time.

This example shows the starter dependencies for a new project in Android Studio with the library dependency added below the starter dependencies.

```
dependencies {
    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    implementation 'com.github.groupby:gb-android-tracker-client:1.0.3'
}
```

3. Perform a Gradle sync.

You'll know you've completed these steps properly if Android Studio has autocompletion help for the `GbTracker` class:

![image](https://user-images.githubusercontent.com/7719209/188748073-c11673ff-3187-4218-9403-d765ad8ccc93.png)
