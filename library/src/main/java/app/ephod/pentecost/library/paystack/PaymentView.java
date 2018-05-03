package app.ephod.pentecost.library.paystack;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import app.ephod.pentecost.library.R;

public class PaymentView extends LinearLayout {

    private Context mContext;
    private AttributeSet attributeSet;
    private int styleAttr;
    private View view;



    Drawable headerSrc;
    String theme;
    Drawable background;
    String backgroundColor;


    public PaymentView(Context context) {
        super(context);
        this.mContext = context;
        initView(context);
    }

    public PaymentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attributeSet = attrs;
        initView(context);
    }

    public PaymentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.attributeSet = attrs;
        this.styleAttr = defStyleAttr;
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PaymentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        this.attributeSet = attrs;
        this.styleAttr = defStyleAttr;
        initView(context);
    }

    public void setHeaderSrc(Drawable headerSrc) {
        this.headerSrc = headerSrc;
    }

    public void setPentecostTheme(String theme) {
        this.theme = theme;
    }

    public void setPentecostBackground(Drawable background) {
        this.background = background;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void initView(Context context){
        View view = inflate(context, R.layout.paymentview, this);
        //view.findViewById(R.id.hea)
        ImageView headerView = view.findViewById(R.id.header_view);
        RelativeLayout parentView = view.findViewById(R.id.parent_view);

        TypedArray arr = mContext.obtainStyledAttributes(attributeSet, R.styleable.PaymentView, styleAttr, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headerView.setClipToOutline(true);
            parentView.setClipToOutline(true);
        }

        theme = arr.getString(R.styleable.PaymentView_theme);
        background = arr.getDrawable(R.styleable.PaymentView_background);
        backgroundColor = arr.getString(R.styleable.PaymentView_backgroundColor);
        headerSrc = arr.getDrawable(R.styleable.PaymentView_headerSrc);

        arr.recycle();
    }
}
