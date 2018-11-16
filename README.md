# Pentecost - Payment Android UI Library for PayStack

[![Knightbenax Approved](/knightbenax-approved-blue.svg)](https://twitter.com/knightbenax) [![Platform](/platform.svg)]()



Pentecost gives you a quick customizable PaymentView to use with PayStack. Simply show and hide as needed. 
Now supports Paystack payment with banks. 




![Screenshot](/two@2x.png?raw=true)


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
    <version>1.1.12</version> 
    <type>pom</type> 
</dependency>
``` 


### Gradle

```
implementation 'app.ephod.pentecost:pentecost:1.1.12'
```



## Setup

For card payments, you are set from the go. Only payment with banks gets a little tricky. First you have to get the 
list of Paystack's supported banks to populate the banks spinner from here - https://api.paystack.co/bank?gateway=emandate&pay_with_bank=true

Populate the spinner

```
paymentView.setBanksSpinner(String[] arrayOfBanks)
```

Then you are all set. Pentecost handles the birthday field by showing a datepicker dialog when the user taps. 



## Handling OTP and Phone Token

Paystack's pay with bank is a three step process where the user is asked to input his/her OTP sent from the bank and then the token sent to the registered phone number with the paying account. Ideally you can show your own OTP and Phone Token views but since you already have the Paystack SDK added, we can just borrow views from there to use. 

1. Send bank details and birthday to server which then sends to Paystack. 
2. Save the reference gotten from the response and show the OTP view. Send response to your server. Your server hits Paystack
3. You can save the reference again from the response or if you are hard guy, you can fetch the previously saved one. Show the Phone Token view. Send response to your server. Your server hits Paystack.

Paystack's OTP views makes use of sync locks to wait for user input, so you have to run in an asynctask.

```
private class BankOtpAsyncTask extends AsyncTask<Void, Void, String>{

        String reference, message;

        public BankOtpAsyncTask(String reference, String message){
            this.reference = reference;
            this.message = message;
        }

        @Override
        protected String doInBackground(Void... voids) {
            //this sets the message to show the user when the OTP view pops
            otpSingleton.setOtpMessage(message);

            Intent intent = new Intent(Activity.this, OtpActivity.class);
            startActivity(intent);

            synchronized (otpSingleton){
                try {
                    otpSingleton.wait();
                } catch (InterruptedException e) {
                    //showUniversalError("OTP Interrupted");
                }
            }


            return otpSingleton.getOtp();
        }

        @Override
        protected void onPostExecute(String otp) {
            super.onPostExecute(otp);
            if (otp != null) {
                //tell server. This is where we process the OTP/Phone Token entered
                sendBankOtpOrPhoneToken(reference, otp);
            } else {
                //showUniversalError("You did not provide an OTP");
                //showUniversalError("You did not provide a token");
            }
        }
    }
```

and then 

```
new BankOtpAsyncTask(reference,"Enter Bank Otp").execute();
```

The same can be done for the Phone Token.



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
    paymentView.getCardNumber()
```

#### Get CardCVV
```
    paymentView.getCardCVV()
```

#### Get Card Expiry Date
```
    paymentView.getCardExpDate()
```

#### Get Selected Bank
```
    paymentView.getBankName()
```

#### Get entered Account Number
```
    paymentView.getAccountNumber()
```

#### Get entered birthday (Paystack needs this for payments using banks)
```
    paymentView.getAccountHolderBirthday()
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

#### Set Pay Button listener

This sets the event that should fire when the user clicks the pay button either paying with card or bank. Pentecost already checks for empty views, so don't worry your action won't fire if user leaves the fields empty. 
 
```
    paymentView.setChargeListener(new PaymentView.ChargeListener() {
            @Override
            public void onChargeCard() {
                //Use Paystack's SDK chargeCard or whatever you want really.
            }


            @Override
            public void onChargeBank() {
                //send the bank details to your server
                //which then sends to Paystack's pay_with_bank api
            }

            @Override
            public void onSuccess() {
            }
    });
```


## Using this library?

If you are using this library, you can give me a quick shoutout on Twitter with a link to your app and I would showcase here. 

[@knightbenax](https://twitter.com/knightbenax)





