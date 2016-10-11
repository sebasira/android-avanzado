package sebasira.com.clase_04_avz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlarmManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);

        Button btnTRT = (Button) findViewById(R.id.btnTRT);
        btnTRT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TRTActivity.class);
                startActivity(i);
            }
        });

        Button btnRTC = (Button) findViewById(R.id.btnRTC);
        btnRTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RTCActivity.class);
                startActivity(i);
            }
        });
    }
}
