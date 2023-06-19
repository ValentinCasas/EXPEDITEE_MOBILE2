package com.example.expeditee_mobile;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.example.expeditee_mobile.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("");
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#197A3C")));
            int textColor = Color.parseColor("#8DECB0");
            SpannableString spannableString = new SpannableString(actionBar.getTitle());
            spannableString.setSpan(new ForegroundColorSpan(textColor), 0, spannableString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            actionBar.setTitle(spannableString);

        binding.btnIrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity2.class);
                startActivity(intent);
            }
        });


    }
}