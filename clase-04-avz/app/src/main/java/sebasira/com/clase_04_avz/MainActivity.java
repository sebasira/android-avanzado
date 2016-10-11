package sebasira.com.clase_04_avz;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION_FIN_TIEMPO = "sebasira.com.clase_04_avz.FIN_TIEMPO";

    // STARTED
    Intent startedIntent;

    TemporizadorBroadcastReceiver miReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miReceiver = new TemporizadorBroadcastReceiver();


        //##################################################################
        //#### STARTED SERVICE

        // Intento para el StartedService
        startedIntent = new Intent(getApplicationContext(), StartedService.class);

        // Boton para INICIAR el StartedService
        Button btnIniStarted = (Button) findViewById(R.id.btnIniStarted);
        btnIniStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(startedIntent);
            }
        });

        // Boton para FINALIZAR el StartedService
        Button btnFinStarted = (Button) findViewById(R.id.btnFinStarted);
        btnFinStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(startedIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(ACTION_FIN_TIEMPO);
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(miReceiver, iFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(miReceiver);
    }

    public class TemporizadorBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(), "Temporizador finalizado", Toast.LENGTH_SHORT )
                    .show();
        }
    }
}
