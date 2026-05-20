package com.ifms.lp3spring.strategy;

public class CalculoLimiteFactory {
    public static CalculoLimiteStrategy obterEstrategia(double renda) {
        if (renda > 2000) {
            return new LimiteAltoStrategy();
        }
        return new LimiteBaixoStrategy();
    }
}
