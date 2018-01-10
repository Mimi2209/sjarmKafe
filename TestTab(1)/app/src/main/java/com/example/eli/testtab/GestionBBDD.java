package com.example.eli.testtab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Eli on 26/12/2017.
 */

public class GestionBBDD {
    private static String Classe = "com.mysql.jdbc.Driver";
    private static String datosConexion = "jdbc:mysql://e80760-mysql.services.easyname.eu/";
    private static String baseDatos = "u125322db1";
    private static String usuario = "u125322db1";
    private static String pass = "Proyecto2018";
    private Connection con;

    public GestionBBDD() {
        try {
            Class.forName(Classe);
            con = DriverManager.getConnection(datosConexion + baseDatos, usuario, pass);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
        }
    }

    //   ----------------------- INSERTAR USUARIO -------------------------------------------------------------
    public int insertUsuario(Usuario miUsuario) throws SQLException {
        int id_usr=0;
        String query = "select LAST_INSERT_ID();";
        Statement stmt = null;
        PreparedStatement st = con.prepareStatement("INSERT INTO `u125322db1`.`usuario` (`nombre_usuario`, `pwd`, `ultima_conexion`, `email`, `foto`) VALUES  (?,?,?,?,?)");
        stmt = con.createStatement();
        st.setString(1, miUsuario.getNombre());
        st.setString(2, miUsuario.getPwd());
        st.setTimestamp(3, (Timestamp) miUsuario.getUltima_conexion());
        st.setString(4, miUsuario.getE_mail());
        Bitmap bitmap = miUsuario.getFoto();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos);
        byte[] image = bos.toByteArray();
        st.setBytes(5, image);
        try {
            st.executeUpdate();  // insert
            ResultSet rs = stmt.executeQuery(query); // get last ID
            rs.next();
            id_usr = rs.getInt(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        } finally {
            st.close();
            return id_usr;
        }
    }

    // ------- Insert Cafeteria  --------------------------------------------------------------------------------
    public boolean insertCafeteria(Cafeteria miCafeteria) throws SQLException {
        PreparedStatement st = con.prepareStatement("INSERT INTO u125322db1.cafeteria (`nombre`, `address`, `descripción`, `tip_cafe`, `longitud`, `latitud`, `mesas`, `terraza`, `wifi`, `comida`, `tienda`, `perros`, `horario`, `servicio_express`, `valoracion` , `img`) VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        st.setString(1, miCafeteria.getNombre_cafeteria());
        st.setString(2, miCafeteria.getAddress());
        st.setString(3, miCafeteria.getDescripcion());
        st.setInt(4, miCafeteria.getTip_cafe());
        st.setFloat(5, miCafeteria.getLongitut());
        st.setFloat(6, miCafeteria.getLatitut());
        st.setBoolean(7, miCafeteria.isMesas());
        st.setBoolean(8, miCafeteria.isTerraza());
        st.setBoolean(9, miCafeteria.isWifi());
        st.setBoolean(10, miCafeteria.isComida());
        st.setBoolean(11, miCafeteria.isTienda());
        st.setBoolean(12, miCafeteria.isPerros());
        st.setString(13, miCafeteria.getHorario());
        st.setBoolean(14, miCafeteria.isServicio_expres());
        st.setInt(15, miCafeteria.getValoracion_global());
        // imatge
        Bitmap bitmap = miCafeteria.getImg();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos);
        byte[] image = bos.toByteArray();
        st.setBytes(16, image);

        try {
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            st.close();
            return true;
        }
    }

    // ------- Recupero info cafeteria ----- Class Cafeteria --------------------------------------------------------------------------------
    public Cafeteria verCafeteria(int id_cafeteria) throws SQLException {
        String query = "SELECT * FROM u125322db1.cafeteria  where id_cafeteria = " + id_cafeteria + ";";
        Statement stmt = null;
        Cafeteria miCafeteria = null;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String nom;
            String address;
            String descripcion;
            int tip_cafe;
            Boolean mesas;
            Boolean terraza;
            Boolean wifi;
            Boolean comida;
            Boolean tienda;
            Boolean perros;
            String horario;
            Boolean servicio_expres;
            Integer valoracion;
            byte[] image = null;
            Bitmap bitmap = null;

            while (rs.next()) {
                nom = rs.getString(2);
                address = rs.getString(3);
                descripcion = rs.getString(4);
                tip_cafe = rs.getInt(5);
                mesas = rs.getBoolean(8);
                terraza = rs.getBoolean(9);
                wifi = rs.getBoolean(10);
                comida = rs.getBoolean(11);
                tienda = rs.getBoolean(12);
                perros = rs.getBoolean(13);
                horario = rs.getString(14);
                servicio_expres = rs.getBoolean(15);
                valoracion = rs.getInt(16);
                image = rs.getBytes(17); // array de bytes
                BitmapFactory.Options options = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, options); //Convert bytearray to bitmap
                miCafeteria = new Cafeteria(id_cafeteria, nom, address, descripcion, tip_cafe, mesas, terraza, wifi, comida, tienda, perros, horario, servicio_expres, valoracion, bitmap);
            }

            rs.close();
            stmt.close();
        } catch (SQLException se) {
            System.out.println("oops! No se puede conectar. Error: " + se.toString());

        } finally {
            return miCafeteria;
        }

    }

    // ------- Recupero lista  cafeterias por distancia (para Listing y para Map)
    public ArrayList<Cafeteria> verListCafeterias(float longi, float ltg) throws SQLException {

        String query = "SELECT * FROM u125322db1.cafeteria ;";
        Statement stmt = null;
        ArrayList<Cafeteria> listCafeterias = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int id_cafeteria;
            String nom;
            String address;
            String descripcion;
            String horario;
            float caf_long;
            float caf_ltg;
            Integer valoracion;
            double distancia=0;
            Bitmap bitmap = null;
            Bitmap bitmap_sortida = null;
            byte[] image = null;
            while (rs.next()) {
                id_cafeteria = rs.getInt(1);
                nom = rs.getString(2);
                address = rs.getString(3);
                descripcion = rs.getString(4);
                caf_long = rs.getFloat(6);
                caf_ltg = rs.getFloat(7);
                horario = rs.getString(14);
                valoracion = rs.getInt(16);
                image = rs.getBytes(17); // array de bytes
                BitmapFactory.Options options = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, options); //Convert bytearray to bitmap
                // Calculo distancia y la guardo para ordenar por distancia

                distancia = distance(longi, ltg, caf_long, caf_ltg);
                // if(distancia<2.5) {
                listCafeterias.add(new Cafeteria(id_cafeteria, nom, address, descripcion, caf_long, caf_ltg, horario, valoracion, distancia, bitmap));
                // }
            }

            rs.close();
            stmt.close();
        } catch (SQLException se) {
            System.out.println("oops! No se puede conectar. Error: " + se.toString());

        } finally {
            return listCafeterias;
        }

    }
    // ------- Recupero lista  cafeterias por distancia (para Listing y para Map)
    public ArrayList<Cafeteria> verListCafeterias(String query) throws SQLException {
        Statement stmt = null;
        ArrayList<Cafeteria> listCafeterias = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int id_cafeteria;
            String nom;
            String address;
            String descripcion;
            String horario;
            float caf_long;
            float caf_ltg;
            Integer valoracion;
            double distancia=0;
            Bitmap bitmap = null;
            Bitmap bitmap_sortida = null;
            byte[] image = null;
            while (rs.next()) {
                id_cafeteria = rs.getInt(1);
                nom = rs.getString(2);
                address = rs.getString(3);
                descripcion = rs.getString(4);
                caf_long = rs.getFloat(6);
                caf_ltg = rs.getFloat(7);
                horario = rs.getString(14);
                valoracion = rs.getInt(16);
                image = rs.getBytes(17); // array de bytes
                BitmapFactory.Options options = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, options); //Convert bytearray to bitmap
                // Calculo distancia y la guardo para ordenar por distancia

       //         distancia = distance(longi, ltg, caf_long, caf_ltg);
                // if(distancia<2.5) {
                listCafeterias.add(new Cafeteria(id_cafeteria, nom, address, descripcion, caf_long, caf_ltg, horario, valoracion, distancia, bitmap));
                // }
            }

            rs.close();
            stmt.close();
        } catch (SQLException se) {
            System.out.println("oops! No se puede conectar. Error: " + se.toString());

        } finally {
            return listCafeterias;
        }

    }
    //   ----------------------- INSERTAR VALORACION -------------------------------------------------------------
    public boolean insertValoracion(Valoracion miValoracion) throws SQLException {

        PreparedStatement st = con.prepareStatement("INSERT INTO `u125322db1`.`valoracion` (`id_val_usuario`, `id_val_cafeteria`, `valoracion_gobal`, `limpieza`, `rapidez_servicio`, `trato`, `ambiente`, `precios`, `disenyo`, `accesibilidad`, `facil_aparcar`, `titulo_comentario`, `texto_comentario`, `date_val`) VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        st.setString(1, miValoracion.getval_usuario());
        st.setInt(2, miValoracion.getId_val_cafeteria());
        st.setInt(3, miValoracion.getValoracion_global());
        st.setInt(4, miValoracion.getLimpieza());
        st.setInt(5, miValoracion.getRapidez_servicio());
        st.setInt(6, miValoracion.getTrato());
        st.setInt(7, miValoracion.getAmbiente());
        st.setInt(8, miValoracion.getPrecios());
        st.setInt(9, miValoracion.getDisenyo());
        st.setInt(10, miValoracion.getAccesibilidad());
        st.setInt(11, miValoracion.getFacil_aparcar());
        st.setString(12, miValoracion.getCom_titulo());
        st.setString(13, miValoracion.getCom_text());
        st.setDate(14, (java.sql.Date) miValoracion.getData());


        try {
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            st.close();
            return true;
        }
    }

    // ------- Recupero valoracion cafeteria --(ArrayList) -----------------------------------------------------------------------------------
    public Valoracion verValoracion(int id_cafeteria) throws SQLException {
        // Vis libro

        String query = "SELECT * FROM u125322db1.valoracion  where id_val_cafeteria = " + id_cafeteria + ";";
        Statement stmt = null;
        ArrayList<Valoracion> valoraciones = new ArrayList<>();
        Valoracion valParcial;
        Valoracion valoracion_cafeteria=null;

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int val_global;
            int limpieza;
            int rapidez;
            int trato;
            int ambiente;
            int precios;
            int disenyo;
            int acceso;
            int aparcar;
            while (rs.next()) {
                val_global = rs.getInt(4);
                limpieza = rs.getInt(5);
                rapidez = rs.getInt(6);
                trato = rs.getInt(7);
                ambiente = rs.getInt(8);
                precios = rs.getInt(9);
                disenyo = rs.getInt(10);
                acceso = rs.getInt(11);
                aparcar = rs.getInt(12);
                valoraciones.add(new Valoracion(val_global, limpieza, rapidez, trato, ambiente, precios, disenyo, acceso, aparcar));
            }
            val_global = 0;
            limpieza = 0;
            rapidez = 0;
            trato = 0;
            ambiente = 0;
            precios = 0;
            disenyo = 0;
            acceso = 0;
            aparcar = 0;
            int numVal = valoraciones.size();
            for (int i = 0; i < valoraciones.size(); i++) { // calculo promedios
                valParcial = valoraciones.get(i);
                limpieza += valParcial.getLimpieza();
                rapidez += valParcial.getRapidez_servicio();
                trato += valParcial.getTrato();
                ambiente += valParcial.getAmbiente();
                precios += valParcial.getPrecios();
                disenyo += valParcial.getDisenyo();
                acceso += valParcial.getAccesibilidad();
                aparcar += valParcial.getFacil_aparcar();
            }

            valoracion_cafeteria = new Valoracion(val_global, limpieza / numVal, rapidez / numVal, trato / numVal, ambiente / numVal, precios / numVal, disenyo / numVal, acceso / numVal, aparcar / numVal);
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            System.out.println("oops! No se puede conectar. Error: " + se.toString());

        } finally {
            return valoracion_cafeteria;
        }

    }

    // ------- Recupero comentarios cafeteria -(ArrayList) ------------------------------------------------------------------------------------
    public ArrayList<Valoracion> verComentarios(int id_cafeteria) throws SQLException {
        // Vis libro

        String query = "SELECT * FROM u125322db1.valoracion left join u125322db1.usuario on id_val_usuario=id_usuario where id_val_cafeteria  = " + id_cafeteria + ";";
        Statement stmt = null;
        ArrayList<Valoracion> comentarios = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String com_usuario=null;
            int com_val;
            String com_titulo;
            String com_text;
            Date com_fecha;
            Bitmap bitmap;
            byte[] image=null;

            while (rs.next()) {
                com_val = rs.getInt(4);
                com_titulo = rs.getString(13);
                com_text = rs.getString(14);
                com_fecha = rs.getDate(15);
                com_usuario = rs.getString(17);
                image = rs.getBytes(21); // array de bytes
                BitmapFactory.Options options = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, options); //Convert bytearray to bitmap

                comentarios.add(new Valoracion(com_val,com_titulo, com_text, com_fecha,com_usuario,bitmap));
            }
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            System.out.println("oops! No se puede conectar. Error: " + se.toString());

        } finally {
            return comentarios;
        }

    }
    //   ----------------------- INSERTAR EVENTO -------------------------------------------------------------

    public boolean insertEvento(Evento miEvento) throws SQLException {

        PreparedStatement st = con.prepareStatement("INSERT INTO `u125322db1`.`evento` (`event_id_cafeteria`, `event_id_usr`, `event_name`, `event_descripcion`, `event_location`, `event_inicio`, `event_fin`) VALUES  (?,?,?,?,?,?,?)");
        st.setInt(1, miEvento.getId_evento_cafeteria());
        st.setInt(2, miEvento.getId_evento_usuario());
        st.setString(3, miEvento.getEvent_name());
        st.setString(4, miEvento.getEvent_descrip());
        st.setString(5, miEvento.getEvent_location());
        st.setTimestamp(6, miEvento.getEvent_inicio());
        st.setTimestamp(7, miEvento.getEvent_fin());

        try {
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            st.close();
            return true;
        }
    }

    // ------- Recupero Eventos cafeteria -(ArrayList) ------------------------------------------------------------------------------------
    public ArrayList<Evento> verEventos(int id_cafeteria) throws SQLException {

        String query = "SELECT * FROM u125322db1.evento  where event_id_cafeteria = " + id_cafeteria + ";";
        Statement stmt = null;
        ArrayList<Evento> eventos = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int evento_usuario;
            String evento_titulo;
            String evento_descripcion;
            String evento_locaclizacion;
            Timestamp com_fecha_ini;
            Timestamp com_fecha_fin;

            while (rs.next()) {
                evento_usuario = rs.getInt(3);
                evento_titulo = rs.getString(4);
                evento_descripcion = rs.getString(5);
                evento_locaclizacion = rs.getString(6);
                com_fecha_ini = rs.getTimestamp(7);
                com_fecha_fin = rs.getTimestamp(8);

                eventos.add(new Evento(evento_usuario, evento_titulo, evento_descripcion, evento_locaclizacion, com_fecha_ini, com_fecha_fin));
            }
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            System.out.println("oops! No se puede conectar. Error: " + se.toString());

        } finally {
            return eventos;
        }

    }

    // ------- Recupero info cafeteria ----- Class Cafeteria --------------------------------------------------------------------------------
    public ArrayList<Tipo_cafe> verTipCafe() throws SQLException {
        String query = "SELECT * FROM u125322db1.tipo_Cafe;";
        Statement stmt = null;
        ArrayList<Tipo_cafe> miTipCafe = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int id;
            String nom;
            String caract;

            while (rs.next()) {
                id = rs.getInt(1);
                nom = rs.getString(2);
                caract = rs.getString(3);

                miTipCafe.add(new Tipo_cafe(id, nom, caract));
            }

            rs.close();
            stmt.close();
        } catch (SQLException se) {
            System.out.println("oops! No se puede conectar. Error: " + se.toString());

        } finally {
            return miTipCafe;
        }

    }

    // ------- Recupero info usuario ----- Class Usuario - comprueba usr + pwd para sign-in ---------------------------------------------
    public Usuario verUsuario(String email, String pwd) throws SQLException {
        String query = "SELECT * FROM u125322db1.usuario  where email = '" + email + "' and pwd ='" + pwd + "';";
        Statement stmt = null;
        Usuario miUsuario = null;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int id_usr;
            String nom;
            String password;
            Timestamp uconexion;
            String uemail;
            byte[] image = null;
            Bitmap bitmap = null;

            while (rs.next()) {
                id_usr = rs.getInt(1);
                nom = rs.getString(2);
                password = rs.getString(3);
                uconexion = rs.getTimestamp(4);
                uemail = rs.getString(5);
                image = rs.getBytes(6); // array de bytes
                BitmapFactory.Options options = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, options); //Convert bytearray to bitmap
                miUsuario=new Usuario(id_usr,nom,password,uconexion,uemail,bitmap);}

            rs.close();
            stmt.close();
        } catch (SQLException se) {
            System.out.println("oops! No se puede conectar. Error: " + se.toString());

        } finally {
            return miUsuario;
        }

    }
    // ------- Recupero info usuario ----- Class Usuario - comprueba email  para enviar email --------------------------------------------
    public Usuario verUsuario(String email) throws SQLException {
        String query = "SELECT * FROM u125322db1.usuario  where email = '" + email + "';";
        Statement stmt = null;
        Usuario miUsuario = null;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String nom;
            String password;
            Timestamp uconexion;
            String uemail;
            byte[] image = null;
            Bitmap bitmap = null;

            while (rs.next()) {
                nom = rs.getString(2);
                password = rs.getString(3);
                uconexion = rs.getTimestamp(4);
                uemail = rs.getString(5);
                image = rs.getBytes(6); // array de bytes
                BitmapFactory.Options options = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, options); //Convert bytearray to bitmap
                miUsuario=new Usuario(nom,password,uconexion,uemail,bitmap);}

            rs.close();
            stmt.close();
        } catch (SQLException se) {
            System.out.println("oops! No se puede conectar. Error: " + se.toString());

        } finally {
            return miUsuario;
        }

    }

    // ------- Recupero info usuario ----- Class Usuario --------------------------------------------------------------------------------
    public Usuario verUsuario(int id_usuario) throws SQLException {
        String query = "SELECT * FROM u125322db1.usuario  where id_usuario = " + id_usuario + ";";
        Statement stmt = null;
        Usuario miUsuario = null;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String nom;
            String password;
            Timestamp uconexion;
            String uemail;
            byte[] image = null;
            Bitmap bitmap = null;

            while (rs.next()) {
                nom = rs.getString(2);
                password = rs.getString(3);
                uconexion = rs.getTimestamp(4);
                uemail = rs.getString(5);
                image = rs.getBytes(6); // array de bytes
                BitmapFactory.Options options = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, options); //Convert bytearray to bitmap
                miUsuario=new Usuario(nom,password,uconexion,uemail,bitmap);}

            rs.close();
            stmt.close();
        } catch (SQLException se) {
            System.out.println("oops! No se puede conectar. Error: " + se.toString());

        } finally {
            return miUsuario;
        }

    }
    //-----------------------------------------------------------------------------------------------------------------
//---------- Calculo distancia entre 2 cafeteries     ----------------------------------------------------------------
    private double distance(float lat1, float lon1, float lat2, float lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344; // KM
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


}