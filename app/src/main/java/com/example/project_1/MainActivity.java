package com.example.project_1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// check if prime number <!--
class Prime {

    public static boolean isPrime(long num) {
        if (num == 0 || num == 1) return false;

        int f = 0;

        long n = num / 2;

        for (int i = 2; (i <= n) && (f == 0); i++) {
            if (num % i == 0) f = 1;
        }

        return (f == 0);

    }
}

//  -->

//-----------------------------------------------------------------------------------------------------------------------------------------

// check if Palindrome number <!--

class Palindrome {
    public static boolean isPalindrome(long num)
    {
        long sum =0;
        for(long i=num ; i>0 ; i /=10) {  sum=(sum*10)+(i%10); }
        return  (num == sum);
    }

}


//  -->

//-----------------------------------------------------------------------------------------------------------------------------------------

// check if twisted/emirp number <!--

class Emirp {
    static long reverse(long num)
    {
        long n=0;
        while(num>0){ n=(n*10)+(num%10); num=num/10; }
        return n;
    }

    public static boolean isEmirp(long num)
    {
        if(num<13)return false;
        return ( Prime.isPrime(num)
                && (!Palindrome.isPalindrome(num))
                &&  Prime.isPrime(Emirp.reverse(num) )
        );
    }
}

//  -->

public class MainActivity extends AppCompatActivity implements View.OnClickListener , View.OnFocusChangeListener
{

    private Button ok;
    private Button clear;
    private Button exit;
    private TextView num;
    private TextView ans;
    private TextView  ans2;
    private TextView  ans3;
    private EditText input;

    @Override
    public boolean onSupportNavigateUp() {

        Log.w ("hello", "onSupportNavigateUp is calll");
        exit.callOnClick ();

        return super.onSupportNavigateUp ();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            input.clearFocus();
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.w ("hello", "this onbackpress is calll");
        Toast.makeText(getApplicationContext(), " goodbye ;-) ", Toast.LENGTH_SHORT).show();
        super.onBackPressed ();
        this.finish();
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar= getSupportActionBar ();
        bar.setHomeButtonEnabled ( true );
        bar.setDisplayHomeAsUpEnabled ( true );
        bar.setHomeAsUpIndicator ( R.drawable.icon_smal);

        ok  = (Button) findViewById (R.id.ok);
        clear    = (Button) findViewById (R.id.clear);
        exit    = (Button) findViewById (R.id.exit);
        num = findViewById(R.id.num);
        ans = findViewById (R.id.ans);
        ans2 = findViewById (R.id.ans2);
        ans3 = findViewById (R.id.ans3);
        input   = findViewById (R.id.input);

        input.setOnKeyListener ( new View.OnKeyListener () {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK){input.clearFocus(); return false;}
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    ok.callOnClick ();

                    System.out.println("wlow");

                    //input.onEditorAction(EditorInfo.IME_ACTION_NONE);
                    //input.onEditorAction(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
                    return false;
                }
                if(keyCode == event.KEYCODE_DEL){ System.out.println("wlow2"); return false;}
                return ((keyCode <event.KEYCODE_0) || (keyCode >event.KEYCODE_9) );

            }
        } );

        input.addTextChangedListener ( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                System.out.println("beforeTextChanged()   is called");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("onTextChanged()   is called");
                // clear.callOnClick ();
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("afterTextChanged()   is called");
                ok.callOnClick ();
                // <!-- co1
                String e=input.getText ().toString ();
                if (e.isEmpty ()){
                    //clear.callOnClick();

                    num.setText("Number_Is :");
                    ans.setText ("isPalindrome?");
                    ans2.setText ("isPrime?");
                    ans3.setText ("is(Twisted/ Emirp)?");

                    System.out.println("test1");
                }
                // co1 -->
            }
        } );

        input.setOnFocusChangeListener(this);
        ok.setOnClickListener (this);
        clear.setOnClickListener (this);
        exit.setOnClickListener (this);

        clear.callOnClick ();

        Toast.makeText(getApplicationContext(), " welcome to my program ;-) ", Toast.LENGTH_SHORT).show();
    }

// <!-- edit

    public void fun1(){

        String s=input.getText ().toString ();
        if (s.isEmpty ())
        {
            num.setText( "No number is provided" );
            ans.setText ( "No number is provided" );
            ans2.setText ( "No number is provided" );
            ans3.setText ( "No number is provided" );
        }

        else
        {

            long N= Long.parseLong(s);
            num.setText("Number_Is : "+N);
            ans.setText ( Palindrome.isPalindrome(N)? "is Palindrome"  : "is not Palindrome" );
            ans2.setText ( Prime.isPrime(N)?  "is Prime" : "is not Prime" );
            ans3.setText ( Emirp.isEmirp(N)? "is twisted/ emirp" : "is not (twisted/ emirp)" );

        }

    }

    public void fun2(){

        input.setText("");
        num.setText("Number_Is :");
        ans.setText ("isPalindrome?");
        ans2.setText ("isPrime?");
        ans3.setText ("is(Twisted/ Emirp)?");

    }

    public void fun3(){

      //  clear.callOnClick();
        onBackPressed ();
    }

// edit -->

    @Override
    public void onClick(View v) {

        if (v.getId ()==R.id.ok){ fun1(); }
        else
        if (v.getId ()==R.id.clear){ fun2(); }
        else
        if (v.getId ()==R.id.exit){ fun3(); }
    }

      @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v==input)
        {
            if (!hasFocus) {
                Log.d("focus", "focus lost");
                // Do whatever you want here
            } else {
                Toast.makeText(getApplicationContext(), " Tap outside edittext to lose focus ", Toast.LENGTH_SHORT).show();
                Log.d("focus", "focused");
            }
        }
    }

 // <!-- Clear focus on touch outside for all EditText inputs. "Clear focus input"
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

// "Clear focus input" -->

}