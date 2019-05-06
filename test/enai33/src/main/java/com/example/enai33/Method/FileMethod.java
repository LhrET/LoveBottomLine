package com.example.enai33.Method;

import android.os.Environment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FileMethod {

    public static void removeFile(String filename) {
        File file = new File(Environment.getExternalStorageDirectory(),filename);
        if(file.exists()) {
            file.delete();
        }
    }

    public static String readLastLineFile(String filename){
        File file = new File(Environment.getExternalStorageDirectory(),filename);
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line = null;
            while((line = br.readLine()) != null) {
                arrayList.add(line);
            }
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String saidArray = arrayList.get(arrayList.size()-1);
        return saidArray;
    }


    public static String[] readAllFile(String filename){
        File file = new File(Environment.getExternalStorageDirectory(),filename);
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line;
            while((line = br.readLine()) != null) {
                arrayList.add(line);
            }
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int len = arrayList.size();
        String[] saidArray = arrayList.toArray(new String[len]);
        return saidArray;
    }


    public static void writeLineFile(String filename, String str){
        File file = new File(Environment.getExternalStorageDirectory(),filename);
        try {
            FileOutputStream out = new FileOutputStream(file, true);
            OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
            BufferedWriter bufWrite = new BufferedWriter(outWriter);

            bufWrite.write(str + "\r\n");

            bufWrite.close();
            outWriter.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取" + filename + "出错！");
        }
    }


}
