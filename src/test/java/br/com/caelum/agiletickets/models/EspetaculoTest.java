package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	@Test
	public void DeveCriarUmaSessaoSeDataFinalIgualDataInicial() {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate today = new LocalDate();
		LocalTime hora = new LocalTime().plusHours(3);
		List<Sessao> sessoes = espetaculo.criaSessoes(today,today, hora, Periodicidade.DIARIA);
		int tamanho = sessoes.size();
		assertTrue(tamanho == 1);
	}
	
	
	@Test
	public void naoDeveCriarSessaoDataInicioMaiorDataFim() {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate today = new LocalDate();
		LocalTime hora = new LocalTime().plusHours(3);
		List<Sessao> sessoes = espetaculo.criaSessoes(today,today.minusDays(1), hora, Periodicidade.DIARIA);
		
		assertTrue(sessoes == null);
	}
	
	@Test
	public void deveCriarSessoesSemanamente() {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate today = new LocalDate();
		LocalTime hora = new LocalTime().plusHours(3);
		// validar se 3 sessoes serao criadas
		List<Sessao> sessoes = espetaculo.criaSessoes(today,today.plusDays(20), hora, Periodicidade.SEMANAL);
		
		assertTrue(sessoes.size() == 3);
	}
	
	@Test
	public void deveCriarSessoesDiariamente() {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate today = new LocalDate();
		LocalTime hora = new LocalTime().plusHours(3);
		// validar se 3 sessoes serao criadas
		List<Sessao> sessoes = espetaculo.criaSessoes(today,today.plusDays(2), hora, Periodicidade.DIARIA);
		
		assertTrue(sessoes.size() == 3);
	}
	
	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
