package app.ephod.pentecost.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.ephod.pentecost.library.paystack.PaymentView;

public class MainActivity extends AppCompatActivity {

    PaymentView paymentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
