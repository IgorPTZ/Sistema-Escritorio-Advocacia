package sistema.advogados.associados.util;

import java.io.Serializable;

public class PesquisaDeClientes implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String textoDePesquisa;

	public String getTextoDePesquisa() {
		return textoDePesquisa;
	}

	public void setTextoDePesquisa(String textoDePesquisa) {
		this.textoDePesquisa = textoDePesquisa;
	}
}
