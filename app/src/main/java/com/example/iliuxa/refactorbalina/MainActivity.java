package com.example.iliuxa.refactorbalina;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.iliuxa.refactorbalina.Model.POJO.Yml_catalog;
import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadDataBase test = new DownloadDataBase();
                test.execute();
            }
        });
    }

    private String getHttpRequest(String path) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(path)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public class DownloadDataBase extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Yml_catalog catalog;
            XmlParserCreator parserCreator = new XmlParserCreator() {
                @Override
                public XmlPullParser createParser() {
                    try {
                        return XmlPullParserFactory.newInstance().newPullParser();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            };

            GsonXml gsonXml = new GsonXmlBuilder()
                    .setXmlParserCreator(parserCreator)
                    .create();
            try {
                catalog = gsonXml.fromXml(
                        getHttpRequest("http://ufa.farfor.ru/getyml/?key=ukAXxeJYZN"),
                Yml_catalog.class);
                catalog.getClass();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
                button.setText("Blala");
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }

}
