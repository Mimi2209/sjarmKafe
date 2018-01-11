package com.example.eli.testtab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    String emailint;
    String pwd;
    static final int NEW_USER_REQUEST = 1;
    GlobalState gs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        gs = (GlobalState) getApplication();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        sign_in = (Button) findViewById(R.id.Enter);
        forgot = (Button) findViewById(R.id.forgot);
        new_user = (Button) findViewById(R.id.newU);

        // envia e-mail

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailint = email.getText().toString();
                Descarga_check_email nuevaDescarga = new Descarga_check_email();
                nuevaDescarga.execute();

            }
        });

// comprueba si existe usr + pwd y obtiene id usuario
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailint = email.getText().toString();
                pwd= password.getText().toString();
                    Descarga_check_usr nuevaDescarga = new Descarga_check_usr();
                    nuevaDescarga.execute();

            }
        });

        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailint = email.getText().toString();
                pwd= password.getText().toString();
                Descarga_new_user nuevaDescarga = new Descarga_new_user();
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
         //       resultIntent.putExtra("user", returnValue);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }

        }
    }

        //--------------- comprueba que existe usr +  pwd ------------------------------------------------------------
        @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
        public class Descarga_check_usr extends AsyncTask<String, Integer, String> {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected String doInBackground(String... urls) {
                try {
                    GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                    miUser= baseDatos.verUsuario(emailint, pwd); // obtiene cafeteria
                } catch (SQLException |NullPointerException se) {
                    System.out.println("oops! No se puede conectar. Error: " + se.toString());

                } finally {
                    return resultat;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                try {

                    if (miUser!=null) {

                        Intent resultIntent = new Intent();
// grabo el la variable global el id de usuario
                        gs.setId_usr(miUser.getId());
                        gs.setNom_usr(miUser.getNombre());
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

    //--------------- NEW USER    ---------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public class Descarga_new_user extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... urls) {


            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                miUser= baseDatos.verUsuario(emailint,pwd); // obtiene cafeteria
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
                    if (miUser == null) {
                        Intent intent = new Intent(getApplicationContext(),
                                NewUserActivity.class);
         //               intent.putExtra("email", emailint);
         //               intent.putExtra("password", passwordint);
// falta que devuelva algo?
                        startActivityForResult(intent, NEW_USER_REQUEST);
                    }else {
                        Toast.makeText(getApplicationContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                    }
                }

        }
    //---------------------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public class Descarga_check_email extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... urls) {




            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                miUser= baseDatos.verUsuario(emailint); // usuario
            } catch (SQLException |NullPointerException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());

            } finally {
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (miUser !=null) {
                    String password_user = miUser.getPwd();
                    sendEmail(emailint, password_user);
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario no existe . Cree el usuario o intentelo de nuevo", Toast.LENGTH_LONG).show();
                }
            } catch (NullPointerException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
                Toast.makeText(getApplicationContext(), "Usuario invalido. Cree el usuario o intentelo de nuevo", Toast.LENGTH_LONG).show();
            }

        }
    }
    protected void sendEmail(String email, String password) {
        String[] TO = {email}; //aquí pon tu correo
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"+miUser.getE_mail()));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
// Esto podrás modificarlo si quieres, el asunto y el cuerpo del mensaje
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "your request for Password SjarmKafe");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear user," +
                "Your password for this app is : " +password);

            startActivity(emailIntent);
            finish();
            Toast.makeText(getApplicationContext(),
                    "Email enviado con contraseña", Toast.LENGTH_SHORT).show();
    }

    }


