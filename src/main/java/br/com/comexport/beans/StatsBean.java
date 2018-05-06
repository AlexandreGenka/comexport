package br.com.comexport.beans;

import java.io.Serializable;

public class StatsBean implements Serializable {

	private static final long serialVersionUID = 2927559193758325285L;

	private Double soma;
	private Double min;
	private Double media;
	private Double max;
	private Integer qtde;

	public Double getSoma() {
		return soma;
	}

	public void setSoma(Double soma) {
		this.soma = soma;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

}
