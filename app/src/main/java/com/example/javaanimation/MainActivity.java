package com.example.javaanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javaanimation.databinding.ActivityMainBinding;


@SuppressLint("MissingInflatedId")
public class MainActivity extends ComponentActivity {

    private ActivityMainBinding binding;
    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setFAB();
    }

    private void setFAB() {
        setInitialState();

        binding.fab.setOnClickListener(v -> {
            if (isExpanded) {
                collapseFab();
            } else {
                expandFAB();
            }
        });
    }

    private void setInitialState() {
        binding.transparentBackground.setAlpha(0f);
        binding.optionTwoContainer.setAlpha(0f);
        binding.optionTwoContainer.setClickable(false);
        binding.optionOneContainer.setAlpha(0f);
        binding.optionOneContainer.setClickable(false);
    }

    private void expandFAB() {
        isExpanded = true;
        ObjectAnimator.ofFloat(binding.plusImageview, "rotation", 0f, 225f).start();
        ObjectAnimator.ofFloat(binding.optionTwoContainer, "translationY", -130f).start();
        ObjectAnimator.ofFloat(binding.optionOneContainer, "translationY", -250f).start();

        binding.optionTwoContainer.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        binding.optionTwoContainer.setClickable(true);
                        binding.optionTwoContainer.setOnClickListener(v -> {
                            Toast.makeText(MainActivity.this, "Option 2", Toast.LENGTH_SHORT).show();
                        });
                    }
                });

        binding.optionOneContainer.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        binding.optionOneContainer.setClickable(true);
                        binding.optionOneContainer.setOnClickListener(v -> {
                            Toast.makeText(MainActivity.this, "Option 1", Toast.LENGTH_SHORT).show();
                        });
                    }
                });

        binding.transparentBackground.animate()
                .alpha(0.9f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        binding.transparentBackground.setClickable(true);
                    }
                });
    }

    private void collapseFab() {
        isExpanded = false;
        ObjectAnimator.ofFloat(binding.plusImageview, "rotation", 0f, -180f).start();
        ObjectAnimator.ofFloat(binding.optionTwoContainer, "translationY", 0f).start();
        ObjectAnimator.ofFloat(binding.optionOneContainer, "translationY", 0f).start();

        binding.optionTwoContainer.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        binding.optionTwoContainer.setClickable(false);
                        binding.optionOneContainer.setOnClickListener(null);
                    }
                });

        binding.optionOneContainer.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        binding.optionOneContainer.setClickable(false);
                    }
                });

        binding.transparentBackground.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        binding.transparentBackground.setClickable(false);
                    }
                });
    }
}