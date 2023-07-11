package com.calculo_compras;

import java.io.Serializable;

public class ModelCalculor implements Serializable {

    private  int id;
    private  double total;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String toString(){
        return String.valueOf("Id: "+id+"   Valor: "+total);
    }
}
