package sebasira.com.clase_02_avz;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

public class WSActivity extends AppCompatActivity {
    TextView tvID;
    TextView tvDesc;
    TextView tvImage;
    TextView tvPrecio;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws);

        tvID = (TextView) findViewById(R.id.tvID);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        tvImage = (TextView) findViewById(R.id.tvImage);
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);
        imagen = (ImageView) findViewById(R.id.img);

        Button btnProd1 = (Button) findViewById(R.id.btnProd1);
        btnProd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TareaGetProducto().execute("1");
            }
        });

        Button btnProd2 = (Button) findViewById(R.id.btnProd2);
        btnProd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TareaGetProducto().execute("2");
            }
        });


        // ImageLoader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }


    // Tarea para consumir servicio REST
    private class TareaGetProducto extends AsyncTask<String, Void, Producto> {
        @Override
        protected Producto doInBackground(String... params) {
            Producto p = null;

            try {
                // Create an HTTP client
                HttpClient client = new DefaultHttpClient();
                HttpGet post = new HttpGet("http://webkathon.com/pruebasit/products.php?id="+params[0]);

                // Perform the request and check the status code
                HttpResponse response = client.execute(post);
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == 200) {
                    // code 200 equals HTTP OK
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();

                    try {
                        String str = IOUtils.toString(content, "utf-8");

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        p = gson.fromJson(str, Producto.class);


                    } catch (Exception ex) {
                    }
                }
            } catch(Exception ex) {
            }
            return p;
        }

        @Override
        protected void onPostExecute(Producto producto) {
            super.onPostExecute(producto);

            tvID.setText(producto.getId());
            tvDesc.setText(producto.getDescription());
            tvImage.setText(producto.getImage());
            tvPrecio.setText(producto.getPrice());

            ImageLoader.getInstance().displayImage(producto.getImage(), imagen);
        }
    }
}
