package com.example.skovgaard.androidcurrencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mAmount;
    Button mConvert;
    TextView mDKK, mSEK, mNOK, mEURO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAmount = findViewById(R.id.amountTxt);
        mConvert = findViewById(R.id.convertBtn);
        mDKK = findViewById(R.id.dkkCurrency);
        mSEK = findViewById(R.id.sekCurrency);
        mNOK = findViewById(R.id.nokCurrency);
        mEURO = findViewById(R.id.euroCurrency);




    }
}
