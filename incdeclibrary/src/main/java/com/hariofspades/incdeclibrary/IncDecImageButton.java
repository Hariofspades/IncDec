package com.hariofspades.incdeclibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Hari on 03/12/16.
 */

public class IncDecImageButton extends RelativeLayout {

    /** Core Items*/
    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private View view;

    /** Static Items*/
    public static String TYPE_INTEGER="INTEGER";
    public static String TYPE_FLOAT="FLOAT";
    public static String TYPE_ARRAY="ARRAY";
    private static final String TAG = "INKDEC";
    public static String INCREMENT="INCREMENT";
    public static String DECREMENT="DECREMENT";
    private static final int VERTICAL= LinearLayout.VERTICAL;
    private static final int HORIZONTAL=LinearLayout.HORIZONTAL;

    /** Attributes */
    private float initialValue=0;
    private float finalValue=Float.MAX_VALUE;
    private Float textSize;
    private int textColor;
    private Drawable leftSrc;
    private Drawable rightSrc;
    private Drawable leftBackground;
    private Drawable rightBackground;
    private int leftButtonTint;
    private int rightButtonTint;
    private int leftDrawableTint;
    private int rightDrawableTint;
    private float interval;
    private int int_val=1;
    private int orientation=LinearLayout.HORIZONTAL;

    /** Listeners */
    private IncDecImageButton.OnValueChangeListener mValueListener;
    private IncDecImageButton.OnClickListener mListener;

    /** Components */
    ImageButton leftButton;
    ImageButton rightButton;
    TextView counter;
    LinearLayout layout;

    /** Helpers */
    private float currentValue;
    private int currentValue_int;
    private int initialValue_int;
    private int finalValue_int;
    private ArrayList<String> array;
    private String type=TYPE_INTEGER;
    private String precesion="%.2f";
    private String leftType;
    private String rightType;
    private int index=0;
    private int startIndex;
    private boolean isLeftButtonLongPressed = false;
    private boolean isRightButtonLongPressed = false;
    private boolean leftLongPress=false;
    private boolean rightLongPress=false;
    private long seconds=500;
    private int oldIntValue;
    private int oldFloatValue;
    private int oldIndex;
    private int startIndexVal=0, stopIndexVal=0;



    public IncDecImageButton(Context context) {
        super(context);
        this.mContext=context;
        initView();
    }

    public IncDecImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        this.attrs=attrs;
        initView();
    }

    public IncDecImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        this.attrs=attrs;
        this.styleAttr=defStyleAttr;
        initView();
    }

    private void initView(){
        this.view=this;
        inflate(mContext,R.layout.incdec_imagebutton,this);
        final Resources resources = getResources();
        final int defaultColor = resources.getColor(R.color.colorPrimary);
        final int defaultTextColor = resources.getColor(R.color.colorTextPrimary);
        final int white=resources.getColor(R.color.white);
        /** Getting the attributes from attrs.xml */
        TypedArray arr = mContext.obtainStyledAttributes(attrs,R.styleable.IncDecImageButton,
                styleAttr,0);
        leftButtonTint=arr.getColor(R.styleable.IncDecImageButton_leftButtonColorTintB,
                0);
        rightButtonTint=arr.getColor(R.styleable.IncDecImageButton_rightButtonColorTinitB,
                0   );
        leftSrc=arr.getDrawable(R.styleable.IncDecImageButton_leftDrawableB);
        rightSrc=arr.getDrawable(R.styleable.IncDecImageButton_rightDrawableB);
        leftDrawableTint=arr.getColor(R.styleable.IncDecImageButton_leftDrawableTintB,white);
        rightDrawableTint=arr.getColor(R.styleable.IncDecImageButton_rightDrawableTintB,white);
        textSize=arr.getFloat(R.styleable.IncDecImageButton_textSizeB,13);
        textColor=arr.getColor(R.styleable.IncDecImageButton_textColorB,defaultTextColor);
        leftBackground=arr.getDrawable(R.styleable.IncDecImageButton_leftBackgroundB);
        rightBackground=arr.getDrawable(R.styleable.IncDecImageButton_rightBackgroundB);
        /** Component declaration */
        leftButton=(ImageButton) findViewById(R.id.decrement_button);
        rightButton=(ImageButton) findViewById(R.id.increment_button);
        counter=(TextView) findViewById(R.id.number_counter);
        layout=(LinearLayout) findViewById(R.id.layout);

        setupLeftButton(leftButton,leftSrc,leftButtonTint,leftDrawableTint,leftBackground);
        setupRightButton(rightButton,rightSrc,rightButtonTint,rightDrawableTint,rightBackground);
        initLongClickListener(leftButton,rightButton,counter);

        arr.recycle();


    }

    private void setupRightButton(ImageButton rightButton, Drawable rightSrc,
                                  int rightButtonTint, int rightDrawableTint,Drawable background) {
        if(rightSrc!=null) {
            rightSrc.setTintList(new ColorStateList(new int[][]{new int[]{0}},
                    new int[]{rightDrawableTint}));
            rightButton.setImageDrawable(rightSrc);
        }
        rightButton.setBackgroundTintList(new ColorStateList(new int[][]{new int[]{0}},
                new int[]{rightButtonTint}));
        rightButton.setBackground(background);
    }

    private void setupLeftButton(ImageButton leftButton, Drawable leftSrc,
                                 int leftButtonTint, int leftDrawableTint,Drawable background) {
        if(leftSrc!=null) {
            leftSrc.setTintList(new ColorStateList(new int[][]{new int[]{0}},
                    new int[]{leftDrawableTint}));
            leftButton.setImageDrawable(leftSrc);
        }
        leftButton.setBackgroundTintList(new ColorStateList(new int[][]{new int[]{0}},
                new int[]{leftButtonTint}));
        leftButton.setBackground(background);

    }

    private void setupConfiguration(int orientation, String type) {
        if(orientation==LinearLayout.VERTICAL)
            setLayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT);
        else
            setLayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,0);

        fixOrientation(layout,orientation);

        if(type.equals(TYPE_ARRAY)){

        }else if(type.equals(TYPE_FLOAT)){

        }else{

        }
    }

    private void fixOrientation(LinearLayout layout, int orientation) {
        layout.setOrientation(orientation);
    }

    public void setBackgroundforButton(Drawable leftBackground,Drawable rightBackground){
        leftButton.setBackground(leftBackground);
        rightButton.setBackground(rightBackground);
    }

    public void setLayoutParams(int first, int second){
        leftButton.getLayoutParams().height=first;
        leftButton.getLayoutParams().width=second;
        rightButton.getLayoutParams().height=first;
        rightButton.getLayoutParams().width=second;
        counter.getLayoutParams().height=first;
        counter.getLayoutParams().width=second;
    }

    private void setTextCounter(TextView counter, Float textSize, int textColor, float initialValue
            ,int flag,float currentValue) {
        if(flag==0)
            counter.setText(String.valueOf(currentValue));
        else if(flag==1)
            counter.setText(String.valueOf(currentValue));
        else
            counter.setText(String.valueOf((int)currentValue));
        counter.setTextSize(textSize);
        counter.setTextColor(textColor);
    }

    /** get Configuration information */
    public void setConfiguration(int orientation,String type,String leftType,String rightType){
        this.orientation=orientation;
        this.type=type;
        setupConfiguration(this.orientation,this.type);
        this.leftType=leftType;
        this.rightType=rightType;
    }

    public void setupValues(float initialValue,float finalValue, float interval,float startValue){
        this.initialValue=initialValue;
        this.finalValue=finalValue;
        this.interval=interval;
        if(type.equals(TYPE_ARRAY)){

        }if(type.equals(TYPE_FLOAT)){
            currentValue=startValue;
            setTextCounter(counter,textSize,textColor,initialValue,1,currentValue);
        }else if(type.equals(TYPE_INTEGER)){
            finalValue_int=(int)finalValue;
            initialValue_int=(int)initialValue;
            currentValue_int=(int)startValue;
            setTextCounter(counter,textSize,textColor,initialValue_int,2,currentValue);
        }
    }

    public void setValue(float startValue){

        if(type.equals(TYPE_FLOAT)){
            setTextCounter(counter,textSize,textColor,initialValue,1,startValue);
        }else if(type.equals(TYPE_INTEGER)) {
            setTextCounter(counter,textSize,textColor,initialValue_int,2,startValue);
        }

    }

    public void setArrayValue(int index){
        try {
            this.startIndex = index;
            counter.setText(array.get(startIndex));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean checkIncVaidation(int startValue, int minimum, int maximum){
        if((startValue>=minimum && startValue<=maximum)&&(startValue!=maximum)) {
            return true;
        }
        else {
            return false;
        }
    }


    private boolean checkDecVaidation(int startValue, int minimum, int maximum){
        if((startValue>=minimum && startValue<=maximum)&&(startValue!=minimum)) {
            return true;
        }
        else {
            return false;
        }
    }


    private void initClickListener(ImageButton leftButton, ImageButton rightButton, final TextView counter) {
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!leftLongPress) {
                    if (leftType.equals(INCREMENT))
                        IncrementAction();
                    else if (leftType.equals(DECREMENT))
                        DecrementAction();
                    else
                        Log.e(TAG, "invalid Type");
                }
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.i(TAG,"increment");
                if(!rightLongPress) {
                    if (rightType.equals(INCREMENT))
                        IncrementAction();
                    else if (rightType.equals(DECREMENT))
                        DecrementAction();
                    else
                        Log.e(TAG, "invalid Type");
                }
            }
        });
    }

    private void initLongClickListener(final ImageButton leftButton, ImageButton rightButton,
                                       final TextView counter){
        leftButton.setOnTouchListener(speakLeftTouchListener);
        leftButton.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(leftLongPress) {
                    isLeftButtonLongPressed = true;
                    if (leftType.equals(INCREMENT))
                        startIncrementTimerThread();
                    else if (leftType.equals(DECREMENT))
                        startDecrementTimerThread();
                    else
                        Log.e(TAG, "invalid Type");
                    return true;
                }else {
                    return false;
                }
            }
        });

        rightButton.setOnTouchListener(speakRightTouchListener);
        rightButton.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(rightLongPress) {
                    isRightButtonLongPressed = true;
                    if (rightType.equals(INCREMENT))
                        startIncrementTimerThread();
                    else if (rightType.equals(DECREMENT))
                        startDecrementTimerThread();
                    else
                        Log.e(TAG, "invalid Type");
                    return true;
                }else{
                    return false;
                }
            }
        });

    }

    private View.OnTouchListener speakLeftTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View pView, MotionEvent pEvent) {
            pView.onTouchEvent(pEvent);
            // We're only interested in when the button is released.
            if (pEvent.getAction() == MotionEvent.ACTION_UP) {
                // We're only interested in anything if our speak button is currently pressed.
                if(leftLongPress){
                    if (isLeftButtonLongPressed) {
                        // Do something when the button is released.
                        isLeftButtonLongPressed = false;
                    }else{
                        if(leftType.equals(INCREMENT))
                            IncrementAction();
                        else if(leftType.equals(DECREMENT))
                            DecrementAction();
                        else
                            Log.e(TAG,"invalid Type");
                    }
                }

            }
            return true;
        }
    };

    private View.OnTouchListener speakRightTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View pView, MotionEvent pEvent) {
            pView.onTouchEvent(pEvent);
            // We're only interested in when the button is released.
            if (pEvent.getAction() == MotionEvent.ACTION_UP) {
                // We're only interested in anything if our speak button is currently pressed.
                if(rightLongPress){
                    if (isRightButtonLongPressed) {
                        // Do something when the button is released.
                        isRightButtonLongPressed = false;
                    }else{
                        if(rightType.equals(INCREMENT))
                            IncrementAction();
                        else if(rightType.equals(DECREMENT))
                            DecrementAction();
                        else
                            Log.e(TAG,"invalid Type");
                    }
                }

            }
            return true;
        }
    };


    private void startDecrementTimerThread() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            private long startTime = System.currentTimeMillis();
            public void run() {
                while (isLeftButtonLongPressed||isRightButtonLongPressed) {
                    try {
                        Thread.sleep(seconds);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable(){
                        public void run() {
                            DecrementAction();
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

    private void startIncrementTimerThread() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            private long startTime = System.currentTimeMillis();
            public void run() {
                while (isLeftButtonLongPressed||isRightButtonLongPressed) {
                    try {
                        Thread.sleep(seconds);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable(){
                        public void run() {
                            IncrementAction();
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }


    public void IncrementAction(){
        if(type.equals(TYPE_ARRAY)){
            try {
                oldIndex = index;
                if (checkIncVaidation(index, startIndexVal, stopIndexVal)) {
                    index = index + int_val;
                    counter.setText(array.get(index));
                    callArryListener(this, oldIndex, index);
                } else {
                    index = stopIndexVal;
                    counter.setText(array.get(stopIndexVal));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if(type.equals(TYPE_FLOAT)){
            float num= Float.parseFloat(counter.getText().toString());
            setFloatNumber(num+interval, true,num);
        }else if(type.equals(TYPE_INTEGER)){
            int num= Integer.parseInt(counter.getText().toString());
            setIntNumber(num+(int)interval,true,num);
        }else
            Log.e(TAG,"error");
    }


    private void DecrementAction() {
        if(type.equals(TYPE_ARRAY)){
            try {
                oldIndex = index;
                if (checkDecVaidation(index, startIndexVal, stopIndexVal)) {
                    index = index - int_val;
                    counter.setText(array.get(index));
                    callArryListener(this, oldIndex, index);
                } else {
                    index = startIndexVal;
                    counter.setText(array.get(startIndexVal));
                }
            }catch (Exception e){}
        }else if(type.equals(TYPE_FLOAT)){
            float num= Float.parseFloat(counter.getText().toString());
            setFloatNumber(num-interval, true,num);
        }else if(type.equals(TYPE_INTEGER)){
            int num= Integer.parseInt(counter.getText().toString());
            setIntNumber(num-(int)interval,true,num);
        }else
            Log.e(TAG,"error");
    }


    public void setFloatNumber(float number, boolean notifyListener,float num){
        setFloatNumber(number);
        if(notifyListener)
        {
            callFloatListener(this,num,number);
        }
    }

    public void setIntNumber(int number,boolean notifyListener,int oldnum){
        setIntNumber(number);
        if(notifyListener)
        {
            callIntListener(this,oldnum,number);
        }
    }

    public void enableLongPress(boolean leftLongpress,boolean rightLongpress,long seconds){
        this.leftLongPress=leftLongpress;
        this.rightLongPress=rightLongpress;
        this.seconds=seconds;
        if(!leftLongPress||!rightLongPress) {
            initClickListener(leftButton, rightButton, counter);
        }
    }

    public void setprecision(String precision){
        this.precesion=precision;
    }

    @SuppressLint("DefaultLocale")
    public void setFloatNumber(float number){
        //finalValue = currentValue;
        this.currentValue = number;
        if(this.currentValue > finalValue)
        {
            this.currentValue = finalValue;
        }
        if(this.currentValue < initialValue)
        {
            this.currentValue = initialValue;
        }
        counter.setText(String.format(precesion,currentValue));
    }

    public void setIntNumber(int number){
        this.currentValue_int=number;
        if(this.currentValue_int>finalValue_int)
            this.currentValue_int=finalValue_int;
        if(this.currentValue_int<initialValue_int)
            this.currentValue_int=initialValue_int;
        counter.setText(String.valueOf(currentValue_int));
    }

    public void callIntListener(View view, int oldvalue, int newValue){
        if(mListener!=null)
            mListener.onClick(view);
        if(mValueListener!=null){
            if(finalValue!=currentValue)
                mValueListener.onValueChange(this,oldvalue,newValue);
        }
    }

    public void callFloatListener(View view, float oldvalue, float newValue){
        if(mListener!=null)
            mListener.onClick(view);
        if(mValueListener!=null){
            if(finalValue!=currentValue)
                mValueListener.onValueChange(this,oldvalue,newValue);
        }
    }

    public void callArryListener(View view, int oldIndex,int newIndex){
        if(mListener!=null)
            mListener.onClick(view);
        if(mValueListener!=null){
            if(finalValue!=currentValue)
                mValueListener.onValueChange(this,oldIndex,newIndex);
        }
    }


    /** Setting arraylist */
    public void setArrayList(ArrayList<String> arrayList){
        this.array=arrayList;
    }

    public void setArrayInitialization(int interval,int startIndex){
        this.int_val=interval;
        this.startIndex=startIndex;
        this.index=startIndex;
        counter.setText(array.get(startIndex));
    }

    public void setArrayIndexes(int startIndexVal, int stopIndexVal, int interval){
        this.startIndexVal=startIndexVal;
        this.stopIndexVal=stopIndexVal;
        this.int_val=interval;
        this.index=startIndexVal;
        counter.setText(array.get(startIndexVal));
    }


    public String getValue(){
        return counter.getText().toString();
    }

    public int getCurrentIndex(){
        return index;
    }

    public interface OnClickListener {
        void onClick(View view);
    }

    public interface OnValueChangeListener {
        void onValueChange(IncDecImageButton view,float oldValue,float newValue);
    }


    public void setOnClickListener(IncDecImageButton.OnClickListener listener){
        this.mListener=listener;
    }

    public void setOnValueChangeListener(IncDecImageButton.OnValueChangeListener listener){
        this.mValueListener=listener;
    }


}

