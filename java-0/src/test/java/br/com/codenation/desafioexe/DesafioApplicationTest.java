package br.com.codenation.desafioexe;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

public class DesafioApplicationTest {

	@Test
	public void fibonacci() {
		assertNotNull(DesafioApplication.fibonacci());
	}

	@Test
	public void deve_possuir_o_valor_zero_na_primeira_posicao(){
		assertTrue(DesafioApplication.fibonacci().get(0).equals(0));
	}

	@Test
	public void deve_possuir_o_valor_duzentos_e_trinta_e_tres_na_ultima_posicao(){
		List<Integer> fibonacci = DesafioApplication.fibonacci();
		assertTrue(fibonacci.get(fibonacci.size() - 1).equals(233));
	}

	@Test
	public void isFibonacci() {
		assertTrue(DesafioApplication.isFibonacci(1));
	}

}
