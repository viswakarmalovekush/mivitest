package mivi.com.mivitestapp;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MyUtils {

    public static String loadJsonAssets(Context mContext) {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("collection.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static void myToast(Context mContext,String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
    }
}
