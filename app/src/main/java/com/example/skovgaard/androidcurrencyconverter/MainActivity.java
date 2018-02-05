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

        mBtnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                asyncRequestForAPI();
            }
        });

    }

    private void asyncRequestForAPI() {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(specificCurrencyURL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"


                Log.i("Catching", new String(response));
                try {
                    JSONObject jsonObject = new JSONObject(new String(response));
                    Log.d("test", jsonObject.getString("SEK"));
                } catch (JSONException e) {
                    Log.d("failed hard", new String(response));
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable throwable) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
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


//    private String getWebsite(String address) {
//
//        StringBuffer stringBuffer = new StringBuffer();
//        BufferedReader reader = null;
//
//        try {
//            URL url = new URL(address);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//
//            reader = new BufferedReader(new InputStreamReader(in));
//            String line = "";
//
//            while ((line = reader.readLine()) != null) {
//                stringBuffer.append(line);
//            }
//
//        } catch (IOException e) {
//            System.out.println("ERROR: " + e);
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    System.out.println("ERROR: " + e);
//                    e.printStackTrace();
//                }
//            }
//        }
//    return stringBuffer.toString();
//    }
//
//    // 1 Params, the type of the parameters sent to the task upon execution.
//    // 2 Progress, the type of the progress units published during the background computation.
//    // 3 Result, the type of the result of the background computation.
//    public class GetWebpageTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... url) {
//
//            return getWebsite(url[0]);
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//
//
//            super.onPostExecute(result);
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//            super.onPreExecute();
//        }
//    }
//




}
