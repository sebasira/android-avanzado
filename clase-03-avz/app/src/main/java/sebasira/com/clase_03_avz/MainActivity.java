package sebasira.com.clase_03_avz;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // STARTED
    Intent startedIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // Boton para IR AL CRONOMETROS
        Button btnIrAcrono = (Button) findViewById(R.id.btnIrAcrono);
        btnIrAcrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CronoActivity.class);
                startActivity(i);
            }
        });

    }
}
