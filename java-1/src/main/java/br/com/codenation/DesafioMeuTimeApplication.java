package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.sun.corba.se.impl.util.RepositoryId.cache;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

    List<TimeDeFutebol> timesDeFutebol = new ArrayList<>();
    List<Jogador> jogadores = new ArrayList<>();


    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        validarSeIdentificadorDoTimeJaExiste(id);
        timesDeFutebol.add(new TimeDeFutebol(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        validarSeIdentificadorDoJogadorJaExiste(id);
        validarSeTimeExiste(idTime);
        jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
    }

    private void validarSeIdentificadorDoJogadorJaExiste(Long id) {
        if (jogadores.stream().anyMatch(x -> x.getId().equals(id))) {
            throw new IdentificadorUtilizadoException();
        }
    }

    private void validarSeIdentificadorDoTimeJaExiste(Long idTime) {
        if (timesDeFutebol.stream().anyMatch(x -> x.id.equals(idTime))) {
            throw new IdentificadorUtilizadoException();
        }
    }



    private void validarSeTimeExiste(Long idTime) {
        timesDeFutebol.stream().filter(x -> x.id.equals(idTime)).findAny().orElseThrow(() -> new TimeNaoEncontradoException());
    }

    public void definirCapitao(Long idJogador) {
        jogadores.stream().filter(x -> x.getId().equals(idJogador)).findFirst().orElseThrow(() -> new JogadorNaoEncontradoException());
        jogadores.forEach(x-> x.isCapitao  = x.getId().equals(idJogador));
    }

    public Long buscarCapitaoDoTime(Long idTime) {
        validarSeTimeExiste(idTime);
        return jogadores.stream().filter(x -> x.idTime.equals(idTime) && x.isCapitao).findFirst().orElseThrow(() -> new CapitaoNaoInformadoException()).getId();
    }

    public String buscarNomeJogador(Long idJogador) {
        return jogadores.stream().filter(x -> x.getId().equals(idJogador)).findFirst().orElseThrow(() -> new JogadorNaoEncontradoException()).nome;
    }

    public String buscarNomeTime(Long idTime) {
        return timesDeFutebol.stream().filter(x -> x.id.equals(idTime)).findFirst().orElseThrow(() -> new TimeNaoEncontradoException()).nome;
    }

    public List<Long> buscarJogadoresDoTime(Long idTime) {
        validarSeTimeExiste(idTime);
        return jogadores.stream().filter(x -> x.idTime.equals(idTime)).map(Jogador::getId).collect(Collectors.toList());
    }

    public Long buscarMelhorJogadorDoTime(Long idTime) {
        validarSeTimeExiste(idTime);
        return jogadores.stream().max(Comparator.comparing((Jogador::getNivelHabilidade))).get().getId();
    }

    public Long buscarJogadorMaisVelho(Long idTime) {
        validarSeTimeExiste(idTime);
        return jogadores.stream().min(Comparator.comparing((Jogador::getDataNascimento))).get().getId();
    }

    public List<Long> buscarTimes() {
        return timesDeFutebol.stream().map(x -> x.id).collect(Collectors.toList());
    }

    public Long buscarJogadorMaiorSalario(Long idTime) {
        validarSeTimeExiste(idTime);
        return jogadores.stream().max(Comparator.comparing((Jogador::getSalario))).get().getId();
    }

    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        return jogadores.stream().filter(x -> x.getId().equals(idJogador)).findFirst().orElseThrow(() -> new JogadorNaoEncontradoException()).getSalario();
    }

    public List<Long> buscarTopJogadores(Integer top) {
        return jogadores.stream().sorted(Comparator.comparing((Jogador::getNivelHabilidade)).thenComparing(Jogador::getId).reversed())
                .limit(top).map(Jogador::getId).collect(Collectors.toList());
    }

}
