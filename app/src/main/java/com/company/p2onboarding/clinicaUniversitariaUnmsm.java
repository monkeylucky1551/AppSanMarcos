package com.company.p2onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class clinicaUniversitariaUnmsm extends AppCompatActivity implements TextToSpeech.OnInitListener{

    ViewFlipper v_flipper;
    private TextToSpeech tts;
    private TextView textViewClinica;
    private ImageButton btnClinicaPlay;
    private ImageButton btnClinicaStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinica_universitaria_unmsm);

        int[] images = {R.drawable.clinica_unmsm1,R.drawable.clinica_unmsm2,R.drawable.clinica_unmsm3};

        v_flipper = findViewById(R.id.flipper_clinica);

        for(int image:images){
            flipperImages(image);
        }

        tts = new TextToSpeech(this,this);
        btnClinicaPlay = (ImageButton) findViewById(R.id.clinicaVozPlay);
        btnClinicaStop = (ImageButton) findViewById(R.id.ClinicaAVozStop);
        textViewClinica = (TextView) findViewById(R.id.cuerpoClinica);
        btnClinicaPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
                Toast.makeText(clinicaUniversitariaUnmsm.this, "Espere un momento por favor", Toast.LENGTH_SHORT).show();
            }
        });
        btnClinicaStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.stop();
            }
        });
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);
        v_flipper.setInAnimation(this,android.R.anim.slide_out_right);
        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.getDefault());
            if(result==TextToSpeech.LANG_NOT_SUPPORTED
                    || result ==TextToSpeech.LANG_MISSING_DATA){
                Log.e("TTS","Este lenguaje no es soportado");
            }
        }
        else{
            Log.e("TTS","inicializacion del lenguaje falla");
        }
    }

    private void speakOut() {
        String textoClinica = textViewClinica.getText().toString();
        tts.speak(textoClinica,TextToSpeech.QUEUE_FLUSH,null);
    }


}