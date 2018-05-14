# Pentecost - Payment Android UI Library for PayStack

[![Knightbenax Approved](/knightbenax-approved-blue.svg)](https://twitter.com/knightbenax) [![Platform](/platform.svg)]()


Pentecost gives you a quick customizable PaymentView to use with PayStack. Simply show and hide as needed. 


![Screenshot](/one@2x.png?raw=true)


## Why PayStack?

Why not PayStack? PayStack is currently one of the easiest and fastest way to receive payments. Their Android SDK gets you set up in a few minutes.


## Installation

Add to your root build.gradle at the end of repositories 

```
allprojects {
    repositories {
        ...
        jcenter()
        ...
    }
}
```
 

### Maven

```
<dependency> 
    <groupId>app.ephod.pentecost</groupId> 
    <artifactId>pentecost</artifactId> 
    <version>1.0</version> 
    <type>pom</type> 
</dependency>
``` 


### Gradle

```
compile 'app.ephod.pentecost:pentecost:1.0'
```


## Usage

### Define in XML

```
<app.ephod.pentecost.pentecost.paystack.PaymentView
    android:layout_width="match_parent"
    android:id="@+id/paymentView"
    android:layout_height="match_parent">
```

### Set Theme

```
    app:pentecostTheme="black|white"
```

### Set Background Drawable
```
    app:pentecostBackground=""
```

### Set Background Color
```
    app:pentecostBackgroundColor=""
```

### Set Header Image Src
```
    app:pentecostHeaderSrc=""
```




