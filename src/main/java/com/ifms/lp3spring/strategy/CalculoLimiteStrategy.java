package com.ifms.lp3spring.strategy;

// Calculo de limite de credito inicial
public interface CalculoLimiteStrategy {
    double calcular(double renda);
}
