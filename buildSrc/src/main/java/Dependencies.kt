    /**
     * 版本
     */

    object Versions {

        val compileSdkVersion =27

        val minSdkVersion =19

        val targetSdkVersion =27

        val versionCode =1

        val versionName ="v1.0.0"

        val kotlin_version = "1.2.30"

        val support_lib ="27.1.1"

        val retrofit ="2.3.0"

        val rxjava ="2.1.9"

        val constraint ="1.0.2"

        val material_dialogs = "0.9.6.0"

        val eventBus = "3.0.0"

    }

    /**
     * app
     */

    object App {

        val applicationId ="com.young.L.easyApp"

    }

    /**
     * 依赖包
     */

    object Libs {
        val junit = "junit:junit:4.12"
        val permissionsdispatcher  ="com.github.hotchemi:permissionsdispatcher-processor:2.1.3"
        val material_dialogs_core = "com.afollestad.material-dialogs:core:${Versions.material_dialogs}"
        val material_dialogs_commons = "com.afollestad.material-dialogs:commons:${Versions.material_dialogs}"
        val kotlin_stdlib_jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
        val kotlin_stdlib_jre7 = "org.jetbrains.kotlin:kotlin-stdlib-jre7:${Versions.kotlin_version}"
        val runner = "com.android.support.test:runner:1.0.2"
        val espresso_core = "com.android.support.test.espresso:espresso-core:3.0.2"
        val constraint_layout ="com.android.support.constraint:constraint-layout:${Versions.constraint}"
        val support_annotations ="com.android.support:support-annotations:${Versions.support_lib}"
        val support_appcompat_v7 ="com.android.support:appcompat-v7:${Versions.support_lib}"
        val support_v4 ="com.android.support:support-v4:${Versions.support_lib}"

        val retrofit2 ="com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        val retrofit_rxjava_adapter2 ="com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        val rxjava2 ="io.reactivex.rxjava2:rxjava:${Versions.rxjava}"

        val eventbus =  "org.greenrobot:eventbus:${Versions.eventBus}"
        val zxing =  "com.google.zxing:core:3.2.1"
        val greendao =  "org.greenrobot:greendao:3.2.0"
        val  flyco = "com.flyco.tablayout:FlycoTabLayout_Lib:2.1.2@aar"


    }
