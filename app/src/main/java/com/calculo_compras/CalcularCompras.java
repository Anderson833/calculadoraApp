package com.calculo_compras;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Essa classe  tem o objetivo de fazer os calculos das compras em varejo
 */
public class CalcularCompras extends AppCompatActivity {

     private boolean opcaoTotal =false, operacao =false;
     // Variáveis feitas na linha de baixo é para armazenar os números digitados e os simbolos das operações
     private  char sinal,digito;
     // Variáveis contruidas na linha debaixo serve para armazenar os totais dos calculos
     private double todoTotal=0, total_de_todas_compras;
     private int cont=0, operacoes=0;
     // Variável para fazer as manipulações do banco de dados
      private Bd BD;
     private  String dados="", valorFixo="",valorAdicionado="";
     // variáveis do tipo TextView para pegar os dados que foi digitados no campo
    private TextView espresso, espressaResultado,digitoClimpar,DIGITO_OFF,DIGITO_OP,CEtUDO, todo_Total, totalCompras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_compras);
        getSupportActionBar().hide();

        espresso = findViewById(R.id.expressao);
        espressaResultado = findViewById(R.id.ExibirespressaoRecente);
        todo_Total =findViewById(R.id.Exibirexpressaodototal);
        CEtUDO = findViewById(R.id.CE);
        digitoClimpar = findViewById(R.id.C_LIMPAR);
         DIGITO_OFF= findViewById(R.id.opcao);
         totalCompras = findViewById(R.id.TotalDasCompres);
        BD = new Bd(this);
        /**
         * Método para realizar as ações de armazenamento de caracteres a cada digito informado
         */
        espresso.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Antes de ser alterado
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // sendo escrito
                //  Laço de repetição para limita a quantidade de simbolos das operações aritimetrica
                String caracter=espresso.getText().toString();
                for (int i=0;i<caracter.length();i++){
                    // Variável cahr para armazenar todos caracters do campos que receber os números
                    char digitos=caracter.charAt(i);
                    if(digitos=='+' || digitos=='-'|| digitos=='/'||digitos=='*'){
                        // armazenar a quantidade de simbolos da operação de for adicionada
                       operacoes=operacoes+1;
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
               // depois de escrirto
                dados=""+s;
                //Método para evitar error ao executar uma operação apenas com um número e simbolo da operação
               setaOperacaoCorreta();
               // Variável para armazenar 7 caracter e impedir que acima dos 7 caracteres não executer o sistema com
                // um simbola da operação no final
                String ultimoindex=espresso.getText().toString();
                //condição caso a já um caracter de alguma operação
                if(operacoes==1){
                     // Condições para impedir de ser executado caso  tenha um simbolo da operação no final depois
                    //  dos setes caracters
                    if((ultimoindex.length()>7 && ultimoindex.endsWith("+"))){
                        espresso.setText(ultimoindex.substring(0,ultimoindex.length()-1));
                    } else if (ultimoindex.length()>7 && ultimoindex.endsWith("-")) {
                        espresso.setText(ultimoindex.substring(0,ultimoindex.length()-1));
                    } else if (ultimoindex.length()>7 && ultimoindex.endsWith("*")) {
                        espresso.setText(ultimoindex.substring(0,ultimoindex.length()-1));
                    } else if (ultimoindex.length()>7 && ultimoindex.endsWith("/")) {
                        espresso.setText(ultimoindex.substring(0,ultimoindex.length()-1));
                    }
                }
                //Condição para saber se contém dois simbolos da operaçõo
                if(operacoes==2){
                   // Toast.makeText(CalcularCompras.this, ""+operacoes+"vezes", Toast.LENGTH_SHORT).show();
                    String index=espresso.getText().toString();
                    espresso.setText(index.substring(0,index.length()-1));
                }
                // Zerando a variável
                operacoes=0;
            }
        });

     // método para exibir o total do banco de dados
    setaTotal();
    }


    /**
     * Método para seta o número 0 ao campo de texto
     * @param view
     */
    public void setaNumero0(View view){
        digito='0';
        espresso.setText(espresso.getText()+"0");
    }
    /**
     * Método para seta o número 1 ao campo de texto
     * @param view
     */
    public void setaNumero1(View view){
        digito='1';
        espresso.setText(espresso.getText()+"1");
    }
    /**
     * Método para seta o número 2 ao campo de texto
     * @param view
     */
    public void setaNumero2(View view){
        espresso.setText(espresso.getText()+"2");
        digito='2';
    }
    /**
     * Método para seta o número 3 ao campo de texto
     * @param view
     */
    public void setaNumero3(View view){
        digito='3';
        espresso.setText(espresso.getText()+"3");
    }
    /**
     * Método para seta o número 4 ao campo de texto
     * @param view
     */
    public void setaNumero4(View view){
        digito='4';
        espresso.setText(espresso.getText()+"4");
    }
    /**
     * Método para seta o número 5 ao campo de texto
     * @param view
     */
    public void setaNumero5(View view){
        digito='5';
        espresso.setText(espresso.getText()+"5");
    }
    /**
     * Método para seta o número 6 ao campo de texto
     * @param view
     */
    public void setaNumero6(View view){
        digito='6';
        espresso.setText(espresso.getText()+"6");
    }
    /**
     * Método para seta o número 7 ao campo de texto
     * @param view
     */
    public void setaNumero7(View view){
        digito='7';
        espresso.setText(espresso.getText()+"7");
    }
    /**
     * Método para seta o número 8 ao campo de texto
     * @param view
     */
    public void setaNumero8(View view){
        digito='8';
        espresso.setText(espresso.getText()+"8");
    }
    /**
     * Método para seta o número 9 ao campo de texto
     * @param view
     */
    public void setaNumero9(View view){
        digito='9';
        espresso.setText(espresso.getText()+"9");
    }

    /**
     * Método para executar uma opção de calculos
     * @param view
     */
    public void opcao(View view){
        cont++;
        if(cont==1){
         DIGITO_OFF.setText("ON");
         totalCompras.setText("R$ 0,00");
        }else {
            cont=0;
            DIGITO_OFF.setText("OFF");
            setaTotal();
            deletaValores(view);
        }

    }
    //  classe DecimaFormat para colocar os valores em casas decimais
    DecimalFormat decimalFormat  = new DecimalFormat("#,##0.00");

    /**
     * Método para seta o sinal de ponto ao campo da operação
     * @param view
     */
    public void setaPonto(View view){
        sinal='.';
        String expressao=espresso.getText().toString();
        if(expressao.isEmpty()){
            espresso.setText(espresso.getText()+"0.");
        }else{
            espresso.setText(espresso.getText()+".");
        }
    }



    /**
     *  Método para executar o calculor ao clicar no botão igual
     * @param view
     */
    public void setaIgual(View view){

         if(DIGITO_OFF.getText().toString().equals("ON")){
             opcaoOn();
         }else {
             String dados = espresso.getText().toString();
             if (dados.isEmpty()) {
             } else {
                 // Condições para realizar as operações
                 if (dados.contains("+") && operacao == false) {
                     retornaTodototal();
                 }
                 if (dados.contains("-") && operacao == false) {
                     retornaTodototal();
                 }
                 if (dados.contains("*") && operacao == false) {
                     retornaTodototal();
                 }
                 if (dados.contains("/") && operacao == false) {
                     retornaTodototal();
                 }
             }
         }
    }

    /**
     * Método  para calcular as operações comforme os valores e um sinal da operação passada nos parametros
     * @param
     * @param
     * @param
     * @return Todor total calculado pelo sistema
     */
    public  double retornaTodototal(){
        String global=espresso.getText().toString();
        if(global.contains("+")){
            String index=espresso.getText().toString();
            String dados[]=index.replace("+"," ").split(" ");
            String ix0=dados[0];
            String ix1=dados[1];

            double valorP1 =Double.parseDouble(ix0);
            double valorP2 =Double.parseDouble(ix1);
            double total=valorP1+valorP2;
          //  String ttl=df.format(total);
            espressaResultado.setText("   "+espresso.getText().toString());
            todoTotal=total;

            if(opcaoTotal){
                total_de_todas_compras=todoTotal;
                String valorTotal=decimalFormat.format(total_de_todas_compras);
                totalCompras.setText("R$ "+valorTotal);
                //Método para alterar o valor
                alteraValor(total_de_todas_compras);
                String totalRc=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+totalRc);
                espresso.setText("");
                opcaoTotal=false;
            }else if(opcaoTotal==false){
                double td=BD.buscarTotalPeloId();
                double tdTotal=td+todoTotal;
                String Tdvalor=decimalFormat.format(tdTotal);
                //Método para alterar o valor
                alteraValor(tdTotal);
                totalCompras.setText("R$ "+Tdvalor);
                String totalRc=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+totalRc);
                espresso.setText("");
            }
              // opcaoTotal=false;
        } else if (global.contains("-")) {
            String index=espresso.getText().toString();
            String dados[]=index.split("-");
            String ix0=dados[0];
            String ix1=dados[1];
            double valorP1 =Double.parseDouble(ix0);
            double valorP2 =Double.parseDouble(ix1);
            double total=valorP1-valorP2;
            if(valorP1<valorP2){
                String valor=String.valueOf(total);
                String tiraNegativo=valor.replace("-"," ");
                double vl=Double.parseDouble(tiraNegativo);
                todoTotal=vl;
            }else{
                todoTotal=total;
            }
            espressaResultado.setText("   "+espresso.getText().toString());

            if(opcaoTotal){
                total_de_todas_compras=todoTotal;
                //Método para alterar o valor
                 alteraValor(total_de_todas_compras);
                String opTotalCompras=decimalFormat.format(total_de_todas_compras);
                totalCompras.setText("R$ "+opTotalCompras);
                String opTotalRc=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+opTotalRc);
                espresso.setText("");
                opcaoTotal=false;
            }else if(opcaoTotal==false){
                String opTotalRc=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+opTotalRc);
                espresso.setText("");
            }

        }else if (global.contains("/")) {
            String index=espresso.getText().toString();
            String dados[]=index.split("/");
            String ix0=dados[0];
            String ix1=dados[1];
            double valorP1 =Double.parseDouble(ix0);
            double valorP2 =Double.parseDouble(ix1);
            double total=valorP1/valorP2;
            espressaResultado.setText("   "+espresso.getText().toString());
            todoTotal=total;
            if(opcaoTotal){
                total_de_todas_compras=todoTotal;
                //Método para alterar o valor
                alteraValor(total_de_todas_compras);
                String optotalCps=decimalFormat.format(total_de_todas_compras);
                totalCompras.setText("R$ "+optotalCps);
                String totalNormal=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+totalNormal);
                espresso.setText("");
                opcaoTotal=false;
            }else if(opcaoTotal==false){
                String totalNormal=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+totalNormal);
                espresso.setText("");
            }

        }else if (global.contains("*")) {
            String index=espresso.getText().toString();
            String dados[]=index.replace("*"," ").split(" ");
            String ix0=dados[0];
            String ix1=dados[1];
            double valorP1 =Double.parseDouble(ix0);
            double valorP2 =Double.parseDouble(ix1);
            double total=valorP1*valorP2;
            espressaResultado.setText("   "+espresso.getText().toString());
            todoTotal=total;
            if(opcaoTotal){
                 String opcaoTodoTotal=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+opcaoTodoTotal);
                espresso.setText("");
                opcaoTotal=false;
            }else{
                double bdTotal=BD.buscarTotalPeloId();
                double valortotal=bdTotal+todoTotal;
                String totalCompra=decimalFormat.format(valortotal);
                //Método para alterar o valor
                alteraValor(valortotal);
                totalCompras.setText("R$ "+totalCompra);
                String totalRc=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+totalRc);
                espresso.setText("");
            }
        }
        return total_de_todas_compras;
    }

    /**
     * Método para corrigir os calculos caso esteja incorretos
     * @return
     */
    public void setaOperacaoCorreta(){
       boolean verificar=false;
            String  opcao=espresso.getText().toString();
            if (opcao.contains("..") ||opcao.endsWith(sinal+".") || opcao.endsWith("."+sinal)){
                espresso.setText(opcao.substring(0,opcao.length()-1));
                operacao=true;
            }else {
                verificar=false;
                operacao=false;
            }

            String texto=espresso.getText().toString();
        if(texto.equals(digito+""+sinal)){
            operacao=true;
        }else if(dados.length()>0 && dados.endsWith(sinal+"")){
            operacao=true;
        }else {
            operacao=false;
        }
    }


    /**
     * Método para limpar por cada caracteres
     * @param view
     */
    public void setaLimpar(View view){
        String  dgt=espresso.getText().toString();
        if(dgt.isEmpty()){
        }else{
            espresso.setText(dgt.substring(0,dgt.length()-1));
            if(opcaoTotal){
               espresso.setText("");
             opcaoTotal=false;
            }else{
                opcaoTotal=false;
            }
        }
    }

    /**
     * Método para limpar todor total das operações que foram feitas
     * @param view
     */
    public void limparTd(View view){
        // Questionando o usuário para ter certeza que está ciente da exclusão de tudo
       AlertDialog.Builder al = new AlertDialog.Builder(this);
        al.setTitle("Limpar Total");
        al.setMessage("Deseja apagar todo total");
        al.setCancelable(false);
         al.setIcon(R.drawable.baseline_delete_forever_24);
        al.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                espressaResultado.setText("");
                total_de_todas_compras=0;
                alteraValor(total_de_todas_compras);
                totalCompras.setText("R$ "+BD.buscarTotalPeloId());
                todo_Total.setText("");
                espresso.setText("");
                Toast.makeText(CalcularCompras.this, "Todo total excluido!", Toast.LENGTH_SHORT).show();

            }
        });

        al.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CalcularCompras.this, "Total não excluido!", Toast.LENGTH_SHORT).show();
            }
        });
        al.create();
        al.show();

    }

    /**
     * Método para seta o simbolo da adição ao clicar no botão
     * @param view
     */
    public void setaSoma(View view){

        if(espresso.getText().toString().isEmpty()){

        }else{
            sinal='+';
            espresso.setText(espresso.getText().toString()+sinal);
        }
    }

    /**
     * Método para seta o simbolo de subtração ao clicar no botão
     * @param view
     */
    public void setaSubtracao(View view){
        if(espresso.getText().toString().isEmpty()){
        }else{
            sinal='-';
            espresso.setText(espresso.getText().toString()+sinal);
        }
    }

    /**
     * Método para deletar todor total das operações
     * @param view
     */
    public void deletaValores(View view){
      espresso.setText("");
      espressaResultado.setText("");
      todo_Total.setText("");
    }

    /**
     * Método para quando clicar no campo de todor total seta todor valor no campo da operação
     * @param view
     */
    public void setaTotalNaExpressa(View view){
        if(BD.buscarTotalPeloId()==0){
            espresso.setText("");
        }else {
            // Nesse caso vai realizar um tipo de operação especificar com
            // a variável opcaoTotal recebendo valor de true
            opcaoTotal=true;
            valorFixo=espresso.getText().toString();
         //   double setaTotal=Double.parseDouble(totalCompras.getText().toString().replace("R$ ",""));
            String format=decimalFormat.format(BD.buscarTotalPeloId()).replace(",",".");
            espresso.setText(format);
        }
    }

    /**
     * Método para seta o sinal de divisão no campo da operação
     * @param view
     */
    public void setaDivisao(View view){
        if(espresso.getText().toString().isEmpty()){
        }else{
            sinal='/';
            espresso.setText(espresso.getText().toString()+sinal);
        }
    }

    /**
     * Método para seta o sinal de multiplicação no campo da operação
     * @param view
     */
    public void setaMultiplicacao(View view){
        if(espresso.getText().toString().isEmpty()){
        }else{
            sinal='*';
            espresso.setText(espresso.getText().toString()+sinal);
        }
    }

    /**
     * Método para altera o total no banco de dados
     * @param valor
     */
    public void alteraValor(double valor){
        ModelCalculor m = new ModelCalculor();
        m.setId(60);
        m.setTotal(valor);
        BD.atualizar(m);
      double total=BD.buscarTotalPeloId();
      if(total==0){
         // Toast.makeText(this, "Total Zerado! "+total, Toast.LENGTH_SHORT).show();
      }else {
          Toast.makeText(this, "Total Atualizado! " + total, Toast.LENGTH_SHORT).show();
      }
    }

    /**
     * Método para adiconar no banco de dados
     * @param valor
     */
    public  void salvar(double valor){
        ModelCalculor cal = new ModelCalculor();
        cal.setTotal(valor);
        long id = BD.inserir(cal);
        Toast.makeText(this, "Id salvo "+id, Toast.LENGTH_SHORT).show();
    }

    /**
     * Método para seta o valor total da coluna total do banco de dados
     */
    public  void setaTotal(){
        double total=BD.buscarTotalPeloId();
        String Tdvalor=decimalFormat.format(total);
        totalCompras.setText("R$ "+Tdvalor);
       //espresso.setText(""+total);
    }

    public void opcaoOn() {
        String conteudo = espresso.getText().toString();
        if (conteudo.contains("+")) {
            String index[] = conteudo.replace("+", " ").split(" ");
            String i0 = index[0];
            String i1 = index[1];
            double x0 = Double.parseDouble(i0);
            double x1 = Double.parseDouble(i1);
            double t = x0 + x1;
            String dc=decimalFormat.format(t);
            espressaResultado.setText("  " + conteudo);
            todo_Total.setText(" R$ " + dc);
            espresso.setText("");
        } else if (conteudo.contains("-")) {
            String index[] = conteudo.split("-");
            String i0 = index[0];
            String i1 = index[1];
            double x0 = Double.parseDouble(i0);
            double x1 = Double.parseDouble(i1);
            double t = x0 - x1;
            String dc=decimalFormat.format(t);
            espressaResultado.setText("  " + conteudo);
            todo_Total.setText(" R$ " + dc);
            espresso.setText("");

        }else if (conteudo.contains("/")) {
            String index[] = conteudo.split("/");
            String i0 = index[0];
            String i1 = index[1];
            double x0 = Double.parseDouble(i0);
            double x1 = Double.parseDouble(i1);
            double t = x0/x1;
            String dc=decimalFormat.format(t);
            espressaResultado.setText("  " + conteudo);
            todo_Total.setText(" R$ " +dc);
            espresso.setText("");

        }else if (conteudo.contains("*")) {
            String index[] = conteudo.replace("*"," ").split(" ");
            String i0 = index[0];
            String i1 = index[1];
            double x0 = Double.parseDouble(i0);
            double x1 = Double.parseDouble(i1);
            double t = x0*x1;
            String dc=decimalFormat.format(t);
            espressaResultado.setText("  " + conteudo);
            todo_Total.setText(" R$ " +dc);
            espresso.setText("");

        }
    }
}