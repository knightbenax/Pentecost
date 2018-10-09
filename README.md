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
        maven { url 'https://jitpack.io' }
        ...
    }
}
```
 

### Maven

```
<dependency> 
    <groupId>app.ephod.pentecost</groupId> 
    <artifactId>pentecost</artifactId> 
    <version>1.0.1</version> 
    <type>pom</type> 
</dependency>
``` 


### Gradle

```
implementation 'app.ephod.pentecost:pentecost:1.1.6'
```


## Usage - XML

#### Define in XML

```
<app.ephod.pentecost.pentecost.paystack.PaymentView
    android:layout_width="match_parent"
    android:id="@+id/paymentView"
    android:layout_height="match_parent">
```

#### Set Theme
```
    app:pentecostTheme="black|white"
```

#### Set Background Drawable
```
    app:pentecostBackground=""
```

#### Set Background Color
```
    app:pentecostBackgroundColor=""
```

#### Set Header Image Src
```
    app:pentecostHeaderSrc=""
```

## Usage - Code Behind
#### Set Theme
```
    paymentView.setPentecostTheme("black|white");
```

#### Set Background Drawable
```
    paymentView.setPentecostBackground(drawable);
```

#### Set Background Color
```
    paymentView.setPentecostBackgroundColor(color);
```

#### Set Billable Header

This is line that describes the payment to the user. Can leave blank

```
    paymentView.setBillHeader('')
```

#### Set Billable Amount

This shows the amount the user is paying, including the currency. This would be different from the value you would be sending to PayStack, which is in kobo

```
    paymentView.setBillContent('')
```

#### Set Card Number
```
    paymentView.setCardNumber('')
```

#### Set CardCVV
```
    paymentView.setCardCVV('')
```

#### Set Card Expiry Date
```
    paymentView.setCardExpDate('')
```

#### Get Card Number
```
    paymentView.getCardNumber('')
```

#### Get CardCVV
```
    paymentView.getCardCVV('')
```

#### Get Card Expiry Date
```
    paymentView.getCardExpDate('')
```

#### Put PaymentView in loading mode

This can be used when the transaction is being made to PayStack or to indicate an initiated action is in progress 
```
    paymentView.showLoader();
```

#### Remove PaymentView from loading mode
 
```
    paymentView.hideLoader();
```

#### Get PaymentView Pay Button

This returns a Button. You can add a click listener to it and process events.
 
```
    paymentView.getPayButton()
```

## Using this library?

If you are using this library, you can give me a quick shoutout on Twitter with a link to your app and I would showcase here. 

[@knightbenax](https://twitter.com/knightbenax)





