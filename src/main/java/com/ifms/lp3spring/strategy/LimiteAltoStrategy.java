package com.ifms.lp3spring.strategy;

public class LimiteAltoStrategy implements CalculoLimiteStrategy {
    @Override
    public double calcular(double renda) {
        return renda * 0.5; // 50% da renda
    }
}
