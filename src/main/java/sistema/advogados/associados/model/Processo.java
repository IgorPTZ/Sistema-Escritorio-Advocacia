package sistema.advogados.associados.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Processo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String numero;

	@ManyToOne
	private SituacaoDoProcesso situacao;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "processo_reu", 
	           joinColumns = @JoinColumn(name = "processo_id"), 
	           inverseJoinColumns = @JoinColumn(name = "reu_id"))
	private List<Reu> reus;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "processo_cliente", 
	           joinColumns = @JoinColumn(name = "processo_id"), 
	           inverseJoinColumns = @JoinColumn(name = "cliente_id"))
	private List<Cliente> clientes;

	private String observacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public SituacaoDoProcesso getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoDoProcesso situacao) {
		this.situacao = situacao;
	}

	public List<Reu> getReus() {
		return reus;
	}

	public void setReus(List<Reu> reus) {
		this.reus = reus;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
