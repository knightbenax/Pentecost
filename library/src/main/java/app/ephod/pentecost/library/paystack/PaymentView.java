package app.ephod.pentecost.library.paystack;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import app.ephod.pentecost.library.R;

public class PaymentView extends LinearLayout {
    public PaymentView(Context context) {
        super(context);
        initView(context);
    }

    public PaymentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PaymentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PaymentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public void initView(Context context){
        View view = inflate(context, R.layout.paymentview, this);
        //view.findViewById(R.id.hea)
        ImageView headerView = view.findViewById(R.id.header_view);
        RelativeLayout parentView = view.findViewById(R.id.parent_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headerView.setClipToOutline(true);
            parentView.setClipToOutline(true);

        }
    }
}
