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

public class CronoActivity extends AppCompatActivity {

    // BOUND
    Intent boundIntent;
    boolean boundConectado;
    BoundService cronometro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound);

        //##################################################################
        //#### BOUND SERVICE

        // Boton para iniciar cronometro
        Button btnIniCrono = (Button) findViewById(R.id.btnIniCrono);
        btnIniCrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cronometro != null){
                    cronometro.iniciarCronometro();
                    Toast.makeText(getApplicationContext(), "Cronómetro Iniciado",
                            Toast.LENGTH_SHORT).show();

                    // Es importante remarcar que iniciarCronometro() es ejecutado en el mismo hilo
                    // (Thread) que el cliente que lo invoca, en este caso el UI Thread, por tanto
                    // si la operación es de largo tiempo de ejecución, deberíamos crear un hilo
                    // a parte, donde correr este método
                }
            }
        });



        // Boton para Obtener tiempo
        Button btnGetTiempo = (Button) findViewById(R.id.btnGetTiempo);
        btnGetTiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cronometro != null){
                    Toast.makeText(getApplicationContext(), "Milisegundos transcurridos: " +
                                    cronometro.obtenerTiempo(),
                            Toast.LENGTH_SHORT).show();

                    // Es importante remarcar que obtenerTiempo() es ejecutado en el mismo hilo
                    // (Thread) que el cliente que lo invoca, en este caso el UI Thread, por tanto
                    // si la operación es de largo tiempo de ejecución, deberíamos crear un hilo
                    // a parte, donde correr este método
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Intento para el BoundService
        boundIntent = new Intent(getApplicationContext(), BoundService.class);
        bindService(boundIntent, conexion, BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();

        if (boundConectado == true){
            unbindService(conexion);
            boundConectado = false;
        }
    }


    // ServiceConection que debe proveer el cliente para conectarse a un servicio
    private ServiceConnection conexion = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // Primero hago un CAST para el tipo de binder, y luego obtengo la referencia
            // a ese servicio
            BoundService.CronoBinder binder = (BoundService.CronoBinder) service;
            cronometro = binder.obtenerServicio();
            boundConectado = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            boundConectado = false;
        }
    };
}
