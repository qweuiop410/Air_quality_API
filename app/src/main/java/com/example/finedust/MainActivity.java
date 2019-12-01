package com.example.finedust;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String requestUrl;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAsyncTask2 myAsyncTask2 = new MyAsyncTask2();
        myAsyncTask2.execute();
    }

    // 미세먼지
    public class MyAsyncTask2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst?serviceKey=서비스키&numOfRows=10&pageSize=10&pageNo=1&startPage=1&itemCode=PM10&dataGubun=HOUR&searchCondition=MONTH";
            try {
                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            //parser가 시작 될때

                            break;
                        case XmlPullParser.START_TAG:
                            //parser가 끝날때 - ex) <item>

                            tag = parser.getName();
                            if(tag.equals("seoul") || tag.equals("dataTime")) // <addr> 이 맞으면
                            {
                                parser.next(); // <addr> value </addr> 에서 <addr> 다음인 value에 접근, parser는 "value"
                                Log.d(" Value ",parser.getText()); // Value: value
                            }
                            break;
                        case XmlPullParser.END_DOCUMENT:

                            break;
                        case XmlPullParser.END_TAG:
                            //parser가 끝날때 - ex) </item>

                            break;
                        case XmlPullParser.TEXT:
                            // parser가 내용에 접근했을때

                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
