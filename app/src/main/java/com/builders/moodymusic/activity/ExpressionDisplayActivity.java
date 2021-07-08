package com.builders.moodymusic.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.builders.moodymusic.AppController;
import com.builders.moodymusic.R;
import com.builders.moodymusic.constants.MoodConstants;

public class ExpressionDisplayActivity extends AppCompatActivity {

    LinearLayout expressionBackgroundRL;
    ImageView emojiPlaceholderIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_expression_display);
        init();
        setMood();

    }

    private void init() {
        expressionBackgroundRL = findViewById(R.id.expression_background_ll);
        emojiPlaceholderIV = findViewById(R.id.emoji_placeholder_iv);
    }

    private void setMood() {

        int emoji = R.drawable.happy;
        int background = R.drawable.happy_bg;

        if (AppController.currentMood == MoodConstants.MOOD.SAD) {
            emoji = R.drawable.sad;
            background = R.drawable.sad_bg;
        } else if (AppController.currentMood == MoodConstants.MOOD.HAPPY) {
            emoji = R.drawable.happy;
            background = R.drawable.happy_bg;
        } else if (AppController.currentMood == MoodConstants.MOOD.MIDDLE) {
            emoji = R.drawable.calm;
            background = R.drawable.calm_bg;
        }

        emojiPlaceholderIV.setImageResource(emoji);
        expressionBackgroundRL.setBackground(getResources().getDrawable(background));

//        while (true){
//            new Handler().postDelayed(() -> forceRippleAnimation(expressionBackgroundRL) , 2000);
//        }

    }

    protected void forceRippleAnimation(View view)
    {
        Drawable background = view.getBackground();

        if(Build.VERSION.SDK_INT >= 21 && background instanceof RippleDrawable)
        {
            final RippleDrawable rippleDrawable = (RippleDrawable) background;

            rippleDrawable.setState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled});

            Handler handler = new Handler();

            handler.postDelayed(() -> rippleDrawable.setState(new int[]{}), 200);
        }
    }

}