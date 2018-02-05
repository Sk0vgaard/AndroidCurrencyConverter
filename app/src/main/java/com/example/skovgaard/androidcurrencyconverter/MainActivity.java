package com.example.skovgaard.androidcurrencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    EditText mAmount;
    Button mBtnConvert;
    TextView mDKK, mSEK, mNOK, mEURO, mPound;

    private Double sekCurrency, dkkCurrency, euroCurrency, gbpCurrency, nokCurrency;

    private static final String allCurrencyURL = "https://openexchangerates.org/api/latest.json?app_id=000bcc9aa760417880d91ecd4fd665cf";
    private static final String specificCurrencyURL = "https://openexchangerates.org/api/latest.json?app_id=000bcc9aa760417880d91ecd4fd665cf&symbols=DKK,SEK,NOK,EUR,GBP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAmount = findViewById(R.id.amountTxt);
        mBtnConvert = findViewById(R.id.convertBtn);
        mDKK = findViewById(R.id.dkkCurrency);
        mSEK = findViewById(R.id.sekCurrency);
        mNOK = findViewById(R.id.nokCurrency);
        mEURO = findViewById(R.id.euroCurrency);
        mPound = findViewById(R.id.poundCurrency);

        asyncRequestForAPI();

        mBtnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    calcOfCurrencies();
                } catch (Exception e) {
                    Log.d("Catching", e.getMessage());
                    e.printStackTrace();
                }
            }
        });

    }

    private void calcOfCurrencies() {
        double usdAmount = Double.parseDouble(mAmount.getText().toString());

        mDKK.setText("DKK: " + usdAmount * dkkCurrency);
        mSEK.setText("SEK: " + usdAmount * sekCurrency);
        mNOK.setText("NOK: " + usdAmount * nokCurrency);
        mEURO.setText("EURO: " + usdAmount * euroCurrency);
        mPound.setText("GPD: " + usdAmount * gbpCurrency);
    }

    private void asyncRequestForAPI() {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(specificCurrencyURL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                try {
                    Log.i("Catching", "On Success");
                    JSONObject jsonObject = new JSONObject(new String(response));
                    JSONObject currencies = jsonObject.getJSONObject("rates");

                    sekCurrency = currencies.getDouble("SEK");
                    dkkCurrency = currencies.getDouble("DKK");
                    nokCurrency = currencies.getDouble("NOK");
                    gbpCurrency = currencies.getDouble("GBP");
                    euroCurrency = currencies.getDouble("EUR");

                    Log.d("Catching", currencies + "");
                } catch (JSONException e) {
                    Log.d("Catching", e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable throwable) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("Catching", "Response failed");
            }

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }

        });
    }

}
