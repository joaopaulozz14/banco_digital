package com.ifms.lp3spring.strategy;

public class LimiteBaixoStrategy implements CalculoLimiteStrategy {
    @Override
    public double calcular(double renda) {
        return renda * 0.2; // 20% da renda
    }
}
