package br.com.comexport.beans;

import java.io.Serializable;
import java.util.Date;

public class ContabilBean implements Serializable {

	private static final long serialVersionUID = -5896677934978885798L;

	private Integer id;
	private Integer contaContabil;
	private Date data;
	private Double valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(Integer contaContabil) {
		this.contaContabil = contaContabil;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
