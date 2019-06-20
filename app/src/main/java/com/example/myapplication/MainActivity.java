package com.example.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Document;

import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    ImageView weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherIcon=(ImageView)findViewById(R.id.weather_icon);

        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputStream, null);
        org.w3c.dom.Element tempElement = (org.w3c.dom.Element)(doc.get)

    }

    HttpCallback httpCallback=new HttpCallback() {
        @Override
        public void onResult(String result) {
            //add~~~~~~~~~~~~~~
            try{
                JSONObject root=new JSONObject(result);
                titleView.setText(root.getString("title"));
                dateView.setText(root.getString("date"));
                contentView.setText(root.getString("content"));

                String imageFile=root.getString("file");
                if(imageFile!=null && !imageFile.equals("")){
                    HttpImageRequester imageRequester=new HttpImageRequester();
                    imageRequester.request("http://192.168.0.57:8000/files/"+imageFile, null, imageCallback);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    HttpImageCallback imageCallback=new HttpImageCallback() {
        @Override
        public void onResult(Bitmap d) {
            //add~~~~~~~~~~~~
            imageView.setImageBitmap(d);
        }
    };

}
