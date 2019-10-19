package com.gurgur.gunlukkurlar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterDeger mAdapter;
    List<ModelDeger> mList;
    TextView updateDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.degerr);
        mList = new ArrayList<>();
        updateDate = findViewById(R.id.lastUpdate);

        GetDatas();
    }

    public void AutoAdd(JSONObject jsonObject,String adi){

        try {
            JSONObject jsonAbdDolar = new JSONObject(jsonObject.getString(adi));
            mList.add(new ModelDeger(adi,jsonAbdDolar.getString("Alış"),jsonAbdDolar.getString("Satış"),jsonAbdDolar.getString("Tür")));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void GetDatas(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://finans.truncgil.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        DovizApiInterface client = retrofit.create(DovizApiInterface.class);

        Call<ResponseBody> call = client.OgrencileriAl();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {

                    JSONObject jsonObject =new JSONObject(response.body().string());

                /*    JSONObject jsonAbdDolar = new JSONObject(jsonObject.getString("ABD DOLARI"));
                    mList.add(new ModelDeger("ABD DOLARI",jsonAbdDolar.getString("Alış"),jsonAbdDolar.getString("Satış"),jsonAbdDolar.getString("Tür")));
*/

                    updateDate.setText("Son Güncelleme : " + jsonObject.getString("Güncelleme Tarihi"));

                    AutoAdd(jsonObject,"ABD DOLARI");
                    AutoAdd(jsonObject,"AVUSTRALYA DOLARI");


                    AutoAdd(jsonObject,"DANİMARKA KRONU");
                    AutoAdd(jsonObject,"EURO");
                    AutoAdd(jsonObject,"İNGİLİZ STERLİNİ");
                    AutoAdd(jsonObject,"İSVİÇRE FRANGI");
                    AutoAdd(jsonObject,"İSVEÇ KRONU");
                    AutoAdd(jsonObject,"KANADA DOLARI");
                    AutoAdd(jsonObject,"KUVEYT DİNARI");
                    AutoAdd(jsonObject,"NORVEÇ KRONU");
                    AutoAdd(jsonObject,"SUUDİ ARABİSTAN RİYALİ");
                    AutoAdd(jsonObject,"JAPON YENİ");
                    AutoAdd(jsonObject,"BULGAR LEVASI");
                    AutoAdd(jsonObject,"RUMEN LEYİ");
                    AutoAdd(jsonObject,"RUS RUBLESİ");
                    AutoAdd(jsonObject,"İRAN RİYALİ");
                    AutoAdd(jsonObject,"ÇİN YUANI");
                    AutoAdd(jsonObject,"PAKİSTAN RUPİSİ");
                    AutoAdd(jsonObject,"KATAR RİYALİ");
                    AutoAdd(jsonObject,"Çeyrek Altın");
                    AutoAdd(jsonObject,"Yarım Altın");
                    AutoAdd(jsonObject,"Tam Altın");
                    AutoAdd(jsonObject,"Cumhuriyet Altını");
                    AutoAdd(jsonObject,"Ons");
                    AutoAdd(jsonObject,"Gram Altın");
                    AutoAdd(jsonObject,"Ata Altın");
                    AutoAdd(jsonObject,"14 Ayar Altın");
                    AutoAdd(jsonObject,"18 Ayar Altın");
                    AutoAdd(jsonObject,"22 Ayar Bilezik");
                    AutoAdd(jsonObject,"İkibuçuk Altın");
                    AutoAdd(jsonObject,"Beşli Altın");
                    AutoAdd(jsonObject,"Gremse Altın");
                    AutoAdd(jsonObject,"Gümüş");

                    mAdapter =new AdapterDeger(mList,MainActivity.this);


                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    linearLayoutManager.scrollToPosition(0);
                    recyclerView.setLayoutManager(linearLayoutManager);





                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());






                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }


}
