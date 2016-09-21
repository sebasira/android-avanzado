package sebasira.com.clase_01_avz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Fragmento1 f1;
    Fragmento2 f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f1 = (Fragmento1) getSupportFragmentManager().findFragmentById(R.id.fragmento1);
        f2 = (Fragmento2) getSupportFragmentManager().findFragmentById(R.id.fragmento2);

        Button btn = (Button) findViewById(R.id.irAMaster);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PeliculaListActivity.class);
                startActivity(i);
            }
        });
    }

    public void clickFragmento1(){
        f1.fragmentoSeleccionado(true);
        f2.fragmentoSeleccionado(false);
    }

    public void clickFragmento2(){
        f1.fragmentoSeleccionado(false);
        f2.fragmentoSeleccionado(true);
    }
}
