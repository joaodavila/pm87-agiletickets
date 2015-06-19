package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco = sessao.getPreco();
		TipoDeEspetaculo tipoEspetaculo = sessao.getEspetaculo().getTipo();
		
		boolean cinema = tipoEspetaculo.equals(TipoDeEspetaculo.CINEMA);
		boolean show = tipoEspetaculo.equals(TipoDeEspetaculo.SHOW);
		boolean orquestra = tipoEspetaculo.equals(TipoDeEspetaculo.ORQUESTRA);
		boolean ballet = tipoEspetaculo.equals(TipoDeEspetaculo.BALLET) ;
		
		if(cinema || show) {
			preco = calculaPrecoCinemaEShow(sessao, preco); 
		} else if(ballet || orquestra )  {
			preco = calculaPrecoBalletEOrquestra(sessao, preco);
		} 
		
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calculaPrecoBalletEOrquestra(Sessao sessao,
			BigDecimal preco) {
		//quando estiver acabando os ingressos... 
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
		} 
		
		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

	private static BigDecimal calculaPrecoCinemaEShow(Sessao sessao,
			BigDecimal preco) {
		//quando estiver acabando os ingressos... 
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.05) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

}