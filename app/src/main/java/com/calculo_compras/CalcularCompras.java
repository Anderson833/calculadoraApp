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
     private  String dados="";
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

     //   bd= Room.databaseBuilder(getApplicationContext(),MyBd.class,"BdCompras").build();

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
        }else {
            cont=0;
            DIGITO_OFF.setText("OFF");
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
        if(espresso.getText().toString().isEmpty()){
        }else{
            espresso.setText(espresso.getText()+".");
        }
    }

    /**
     *  Método para executar o calculor ao clicar no botão igual
     * @param view
     */
    public void setaIgual(View view){

        if(espresso.getText().toString().isEmpty()){

        }else {
        // Condições para realizar as operações
        if(sinal=='+'){
            valorTotal(sinal);
        }
        if(sinal=='-'){
            valorTotal(sinal);
        }
        if(sinal=='*'){
            valorTotal(sinal);
        }
        if(sinal=='/') {
            valorTotal(sinal);
        } }
    }

    /**
     * Método para calculor os valores conforme o sinal passado no paramentro
     * @param sinal
     * @return O total
     */
    public double valorTotal(char sinal){
        //Variavel para armazenar o total de cada operação
        double resultado=0;
        // condição para saber se os sinal das operações estão corretas
        if(operacao==true){
            // caso não esteja nada acontecer
        }else{
            // caso esteja será executado o calculor

        // condição para as opções
        if(DIGITO_OFF.getText().toString().equals("OFF")){
            //Variavel para armazenar o total de cada operação
            // recebendo o sinal do paramentro
            switch (sinal) {
                case '-':
                {
                    // Operação para fazer a subtração
                    String []md=espresso.getText().toString().split("-");
                    String p1=md[0];
                    String p2=md[1];
                  //  double total=valorP1-valorP2;
                    resultado= resultado=retornaTodototal(p1,p2,'-');;
                    break;
                }
                case '+':
                {
                    // Operação para fazer a adição
                    String []md=espresso.getText().toString().replace("+"," ").split(" ");
                    String p1=md[0];
                    String p2=md[1];
                    // Corrigir ação para quando o index 1 tiver com sinal de ponto
                        resultado=retornaTodototal(p1,p2,'+');

                    break;
                }
                case '*':
                {
                    // Operação para fazer a multiplicação
                    String []md=espresso.getText().toString().replace("*"," ").split(" ");
                    String p1=md[0];
                    String p2=md[1];
                    //double total=valorP1*valorP2;
                    resultado= resultado=retornaTodototal(p1,p2,'*');;
                    break;
                }
                case '/':
                {
                    // Operação para fazer a divisão
                    String []md=espresso.getText().toString().split("/");
                    String p1=md[0];
                    String p2=md[1];
                   // double total=valorP1/valorP2;
                    resultado= resultado=retornaTodototal(p1,p2,'/');;
                    break;
                }
                default:
                    break;
            }

        }else {

            // recebendo o sinal do paramentro
            switch (sinal) {
                case '-': {
                    // Operação para fazer a subtração
                    String[] md = espresso.getText().toString().split("-");
                    String p1 = md[0];
                    String p2 = md[1];
                    double valorP1 = Double.parseDouble(p1);
                    double valorP2 = Double.parseDouble(p2);
                    double total = valorP1 - valorP2;
                    espressaResultado.setText("" + total);
                    espresso.setText("");
                    resultado = total;
                    break;
                }
                case '+': {
                    // Operação para fazer a adição
                    String[] md = espresso.getText().toString().replace("+", " ").split(" ");
                    String p1 = md[0];
                    String p2 = md[1];
                    double valorP1 = Double.parseDouble(p1);
                    double valorP2 = Double.parseDouble(p2);
                    double total = valorP1 + valorP2;
                    espressaResultado.setText("" + total);
                    espresso.setText("");
                    resultado = total;
                    break;
                }
                case '*': {
                    // Operação para fazer a multiplicação
                    String[] md = espresso.getText().toString().replace("*", " ").split(" ");
                    String p1 = md[0];
                    String p2 = md[1];
                    double valorP1 = Double.parseDouble(p1);
                    double valorP2 = Double.parseDouble(p2);
                    double total = valorP1 * valorP2;
                    espressaResultado.setText("" + total);
                    espresso.setText("");
                    resultado = total;
                    break;
                }
                case '/': {
                    // Operação para fazer a divisão
                    String[] md = espresso.getText().toString().split("/");
                    String p1 = md[0];
                    String p2 = md[1];
                    double valorP1 = Double.parseDouble(p1);
                    double valorP2 = Double.parseDouble(p2);
                    double total = valorP1 / valorP2;
                    espressaResultado.setText("" + total);
                    espresso.setText("");
                    resultado = total;
                    break;
                }
                default:
                    break;
            }
         }
        }
        // retorna o total da operação que foi executada
        return resultado;
    }

    /**
     * Método  para calcular as operações comforme os valores e um sinal da operação passada nos parametros
     * @param p1
     * @param p2
     * @param sinal
     * @return Todor total calculado pelo sistema
     */
    public  double retornaTodototal(String p1,String p2, char sinal){
        if(sinal=='+'){
            double valorP1 =Double.parseDouble(p1);
            double valorP2 =Double.parseDouble(p2);
            double total=valorP1+valorP2;
          //  String ttl=df.format(total);
            espressaResultado.setText("   "+espresso.getText().toString());
            todoTotal=total;

            if(opcaoTotal){
                total_de_todas_compras=todoTotal;
                String valorTotal=decimalFormat.format(total_de_todas_compras);
                totalCompras.setText("R$ "+valorTotal);
             /*   Dados d = new Dados();
                d.setTodoTotal(total_de_todas_compras);
                d.getTodoTotal();
                bd.daoDados().insert(d);*/
           //   String totalOp=converteValores(total_de_todas_compras,todoTotal);
             //   guardaTotal(valorTotal);
                String totalRc=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+totalRc);
                espresso.setText("");
                opcaoTotal=false;
            }else if(opcaoTotal==false){
                double tdTotal=total_de_todas_compras=total_de_todas_compras+todoTotal;
                String Tdvalor=decimalFormat.format(tdTotal);
               // double calculor=Double.parseDouble(espressaResultado.getText().toString());
                salvar(tdTotal);
            //  String valor=converteValores(total_de_todas_compras,todoTotal);
                totalCompras.setText("R$ "+Tdvalor);
                String totalRc=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+totalRc);
                espresso.setText("");
            }
              // opcaoTotal=false;
        } else if (sinal=='-') {
            double valorP1 =Double.parseDouble(p1);
            double valorP2 =Double.parseDouble(p2);
            double total=valorP1-valorP2;
            espressaResultado.setText("   "+espresso.getText().toString());
            todoTotal=total;
            if(opcaoTotal){
                total_de_todas_compras=todoTotal;
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

        }else if (sinal=='/') {
            double valorP1 =Double.parseDouble(p1);
            double valorP2 =Double.parseDouble(p2);
            double total=valorP1/valorP2;
            espressaResultado.setText("   "+espresso.getText().toString());
            todoTotal=total;
            if(opcaoTotal){
                total_de_todas_compras=todoTotal;
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

        }else if (sinal=='*') {
            double valorP1 =Double.parseDouble(p1);
            double valorP2 =Double.parseDouble(p2);
            double total=valorP1*valorP2;
            espressaResultado.setText("   "+espresso.getText().toString());
            todoTotal=total;
            if(opcaoTotal){
              /*  double valortotal=total_de_todas_compras+todoTotal;
                String totalCompra=decimalFormat.format(valortotal);
                //  guardaTotal(totalCompra);
                totalCompras.setText("R$ "+totalCompra);*/
                 String opcaoTodoTotal=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+opcaoTodoTotal);
                espresso.setText("");
                opcaoTotal=false;
            }else{
                double valortotal=total_de_todas_compras=total_de_todas_compras+todoTotal;
                String totalCompra=decimalFormat.format(valortotal);
              //  guardaTotal(totalCompra);

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
          //  espressaResultado.setText("");
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
                totalCompras.setText("R$ "+total_de_todas_compras);
                todo_Total.setText("");
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
    public void deletaTdTotal(View view){
        double zeraTotal=total_de_todas_compras=0;
        String convert=decimalFormat.format(zeraTotal);
     totalCompras.setText("R$ "+convert);
    }

    /**
     * Método para quando clicar no campo de todor total seta todor valor no campo da operação
     * @param view
     */
    public void setaTotalNaExpressa(View view){

        if(total_de_todas_compras==0){
            espresso.setText("");
        }else {
            // Nesse caso vai realizar um tipo de operação especificar com
            // a variável opcaoTotal recebendo valor de true
            opcaoTotal=true;
         //   double setaTotal=Double.parseDouble(totalCompras.getText().toString().replace("R$ ",""));
            espresso.setText(""+total_de_todas_compras);
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

    public  void salvar(double valor){
        ModelCalculor cal = new ModelCalculor();
        cal.setTotal(valor);
        long id = BD.inserir(cal);
        Toast.makeText(this, "Id salvo "+id, Toast.LENGTH_SHORT).show();
    }

}