package com.example.projeto3mundo4;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {

    private AudioHelper audioHelper;
    private TextToSpeech tts;
    private ArrayList<String> tasks;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_view);
        tasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);
        audioHelper = new AudioHelper(this);

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(new Locale("pt", "BR"));
            }
        });

        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String task = matches.get(0);
                    tasks.add(task);
                    adapter.notifyDataSetChanged();

                    if (audioHelper.isSpeakerAvailable() || audioHelper.isBluetoothConnected()) {
                        tts.speak("Item adicionado", TextToSpeech.QUEUE_FLUSH, null, null);
                    } else {
                        Toast.makeText(MainActivity.this, "Item adicionado", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override public void onError(int error) {
                tts.speak("Erro ao reconhecer a fala", TextToSpeech.QUEUE_FLUSH, null, null);
            }

            @Override public void onReadyForSpeech(Bundle params) {}
            @Override public void onBeginningOfSpeech() {}
            @Override public void onRmsChanged(float rmsdB) {}
            @Override public void onBufferReceived(byte[] buffer) {}
            @Override public void onEndOfSpeech() {}
            @Override public void onPartialResults(Bundle partialResults) {}
            @Override public void onEvent(int eventType, Bundle params) {}
        });

        Button btnTalk = findViewById(R.id.button_add);
        btnTalk.setOnClickListener(v -> {
            if (audioHelper.isSpeakerAvailable() || audioHelper.isBluetoothConnected()) {
                tts.speak("Escutando", TextToSpeech.QUEUE_FLUSH, null, null);

                tts.setOnUtteranceProgressListener(new android.speech.tts.UtteranceProgressListener() {
                    @Override
                    public void onStart(String utteranceId) {}

                    @Override
                    public void onDone(String utteranceId) {
//                        runOnUiThread(() -> speechRecognizer.startListening(recognizerIntent));
                        runOnUiThread(() -> processSimulatedSpeech("Dormir"));
                    }

                    @Override
                    public void onError(String utteranceId) {
//                        runOnUiThread(() -> speechRecognizer.startListening(recognizerIntent));
                        runOnUiThread(() -> processSimulatedSpeech("Dormir"));
                    }
                });
                tts.speak("Escutando", TextToSpeech.QUEUE_FLUSH, null, "utteranceId");
            } else {
                Toast.makeText(MainActivity.this, "Escutando...", Toast.LENGTH_SHORT).show();
                speechRecognizer.startListening(recognizerIntent);
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

    private void processSimulatedSpeech(String text) {
        tasks.add(text);
        adapter.notifyDataSetChanged();

        if (audioHelper.isSpeakerAvailable() || audioHelper.isBluetoothConnected()) {
            tts.speak("Item adicionado", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            Toast.makeText(MainActivity.this, "Item adicionado", Toast.LENGTH_SHORT).show();
        }
    }
}
