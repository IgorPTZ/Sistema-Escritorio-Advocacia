package sistema.advogados.associados.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pericia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Processo processo;
	
	@Column(nullable = false)
	private LocalDate dataDaPericia;
	
	@Column(nullable = false)
	private String horario;
	
	@Column(nullable = false)
	private String endereco;
	
	@Column(nullable = false)
	private String telefone;
	
	@Column(nullable = false)
	private String nomePerito;

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

	public LocalDate getDataDaPericia() {
		return dataDaPericia;
	}

	public void setDataDaPericia(LocalDate dataDaPericia) {
		this.dataDaPericia = dataDaPericia;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomePerito() {
		return nomePerito;
	}

	public void setNomePerito(String nomePerito) {
		this.nomePerito = nomePerito;
	}
}
