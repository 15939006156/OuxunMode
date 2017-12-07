# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\sdk\sdk_8/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
# Android.useDeprecatedNdk=true
#okhttputils
-keepclassmembers class fqcn.of.javascript.interface.for.webview{
 public *;
}

#制定代码的压缩级别
#-optimizationpasses  5
#包名不混合大小写
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
#优化
-dontoptimize
#预校验
-dontpreverify
#混淆时是否记录日志
-verbose
#混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#保护注解
-keepattributes *Annotation*

#保持哪些类不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.support.v4.app.Fragment

#忽略警告
-ignorewarning

#如果引用了v4或者v7包
-dontwarn android.support.**

#保持native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义控件类不被混淆
-keepclasseswithmembers class *{
   public <init> (android.content.Context,android.util.AttributeSet);
}
#保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity{
public void *(android.view.View);
}

-keep public class * extends android.view.View{
 public <init>(android.content.Context);
 public <init>(android.content.Context,android.util.AttributeSet);
 public <init>(android.content.Context,android.util.AttributeSet,int);
 public void set*(...);
}

#保持Parcelable不被混淆
-keep class * implements android.os.Parcelable{
 public static final android.os.Parcelable$Creator *;
}
#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serivlizable

#保持Serializable 不被混淆并且enum类也不被混淆
-keepclassmembers class * implements java.io.Serializable{
 static final long serialVersionUID;
 private static final java.io.ObjectStreamField[] serialPersistentFields;
 !static !transient <fields>;
 !private <fields>;
 !private <methods>;
 private void writeObject(java.io.ObjectOutputStream);
 private void readObject(java.io.ObjectInputStream);
 java.lang.Object writeReplace();
 java.lang.Object readResolve();
}
#保持枚举类enum类不被混淆
-keepclassmembers enum *{
public static **[] values();
public static ** valueOf(java.lang.String);
}
-keepclassmembers class *{
 public void *ButtonClicked(android.view.View);
}

#不混淆资源类
-keepclassmembers class **.R$*{
public static <fields>;
}
#避免混淆泛型 如果混淆报错建议关掉
-keepattributes Signature

#混淆第三方库

#okhttp
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers public class * extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(android.view.View);
}

#这是glide的混淆
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# for DexGuard only
-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

#fastjson
-dontwarn com.alibaba.fastjson.**
-keep class com.baidu.** { *; }
-keep class com.alibaba.fastjson.** { *; }
-keepattributes Signature
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
