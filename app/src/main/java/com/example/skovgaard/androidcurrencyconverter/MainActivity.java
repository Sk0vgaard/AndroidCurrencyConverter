package com.example.skovgaard.androidcurrencyconverter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    EditText mAmount;
    Button mConvert;
    TextView mDKK, mSEK, mNOK, mEURO;

    private static final String currencyURL = "https://openexchangerates.org/api/latest.json?app_id=000bcc9aa760417880d91ecd4fd665cf";

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

        new GetWebpageTask().execute(currencyURL);


    }

    private String getWebsite(String address) {

        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = null;

        try {
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }

        } catch (IOException e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("ERROR: " + e);
                    e.printStackTrace();
                }
            }
        }
    return stringBuffer.toString();
    }

    // 1 Params, the type of the parameters sent to the task upon execution.
    // 2 Progress, the type of the progress units published during the background computation.
    // 3 Result, the type of the result of the background computation.
    public class GetWebpageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            return getWebsite(url[0]);

        }

        @Override
        protected void onPostExecute(String result) {

            Log.i("Catching", result);

            super.onPostExecute(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
    }

}
