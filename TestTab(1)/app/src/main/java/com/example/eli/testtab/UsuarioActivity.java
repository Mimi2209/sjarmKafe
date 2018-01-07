package com.example.eli.testtab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.PasswordAuthentication;
import java.sql.SQLException;

public class UsuarioActivity extends AppCompatActivity {
    Context c;
    EditText email;
    EditText password;
    Button sign_in;
    Button forgot;
    Button new_user;
    Usuario miUser;
    String resultat;
    static final int NEW_USER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        sign_in = (Button) findViewById(R.id.Enter);
        forgot = (Button) findViewById(R.id.forgot);
        new_user = (Button) findViewById(R.id.newU);




        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Descarga nuevaDescarga = new Descarga();
                    nuevaDescarga.execute();

            }
        });

        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Descarga2 nuevaDescarga = new Descarga2();
                nuevaDescarga.execute();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_USER_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String returnValue = data.getStringExtra("user");//no deberiamos pasar el usuario que la crea?
                Intent resultIntent = new Intent();
                resultIntent.putExtra("user", returnValue);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }

        }
    }

        //---------------------------------------------------------------------------
        @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
        public class Descarga extends AsyncTask<String, Integer, String> {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected String doInBackground(String... urls) {

                String emailint = email.getText().toString();


                try {
                    GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                    miUser= baseDatos.verUsuario(emailint); // obtiene cafeteria
                } catch (SQLException |NullPointerException se) {
                    System.out.println("oops! No se puede conectar. Error: " + se.toString());

                } finally {
                    return resultat;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                //      Cafeteria[] cafeterias = misCafeterias.toArray(new Cafeteria[misCafeterias.size()]);
                //      MyAdapter adapter = new MyAdapter(getActivity(), cafeterias,"cafe");
                try {
                    String passwordint = String.valueOf(password.getText());
                    String email_user = miUser.getE_mail();
                    String password_user = miUser.getPwd();
                    if (passwordint == password_user) {

                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("user", email_user);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Password incorrecto. Intentelo de nuevo", Toast.LENGTH_SHORT).show();

                    }
                } catch (NullPointerException se) {
                    System.out.println("oops! No se puede conectar. Error: " + se.toString());
                    Toast.makeText(getApplicationContext(), "Usuario invalido. Cree el usuario o intentelo de nuevo", Toast.LENGTH_LONG).show();
                }

                }
    }

    //---------------------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public class Descarga2 extends AsyncTask<String, Integer, String> {
        String emailint = email.getText().toString();
        String passwordint = password.getText().toString();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... urls) {


            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                miUser= baseDatos.verUsuario(emailint); // obtiene cafeteria
            } catch (SQLException |NullPointerException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());

            } finally {
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //      Cafeteria[] cafeterias = misCafeterias.toArray(new Cafeteria[misCafeterias.size()]);
            //      MyAdapter adapter = new MyAdapter(getActivity(), cafeterias,"cafe");

                    Intent intent = new Intent(getApplicationContext(),
                            NewUserActivity.class);
                    intent.putExtra("email",emailint);
                    intent.putExtra("password",passwordint);
                    startActivityForResult(intent,NEW_USER_REQUEST);
                    getApplicationContext().startActivity(intent);


                //    Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();

                }

        }


    }


