package sistema.advogados.associados.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Audiencia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Processo processo;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDaAudiencia;
	
	@Column(nullable = false)
	private String horario;
	
	@Column(nullable = false)
	private String vara;
	
	@Column(nullable = false)
	private String endereco;
	
	@Column(name = "flag_precatoria", nullable = false)
	private Boolean flagPrecatoria;
	
	@Column(name = "numero_processo_gerado")
	private String numeroDoProcessoGerado;
	
	@Column(name = "testemunhas")
	private String testemunhas;
	
	@Column(nullable = false)
	private String observacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public LocalDate getDataDaAudiencia() {
		return dataDaAudiencia;
	}

	public void setDataDaAudiencia(LocalDate dataDaAudiencia) {
		this.dataDaAudiencia = dataDaAudiencia;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getVara() {
		return vara;
	}

	public void setVara(String vara) {
		this.vara = vara;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Boolean getFlagPrecatoria() {
		return flagPrecatoria;
	}

	public void setFlagPrecatoria(Boolean flagPrecatoria) {
		this.flagPrecatoria = flagPrecatoria;
	}

	public String getNumeroDoProcessoGerado() {
		return numeroDoProcessoGerado;
	}

	public void setNumeroDoProcessoGerado(String numeroDoProcessoGerado) {
		this.numeroDoProcessoGerado = numeroDoProcessoGerado;
	}

	public String getTestemunhas() {
		return testemunhas;
	}

	public void setTestemunhas(String testemunhas) {
		this.testemunhas = testemunhas;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
