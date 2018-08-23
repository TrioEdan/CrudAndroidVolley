package com.example.muhammadadam.crudandroidvolley;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.muhammadadam.crudandroidvolley.Util.AppController;
import com.example.muhammadadam.crudandroidvolley.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertData extends AppCompatActivity {
    EditText npm, nama, prodi, kelas;
    Button btnBatal, btnInput;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        npm = (EditText) findViewById(R.id.inp_npm);
        nama = (EditText) findViewById(R.id.inp_nama);
        prodi = (EditText) findViewById(R.id.inp_prodi);
        kelas = (EditText) findViewById(R.id.inp_kelas);
        btnBatal = (Button) findViewById(R.id.btn_batal);
        btnInput = (Button) findViewById(R.id.btn_simpan);
        pd = new ProgressDialog(InsertData.this);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanData();
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(InsertData.this, MainActivity.class);
                startActivity(main);
            }
        });

    }

    private void simpanData(){
        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertData.this, "pesan :"+ res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity( new Intent(InsertData.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertData.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("npm",npm.getText().toString());
                map.put("nama",nama.getText().toString());
                map.put("prodi",prodi.getText().toString());
                map.put("kelas",kelas.getText().toString());

                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(sendData);
    }
}
