package mivi.com.mivitestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    private TextView username, useremail, planName, planPrice, remainingData, creditbal, overallCreditBal, overallDatabal,
            internationtalkBal, extDate, autorenewal, primayScub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        username = (TextView) findViewById(R.id.username);
        useremail = (TextView) findViewById(R.id.useremail);
        planName = (TextView) findViewById(R.id.planName);
        planPrice = (TextView) findViewById(R.id.planPrice);
        remainingData = (TextView) findViewById(R.id.remainingData);
        creditbal = (TextView) findViewById(R.id.creditbal);
        overallCreditBal = (TextView) findViewById(R.id.overallCreditBal);
        overallDatabal = (TextView) findViewById(R.id.overallDatabal);
        internationtalkBal = (TextView) findViewById(R.id.internationtalkBal);
        extDate = (TextView) findViewById(R.id.extDate);
        autorenewal = (TextView) findViewById(R.id.autorenewal);
        primayScub = (TextView) findViewById(R.id.primayScub);


        try {
            JSONObject jsonObject = new JSONObject(MyUtils.loadJsonAssets(HomeActivity.this));
            JSONObject data=jsonObject.getJSONObject("data");
            JSONObject attributes=data.getJSONObject("attributes");
            username.setText(" User Name : "+attributes.getString("first-name")+" "+attributes.getString("last-name") );
            useremail.setText(" User Email : "+attributes.getString("email-address"));

            JSONArray jsonArray = jsonObject.getJSONArray("included");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject includJsonObj = jsonArray.getJSONObject(i);
                String type = includJsonObj.getString("type");
                JSONObject attribute = includJsonObj.getJSONObject("attributes");
                if (type.equalsIgnoreCase("products")) {
                    planName.setText("Product Name : " + attribute.getString("name"));
                    planPrice.setText("Product Price : " + attribute.getString("price")+" USD");
                } else if (type.equalsIgnoreCase("subscriptions")) {
                    remainingData.setText("Remaining Balance : " + attribute.getString("included-data-balance"));
                    creditbal.setText("Credit Balance : " + attribute.getString("included-credit-balance"));
                    overallCreditBal.setText("Rollover Credit balance : " + attribute.getString("included-rollover-credit-balance"));
                    overallDatabal.setText("Rollover Data Balance : " + attribute.getString("included-rollover-data-balance"));
                    internationtalkBal.setText("International Talk Balance : " + attribute.getString("included-international-talk-balance"));
                    extDate.setText("Expiry Date : " + attribute.getString("expiry-date"));
                    autorenewal.setText("Auto Renewal : " + attribute.getString("auto-renewal"));
                    primayScub.setText("Primary Subscription : " + attribute.getString("primary-subscription"));
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
