package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		int numeroAtual = 1;
		int numeroAnterior = 0;

		List<Integer> listaFibonacci = new ArrayList<>();

		while (numeroAnterior  < 400){
			listaFibonacci.add(numeroAnterior);

			int numerosSomados = numeroAtual + numeroAnterior;
			numeroAnterior = numeroAtual;
			numeroAtual = numerosSomados;
		}

		return listaFibonacci;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}