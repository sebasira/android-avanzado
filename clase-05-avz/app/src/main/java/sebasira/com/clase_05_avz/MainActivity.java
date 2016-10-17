package sebasira.com.clase_05_avz;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAlarma = (Button) findViewById(R.id.btnAlarm);
        btnAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarma();
            }
        });
    }


    /* SETEAR ALARMA */
    /* ************* */
    private void setAlarma(){
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this,
                0,
                intent,
                0);

        // Seleccion del tipo de Alarma
        int tipoAlarma = AlarmManager.ELAPSED_REALTIME;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(tipoAlarma,
                SystemClock.elapsedRealtime() + (1000 * 10),
                alarmIntent);
        finish();
    }
}
