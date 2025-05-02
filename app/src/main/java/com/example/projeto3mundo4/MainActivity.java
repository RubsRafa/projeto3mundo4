package com.example.projeto3mundo4;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends Activity {

    private AudioHelper audioHelper;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        audioHelper = new AudioHelper(this);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(new Locale("pt", "BR"));
                }
            }
        });

        Button btnRunAudio = findViewById(R.id.button_add);
        btnRunAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean speaker = audioHelper.isSpeakerAvailable();
                boolean bluetooth = audioHelper.isBluetoothConnected();

                String message = "";

                if (speaker) {
                    message += "Alto-falante disponível \n";
                } else {
                    message += "Sem alto-falante \n";
                }

                if (bluetooth) {
                    message += "Fone Bluetooth conectado \n";
                } else {
                    message += "Sem fone Bluetooth \n";
                }

                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });


    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
