package com.demo.userlogin;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.loginSignupTextView)
    TextView loginSignupTextView;

    @BindView(R.id.signUpButtonTextView)
    TextView signUpButtonTextView;

    @BindView(R.id.loginButtonTextView)
    TextView loginButtonTextView;

    @BindView(R.id.signUpLinearLayout)
    RelativeLayout signUpLinearLayout;

    @BindView(R.id.signInRelativeLayout1)
    RelativeLayout signInRelativeLayout1;

    @BindView(R.id.closeImageTextView)
    TextView closeImageTextView;

    @BindView(R.id.facebookIconTextView)
    TextView facebookIconTextView;

    @BindView(R.id.googleIconTextView)
    TextView googleIconTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Typeface fontAwesome = Typeface.createFromAsset(getAssets(), "fontawesomeicon.ttf");
        closeImageTextView.setTypeface(fontAwesome);
        facebookIconTextView.setTypeface(fontAwesome);
        googleIconTextView.setTypeface(fontAwesome);
        setOnClickListener();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.loginButtonTextView:
                loginSignupTextView.setText("Login");
                showSignInLayout();
                break;

            case R.id.signUpButtonTextView:
                loginSignupTextView.setText("Signup");
                showSignUpLayout();
                break;
        }
    }

    private void setOnClickListener(){
        loginButtonTextView.setOnClickListener(this);
        signUpButtonTextView.setOnClickListener(this);
    }

    private void showSignUpLayout() {
        signUpLinearLayout.startAnimation(getAnimation(AnimationDirection.OUT_TO_LEFT));
        signUpLinearLayout.setVisibility(View.GONE);
        signInRelativeLayout1.startAnimation(getAnimation(AnimationDirection.IN_FROM_RIGHT));
        signInRelativeLayout1.setVisibility(View.VISIBLE);
    }

    private void showSignInLayout() {
        signUpLinearLayout.startAnimation(getAnimation(AnimationDirection.IN_FROM_LEFT));
        signUpLinearLayout.setVisibility(View.VISIBLE);
        signInRelativeLayout1.startAnimation(getAnimation(AnimationDirection.OUT_TO_RIGHT));
        signInRelativeLayout1.setVisibility(View.GONE);
    }

    public enum AnimationDirection {
        IN_FROM_LEFT,
        OUT_TO_LEFT,
        IN_FROM_RIGHT,
        OUT_TO_RIGHT
    }

    public static Animation getAnimation(AnimationDirection direction) {
        Float fromX = 0.0f;
        Float toX = 0.0f;
        Float fromY = 0.0f;
        Float toY = 0.0f;
        switch (direction) {
            case IN_FROM_LEFT:
                fromX = -1.0f;
                break;
            case OUT_TO_LEFT:
                toX = -1.0f;
                break;
            case IN_FROM_RIGHT:
                fromX = +1.0f;
                break;
            case OUT_TO_RIGHT:
                toX = +1.0f;
                break;
        }
        return getAnimationForValue(fromX, toX, fromY, toY, 500);

    }

    private static Animation getAnimationForValue(Float fromX, Float toX, Float fromY, Float toY,
                                                  long durationInMilliSecond) {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, fromX,
                Animation.RELATIVE_TO_PARENT, toX,
                Animation.RELATIVE_TO_PARENT, fromY,
                Animation.RELATIVE_TO_PARENT, toY);
        animation.setDuration(durationInMilliSecond);
        animation.setInterpolator(new AccelerateInterpolator());
        return animation;
    }
}