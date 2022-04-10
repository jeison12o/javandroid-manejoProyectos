package com.project.pmi.conexiones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class Conexion extends SQLiteOpenHelper {

    private static final String database = "pmi.db";
    /*Para manipular el registro que retorna la DB*/
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final int version = 1;
    /*Instancia de la base de datos*/
    SQLiteDatabase bd;

    /*Constructor si uno quiere especificar otra DB*/
    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    /*Para usar la base de datos establecida*/
    public Conexion(Context context) {
        super(context, database,factory, version);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
            //db.setForeignKeyConstraintsEnabled(true);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(" +
                "id integer primary key AUTOINCREMENT, " +
                "tipoDocumento text, " +
                "numeroDocumento text NOT NULL UNIQUE, " +
                "nombres text," +
                "apellidos text, " +
                "fechaNacimiento text, " +
                "contrasena text," +
                "correoElectronico text NOT NULL UNIQUE, " +
                "tipoUsuario text )");
        db.execSQL("create table proyecto(" +
                "id integer primary key AUTOINCREMENT, " +
                "nombre text NOT NULL UNIQUE, " +
                "fechaInicio text," +
                "fechaFin text, " +
                "etapa text," +
                "idDirector integer, " +
                "FOREIGN KEY( idDirector ) REFERENCES  usuario(id) ON DELETE CASCADE)");
        db.execSQL("create table integranteProyecto (" +
                "id integer primary key AUTOINCREMENT, " +
                "idProyecto integer, " +
                "idIntegrante integer, " +
                "FOREIGN KEY( idProyecto ) REFERENCES  proyecto(id) ON DELETE CASCADE, " +
                "FOREIGN KEY( idIntegrante ) REFERENCES  usuario(id) ON DELETE CASCADE )");
        db.execSQL("create table encargo(" +
                "id integer primary key AUTOINCREMENT, " +
                "nombre text, " +
                "descripcion text, " +
                "horario text, " +
                "salario text, " +
                "idProyecto integer, " +
                "FOREIGN KEY( idProyecto ) REFERENCES  proyecto(id) ON DELETE CASCADE )");
        db.execSQL("create table actividad(" +
                "id integer primary key AUTOINCREMENT, " +
                "nombre text," +
                "descripcion text, " +
                "fechaInicio text, " +
                "fechaFin text, " +
                "idProyecto integer, " +
                "idIntegranteProyecto integer, " +
                "FOREIGN KEY( idProyecto ) REFERENCES  proyecto(id) ON DELETE CASCADE, " +
                "FOREIGN KEY( idIntegranteProyecto ) REFERENCES  integranteProyecto(id) ON DELETE CASCADE )");
        db.execSQL("create table tarea(" +
                "id integer primary key AUTOINCREMENT, " +
                "nombre text, " +
                "porcentajeDesarrollo text, " +
                "fechaInicio text, " +
                "fechafin text, " +
                "estado text, " +
                "idActividad integer, " +
                "FOREIGN KEY( idActividad ) REFERENCES  actividad(id) ON DELETE CASCADE )");
        db.execSQL("create table recurso(" +
                "id integer primary key AUTOINCREMENT, " +
                "nombre text, " +
                "cantidad integer, " +
                "descripcion text, " +
                "ubicacion text )");
        db.execSQL("create table recurso_tarea(" +
                "id integer primary key AUTOINCREMENT, " +
                "idTarea integer, " +
                "idRecurso integer, " +
                "FOREIGN KEY( idTarea ) REFERENCES  tarea(id) ON DELETE CASCADE, " +
                "FOREIGN KEY( idRecurso ) REFERENCES  recurso(id) ON DELETE CASCADE )");
        db.execSQL("create table reunion(" +
                "id integer primary key AUTOINCREMENT, " +
                "latitud text, " +
                "longitud text, " +
                "mensajeSitio text, " +
                "tematica text, " +
                "idProyecto integer, " +
                "FOREIGN KEY( idProyecto ) REFERENCES  proyecto(id) ON DELETE CASCADE )");
        db.execSQL("create table estado(" +
                "id integer primary key AUTOINCREMENT, " +
                "descripcion text, " +
                "estado text, " +
                "porcentajeAvance text, " +
                "fecha text, " +
                "idProyecto integer, " +
                "FOREIGN KEY( idProyecto ) REFERENCES  proyecto(id) ON DELETE CASCADE )");
        db.execSQL("create table comentario(" +
                "id integer primary key AUTOINCREMENT, " +
                "mensaje text, " +
                "idTarea integer, " +
                "FOREIGN KEY( idTarea ) REFERENCES  tarea(id) ON DELETE CASCADE )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte,int versionNue) {
        db.execSQL("drop table if exists usuario");
        db.execSQL("drop table if exists proyecto");
        db.execSQL("drop table if exists integranteProyecto");
        db.execSQL("drop table if exists encargo");
        db.execSQL("drop table if exists actividad");
        db.execSQL("drop table if exists tarea");
        db.execSQL("drop table if exists recurso");
        db.execSQL("drop table if exists recurso_tarea");
        db.execSQL("drop table if exists reunion");
        db.execSQL("drop table if exists estado");
        db.execSQL("drop table if exists comentario");
        onCreate(db);
    }

    public void cerrarConexion() {
        bd.close();
    }

    public boolean ejecutarInsert(String tabla, ContentValues registro) throws Exception {
        try {
            // Objeto para lectura y escritura en la base de datos
            bd = this.getWritableDatabase();
            /*null es los campos que no se van a registrar, y rertona -1 si hubo error*/
            int  res = (int) bd.insert(tabla, null, registro);
            cerrarConexion();
            if (res != -1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public boolean ejecutarDelete(String tabla, String condicion) {
        bd = this.getWritableDatabase();
        /*Si la clausula del where - Condicion esta con ?, en este otro parametro
        se envian los datos,
        * por ejemplo:
        * db.delete("tablename","id=? and name=?",new String[]{"1","jack"});*/
        int cant = bd.delete(tabla, condicion, null);
        cerrarConexion();

        if (cant >= 1) {
            return true;
        } else {
            return false;
        }
    }


    public boolean ejecutarUpdate(String tabla, String condicion, ContentValues registro) {
        try {

            bd = this.getWritableDatabase();

            int cant = bd.update(tabla, registro, condicion, null);

            cerrarConexion();

            if (cant == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public Cursor ejecutarSearch(String consulta) {
        try {
            // Objeto para lectura y escritura en la base de datos
            bd = this.getWritableDatabase();
            /* Definimos un objeto de tipo cursor que almacena la info de la
             base de datos, ademas ejecutamos una consulta sql
            En el null se especifican los parametros, dado el caso que en
            el SQL no, como con */
            Cursor fila = bd.rawQuery(consulta, null);
            return fila;

        } catch (Exception e) {
            cerrarConexion();
            return null;
        }
    }
}
