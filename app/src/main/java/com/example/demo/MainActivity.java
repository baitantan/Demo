package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.util.HttpUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private String ss;
    private TextView editText;
    String strurl="http://59.110.220.142:8080/Demo/DemoServlet";
    URL url=null;
    String responseData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edit_query);
        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ss=editText.getText().toString();
               // Toast.makeText(MainActivity.this,"ss",Toast.LENGTH_SHORT).show();
               String q="http://59.110.220.142:8080/Demo/DemoServlet?param=ss";
               String a="http://www.baidu.com";
                HttpUtil.sendOkHttpRequest(q,new okhttp3.Callback(){
                    @Override
                    public void onResponse(Call call, final Response response) throws IOException{
                        
                        responseData = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                editText.setText(response.toString());
                                Log.d("jieguo",responseData);
                               // Toast.makeText(MainActivity.this,"ss1",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    @Override
                    public void onFailure(Call call,IOException e){

                    }
                });
                /*private void postDataWithParame() {
                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
                    FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
                    formBody.add("username","zhangsan");//传递键值对参数
                    Request request = new Request.Builder()//创建Request 对象。
                            .url("http://www.baidu.com")
                            .post(formBody.build())//传递请求体
                            .build();
                    client.newCall(request).enqueue(new Callback() {。。。});//回调方法的使用与get异步请求相同，此时略。
                }*/
               /* String strurl="http://59.110.220.142:8080/Demo/DemoServlet";
                URL url=null;
                try{
                    url=new URL(strurl);
                    HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setUseCaches(false);
                    urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    urlConnection.setRequestProperty("Charset","utf-8");

                    urlConnection.connect();

                    DataOutputStream dop =new DataOutputStream(urlConnection.getOutputStream());
                    dop.writeBytes("param="+ URLEncoder.encode(ss,"utf-8"));
                    dop.flush();
                    dop.close();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String result = "";
                    String readline = null;
                    while ((readline=bufferedReader.readLine())!=null){
                        result += readline;
                    }
                    bufferedReader.close();
                    urlConnection.disconnect();
                    Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }*/
            }
        });


    }
    public void getDatasync(){
        new Thread(new Runnable() {

            @Override

            public void run() {
                try {
                    //url=new URL(strurl);
                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                    Request request = new Request.Builder()
                            .url("http://www.baidu.com")//请求接口。如果需要传参拼接到接口后面。
                            .build();//创建Request 对象
                    Response response = null;
                    response = client.newCall(request).execute();//得到Response 对象
                    if (response.isSuccessful()) {
                        Log.d("kwwl","response.code()=="+response.code());
                        Log.d("kwwl","response.message()=="+response.message());
                        Log.d("kwwl","res=="+response.body().string());
                        //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
