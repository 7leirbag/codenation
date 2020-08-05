package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Estacionamento {

    private List<Carro> carros = new ArrayList<>();
    private static final int LIMITE_VAGAS = 10;

    public void estacionar(Carro carro) {
        validarMotorista(carro.getMotorista());
        validarHabilitacao(carro.getMotorista());

        if(isVagaDisponivel()){
            carros.add(carro);
        }
        else{
            this.remanejarCarros(carro);
        }

    }

    private void remanejarCarros(Carro carro){
        int indexVagaDisponivel = IntStream.range(0, carros.size())
                .filter(x-> carros.get(x).getMotorista().getIdade() < 56)
                .findFirst().orElse(-1);

        if(indexVagaDisponivel >= 0){
            carros.set(indexVagaDisponivel, carro);
        }
        else{
            throw new EstacionamentoException("Nenhuma vaga disponível.");
        }
    }

    private Boolean isVagaDisponivel() {
        return this.carrosEstacionados() < 10;
    }

    private void validarMotorista(Motorista motorista) {
        if(motorista == null)
            throw new EstacionamentoException("Autonomo não pode.");

        if(motorista.getIdade() < 18)
            throw new EstacionamentoException("Menor de idade não pode.");
    }

    private void validarHabilitacao(Motorista motorista){
        if(motorista.getPontos() > 20)
            throw new EstacionamentoException("Habilitação não pode estar suspensa");
    }


    public int carrosEstacionados() { return carros.size(); }

    public boolean carroEstacionado(Carro carro) {
       return carros.stream().anyMatch(x -> x.equals(carro));
    }
}
