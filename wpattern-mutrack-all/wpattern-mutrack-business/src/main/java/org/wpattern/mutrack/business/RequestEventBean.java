package org.wpattern.mutrack.business;

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

}
