package com.hariofspades.incdec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hariofspades.incdeclibrary.IncDecCircular;
import com.hariofspades.incdeclibrary.IncDecImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    IncDecCircular incdec;
    IncDecCircular incdec_vert;
    IncDecCircular incdecInt;
    IncDecImageButton incdecImg;
    IncDecImageButton incdecImg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> values=new ArrayList<>();
        values.add("Apple");
        values.add("Ball");
        values.add("Cat");
        values.add("Dog");
        values.add("Elephant");
        incdec=(IncDecCircular)findViewById(R.id.incdec);
        incdec.setConfiguration(LinearLayout.HORIZONTAL,IncDecCircular.TYPE_ARRAY,
                IncDecCircular.DECREMENT,IncDecCircular.INCREMENT);
        incdec.setArrayList(values,1,2);
        incdec.enableLongPress(true,true,500);

        incdec_vert=(IncDecCircular) findViewById(R.id.incdecvertical);
        incdec_vert.setConfiguration(LinearLayout.VERTICAL,IncDecCircular.TYPE_FLOAT,
                IncDecCircular.INCREMENT,IncDecCircular.DECREMENT);
        incdec_vert.setupValues(2,40,(float)0.7,10);
        incdec_vert.setprecision("%.1f");
        incdec_vert.enableLongPress(false,false,500);

        incdecImg=(IncDecImageButton) findViewById(R.id.incdecImg);
        incdecImg.setConfiguration(LinearLayout.VERTICAL,IncDecCircular.TYPE_FLOAT,
                IncDecCircular.INCREMENT,IncDecCircular.DECREMENT);
        incdecImg.setupValues(2,40,(float)0.7,10);
        incdecImg.setprecision("%.1f");
        incdecImg.enableLongPress(false,false,500);

        incdecInt=(IncDecCircular)findViewById(R.id.incdecInt);
        incdecInt.setConfiguration(LinearLayout.HORIZONTAL,IncDecCircular.TYPE_INTEGER,
                IncDecCircular.DECREMENT,IncDecCircular.INCREMENT);
        incdecInt.setupValues(0,20,1,4);
        incdecInt.enableLongPress(true,true,500);

        incdecImg2=(IncDecImageButton) findViewById(R.id.incdecbut2);
        incdecImg2.setConfiguration(LinearLayout.HORIZONTAL,IncDecCircular.TYPE_INTEGER,
                IncDecCircular.DECREMENT,IncDecCircular.INCREMENT);
        incdecImg2.setupValues(0,20,1,4);
        incdecImg2.enableLongPress(true,true,500);

        incdec_vert.setOnClickListener(new IncDecCircular.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        String.valueOf(incdec.getCurrentIndex()), Toast.LENGTH_SHORT).show();
            }
        });

        incdec_vert.setOnValueChangeListener(new IncDecCircular.OnValueChangeListener() {
            @Override
            public void onValueChange(IncDecCircular view, float oldValue, float newValue) {
                Toast.makeText(MainActivity.this, String.valueOf(oldValue)+"/"+
                        String.valueOf(newValue), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
