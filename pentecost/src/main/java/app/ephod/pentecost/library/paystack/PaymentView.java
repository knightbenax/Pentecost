package app.ephod.pentecost.library.paystack;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.support.annotation.Dimension;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import app.ephod.pentecost.pentecost.R;
import io.ghyeok.stickyswitch.widget.StickySwitch;

public class PaymentView extends LinearLayout {

    private Context mContext;
    private AttributeSet attributeSet;
    private int styleAttr;
    private View view;

    Drawable headerSrc;
    String theme = "0";

    String headerTitleText = "";
    String headerBackgroundBoolean = "0";
    Drawable background;
    int backgroundColor;
    float borderRadius;
    String billHeader;
    String billContent;

    EditText creditNumber;
    EditText creditMonth;
    EditText creditCCV;

    ImageView headerView;


    LinearLayout secondParentView;
    RelativeLayout parentView;
    LinearLayout cardHolder, bankHolder;
    StickySwitch stickySwitch;

    TextView headerTitle, headerContent;

    Button payButton;
    ProgressBar progressBar;


    int formerLength = 0;

    public Button getPayButton() {
        return payButton;
    }

    public TextView getHeaderTitleView() {
        return headerTitle;
    }
    public TextView getHeaderContentView() {
        return headerContent;
    }



    Integer[] imageArray = {R.drawable.visa, R.drawable.mastercard, R.drawable.discover, R.drawable.american_express};

    public void setCardNumber(String mCreditNumber) {
        this.creditNumber.setText(mCreditNumber);
    }

    public void setCardExpDate(String mCreditMonth) {
        this.creditMonth.setText(mCreditMonth);
    }

    public void setCardCCV(String mCreditCCV) {
        this.creditCCV.setText(mCreditCCV);
    }

    public String getCardNumber() {
        return creditNumber.getText().toString();
    }

    public String getCardExpDate() {
        return creditMonth.getText().toString();
    }

    public String getCardCCV() {
        return creditCCV.getText().toString();
    }

    public PaymentView(Context context) {
        super(context);
        this.mContext = context;
        initView(context);
    }

    public PaymentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attributeSet = attrs;
        initView(context);
    }

    public PaymentView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        headerImage.setImageDrawable(headerSrc);
    }


    public void setPentecostTheme(String theme) {
        this.theme = theme;
    }

    public void setBillHeader(String billHeader) {
        this.billHeader = billHeader;
        headerTitle.setText(billHeader);
    }

    public void setBillContent(String billContent) {
        this.billContent = billContent;
        headerContent.setText(billContent);
    }

    public void setPentecostBackground(Drawable background) {
        this.background = background;
        secondParentView.setBackgroundResource(0);
        parentView.setBackground(background);
    }

    public void setPentecostBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;

        //Need to tidy this up
        //String temp = String.valueOf(backgroundColor);
        //int bgColor = Color.parseColor(temp);
        secondParentView.setBackground(null);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(backgroundColor);
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(getResources().getDimension(R.dimen.size_5));
        //gradientDrawable.setStroke((int)getResources().getDimension(R.dimen.size_2), getResources().getColor(R.color.black));
        parentView.setBackground(gradientDrawable);
        //parentView.setBackgroundColor(backgroundColor);
    }

    public void showLoader(){
        progressBar.setVisibility(VISIBLE);
        payButton.setVisibility(INVISIBLE);
        payButton.setEnabled(false);

        creditNumber.setEnabled(false);
        creditMonth.setEnabled(false);
        creditCCV.setEnabled(false);
        //payButton.blockTouch();
        //payButton.morphToProgress(R.color.white, R.dimen.size_2, width, R.dimen.size_14, 10, R.color.colorAccent);
    }

    public void hideLoader(){
        //payButton.unblockTouch();
        progressBar.setVisibility(GONE);
        payButton.setVisibility(VISIBLE);
        payButton.setEnabled(true);

        creditNumber.setEnabled(true);
        creditMonth.setEnabled(true);
        creditCCV.setEnabled(true);
    }

    private void tintProgressBar(ProgressBar mProgressBar){
        // fixes pre-Lollipop progressBar indeterminateDrawable tinting
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            Drawable wrapDrawable = DrawableCompat.wrap(mProgressBar.getIndeterminateDrawable());
            DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(getContext(), R.color.colorPayAccent));
            mProgressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
        } else {
            mProgressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPayAccent), PorterDuff.Mode.SRC_IN);
        }
    }

    ImageView headerImage;

    private void initView(Context context){

        //inflate layout
        View view = inflate(context, R.layout.paymentview, this);

        //init views
        headerView = view.findViewById(R.id.header_view);
        parentView = view.findViewById(R.id.parent_view);
        //EditText creditEdit = view.findViewById(R.id.credit_card_number);
        //EditText ccvEdit = view.findViewById(R.id.credit_card_ccv);
        //EditText dateEdit = view.findViewById(R.id.credit_card_expiry);
        ImageView secureLogo = view.findViewById(R.id.secure_logo);

        secondParentView = view.findViewById(R.id.second_parent);

        TextView billHeaderText = view.findViewById(R.id.bill_header);
        TextView billHeaderContent = view.findViewById(R.id.bill_content);

        TextView left_indicator = findViewById(R.id.left_indicator);
        TextView right_indicator = findViewById(R.id.right_indicator);

        creditNumber = findViewById(R.id.credit_card_number);
        creditMonth = findViewById(R.id.credit_card_expiry);
        creditCCV = findViewById(R.id.credit_card_ccv);

        cardHolder = findViewById(R.id.card_details_section);
        bankHolder = findViewById(R.id.bank_details_section);
        payButton = findViewById(R.id.pay_button);
        progressBar = findViewById(R.id.progress_bar);
        headerImage = findViewById(R.id.header_view);

        headerTitle = findViewById(R.id.bill_header);
        headerContent = findViewById(R.id.bill_content);

        tintProgressBar(progressBar);
        setTextWatchers();

        //we are setting the background resource here again because MorphButton overrides what is set in the xml
        payButton.setBackgroundResource(R.drawable.payment_button);



        TypedArray arr = mContext.obtainStyledAttributes(attributeSet, R.styleable.PaymentView, styleAttr, 0);

        GradientDrawable gradientDrawable = new GradientDrawable();
        GradientDrawable parentGradientDrawable = new GradientDrawable();

        borderRadius =  arr.getDimension(R.styleable.PaymentView_pentecostHeaderBorderRadius, getResources().getDimension(R.dimen.size_5));

        Log.e("Radius", String.valueOf(borderRadius));

        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(getResources().getColor(R.color.transparent));
        float[] image_radii = {borderRadius, borderRadius, 0f, 0f};
        gradientDrawable.setCornerRadii(image_radii);

        Log.e("Radius", String.valueOf(borderRadius));


        parentGradientDrawable.setShape(GradientDrawable.RECTANGLE);
        parentGradientDrawable.setColor(getResources().getColor(R.color.transparent));
        float[] radiii = {borderRadius, borderRadius, borderRadius, borderRadius};
        parentGradientDrawable.setColor(getResources().getColor(R.color.transparent));
        int stroke = (int)getResources().getDimension(R.dimen.size_2);
        parentGradientDrawable.setStroke(stroke, getResources().getColor(R.color.black));
        parentGradientDrawable.setCornerRadius(borderRadius);

        //gradientDrawable.mutate();
        //parentGradientDrawable.mutate();

        //Comeback to this later
        //headerView.setBackground(gradientDrawable);
        //parentView.setBackground(parentGradientDrawable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headerView.setClipToOutline(true);
            parentView.setClipToOutline(true);
        }

        theme = arr.getString(R.styleable.PaymentView_pentecostTheme);

        headerBackgroundBoolean = arr.getString(R.styleable.PaymentView_pentecostHeaderBackground);
        background = arr.getDrawable(R.styleable.PaymentView_pentecostBackgroundDrawable);
        backgroundColor = arr.getInt(R.styleable.PaymentView_pentecostBackgroundColor, R.color.default_bg);
        headerSrc = arr.getDrawable(R.styleable.PaymentView_pentecostHeaderBackgroundDrawable);
        headerTitleText = arr.getString(R.styleable.PaymentView_pentecostHeaderTitle);

        if (theme == null){
            theme = "0";
        }

        if (headerBackgroundBoolean == null){
            headerBackgroundBoolean = "0";
        }

        arr.recycle();

        if (theme.equals("0")){
            parentView.setBackgroundResource(R.drawable.round_dark_bg);
            creditNumber.setBackgroundResource(R.drawable.edit_text_bg);
            creditCCV.setBackgroundResource(R.drawable.edit_text_bg);
            creditMonth.setBackgroundResource(R.drawable.edit_text_bg);
            secureLogo.setImageResource(R.drawable.white_paystack_logo);
            billHeaderText.setTextColor(getResources().getColor(R.color.white));
            billHeaderContent.setTextColor(getResources().getColor(R.color.white));
            left_indicator.setTextColor(getResources().getColor(R.color.white));
            right_indicator.setTextColor(getResources().getColor(R.color.white));
        } else if (theme.equals("1")) {
            parentView.setBackgroundResource(R.drawable.round_white_bg);
            creditNumber.setBackgroundResource(R.drawable.edit_text_white_bg);
            creditCCV.setBackgroundResource(R.drawable.edit_text_white_bg);
            creditMonth.setBackgroundResource(R.drawable.edit_text_white_bg);
            secureLogo.setImageResource(R.drawable.blue_paystack_logo);
            billHeaderText.setTextColor(getResources().getColor(R.color.black));
            billHeaderContent.setTextColor(getResources().getColor(R.color.black));
            left_indicator.setTextColor(getResources().getColor(R.color.black));
            right_indicator.setTextColor(getResources().getColor(R.color.black));
        }

        if (headerBackgroundBoolean.equals("1")){
            headerImage.setImageResource(0);
        }

        Log.e("TAG", theme);

        if (background != null){
            secondParentView.setBackground(null);
            parentView.setBackground(background);
        }

        if (backgroundColor != R.color.default_bg){
            secondParentView.setBackground(null);
            parentView.setBackgroundColor(backgroundColor);
        }

        if (headerSrc != null){
            headerImage.setImageDrawable(headerSrc);
        }

        if (headerTitleText != null){
            headerTitle.setText(headerTitleText);
        }

        stickySwitch = view.findViewById(R.id.sticky_switch);
        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(StickySwitch.Direction direction, String s) {
                switch (direction){
                    case LEFT:
                        setVisibility(bankHolder, View.GONE);
                        setVisibility(cardHolder, View.VISIBLE);
                        break;
                    case RIGHT:
                        setVisibility(bankHolder, View.VISIBLE);
                        setVisibility(cardHolder, View.GONE);
                        break;
                }
            }
        });

        initSpinner();
    }


    private void setVisibility(View view, int visibility){
        view.setVisibility(visibility);
    }

    public static ArrayList<String> listOfPattern()
    {
        ArrayList<String> listOfPattern=new ArrayList<String>();

        String ptVisa = "^4[0-9]$";

        listOfPattern.add(ptVisa);

        String ptMasterCard = "^5[1-5]$";

        listOfPattern.add(ptMasterCard);

        String ptDiscover = "^6(?:011|5[0-9]{2})$";

        listOfPattern.add(ptDiscover);

        String ptAmeExp = "^3[47]$";

        listOfPattern.add(ptAmeExp);

        return listOfPattern;
    }

    private String[] getBankSpinner() {
        return arraySpinner;
    }

    public void setBanksSpinner(String[] arraySpinner) {
        int length = arraySpinner.length + 1;

        String[] tempSpinner = new String[length];

        tempSpinner[0] = "Select Bank";

        System.arraycopy(arraySpinner, 0, tempSpinner, 1, arraySpinner.length);

        this.arraySpinner = tempSpinner;

        Spinner s = findViewById(R.id.bank_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.layout_spinner_item, this.arraySpinner);
        adapter.setDropDownViewResource(R.layout.layout_spinner_item_drop);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                TextView tv = (TextView) view;

                if (position == 0){
                    tv.setTextColor(getResources().getColor(R.color.spinner_hint));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.black));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    String[] arraySpinner;

    private void initSpinner(){
        arraySpinner = new String[]{};

        Spinner s = findViewById(R.id.bank_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.layout_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(R.layout.layout_spinner_item_drop);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                TextView tv = (TextView) view;

                if (position == 0){
                    tv.setTextColor(getResources().getColor(R.color.spinner_hint));
                } else {
                    tv.setTextColor(getResources().getColor(R.color.black));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setTextWatchers() {

        creditNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int j, int j1, int j2) {

                String cardNumber = charSequence.toString();

                if (cardNumber.length() > 2){

                    for (int i = 0; i <  listOfPattern().size(); i++){

                        if (cardNumber.substring(0, 2).matches(listOfPattern().get(i))){

                            creditNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0,  imageArray[i], 0);
                            //break;

                        } else {
                            //creditNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0,  R.drawable.visa, 0);
                            //break;

                        }

                    }

                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String cardNumber = creditNumber.getText().toString();

                if (!cardNumber.equalsIgnoreCase("")){

                    for (int i = 0; i < listOfPattern().size(); i++){

                        if (cardNumber.matches(listOfPattern().get(i))){
                            creditNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0,  imageArray[i], 0);
                        }

                    }

                } else {

                    creditNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0,  0, 0);

                }

            }
        });

        creditMonth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                formerLength = charSequence.length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > formerLength){ //User is adding text
                    if(editable.length() == 2){
                        editable.append("/");
                    }
                } else {
                    if(editable.length() == 2){
                        editable.delete(editable.length() - 1, editable.length());
                    }
                }

            }
        });


    }
}
