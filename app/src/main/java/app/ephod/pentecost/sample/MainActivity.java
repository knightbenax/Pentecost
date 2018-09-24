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

        String[] arraySpinner = new String[]{
                "Access Bank" , "Citibank", "Diamond Bank", "Dynamic Standard Bank", "Ecobank Nigeria", "Fidelity Bank Nigeria",
                "First Bank of Nigeria", "First City Monument Bank", "Guaranty Trust Bank", "Heritage Bank Plc","Jaiz Bank",
                "Keystone Bank Limited", "Providus Bank Plc", "Skye Bank", "Stanbic IBTC Bank Nigeria Limited", "Standard Chartered Bank",
                "Sterling Bank", "Suntrust Bank Nigeria Limited", "Union Bank of Nigeria", "United Bank for Africa", "Unity Bank Plc",
                "Wema Bank", "Zenith Bank"
        };

        paymentView.setBanksSpinner(arraySpinner);

        paymentView.setBillContent("₦5000");
    }
}
