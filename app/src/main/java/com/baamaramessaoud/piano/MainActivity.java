package com.baamaramessaoud.piano;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Dictionary;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button A_key;Button B_key;Button C_key;Button D_key;
    Button E_key;Button F_key;Button G_key;Button H_key;
    Dictionary<Integer,Integer> listRaw = new Hashtable<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                AdView adview = findViewById(R.id.adView);
                AdRequest mAdRequest = new AdRequest.Builder().build();
                adview.loadAd(mAdRequest);
            }
        });

        A_key = findViewById(R.id.A_key);
        A_key.setOnClickListener(this);
        B_key = findViewById(R.id.B_key);
        B_key.setOnClickListener(this);
        C_key = findViewById(R.id.C_key);
        C_key.setOnClickListener(this);
        D_key = findViewById(R.id.D_key);
        D_key.setOnClickListener(this);
        E_key = findViewById(R.id.E_key);
        E_key.setOnClickListener(this);
        F_key = findViewById(R.id.F_key);
        F_key.setOnClickListener(this);
        G_key = findViewById(R.id.G_key);
        G_key.setOnClickListener(this);
        H_key = findViewById(R.id.H_key);
        H_key.setOnClickListener(this);
        listRaw.put(R.id.A_key,R.raw.note1_c);listRaw.put(R.id.B_key,R.raw.note2_d);
        listRaw.put(R.id.C_key,R.raw.note3_e);listRaw.put(R.id.D_key,R.raw.note4_f);
        listRaw.put(R.id.E_key,R.raw.note5_g);listRaw.put(R.id.F_key,R.raw.note6_a);
        listRaw.put(R.id.G_key,R.raw.note7_b);listRaw.put(R.id.H_key,R.raw.note1_c);
    }
    public void startAudio(int rawSound){

        MediaPlayer sound =MediaPlayer.create(MainActivity.this,rawSound);
        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
        sound.start();
    }

    @Override
    public void onClick(View v) {
       startAudio(listRaw.get(v.getId()));
    }
}
