package sebasira.com.clase_02_avz;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONObject;

import java.io.InputStream;

public class WSActivity extends AppCompatActivity {
    TextView tvID;
    TextView tvDesc;
    TextView tvImage;
    TextView tvPrecio;
    ImageView imagen;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws);

        tvID = (TextView) findViewById(R.id.tvID);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        tvImage = (TextView) findViewById(R.id.tvImage);
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);
        imagen = (ImageView) findViewById(R.id.img);

        // Inicializamos la nueva Cola de Requests
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Button btnProd1 = (Button) findViewById(R.id.btnProd1);
        btnProd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://sebasira.com.ar/cursos/android-avanzado/ferreteria.php?id=1";
                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            Gson gson = gsonBuilder.create();
                            Producto producto = gson.fromJson(response.toString(), Producto.class);

                            tvID.setText(producto.getId());
                            tvDesc.setText(producto.getDescription());
                            tvImage.setText(producto.getImage());
                            tvPrecio.setText(producto.getPrice());

                            ImageLoader.getInstance().displayImage(producto.getImage(), imagen);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub

                        }
                    });

                requestQueue.add(jsObjRequest);
            }
        });

        Button btnProd2 = (Button) findViewById(R.id.btnProd2);
        btnProd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new TareaGetProducto().execute("2");

                String url = "http://sebasira.com.ar/cursos/android-avanzado/ferreteria.php?id=2";
                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson gson = gsonBuilder.create();
                                Producto producto = gson.fromJson(response.toString(), Producto.class);

                                tvID.setText(producto.getId());
                                tvDesc.setText(producto.getDescription());
                                tvImage.setText(producto.getImage());
                                tvPrecio.setText(producto.getPrice());

                                ImageLoader.getInstance().displayImage(producto.getImage(), imagen);
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub

                            }
                        });

                requestQueue.add(jsObjRequest);
            }
        });


        Button btnProd3 = (Button) findViewById(R.id.btnProd3);
        btnProd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new TareaGetProducto().execute("3");

                String url = "http://sebasira.com.ar/cursos/android-avanzado/ferreteria.php?id=3";
                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson gson = gsonBuilder.create();
                                Producto producto = gson.fromJson(response.toString(), Producto.class);

                                tvID.setText(producto.getId());
                                tvDesc.setText(producto.getDescription());
                                tvImage.setText(producto.getImage());
                                tvPrecio.setText(producto.getPrice());

                                ImageLoader.getInstance().displayImage(producto.getImage(), imagen);
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub

                            }
                        });

                requestQueue.add(jsObjRequest);
            }
        });


        // ImageLoader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }

/*
    // Tarea para consumir servicio REST
    private class TareaGetProducto extends AsyncTask<String, Void, Producto> {
        @Override
        protected Producto doInBackground(String... params) {
            Producto p = null;

            try {
                // Create an HTTP client
                HttpClient client = new DefaultHttpClient();
                HttpGet post = new HttpGet("http://sebasira.com.ar/cursos/android-avanzado/ferreteria.php?id="+params[0]);

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
*/
}
