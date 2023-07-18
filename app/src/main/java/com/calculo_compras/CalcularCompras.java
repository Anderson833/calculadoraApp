package com.calculo_compras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CalcularCompras extends AppCompatActivity {

     private boolean opcaoTotal =false, operacao =false;
     private  char sinal,digito;
     private double todoTotal=0, total_de_todas_compras;
     private int cont=0, operacoes=0;

     private  String dados="";
     private  final  String arquivo="preferenciaArquivo";
    private TextView espresso, espressaResultado, num0,num1,num2,num3,num4,num5,num6,num7,num8,num9,digitoClimpar,DIGITO_OFF,DIGITO_OP,CEtUDO
            ,num_ponto,simbolo_igual,d_soma,d_subtracao,d_multi,d_divisao, todo_Total, totalCompras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_compras);
        getSupportActionBar().hide();
        espresso = findViewById(R.id.expressao);

        espressaResultado = findViewById(R.id.ExibirespressaoRecente);
        num0 = findViewById(R.id.zero);
        num1 = findViewById(R.id.um);
        num2 = findViewById(R.id.dois);
        num3 = findViewById(R.id.tres);
        num4 = findViewById(R.id.quatro);
        num5 = findViewById(R.id.cinco);
        num6 = findViewById(R.id.seis);
        num7 = findViewById(R.id.sete);
        num8 = findViewById(R.id.oito);
        num9 = findViewById(R.id.nove);
        todo_Total =findViewById(R.id.Exibirexpressaodototal);
        CEtUDO = findViewById(R.id.CE);
        digitoClimpar = findViewById(R.id.C_LIMPAR);
         DIGITO_OFF= findViewById(R.id.opcao);
        num_ponto = findViewById(R.id.ponto);
        simbolo_igual = findViewById(R.id.igual);
         d_soma = findViewById(R.id.soma);
        d_subtracao = findViewById(R.id.subtracao);
        d_multi = findViewById(R.id.multiplicacao);
         d_divisao = findViewById(R.id.divisao);
         totalCompras = findViewById(R.id.TotalDasCompres);
        exibirTotal();
        espresso.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Antes de ser alterado

               // Toast.makeText(CalcularCompras.this, "Antes de escrever"+s, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // sendo escrito
                //  Laço de repetição para limita a quantidade de simbolos das operações aritimetrica
                String caracter=espresso.getText().toString();
                for (int i=0;i<caracter.length();i++){
                    char digitos=caracter.charAt(i);
                    if(digitos=='+' || digitos=='-'|| digitos=='/'||digitos=='*'){
                       operacoes=operacoes+1;
                    }
                }

            }
            @Override
            public void afterTextChanged(Editable s) {
               // depois de escrirto
                dados=""+s;
                //Método para evitar error ao executar uma operação apenas com um número e simbolo da operação
               setaOperacaoCorreta(digito);
                String ultimoindex=espresso.getText().toString();
                if(operacoes==1){
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

                //Condição para saber se contém dos simbolos da operaçõo
                if(operacoes==2){
                   // Toast.makeText(CalcularCompras.this, ""+operacoes+"vezes", Toast.LENGTH_SHORT).show();
                    String index=espresso.getText().toString();
                    espresso.setText(index.substring(0,index.length()-1));

                }
              //  Toast.makeText(CalcularCompras.this, "Tamanho "+dados.length()+" qtd"+operacoes , Toast.LENGTH_SHORT).show();
                operacoes=0;
            }
        });
    }



    public void setaNumero0(View view){
        digito='0';
        espresso.setText(espresso.getText()+"0");
    }
    public void setaNumero1(View view){
        digito='1';
        espresso.setText(espresso.getText()+"1");
    }
    public void setaNumero2(View view){
        espresso.setText(espresso.getText()+"2");
        digito='2';
    }
    public void setaNumero3(View view){
        digito='3';
        espresso.setText(espresso.getText()+"3");
    }
    public void setaNumero4(View view){
        digito='4';
        espresso.setText(espresso.getText()+"4");
    }
    public void setaNumero5(View view){
        digito='5';
        espresso.setText(espresso.getText()+"5");
    }
    public void setaNumero6(View view){
        digito='6';
        espresso.setText(espresso.getText()+"6");
    }
    public void setaNumero7(View view){
        digito='7';
        espresso.setText(espresso.getText()+"7");
    }
    public void setaNumero8(View view){
        digito='8';
        espresso.setText(espresso.getText()+"8");
    }
    public void setaNumero9(View view){
        digito='9';
        espresso.setText(espresso.getText()+"9");
    }

    public void opcao(View view){
        cont++;
        if(cont==1){
         DIGITO_OFF.setText("ON");
        }else {
            cont=0;
            DIGITO_OFF.setText("OFF");
        }

    }
    DecimalFormat decimalFormat  = new DecimalFormat("#,##0.00");
    public void setaPonto(View view){
        sinal='.';

        if(espresso.getText().toString().isEmpty()){

        }else{
            espresso.setText(espresso.getText()+".");
        }

    }

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
    //Método para calculor os valores conforme o sinal passado no paramentro
    public double valorTotal(char sinal){
        //Variavel para armazenar o total de cada operação
        double resultado=0;
        if(operacao==true){
        }else{
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
    DecimalFormat df  = new DecimalFormat("#,##0.00");

    public  double retornaTodototal(String p1,String p2, char sinal){
        if(sinal=='+'){
            double valorP1 =Double.parseDouble(p1);
            double valorP2 =Double.parseDouble(p2);
            double total=valorP1+valorP2;
          //  String ttl=df.format(total);
            espressaResultado.setText(""+espresso.getText().toString());
            todoTotal=total;

            if(opcaoTotal){
                total_de_todas_compras=todoTotal;
                String valorTotal=decimalFormat.format(total_de_todas_compras);
                totalCompras.setText("R$ "+valorTotal);
           //   String totalOp=converteValores(total_de_todas_compras,todoTotal);
             //   guardaTotal(valorTotal);
                String totalRc=decimalFormat.format(todoTotal);
                todo_Total.setText("R$ "+totalRc);
                espresso.setText("");
                opcaoTotal=false;
            }else if(opcaoTotal==false){
                double tdTotal=total_de_todas_compras=total_de_todas_compras+todoTotal;
                String Tdvalor=decimalFormat.format(tdTotal);
            //  String valor=converteValores(total_de_todas_compras,todoTotal);
              //  guardaTotal(Tdvalor);
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
            espressaResultado.setText(""+espresso.getText().toString());
            todoTotal=total;
            if(opcaoTotal){
                total_de_todas_compras=todoTotal;
                String opTotalCompras=decimalFormat.format(total_de_todas_compras);
             //   guardaTotal(opTotalCompras);
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
            espressaResultado.setText(""+espresso.getText().toString());
            todoTotal=total;
            if(opcaoTotal){
                total_de_todas_compras=todoTotal;
                String optotalCps=decimalFormat.format(total_de_todas_compras);
            //    guardaTotal(optotalCps);
                totalCompras.setText("R$ "+optotalCps);
            //    guardaTotal(optotalCps);
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
            espressaResultado.setText(""+espresso.getText().toString());
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
    public boolean setaOperacaoCorreta(char num){
       String muilt="*",subtracao="-",divisao="/",adicao="+";
       boolean verificar=false;
/*
        if(digitos.equals(num+""+divisao+sinal)|| digitos.equals(num+""+muilt+sinal)
                || digitos.equals(num+""+subtracao+sinal)|| digitos.equals(num+""+adicao+sinal)||
                digitos.equals(num+""+sinal+sinal)) {
            String dados=espresso.getText().toString();
            espresso.setText(dados.substring(0,dados.length()-1));
        }*/
            String  opcao=espresso.getText().toString();
            if (opcao.contains("..") ||opcao.endsWith(sinal+".") || opcao.endsWith("."+sinal)){
                espresso.setText(opcao.substring(0,opcao.length()-1));
                verificar=true;
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
        return verificar;
    }
    public void setaLimpar(View view){
        String  dgt=espresso.getText().toString();
        if(dgt.isEmpty()){
        }else{
            espresso.setText(dgt.substring(0,dgt.length()-1));
            espressaResultado.setText("");
        }
    }
    public void limparTd(View view){

       AlertDialog.Builder al = new AlertDialog.Builder(this);
        al.setTitle("Limpar Total");
        al.setMessage("Deseja apagar todo total");
        al.setCancelable(false);
         al.setIcon(R.drawable.baseline_delete_forever_24);
        al.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                espressaResultado.setText("");
                todoTotal=0;
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
    public void setaSoma(View view){

        if(espresso.getText().toString().isEmpty()){

        }else{
            sinal='+';
            espresso.setText(espresso.getText().toString()+sinal);
        }
    }
    public void setaSubtracao(View view){
        if(espresso.getText().toString().isEmpty()){

        }else{
            sinal='-';
            espresso.setText(espresso.getText().toString()+sinal);
        }
    }
    public void deletaTdTotal(View view){
        double zeraTotal=total_de_todas_compras=0;
        String convert=decimalFormat.format(zeraTotal);
        guardaTotal(convert);
     totalCompras.setText("R$ "+convert);
    }
    public void setaTotalNaExpressa(View view){

        if(total_de_todas_compras==0){
            espresso.setText("");
        }else {
          //  todo_Total.setText(""+total_de_todas_compras);
            opcaoTotal=true;
         //   double setaTotal=Double.parseDouble(totalCompras.getText().toString().replace("R$ ",""));
            espresso.setText(""+total_de_todas_compras);
        }
    }
    public void setaDivisao(View view){
        if(espresso.getText().toString().isEmpty()){

        }else{
            sinal='/';
            espresso.setText(espresso.getText().toString()+sinal);
        }
    }
    public void setaMultiplicacao(View view){
        if(espresso.getText().toString().isEmpty()){
        }else{
            sinal='*';
            espresso.setText(espresso.getText().toString()+sinal);
        }
    }
    public void guardaTotal(String valor){

        SharedPreferences  preferences = getSharedPreferences(arquivo,0);
        SharedPreferences.Editor editor = preferences.edit();
         //validar
        if(totalCompras.getText().toString().isEmpty()){
        }else {
          //  String total=totalCompras.getText().toString();
             editor.putString("total",valor);
             editor.commit();
        }
    }
      public String converteValores(double todoTotal,double totalRecente){
          DecimalFormat decimalFormat  = new DecimalFormat("###,##0.00");
          String tdValor=decimalFormat.format(todoTotal);
          String totalRc=decimalFormat.format(totalRecente);
           todo_Total.setText("R$ "+totalRc);
           totalCompras.setText("R$ "+tdValor);
          return tdValor;
     }
    public void exibirTotal(){
        SharedPreferences  preferences = getSharedPreferences(arquivo,0);
        //validar
        if(preferences.contains("total")){
           String todoTotal=preferences.getString("total","");
           totalCompras.setText("R$ "+todoTotal);
        }else {
            totalCompras.setText("");
        }
    }
}