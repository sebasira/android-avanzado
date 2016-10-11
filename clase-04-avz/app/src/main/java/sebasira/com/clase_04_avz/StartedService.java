package sebasira.com.clase_04_avz;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


/**
 * Created by sebas on 10/4/16.
 */
public class StartedService extends Service {
    private static final String TAG = "StartedService";
    TareaTemporizada miTarea;

    // Todo Servicio debe tener este metodo, pero en los Started no los usamos
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Se llama cuando se crea (inicia) por primera vez el servicio. Si ya estaba
    // iniciado, no se vuelve a invocar
    @Override
    public void onCreate() {
        super.onCreate();

        Log.e(TAG, "Servicio Creado");
    }


    // Se llama cada vez que se invoca a startService(intento). Si es la primera vez, previamente
    // se llama a onCreate, si ya se encontraba iniciado, no se llama a onCreate pero sí a este
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "Servicio Iniciado, con ID:" + startId);

        miTarea = new TareaTemporizada();
        miTarea.execute(startId);

        return super.onStartCommand(intent, flags, startId);

        // Vamos a ver distintos valore de RETURN, para definir el comportamiento. Vamos a simular
        // que el sistema detiene la app, haciendo un FORCE STOP de la misma

        // START_STICKY => Cuando el sistema lo detenga, se va a AUTO REINICIAR
        //return START_STICKY;

        // START_NOT_STICKY => Cuando el sistema lo detenga, no se va a reiniciar
        //return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "Servicio Destruido");

        if (miTarea != null){
            miTarea.cancel(true);
        }
    }


    private class TareaTemporizada extends AsyncTask <Integer, Void, Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.e(TAG, "Inicio Temporizacion");
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            int idServicio = params[0];

            Log.e(TAG, "Procesando servicio ID: " + idServicio);

            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            return idServicio;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            Log.e(TAG, "Temporizacion finalizada para el servicio ID: " + integer);

            Intent intent = new Intent(MainActivity.ACTION_FIN_TIEMPO);
            LocalBroadcastManager.getInstance(StartedService.this).sendBroadcast(intent);

            stopSelf(integer);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            Log.e(TAG, "Tarea cancelada");
        }
    }
}
