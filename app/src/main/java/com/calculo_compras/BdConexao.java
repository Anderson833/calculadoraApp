package com.calculo_compras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BdConexao extends SQLiteOpenHelper {

    private  static  final String nomeBd="Conta";
    private  static  final  int version= 19;
    private  String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BdConexao(Context context) {
        super(context, nomeBd, null, version);
       // Toast.makeText(context, "Database criada com sucesso", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table operacao(id integer primary key autoincrement,total double);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
       db.execSQL("drop table operacao;");
       onCreate(db);
    }
}
