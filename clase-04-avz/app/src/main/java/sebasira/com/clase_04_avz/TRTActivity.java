package sebasira.com.clase_04_avz;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class TRTActivity extends AppCompatActivity {
    CheckBox cbWakeUp;
    Button btnSetAlarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trt);

        cbWakeUp = (CheckBox) findViewById(R.id.cbWakeUp);

        btnSetAlarm = (Button) findViewById(R.id.btnSetAlarm);
        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TRTActivity.this, AlarmActivity.class);
                PendingIntent alarmIntent = PendingIntent.getActivity(TRTActivity.this,
                        0,
                        intent,
                        0);

                // Seleccion del tipo de Alarma
                int tipoAlarma;
                if (cbWakeUp.isChecked()) {
                    tipoAlarma = AlarmManager.ELAPSED_REALTIME_WAKEUP;
                }else{
                    tipoAlarma = AlarmManager.ELAPSED_REALTIME;
                }

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(tipoAlarma,
                        SystemClock.elapsedRealtime() + (1000 * 10),
                        alarmIntent);

                finish();
            }
        });
    }
}
