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
import android.widget.TimePicker;

import java.util.Calendar;

public class RTCActivity extends AppCompatActivity {
    CheckBox cbWakeUp;
    Button btnSetAlarm;
    Calendar calendar;
    TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtc);

        cbWakeUp = (CheckBox) findViewById(R.id.cbWakeUp);
        //timePicker = (TimePicker) findViewById(R.id.timePicker);

        calendar = Calendar.getInstance();

        //timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        //timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        //timePicker.setIs24HourView(true);



        btnSetAlarm = (Button) findViewById(R.id.btnSetAlarm);
        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RTCActivity.this, AlarmActivity.class);
                PendingIntent alarmIntent = PendingIntent.getActivity(RTCActivity.this,
                        0,
                        intent,
                        0);

                // Seleccion del tipo de Alarma
                int tipoAlarma;
                if (cbWakeUp.isChecked()) {
                    tipoAlarma = AlarmManager.RTC_WAKEUP;
                }else{
                    tipoAlarma = AlarmManager.RTC;
                }

                /** Para setear la hora de la alarma un par de segundos mas tarde, descomentar
                 * la línea de abajo */
                calendar.add(Calendar.SECOND, 10);

                /** El siguiente bloque de código es para usar el TimePicker para la hora de la
                 * alarma */
                //calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                //calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(tipoAlarma,
                        calendar.getTimeInMillis(),
                        alarmIntent);

                finish();
            }
        });
    }
}
