package com.example.iot;

public class DadosSensor {
    double temperatura;
    int umidade;
    public DadosSensor(double temperatura, int umidade) {
        this.temperatura = temperatura;
        this.umidade = umidade;
    }
    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }
    public int getUmidade() { return umidade; }
    public void setUmidade(int umidade) { this.umidade = umidade; }
    @Override
    public String toString() {
        return "DadosSensor{" +
                "temperatura=" + temperatura +
                ", umidade=" + umidade +
                '}';
    }
}
