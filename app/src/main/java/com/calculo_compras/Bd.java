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
    public Bd(Context context) {
        conexao  = new BdConexao(context);
       BD = conexao.getWritableDatabase();
    }
    /**
     * Método para inserir os dados no banco de dados
     * @param cal
     * @return
     */
    public long inserir(ModelCalculor cal){
        ContentValues valores = new ContentValues();
        valores.put("total",cal.getTotal());
      return   BD.insert("operacao",null,valores);
    }

    /**
     * Método para deleta os dados no banco de dados
     * @param modelo
     */
    public void deletaDados(ModelCalculor modelo){
        BD.delete("operacao","id="+modelo.getId(),null);
    }

    /**
     * Método para deletar todos os dados no banco de dados
     */
    public void deletaTodosDados(){
        BD.delete("operacao",null,null);
    }

    /**
     * Método para atualizar os dados no banco de dados
     * @param md
     */
    public void atualizar(ModelCalculor md){
       // BD.isOpen();
        ContentValues valores = new ContentValues();
        valores.put("total",md.getTotal());
        BD.update("operacao",valores,"id=?",new String[]{String.valueOf(md.getId())});
      //  BD.close();
    }

    /**
     * Método para pagar o valor total no banco de dados
     * @param codigo o id
     * @return valor total
     */
    public double setaId(int codigo){
        double valor=0;
        Cursor bb;
        bb=BD.rawQuery("select total from operacao where id="+codigo,null);
        if(bb.getCount()>0){
            while (bb.moveToNext()){
                ModelCalculor MD = new ModelCalculor();
              valor=bb.getDouble(0);
            }
        }
        return valor;
    }

    /**
     * Método para buscar por um id
     * @return um id
     */
    public int buscarPorId(){
        int codigoId=0;
        Cursor bb;
        bb=BD.rawQuery("select id from operacao",null);
        if(bb.getCount()>0){
            while (bb.moveToNext()){
                ModelCalculor MD = new ModelCalculor();
                codigoId=bb.getInt(0);
                //codigoId=true;
            }
        }
        return codigoId;
    }

    /**
     * Método para lista todos os dados
     * @return
     */
    public List<ModelCalculor> buscar(){
        BD.isOpen();
        List<ModelCalculor> lista = new ArrayList<>();
        String colunas []= new String []{"id","total"};
        Cursor bb;
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
}
