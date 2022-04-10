package com.example.gamezone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
}

    public void onButLoginClicked (View view){
        //Tworzenie ciągu zapytania;
        String url = "http://10.0.2.2:5000/users/" + username.getText().toString() + "&" + password.getText().toString();
//        String url = "http://127.0.0.1:5000/users/" + username.getText().toString() + "&" + password.getText().toString();
        Log.i("mes", url);
        System.out.println(url);

        //Wysłanie zapytanie i oczekiwanie na odpowiedź w OnResponse i OnErrorResponse
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, this::onResponse, this::onErrorResponse);
        queue.add(stringRequest);


        
    }

    private void onResponse(String s) {
        String integer = s.toString();
        System.out.println(integer);
        integer = integer.replaceAll("\"","" );
        System.out.println("tu");
        if (Integer.parseInt(integer.toString().trim()) != 0){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else Toast.makeText(this, "Incorrect login or password", Toast.LENGTH_LONG).show();
    }
    private void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Incorrect login or password", Toast.LENGTH_LONG).show();
    }

    public void finishLoginActivity (View view){ finish(); }


}
