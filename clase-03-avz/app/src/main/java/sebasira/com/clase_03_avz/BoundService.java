package sebasira.com.clase_03_avz;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by sebas on 10/4/16.
 */
public class BoundService extends Service {
    private static final String TAG = "BoundService";
    private IBinder binder;

    private long tiempoInicio;


    @Override
    public void onCreate() {
        super.onCreate();

        // Creamos la interfaz de comunicacion (IBinder) y la devolvemos al cliente
        binder = new CronoBinder();


        Log.e(TAG, "Servicio Creado");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "Servicio Conectado");
        return binder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "Servicio Desconectado");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e(TAG, "Servicio Destruido");
    }


    // METODO PARA INICIAR CRONOMETRO
    public void iniciarCronometro (){
        Log.e(TAG, "Cronometro Iniciado ");
        tiempoInicio = System.currentTimeMillis();
    }


    // METODO PARA OBTENER TIEMPO
    public long obtenerTiempo (){
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
        return tiempoTranscurrido;
    }


    // Clase interna que extiende de BINDER y que tiene un sólo metodo
    // que nos devuelve una referencia a este servicio
    public class CronoBinder extends Binder{

        public BoundService obtenerServicio (){
            return BoundService.this;
        }
    }

}
