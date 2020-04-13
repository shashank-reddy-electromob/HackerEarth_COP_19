package org.electromob.covid19;

import androidx.appcompat.app.AppCompatActivity;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class screen4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);

        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,0);


        dispatcher.addAudioProcessor(new PitchProcessor(PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, new PitchDetectionHandler() {

            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult,
                                    AudioEvent audioEvent) {
                final float pitchInHz = pitchDetectionResult.getPitch();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView text =  findViewById(R.id.frequency);
                        text.setText("" + pitchInHz);
                    }
                });

            }
        }));
        new Thread(dispatcher,"Audio Dispatcher").start();

    }

    public void start(View v){

    }
}
