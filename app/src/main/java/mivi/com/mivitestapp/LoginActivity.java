package mivi.com.mivitestapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        final TextInputEditText inputEditsmn=(TextInputEditText)findViewById(R.id.inputEditsmn);
        Button buttonLogin=(Button)findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputEditsmn.getText().length()>0){

                    try {
                        boolean flag=false;
                        JSONObject jsonObject=new JSONObject(MyUtils.loadJsonAssets(LoginActivity.this));
                        JSONArray jsonArray=jsonObject.getJSONArray("included");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject includJsonObj=jsonArray.getJSONObject(i);
                            String type=includJsonObj.getString("type");
                            if(type.equalsIgnoreCase("services")){
                                JSONObject attribute=includJsonObj.getJSONObject("attributes");
                                String msnid=attribute.getString("msn");
                                if(msnid.equalsIgnoreCase("0468874507")){
                                    flag=true;
                                    break;
                                }
                            }

                        }

                        if(flag){
                            Intent home=new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(home);
                            finish();
                        }else{
                            MyUtils.myToast(LoginActivity.this,"msn id not found in JSON");
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }else{
                    MyUtils.myToast(LoginActivity.this,"please enter msn id!");
                }
            }
        });


    }
}
