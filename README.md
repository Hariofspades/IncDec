# IncDec Android UI Library

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/Hariofspades/IncDec/blob/master/LICENSE)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-IncDec-blue.svg?style=flat)](https://android-arsenal.com/details/1/4784)

<img src=https://raw.githubusercontent.com/Hariofspades/IncDec/master/assets/wallpaper.png >

### Incrementor and Decrementor (IncDec)

IncDec library will allow you to perform increment and decrement operation. As it supports single click and long press, it enhances UX solving the delay problem in the typical number picker.


### Usecase of IncDec ?
* IncDec can be used as an alternate for numberpicker, for fast number picking. User need not scroll the wheel for long time. He can just press and hold the button and IncDec will do it's magic.
* For usecases like shopping cart quantity, medicine units, setting up time and medicine quantity, IncDec will hold good

### Why IncDec ?
* It is based on skeuomorphism concept of picking a number.
* simple and auto-explanatory to the user
* Allows user to quickly pick things
* Supports Array values, Integer and float values
* Highly flexible (supporting color, drawable, speed etc.)
* Long press and single tap support
* FAB and ImageButton support

### Find this project useful ? :heart:
* Support it by clicking the :star: button on the upper right of this page. :v:

## Requirements

IncDec Library can be included in any Android application. 

IncDec Library supports Android 2.3 (Gingerbread) and later. 

## Using IncDec Library in your application

Add this in your build.gradle
```groovy
compile 'com.hariofspades.IncDec:incdeclibrary:0.0.3'
```
Adding IncDec FAB in to the XML
```xml
<com.hariofspades.incdeclibrary.IncDecCircular
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:textSize="44"
        android:id="@+id/incdec"
        app:textColor="@color/white"
        app:leftButtonColorTint="@color/colorPrimaryDark"
        app:rightButtonColorTinit="@color/colorPrimary"
        app:leftDrawableTint="@color/white"
        app:rightDrawableTint="@color/white"
        app:leftDrawable="@drawable/ic_remove_black_24dp"
        app:rightDrawable="@drawable/ic_add_black_24dp"/>
```
Setting up Array values to IncDec
```java
ArrayList<String> values=new ArrayList<>();
        values.add("Apple");
        values.add("Ball");
        values.add("Cat");
        values.add("Dog");
        values.add("Elephant");
        
 IncDecCircular incdec=(IncDecCircular)findViewById(R.id.incdec);
 incdec.setConfiguration(LinearLayout.HORIZONTAL,IncDecCircular.TYPE_ARRAY,
                IncDecCircular.DECREMENT,IncDecCircular.INCREMENT);
 incdec.setArrayList(values);
 incdec.setArrayIndexes(1,3,1);
 incdec.enableLongPress(true,true,500);
```
# Decoding the above function parameters

setConfiguration - Layout's orientation, type(array,integer,float),left button (increment/decrement), right button (increment/decrement)

setArrayList - arraylist values

setArrayIndexes - starting index, stoping index, interval count

enableLongPress - for left button, right button and the speed in ms for counting

Making IncDec behave for float values
```java
IncDecCircular incdec =(IncDecCircular) findViewById(R.id.incdec);
incdec.setConfiguration(LinearLayout.VERTICAL,IncDecCircular.TYPE_FLOAT,
                IncDecCircular.INCREMENT,IncDecCircular.DECREMENT);
incdec.setupValues(2,40,(float)0.7,10);
incdec.setprecision("%.1f");
incdec.enableLongPress(false,false,500);
```
# Decoding function parameters 

setupValues - intial value final value, interval and start value

setprecision - Restricting decimal places

Making IncDec behave for integers
```java
IncDecCircular incdec=(IncDecCircular)findViewById(R.id.incdec);
incdec.setConfiguration(LinearLayout.HORIZONTAL,IncDecCircular.TYPE_INTEGER,
                IncDecCircular.DECREMENT,IncDecCircular.INCREMENT);
incdec.setupValues(0,20,1,4);
incdec.enableLongPress(true,true,500);
```

Listeners for IncDec
```java
 incdec.setOnClickListener(new IncDecCircular.OnClickListener() {
            @Override
            public void onClick(View view) {
                //For Array
                Toast.makeText(MainActivity.this,
                        String.valueOf(incdec.getCurrentIndex()), Toast.LENGTH_SHORT).show();
                //For int and float
                Toast.makeText(MainActivity.this,
                        incdec.getValue(), Toast.LENGTH_SHORT).show();
            }
        });
        
  incdec.setOnValueChangeListener(new IncDecCircular.OnValueChangeListener() {
            @Override
            public void onValueChange(IncDecCircular view, float oldValue, float newValue) {
                Toast.makeText(MainActivity.this, String.valueOf(oldValue)+"/"+
                        String.valueOf(newValue), Toast.LENGTH_SHORT).show();
            }
        });
    
```

IncDec for traditional ImageButton
```xml
 <com.hariofspades.incdeclibrary.IncDecImageButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:textSizeB="24"
        android:id="@+id/incdec"
        android:layout_marginTop="20dp"
        app:textColorB="@color/white"
        app:leftButtonColorTintB="@color/colorPrimaryDark"
        app:rightButtonColorTinitB="@color/colorPrimary"
        app:leftDrawableTintB="@color/white"
        app:rightDrawableTintB="@color/white"
        app:leftDrawableB="@drawable/ic_remove_black_24dp"
        app:rightDrawableB="@drawable/ic_add_black_24dp"/>
```

For Adding your own Background for ImageButton

```java
    IncDecImageButton incdecImg2=(IncDecImageButton) findViewById(R.id.incdecbut2);
    incdecImg2.setBackgroundforButton(ContextCompat.getDrawable(this,R.drawable.round),
                    ContextCompat.getDrawable(this,R.drawable.round));
```

#Decoding function parameters
setBackground - leftbutton background, right button background

Note : By default, the background of the image button will be transparent. You can add
lefttint and righttint for colours.


##Happy Coding!!

### Let's get connected
- [Twitter](https://twitter.com/HariOfSpades)
- [Github](https://github.com/Hariofspades)
- [Medium](http://medium.com/@harivigneshjayapalan)
- [Facebook](http://facebook.com/Hari.Vignesh.J)

### License
```
   Copyright (C) 2016 Hari Vignesh Jayapalan
   Copyright (C) 2011 Android Open Source Project

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
