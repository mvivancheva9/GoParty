-dontshrink
-dontoptimize
-dontpreverify

-dontwarn javax.management.**
-dontwarn java.lang.management.**
-dontwarn org.apache.commons.logging.**
-dontwarn android.support.**
-dontwarn android.security.NetworkSecurityPolicy

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.ContentProvider
-keep class javax.** { *; }
-keep class org.** { *; }

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclasseswithmembers class * {
    void onClick*(...);
}

-keep class com.google.android.gms.common.GooglePlayServicesUtil {*;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {*;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {*;}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keep @interface com.google.android.gms.common.annotation.KeepName
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep public class com.google.android.gms.flags.impl.FlagProviderImpl {
  public <fields>; public <methods>;
}

-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.** *;
}
-keepclassmembers class * {
    @retrofit.** *;
}

-dump proguard/class_files.txt
-printseeds proguard/seeds.txt
-printusage proguard/unused.txt
-printmapping proguard/mapping.txt
