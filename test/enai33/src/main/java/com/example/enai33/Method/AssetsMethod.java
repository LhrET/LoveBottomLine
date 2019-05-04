package com.example.enai33.Method;

import android.app.Activity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class AssetsMethod extends Activity {

    public String ReadAssetsFile(String fileName, int index) {
        String result = "";
        String line;
        InputStreamReader isr = null;
        BufferedReader bufReader = null;
        int i = 1;
        try {
            isr = new InputStreamReader( getAssets().open(fileName) );
            bufReader = new BufferedReader(isr);
            while((line = bufReader.readLine()) != null && i <= index) {
                if(i == index){
                    result = line;
                }
                i++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufReader != null){
                try {
                    bufReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(isr != null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


}
