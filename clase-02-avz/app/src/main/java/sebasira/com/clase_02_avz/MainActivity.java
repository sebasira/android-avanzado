package sebasira.com.clase_02_avz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos referencia al Layout para cambiar su color
        final LinearLayout layout = (LinearLayout) findViewById(R.id.layout);

        // BOTON FONDO ROJO
        Button btnRed = (Button) findViewById(R.id.btnRed);
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundColor(Color.RED);
            }
        });

        // BOTON FONDO AZUL
        Button btnBlue = (Button) findViewById(R.id.btnBlue);
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundColor(Color.BLUE);
            }
        });

        // BOTON PARA CALCULO PESADO
        Button btnFactorial = (Button) findViewById(R.id.btnFactorial);

        btnFactorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si llamamos directamente al calculo, vamos a forzar un ANR
                // Quitar el comentario para ver el efecto
                //int resultado = calculoFactorial();
                //Toast.makeText(getApplicationContext(), "La suma de factoriales de 1 a 50000 es :" + resultado,Toast.LENGTH_LONG).show();


                // En cambio acá abajo realizamos la misma tarea, pero con un
                // AsyncTask justamente para evitar un ANR
                TareaFactorial miTarea = new TareaFactorial(MainActivity.this);
                miTarea.execute();
            }
        });


        // BOTON PARA IR AL WEB SERVICE
        Button btnWS = (Button) findViewById(R.id.btnWS);
        btnWS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), WSActivity.class);
                startActivity(i);
            }
        });
    }

    private int calculoFactorial(){
        int factorial = 0;
        int sumaFactoriales = 0;
        for (int i=0; i<60000; i++){
            factorial = 1;
            for (int j=1; j<i; j++){
                factorial = factorial * j;
            }
            sumaFactoriales += factorial;
        }

        return sumaFactoriales;
    }


    private class TareaFactorial extends AsyncTask <Void, Void, Integer>{
        private Context context;
        private ProgressDialog progress;

        public TareaFactorial (Context context){
            this.context = context;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Creamos el dialogo de progreso en tiempo de ejecución
            progress = new ProgressDialog(context);
            progress.setTitle(context.getString(R.string.progress_title));
            progress.setMessage(context.getString(R.string.progress_msg));
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            // Realizamos la tarea en segundo plano
            return calculoFactorial();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            // Cancelamos el dialogo de progreso
            progress.dismiss();

            // Mostrar el resultado
            Toast.makeText(getApplicationContext(), "La suma de factoriales de 1 a 50000 es :" + integer,Toast.LENGTH_LONG).show();
        }
    }
}