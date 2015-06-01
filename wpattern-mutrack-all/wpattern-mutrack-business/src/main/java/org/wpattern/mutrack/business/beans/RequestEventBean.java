package org.wpattern.mutrack.business.beans;

import org.wpattern.mutrack.utils.BaseBean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("evento")
public class RequestEventBean extends BaseBean {

	private static final long serialVersionUID = 201505301552L;

	@XStreamAlias("tipo")
	private String tipo;

	@XStreamAlias("status")
	private Integer status;

	@XStreamAlias("data")
	private String date;

	@XStreamAlias("hora")
	private String hour;

	@XStreamAlias("descricao")
	private String description;

	@XStreamAlias("recebedor")
	private String receiver;

	@XStreamAlias("documento")
	private String receiverDocument;

	@XStreamAlias("comentario")
	private String comment;

	@XStreamAlias("location")
	private String location;

	@XStreamAlias("codigo")
	private String cep;

	@XStreamAlias("cidade")
	private String city;

	@XStreamAlias("uf")
	private String uf;

	@XStreamAlias("sto")
	private String sto;

	public RequestEventBean() {
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return this.hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverDocument() {
		return this.receiverDocument;
	}

	public void setReceiverDocument(String receiverDocument) {
		this.receiverDocument = receiverDocument;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getSto() {
		return this.sto;
	}

	public void setSto(String sto) {
		this.sto = sto;
	}

}
