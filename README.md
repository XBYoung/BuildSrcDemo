# BuildSrcDemo
具体参考这篇文章：https://www.jianshu.com/p/0e7e0ac8c0e5

步骤1：

首先要集成Kotlin，AndroidStudio3.0以后自动集成，生成项目时勾选即可，之前的版本下载插件。

步骤二:

2.1.新建目录 buildSrc 与app同级




2.2 新建文件 build.gradle.kts 如下，同步gradle




plugins{

    `kotlin-dsl`

}
2.3.如目录新建 Dependencies.kt （自定义）文件，完成之后build下

/**

* 版本

*/

object Versions {

    val compileSdkVersion =27

    val minSdkVersion =19

    val targetSdkVersion =27

    val versionCode =1

    val versionName ="v1.0.0"

    val support_lib ="27.1.0"

    val retrofit ="2.3.0"

    val rxjava ="2.1.9"

    val constraint ="1.0.2"

}

/**

* app

*/

object App {

val applicationId ="chinamobile.iot.mykotlinbuilddemo"

}

/**

* 依赖包

*/

object Libs {

    val constraint_layout ="com.android.support.constraint:constraint-layout:${Versions.constraint}"

    val support_annotations ="com.android.support:support-annotations:${Versions.support_lib}"

    val support_appcompat_v7 ="com.android.support:appcompat-v7:${Versions.support_lib}"

    val support_v4 ="com.android.support:support-v4:${Versions.support_lib}"

    val retrofit ="com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    val retrofit_rxjava_adapter ="com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"

    val rxjava ="io.reactivex.rxjava2:rxjava:${Versions.rxjava}"

}
步骤三：在Moudle下build.gradle中引用

applyplugin:'com.android.application'

applyplugin:'kotlin-android'

applyplugin:'kotlin-android-extensions'

android {

compileSdkVersion Versions.compileSdkVersion

    defaultConfig {

applicationId App.applicationId

        minSdkVersion Versions.minSdkVersion

        targetSdkVersion Versions.targetSdkVersion

        versionCode Versions.versionCode

        versionName Versions.versionName

        testInstrumentationRunner"android.support.test.runner.AndroidJUnitRunner"

    }

buildTypes {

release {

minifyEnabledfalse

            proguardFiles getDefaultProguardFile('proguard-android.txt'),'proguard-rules.pro'

        }

}

}

dependencies {

implementation fileTree(include: ['*.jar'],dir:'libs')

implementation Libs.support_annotations

implementation Libs.support_appcompat_v7

implementation Libs.constraint_layout

}
这里应用和代码中直接引用静态常量一样，可补全可查看，非常简洁直观。

注意：kotlin依赖放到Project/build.gradle中

dependencies {

    classpath'com.android.tools.build:gradle:3.1.3'

    classpath"org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"

    classpath"org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"

    // NOTE: Do not place your application dependencies here; they belong

// in the individual module build.gradle files

}
