package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		if(salarioBase < 1039)
			return 0;

		double salarioLiquido = salarioBase - calcularInss(salarioBase);
		salarioLiquido = salarioLiquido - calcularIrrf(salarioLiquido);

		return Math.round(salarioLiquido);
	}

	private double calcularInss(double salarioBase) {
		double percentual = salarioBase <= 1500? 0.08 : salarioBase <= 4000? 0.09 : 0.11;
		return salarioBase * percentual;
	}

	private double calcularIrrf(double value) {
		double percentual = value <= 3000 ? 0 : value <= 6000? 0.075 : 0.15;
		return value * percentual;
	}
}
