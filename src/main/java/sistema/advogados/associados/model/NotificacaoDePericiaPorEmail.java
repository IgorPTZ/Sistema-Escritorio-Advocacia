package sistema.advogados.associados.model;

import java.io.Serializable;

public class NotificacaoDePericiaPorEmail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long flagTipoDeNotificacao;
	
	private Cliente cliente;
	
	private Processo processo;
	
	private Pericia pericia;

	public Long getFlagTipoDeNotificacao() {
		return flagTipoDeNotificacao;
	}

	public void setFlagTipoDeNotificacao(Long flagTipoDeNotificacao) {
		this.flagTipoDeNotificacao = flagTipoDeNotificacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Pericia getPericia() {
		return pericia;
	}

	public void setPericia(Pericia pericia) {
		this.pericia = pericia;
	}
}
