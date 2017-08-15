package trizio.ram.com.triziocomandroidgradlenuevo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.pdf.parser.Line;

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


public class AnadirBorrarVehiculosDetalle extends Activity {
    String conexion = "";
    String ip = "";
    String empresa = "";
    String sucursal = "";
    String tipo = "";
    String Id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_borrar_vehiculos_detalle);
        conexion = getIntent().getExtras().getString("conexion");
        ip = getIntent().getExtras().getString("ip");
        empresa = getIntent().getExtras().getString("empresa");
        sucursal = getIntent().getExtras().getString("sucursal");
        tipo = getIntent().getExtras().getString("tipo");
    }

    public void añadir(View v){
        final EditText tecnico = (EditText) findViewById(R.id.tecnico);
        if (tecnico.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Por favor digite el valor !", Toast.LENGTH_SHORT).show();
        }else{

            if (tipo.trim().equals("M")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AnadirBorrarVehiculosDetalle.this);
                builder1.setMessage("Esta seguro que desea añadir?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SI",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String url = "http://" + ip + "/guardarMovimientoGad.php";

                                //cod_proceso = "1";
                                List<NameValuePair> params = new ArrayList<NameValuePair>();

                                params.add(new BasicNameValuePair("sReferencia", ""));
                                params.add(new BasicNameValuePair("sModelo", ""));
                                params.add(new BasicNameValuePair("sMarca", tecnico.getText().toString().trim()));
                                params.add(new BasicNameValuePair("sParametro", "marcaNuevo"));

                                String resultServer7 = getHttpPost(url, params);
                                System.out.println(resultServer7);

                                Toast.makeText(getApplicationContext(), "Haz añadido correctamente!", Toast.LENGTH_SHORT).show();
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
            }else{
                if (tipo.trim().equals("R")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AnadirBorrarVehiculosDetalle.this);
                    builder1.setMessage("Esta seguro que desea añadir?");
                    builder1.setCancelable(true);
                    builder1 = new AlertDialog.Builder(AnadirBorrarVehiculosDetalle.this);
                    builder1.setMessage("Esta seguro que desea añadir?");
                    builder1.setCancelable(true);

                    final LinearLayout layout = new LinearLayout(this);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    layout.setPadding(10,10,10,10);

                    final TextView tmarca = new TextView(this);
                    tmarca.setGravity(Gravity.CENTER);
                    tmarca.setText("Inserte marca");

                    final EditText marca = new EditText(this);
                    marca.setGravity(Gravity.CENTER);
                    marca.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);


                    layout.addView(tmarca);
                    layout.addView(marca);

                    builder1.setView(layout);

                    builder1.setPositiveButton(
                            "SI",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (!marca.getText().toString().trim().equals("")) {
                                        String url = "http://" + ip + "/guardarMovimientoGad.php";

                                        //cod_proceso = "1";
                                        List<NameValuePair> params = new ArrayList<NameValuePair>();

                                        params.add(new BasicNameValuePair("sReferencia", tecnico.getText().toString().trim()));
                                        params.add(new BasicNameValuePair("sModelo", ""));
                                        params.add(new BasicNameValuePair("sMarca", marca.getText().toString().trim()));
                                        params.add(new BasicNameValuePair("sParametro", "referenciaNuevo"));

                                        String resultServer7 = getHttpPost(url, params);
                                        System.out.println(resultServer7);

                                        Toast.makeText(getApplicationContext(), "Haz añadido correctamente!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Por favor digite la marca!", Toast.LENGTH_SHORT).show();

                                    }
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
                }else{
                    if (tipo.trim().equals("Y")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(AnadirBorrarVehiculosDetalle.this);
                        builder1.setMessage("Esta seguro que desea añadir?");
                        builder1.setCancelable(true);

                        builder1 = new AlertDialog.Builder(AnadirBorrarVehiculosDetalle.this);
                        builder1.setMessage("Esta seguro que desea añadir?");
                        builder1.setCancelable(true);

                        final LinearLayout layout = new LinearLayout(this);
                        layout.setOrientation(LinearLayout.VERTICAL);
                        layout.setPadding(10,10,10,10);

                        final TextView tmarca = new TextView(this);
                        tmarca.setGravity(Gravity.CENTER);
                        tmarca.setText("Inserte marca");

                        final EditText marca = new EditText(this);
                        marca.setGravity(Gravity.CENTER);
                        marca.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                        final TextView trefe = new TextView(this);
                        trefe.setGravity(Gravity.CENTER);
                        trefe.setText("Inserte referencia");

                        final EditText refe = new EditText(this);
                        refe.setGravity(Gravity.CENTER);
                        refe.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                        layout.addView(tmarca);
                        layout.addView(marca);
                        layout.addView(trefe);
                        layout.addView(refe);

                        builder1.setView(layout);


                        builder1.setPositiveButton(
                                "SI",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        if ( !marca.getText().toString().trim().equals("") || !refe.getText().toString().trim().equals("")) {
                                            String url = "http://" + ip + "/guardarMovimientoGad.php";

                                            //cod_proceso = "1";
                                            List<NameValuePair> params = new ArrayList<NameValuePair>();

                                            params.add(new BasicNameValuePair("sReferencia", refe.getText().toString().trim()));
                                            params.add(new BasicNameValuePair("sModelo", tecnico.getText().toString().trim()));
                                            params.add(new BasicNameValuePair("sMarca", marca.getText().toString().trim()));
                                            params.add(new BasicNameValuePair("sParametro", "modeloNuevo"));

                                            String resultServer7 = getHttpPost(url, params);
                                            System.out.println(resultServer7);

                                            Toast.makeText(getApplicationContext(), "Haz añadido correctamente!", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Por favor digite la marca y la referencia!", Toast.LENGTH_SHORT).show();

                                        }
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
            }
        }
    }

    public void borrar(View v){
        final EditText tecnico = (EditText) findViewById(R.id.tecnico);
        if (tecnico.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(), "Por favor digite el valor !", Toast.LENGTH_SHORT).show();
        }else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(AnadirBorrarVehiculosDetalle.this);
            builder1.setMessage("Esta seguro que desea borrar ?");
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
                            params.add(new BasicNameValuePair("sValRecurso", Id.trim()));
                            if (tipo.trim().equals("M")){
                                params.add(new BasicNameValuePair("sParametro", "borrarMarca"));
                            }else{
                                if (tipo.trim().equals("R")){
                                    params.add(new BasicNameValuePair("sParametro", "borrarReferencia"));
                                }else{
                                    if (tipo.trim().equals("Y")){
                                        params.add(new BasicNameValuePair("sParametro", "borrarModelo"));
                                    }
                                }
                            }


                            String resultServer7 = getHttpPost(url, params);
                            System.out.println(resultServer7);

                            Toast.makeText(getApplicationContext(), "Haz borrado correctamente!", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent((Context) AnadirBorrarVehiculosDetalle.this, (Class) MostrarRombUbic.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("conexion", conexion);
        bundle2.putString("ip", ip);
        if (tipo.trim().equals("M")){
            bundle2.putString("atributo", "modificarMarca");
        }else{
            if (tipo.trim().equals("R")){
                bundle2.putString("atributo", "modificarReferencia");
            }else{
                if (tipo.trim().equals("Y")){
                    bundle2.putString("atributo", "modificarAno");
                }
            }
        }

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
            String id = data.getStringExtra("id");
            if (requestCode == 1) {
                EditText tecnico = (EditText) findViewById(R.id.tecnico);
                tecnico.setText(valor);

                Id = id;
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
