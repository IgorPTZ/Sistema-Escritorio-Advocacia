package sistema.advogados.associados.model;

import java.io.Serializable;

public class NotificacaoDeAudienciaPorEmail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long flagTipoDeNotificacao;
	
	private Long clienteId;
	
	private Long processoId;
	
	private Long audienciaId;
	
	private String observacao;

	public Long getFlagTipoDeNotificacao() {
		return flagTipoDeNotificacao;
	}

	public void setFlagTipoDeNotificacao(Long flagTipoDeNotificacao) {
		this.flagTipoDeNotificacao = flagTipoDeNotificacao;
	}
	
	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getProcessoId() {
		return processoId;
	}

	public void setProcessoId(Long processoId) {
		this.processoId = processoId;
	}
	
	public Long getAudienciaId() {
		return audienciaId;
	}

	public void setAudienciaId(Long audienciaId) {
		this.audienciaId = audienciaId;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
