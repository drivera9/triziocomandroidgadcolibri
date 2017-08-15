package trizio.ram.com.triziocomandroidgradlenuevo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class AnadirBorrarRazones extends Activity {
    String conexion = "";
    String ip = "";
    String empresa = "";
    String sucursal = "";
    String Id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_borrar_razones);
        conexion = getIntent().getExtras().getString("conexion");
        ip = getIntent().getExtras().getString("ip");
        empresa = getIntent().getExtras().getString("empresa");
        sucursal = getIntent().getExtras().getString("sucursal");
    }

    public void añadir(View v){
        final EditText tecnico = (EditText) findViewById(R.id.tecnico);
        if (tecnico.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Por favor digite la razon !", Toast.LENGTH_SHORT).show();
        }else{

            AlertDialog.Builder builder1 = new AlertDialog.Builder(AnadirBorrarRazones.this);
            builder1.setMessage("Esta seguro que desea añadir la razon?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "SI",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String url = "http://" + ip + "/guardarMovimientoGad.php";

                            //cod_proceso = "1";
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("sCodEmpresa", empresa));
                            params.add(new BasicNameValuePair("sCodSucursal", sucursal));
                            params.add(new BasicNameValuePair("sValRecurso", tecnico.getText().toString().trim()));
                            params.add(new BasicNameValuePair("sParametro", "anadirRazon"));

                            String resultServer7 = getHttpPost(url, params);
                            System.out.println(resultServer7);

                            Toast.makeText(getApplicationContext(), "Haz añadido la razon correctamente!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });

            builder1.setNegativeButton(
                    "NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();


        }
    }

    public void borrar(View v){
        final EditText tecnico = (EditText) findViewById(R.id.tecnico);
        if (tecnico.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Por favor digite la razon !", Toast.LENGTH_SHORT).show();
        }else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(AnadirBorrarRazones.this);
            builder1.setMessage("Esta seguro que desea borrar la razon?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "SI",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String url = "http://" + ip + "/guardarMovimientoGad.php";

                            //cod_proceso = "1";
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("sCodEmpresa", empresa));
                            params.add(new BasicNameValuePair("sCodSucursal", sucursal));
                            params.add(new BasicNameValuePair("sValRecurso", Id));
                            params.add(new BasicNameValuePair("sParametro", "borrarRazon"));

                            String resultServer7 = getHttpPost(url, params);
                            System.out.println(resultServer7);

                            Toast.makeText(getApplicationContext(), "Haz borrado la razon correctamente!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });

            builder1.setNegativeButton(
                    "NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();


        }
    }

    public void ver(View v){
        Intent intent = new Intent((Context) AnadirBorrarRazones.this, (Class) MostrarRombUbic.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("conexion", conexion);
        bundle2.putString("ip", ip);
        bundle2.putString("atributo", "RAZON");
        bundle2.putString("empresa", empresa);
        bundle2.putString("sucursal", sucursal);
        intent.putExtras(bundle2);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        try {
            String parametro = data.getStringExtra("parametro");
            String valor = data.getStringExtra("valor");
            Id = valor;
            String descripcion = data.getStringExtra("descripcion");
            if (requestCode == 1) {
                EditText tecnico = (EditText) findViewById(R.id.tecnico);
                tecnico.setText(descripcion);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
}
