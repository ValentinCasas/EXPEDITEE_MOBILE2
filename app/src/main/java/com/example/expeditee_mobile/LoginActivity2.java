package com.example.expeditee_mobile;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;

import com.example.expeditee_mobile.databinding.ActivityLogin2Binding;
public class LoginActivity2 extends AppCompatActivity {

    private ActivityLogin2Binding binding;
    private LoginActivity2ViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        mv = new ViewModelProvider(this).get(LoginActivity2ViewModel.class);
        setContentView(binding.getRoot());


        ActionBar actionBar = getSupportActionBar();

            actionBar.setTitle("Volver");
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#197A3C")));
            int textColor = Color.parseColor("#8DECB0");
            SpannableString spannableString = new SpannableString(actionBar.getTitle());
            spannableString.setSpan(new ForegroundColorSpan(textColor), 0, spannableString.length(), SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
            actionBar.setTitle(spannableString);

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);


            binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.confirmaLogin(binding.etMail.getText().toString(),binding.etClave.getText().toString());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}