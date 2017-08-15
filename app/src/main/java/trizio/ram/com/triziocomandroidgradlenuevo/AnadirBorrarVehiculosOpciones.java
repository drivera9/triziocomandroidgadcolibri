package trizio.ram.com.triziocomandroidgradlenuevo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class AnadirBorrarVehiculosOpciones extends Activity {
    String conexion = "";
    String user = "";
    String pass = "";
    String ip = "";
    String titulo = "";
    String proceso = "";
    Boolean mercar = false;
    String empresa;
    String sucursal;
    String procesoAnterior = "";
    String procesOriginal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_borrar_vehiculos);
        final Bundle bundle = this.getIntent().getExtras();
        user = bundle.getString("user");
        pass = bundle.getString("pass");
        sucursal = bundle.getString("sucursal");
        empresa = bundle.getString("empresa");

        proceso = bundle.getString("proceso");
        conexion = bundle.getString("conexion");
        ip = bundle.getString("ip");
        titulo = bundle.getString("titulo");

        ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();
        datos.add(new Lista_entrada(R.drawable.recoger, "Marca"));
        datos.add(new Lista_entrada(R.drawable.recoger, "Referencia"));
        datos.add(new Lista_entrada(R.drawable.recoger, "Modelo"));

        ListView lista = (ListView) findViewById(R.id.listView3);
        lista.setAdapter(new Lista_adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((Lista_entrada) entrada).get_textoEncima());


                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
                }
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion);

                if (elegido.get_textoEncima().equals("Marca")) {
                    Intent intent = new Intent((Context) AnadirBorrarVehiculosOpciones.this, (Class) AnadirBorrarVehiculosDetalle.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("sucursal", sucursal);
                    bundle2.putString("empresa", empresa);
                    bundle2.putString("conexion", conexion);
                    bundle2.putString("sucursal", sucursal);
                    bundle2.putString("ip", ip);
                    bundle2.putString("tipo", "M");
                    intent.putExtras(bundle2);
                    startActivity(intent);
                }

                if (elegido.get_textoEncima().equals("Referencia")) {
                    Intent intent = new Intent((Context) AnadirBorrarVehiculosOpciones.this, (Class) AnadirBorrarVehiculosDetalle.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("sucursal", sucursal);
                    bundle2.putString("empresa", empresa);
                    bundle2.putString("conexion", conexion);
                    bundle2.putString("sucursal", sucursal);
                    bundle2.putString("ip", ip);
                    bundle2.putString("tipo", "R");
                    intent.putExtras(bundle2);
                    startActivity(intent);
                }

                if (elegido.get_textoEncima().equals("Modelo")) {
                    Intent intent = new Intent((Context) AnadirBorrarVehiculosOpciones.this, (Class) AnadirBorrarVehiculosDetalle.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("sucursal", sucursal);
                    bundle2.putString("empresa", empresa);
                    bundle2.putString("conexion", conexion);
                    bundle2.putString("sucursal", sucursal);
                    bundle2.putString("ip", ip);
                    bundle2.putString("tipo", "Y");
                    intent.putExtras(bundle2);
                    startActivity(intent);
                }
            }
        });

    }

    public String getHttpPost(String url, List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    public void regresar(View v) {
        finish();
    }
}
