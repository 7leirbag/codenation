package challenge;

import com.sun.tools.javac.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class CriptografiaCesariana implements Criptografia {
    private static final long DESLOCAMENTO = 3;

    private  static final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public String criptografar(String texto) {
        return aplicarDeslocamento(texto, false);
    }

    @Override
    public String descriptografar(String texto) {
        return aplicarDeslocamento(texto, true);
    }

    private String aplicarDeslocamento(String texto, boolean isDescriptografia){
        validarTexto(texto);
        String fraseNova = "";

        for(char letra: texto.toLowerCase().toCharArray())
        {
            int posicao = ALFABETO.indexOf(letra);

            fraseNova += (posicao >= 0?  ALFABETO.charAt(isDescriptografia ? getPosicaoParaDesciptofrafar(posicao) : getPosicaoParaCriptofrafar(posicao)) : letra);
        }

        return fraseNova;
    }

    private void validarTexto(String texto){
        if(texto.isEmpty())
            throw new IllegalArgumentException();

        if(texto == null)
            throw new NullPointerException();
    }

    private int getPosicaoParaCriptofrafar(long posicaoAtual){
        long posicao = posicaoAtual + (DESLOCAMENTO+1);


        return (int)(posicao <= Arrays.asList(ALFABETO).size() ? posicao : posicao - Arrays.asList(ALFABETO).size());
    }

    private int getPosicaoParaDesciptofrafar(long posicaoAtual)
    {
        long posicao = posicaoAtual - DESLOCAMENTO;

        return (int)(posicao >= 0 ? posicao : Arrays.asList(ALFABETO).size() - (posicao * -1));
    }
}
