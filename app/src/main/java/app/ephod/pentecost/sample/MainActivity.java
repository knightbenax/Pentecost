package app.ephod.pentecost.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.ephod.pentecost.library.paystack.PaymentView;

public class MainActivity extends AppCompatActivity {

    PaymentView paymentView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paymentView = findViewById(R.id.paymentView);

        button = paymentView.getPayButton();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentView.showLoader();
            }
        });

        paymentView.setBillContent("₦5000");
    }
}
