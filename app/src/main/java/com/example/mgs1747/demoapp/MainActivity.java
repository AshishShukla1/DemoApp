package com.example.mgs1747.demoapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    EditText txt;
    String[] colors1 = new String[]{"#FF3339", "#FF5733", "#FFE033", "#A5FF33", "#443C3C", "#33FFE9", "#339FFF", "#A533FF", "#EC33FF", "#FF336B", "#070707"};
    Integer[] colors2 = new Integer[]{Color.BLUE, Color.GREEN, Color.BLACK, Color.CYAN, Color.DKGRAY, Color.MAGENTA, Color.YELLOW, Color.WHITE, Color.RED, Color.LTGRAY};

    Animation animZoomIn,animBounce,animRotate,animShake,animFadeOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        animShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        animFadeOut= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        animZoomIn.setAnimationListener(this);
        animBounce.setAnimationListener(this);
        animRotate.setAnimationListener(this);
        animShake.setAnimationListener(this);
        animFadeOut.setAnimationListener(this);

        txt = (EditText) findViewById(R.id.editText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeColors(txt.getText().toString(), view);

            }
        });
    }

    void changeColors(String name, View v) {
        try {

            SpannableString ss1 = new SpannableString(name);

            for (int i = 0; i < name.length(); i++) {

                ss1.setSpan(new ForegroundColorSpan(colors2[(int) (Math.random() * (colors2.length - 1) + 1)]), i, (i + 1), 0);

            }
            txt.setText(ss1);
            txt.startAnimation(animZoomIn);

            Snackbar.make(v, "Enjoy the Colors ...", Snackbar.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == animZoomIn) {
            txt.startAnimation(animBounce);
        }else if(animation ==animBounce){
            txt.startAnimation(animRotate);
        }else if(animation==animRotate){
            txt.startAnimation(animShake);
        }else if(animation==animShake){
            txt.startAnimation(animFadeOut);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
       // txt.startAnimation(animation);
    }
}
