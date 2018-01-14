package com.example.eli.testtab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class NewUserActivity extends AppCompatActivity {

    Context c;
    Usuario miUser;
    TextView nombre, password, email;
    EditText newname,newpass, newemail;
    ImageButton newfoto;
    int id_usr;
    String resultat;
    GlobalState gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        gs = (GlobalState) getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombre= (TextView) findViewById(R.id.nom);
        password= (TextView) findViewById(R.id.pw);
        email= (TextView) findViewById(R.id.email);

        newname= (EditText) findViewById(R.id.nomnu);
        newpass= (EditText) findViewById(R.id.pwnu);
        newemail= (EditText) findViewById(R.id.emailnu);

        newfoto = (ImageButton) findViewById(R.id.foto);

        newpass.setText(getIntent().getStringExtra("password"));
        newemail.setText(getIntent().getStringExtra("email"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Descarga nuevaDescarga = new Descarga();
                nuevaDescarga.execute();
            }
        });
    }


    public class Descarga extends AsyncTask<String, Integer, String> {
        String Uname = newname.getText().toString();
        String Upass = newpass.getText().toString();
        String Uemail = newemail.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss");
        String formattedDate = sdf.format(Calendar.getInstance().getTime());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.usericon); // eliminar cuando activemos camara


        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... tipo) {

            try {
                GestionBBDD baseDatos = new GestionBBDD(); // conecta con servidor SQL
                // Falta captura foto
                miUser = new Usuario(Uname,Upass,formattedDate,Uemail,bitmap);
                id_usr=baseDatos.insertUsuario(miUser); // obtiene usuario

            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } finally {

                //         estatus=tNameCafe.getText().toString()+"/"+tAddress.getText().toString()+"/"+ tDescrip.getText().toString()+2+"/"+ cTables.isChecked()+"/"+ cTerrace.isChecked()+"/"+rRating2.getNumStars() ;
                return resultat;
            }
        }

        @Override
        protected void onPostExecute(String result) {

            newname.setText(null);
            newpass.setText(null);
            newemail.setText(null);
            Toast.makeText(getApplicationContext(), " USER WITH ID "+ id_usr+ " ADDED FINE !! ", Toast.LENGTH_SHORT).show();
            gs.setId_usr(id_usr);
            gs.setNom_usr(Uname);
            Intent resultIntent = new Intent();
       //     resultIntent.putExtra("user", Uemail);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }
}
