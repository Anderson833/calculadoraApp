package com.calculo_compras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Bd {
    private SQLiteDatabase BD;
    private BdConexao conexao;

    private  String mensagem;

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Bd(Context context) {
        conexao  = new BdConexao(context);
       BD = conexao.getWritableDatabase();


    }

    public long inserir(ModelCalculor md){
        ContentValues valores = new ContentValues();
        valores.put("total",md.getTotal());
      return   BD.insert("operacao",null,valores);
    }

    public void deletaDados(ModelCalculor modelo){
        BD.delete("operacao","id="+modelo.getId(),null);
    }
    public void deletaTodosDados(){
        BD.delete("operacao",null,null);
    }
    public void atualizar(ModelCalculor md){
        BD.isOpen();
        ContentValues valores = new ContentValues();
        valores.put("total",md.getTotal());
        BD.update("operacao",valores,"id=?",new String[]{String.valueOf(md.getId())});
        BD.close();
    }


    public List<ModelCalculor> buscar(){
         BD.isOpen();
        List<ModelCalculor> lista = new ArrayList<>();
        String colunas []= new String []{"id","total"};
        Cursor bb;
      //  bb=BD.rawQuery("select Max(total) as total from operacao",null);
       //  bb=BD.query("operacao",colunas,null,null,null,null,null)
        String x="id";
        BD=this.conexao.getReadableDatabase();
        bb=BD.rawQuery("select * from operacao ",null);
        bb.moveToFirst();
        if(bb.getCount()>0){


            while (bb.moveToNext()){
                ModelCalculor MD = new ModelCalculor();
               // bb.getString(0);
              //  int index= bb.getColumnIndex("");
                 MD.setId(bb.getInt(0));
               MD.setTotal(bb.getDouble(1));
                lista.add(MD);
            }
            BD.close();
        }
        return lista;
    }

    public List<ModelCalculor> buscarId(int id){
        BD.isOpen();
        List<ModelCalculor> lista = new ArrayList<>();
        String colunas []= new String []{"id","total"};
        Cursor bb;
        //  bb=BD.rawQuery("select Max(total) as total from operacao",null);
        //  bb=BD.query("operacao",colunas,null,null,null,null,null)
        String x="id";
        BD=this.conexao.getReadableDatabase();
        bb=BD.rawQuery("select total from operacao where id ="+id,null);
        bb.moveToFirst();
        if(bb.getCount()>0){


            while (bb.moveToNext()){
                ModelCalculor MD = new ModelCalculor();
                // bb.getString(0);
                //  int index= bb.getColumnIndex("");
                MD.setId(bb.getInt(0));
               // MD.setTotal(bb.getDouble(1));
                lista.add(MD);
            }
            BD.close();
        }
        return lista;
    }
}
