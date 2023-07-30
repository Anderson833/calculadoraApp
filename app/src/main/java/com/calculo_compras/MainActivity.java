package com.calculo_compras;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RadioGroup btGrupoo;
    private EditText PRECO,QTD;
    private Button calc;
    private TextView total;
    private Bd  dao;
    private SQLiteDatabase BD;
    private List<ModelCalculor> listaValores;
    private double soma=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btGrupoo = findViewById(R.id.btGrupo);
        //   calc = (Button) findViewById(R.id.btCalc);
        total = findViewById(R.id.Totaldamultiplicacao);
        PRECO = findViewById(R.id.Preco);
        QTD = findViewById(R.id.Qtd);
        QTD.setText("1");

        dao = new Bd(this);
       /* calc.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {

                int itemRadioGrup = btGrupoo.getCheckedRadioButtonId();
                double qtd=Double.parseDouble(Qtd);
                double prc=Double.parseDouble(preco);
                double resultado=0, somageral=0;

                if(itemRadioGrup != -1){
                    RadioButton rbtoperacao = findViewById(itemRadioGrup);
                    String operacao= rbtoperacao.getText().toString();

                    if(operacao.equals(" +")){
                        resultado=prc*qtd;
                        somageral=somageral+resultado;
                        total.setText("Total: "+somageral);
                       adicionar();
                    } else if (operacao.equals(" -")) {
                        resultado=prc-qtd;
                        total.setText("Total: "+resultado);
                        adicionar();
                      //  deletaTodosDados();
                    }else{
                        Toast.makeText(MainActivity.this, ""+operacao, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Operação não escolhida ", Toast.LENGTH_SHORT).show();
                }
            }

        });*/
    }
    public void adicionar(View view){
        int itemRadioGrup = btGrupoo.getCheckedRadioButtonId();
        if (verificaCampos()){
        }else {
            double qtd = Double.parseDouble(QTD.getText().toString());
            double prc = Double.parseDouble(PRECO.getText().toString());
            double resultado = 0, somageral = 0;
            if (itemRadioGrup != -1) {
                RadioButton rbtoperacao = findViewById(itemRadioGrup);
                String operacao = rbtoperacao.getText().toString();
                if (operacao.equals(" +")) {
                    resultado = prc * qtd;
                    soma = soma + resultado;
                    //  String QtdIndicada=QTD.getText().toString();
                    total.setText("Total: " + soma);
                    if (prc==0&&qtd==0){
                        soma=0;
                        total.setText("Total: " + soma);
                    }
                    //    QTD.setText(""+soma);
                    //    alteraValor();
                    PRECO.requestFocus();
                    //  Toast.makeText(this, "total"+soma, Toast.LENGTH_SHORT).show();
                    //  salvar(soma);//
            /*   for(ModelCalculor m:dao.buscar()){
                    if(m.getId()==52){
                        Toast.makeText(this, "Valor anterior "+m.getTotal(), Toast.LENGTH_SHORT).show();
                        total.setText("Total: "+m.getTotal());
                    }

               }*/
                } else if (operacao.equals(" -")) {
                    resultado = prc * qtd;
                    soma = soma - resultado;
                    total.setText("Total: " + soma);
                    if (prc==0&&qtd==0){
                        soma=0;
                        total.setText("Total: " + soma);
                    }
                    //  alteraValor();
                    // deletaTotal();
                    //deletaTodosTotais();
                    //  salvar();
                }
            } else {
                Toast.makeText(MainActivity.this, "Operação não escolhida ", Toast.LENGTH_SHORT).show();
               /* Intent list = new Intent(MainActivity.this, ListaDadosActivity.class);
                startActivity(list);*/
            }

        }
    }

    public  boolean verificaCampos(){
        boolean campos=false;

        if (QTD.getText().toString().equals("")&&PRECO.getText().toString().equals("")){
            campos=true;
            Toast.makeText(this, "Por favor, preencha os campo.", Toast.LENGTH_SHORT).show();

        } else if (QTD.getText().toString().equals("")) {
            campos=true;
            Toast.makeText(this, "Por favor, preencha o campo de quantidade.", Toast.LENGTH_SHORT).show();
            QTD.requestFocus();
        } else if (PRECO.getText().toString().equals("")) {
            campos=true;
            Toast.makeText(this, "Por favor, preencha o campo do Preço.", Toast.LENGTH_SHORT).show();
            PRECO.requestFocus();
        }else{
            campos=false;
        }
        return campos;
    }

    public void setaTotal(){
        for(ModelCalculor m:dao.buscar()){
            if(m.getId()==52){
                Toast.makeText(this, "Valor anterior ", Toast.LENGTH_SHORT).show();
                total.setText("Total: "+m.getTotal());
            }

        }
    }


    public  void salvar(double valor){
        ModelCalculor m = new ModelCalculor();
        m.setTotal(valor);
        long id = dao.inserir(m);
        Toast.makeText(this, "Id salvo "+id, Toast.LENGTH_SHORT).show();
    }
    public void deletaTotal(){
        ModelCalculor md = new ModelCalculor();
        md.setId(45);
        dao.deletaDados(md);
        Toast.makeText(this, "Dado deletado com sucesso", Toast.LENGTH_SHORT).show();
    }
    public void deletaTodosTotais(){

        dao.deletaTodosDados();
        Toast.makeText(this, "Todos dados deletado com sucesso", Toast.LENGTH_SHORT).show();
    }
    public void alteraValor(){
        ModelCalculor m = new ModelCalculor();
        m.setId(52);
        m.setTotal(Double.parseDouble(QTD .getText().toString()));
        // m.setTotal();
        dao.atualizar(m);
        Toast.makeText(this, "Id 51 ATUALIZADO ", Toast.LENGTH_SHORT).show();
    }


    /* public void criaDatabase(){
        BD=openOrCreateDatabase("bd_operacao",MODE_PRIVATE,null);
         Toast.makeText(this, "Criado a base de dados", Toast.LENGTH_SHORT).show();
     }
    public void criarTabela(){
        BD.execSQL("CREATE TABLE IF NOT EXISTS conta(id Integer primary key autoincrement,total double )");
        Toast.makeText(this, "TABELA CRIADA", Toast.LENGTH_SHORT).show();

    }

    public void inserir (){
        double x=34;
        BD.execSQL("insert into conta(total)values(2)");
        Toast.makeText(this, "Valor adicionado", Toast.LENGTH_SHORT).show();
    }
       public void deleta(){
        BD.execSQL("DELETE  FROM conta where total=2");
           Toast.makeText(this, "Dados deletados", Toast.LENGTH_SHORT).show();
       }

    public void lista(){
        Cursor bb;
        String slq="select * from conta";
        bb = BD.rawQuery(slq,null);
        bb.moveToFirst();
        if (bb != null){

             String x=""+  bb.getDouble(1);
                Toast.makeText(this, "Valor da lista" +x, Toast.LENGTH_SHORT).show();

        }

    }
   /*  public void setaUltimoId(){
         Cursor bb;
         String slq="select max(id) from conta";
         bb = BD.rawQuery(slq,null);
         bb.moveToFirst();
         if (bb != null){

             String x=""+ bb.getString(0);
             Toast.makeText(this, "ultimo id" +x, Toast.LENGTH_SHORT).show();

         }
     }*/
    /* public void deletaTodosDados(){
        Cursor b;
        String sql="DELETE FROM Conta";
        b =BD.rawQuery(sql,null);
        if(b!= null){
            Toast.makeText(this, "Todos dados removidos", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Não foram removidos", Toast.LENGTH_SHORT).show();
        }
     }
*/
}
