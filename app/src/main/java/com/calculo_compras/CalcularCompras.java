package com.calculo_compras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalcularCompras extends AppCompatActivity {

     private  char sinal;
     private double todoTotal=0, total_de_todas_compras;
private int cont=0;
    private TextView espresso, espressaResultado, num0,num1,num2,num3,num4,num5,num6,num7,num8,num9,digitoClimpar,DIGITO_OFF,DIGITO_OP,CEtUDO
            ,num_ponto,simbolo_igual,d_soma,d_subtracao,d_multi,d_divisao, todo_Total, totalCompras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_compras);
        getSupportActionBar().hide();

        espresso = findViewById(R.id.espressao);

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
    }

    public void setaNumero0(View view){
        espresso.setText(espresso.getText()+"0");
    }
    public void setaNumero1(View view){
        espresso.setText(espresso.getText()+"1");
    }
    public void setaNumero2(View view){
        espresso.setText(espresso.getText()+"2");
    }
    public void setaNumero3(View view){
        espresso.setText(espresso.getText()+"3");
    }
    public void setaNumero4(View view){
        espresso.setText(espresso.getText()+"4");
    }
    public void setaNumero5(View view){
        espresso.setText(espresso.getText()+"5");
    }
    public void setaNumero6(View view){
        espresso.setText(espresso.getText()+"6");
    }
    public void setaNumero7(View view){
        espresso.setText(espresso.getText()+"7");
    }
    public void setaNumero8(View view){
        espresso.setText(espresso.getText()+"8");
    }
    public void setaNumero9(View view){
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
    public void setaPonto(View view){
        sinal='.';
        espresso.setText(espresso.getText()+".");
       /* if(espresso.getText().toString().contains(".")){

        }else if(espresso.getText().toString().contains("+")){
            sinal='.';
            espresso.setText(espresso.getText()+".");
        }else if(espresso.getText().toString().contains("-")){
            sinal='.';
            espresso.setText(espresso.getText()+".");
        }else if(espresso.getText().toString().contains("/")){
            sinal='.';
            espresso.setText(espresso.getText()+".");
        }else if(espresso.getText().toString().contains("*")){
            sinal='.';
            espresso.setText(espresso.getText()+".");
        }else {

        }*/

    }

    public void setaIgual(View view){
       // espresso.setText(espresso.getText()+"=");
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
        if(sinal=='/'){
            valorTotal(sinal);
        }

    }
    //Método para calculor os valores conforme o sinal passado no paramentro
    public double valorTotal(char sinal){
        //Variavel para armazenar o total de cada operação
        double resultado=0;
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
        // retorna o total da operação que foi executada
        return resultado;
    }

    public  double retornaTodototal(String p1,String p2, char sinal){
        if(sinal=='+'){
            double valorP1 =Double.parseDouble(p1);
            double valorP2 =Double.parseDouble(p2);
            double total=valorP1+valorP2;
            espressaResultado.setText(""+espresso.getText().toString());
            todoTotal=total;
            total_de_todas_compras=todoTotal;
            totalCompras.setText(""+total_de_todas_compras);
            todo_Total.setText(""+todoTotal);
            espresso.setText("");

        } else if (sinal=='-') {
            double valorP1 =Double.parseDouble(p1);
            double valorP2 =Double.parseDouble(p2);
            double total=valorP1-valorP2;
            espressaResultado.setText(""+espresso.getText().toString());
            todoTotal=total;
            total_de_todas_compras=todoTotal;
            totalCompras.setText(""+total_de_todas_compras);
            todo_Total.setText(""+todoTotal);
            espresso.setText("");
        }else if (sinal=='/') {
            double valorP1 =Double.parseDouble(p1);
            double valorP2 =Double.parseDouble(p2);
            double total=valorP1/valorP2;
            espressaResultado.setText(""+espresso.getText().toString());
            todoTotal=total;
            total_de_todas_compras=todoTotal;
            totalCompras.setText(""+total_de_todas_compras);
            todo_Total.setText(""+todoTotal);
            espresso.setText("");
        }else if (sinal=='*') {
            double valorP1 =Double.parseDouble(p1);
            double valorP2 =Double.parseDouble(p2);
            double total=valorP1*valorP2;
            espressaResultado.setText(""+espresso.getText().toString());
            todoTotal=total;
            total_de_todas_compras=total_de_todas_compras+todoTotal;
            totalCompras.setText(""+total_de_todas_compras);
            todo_Total.setText(""+todoTotal);
            espresso.setText("");
        }

        return total_de_todas_compras;
    }

    public void setaLimpar(View view){
        espresso.setText("");
    }
    public void limparTd(View view){

      /*  AlertDialog.Builder al = new AlertDialog.Builder(this);
        al.setMessage("Deseja apagar tudo");
        al.setNeutralButton("Ok",null);
        al.setCancelable(true);*/
            espressaResultado.setText("");
            todoTotal=0;
            todo_Total.setText(""+todoTotal);


    }

    public void setaSoma(View view){
        sinal='+';
        setaTotalPeloSinal(sinal);
    }

    public void setaTotalPeloSinal(char sinal){
        if(espresso.getText().toString().isEmpty() && total_de_todas_compras!=0){
            espresso.setText(total_de_todas_compras+""+sinal);
        }else {
            espresso.setText(espresso.getText()+""+sinal);
        }
    }
    public void setaSubtracao(View view){
        sinal='-';
        setaTotalPeloSinal(sinal);
    }
    public void deletaTdTotal(View view){
        double zeraTotal=total_de_todas_compras=0;
     totalCompras.setText(""+zeraTotal);
    }
    public void setaTotalNaExpressa(View view){

        if(total_de_todas_compras==0){
            espresso.setText("");
        }else {
            espresso.setText("" + total_de_todas_compras);
          //  todo_Total.setText(""+total_de_todas_compras);
      //     totalCompras.setText(""+total_de_todas_compras);
          //  total_de_todas_compras=0;
        }
    }
    public void setaDivisao(View view){
        sinal='/';
        setaTotalPeloSinal(sinal);
    }
    public void setaMultiplicacao(View view){
        sinal='*';
        setaTotalPeloSinal(sinal);
    }

}