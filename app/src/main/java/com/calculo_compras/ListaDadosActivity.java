package com.calculo_compras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaDadosActivity extends AppCompatActivity {

    private ListView  listView;
    private  Bd banco;
    private List<ModelCalculor> dados;
    private  List<ModelCalculor> listaFiltro = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dados);

        listView = findViewById(R.id.listadados);
        banco = new Bd(this);
        dados =banco.buscar();
        listaFiltro.addAll(dados);
        ArrayAdapter<ModelCalculor> adaptador = new ArrayAdapter<ModelCalculor>(this, android.R.layout.simple_list_item_1,dados);
        listView.setAdapter(adaptador);
    }
}