package org.wpattern.mutrack.business.beans;

import java.util.List;

import org.wpattern.mutrack.utils.BaseBean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("sroxml")
public class RequestBean extends BaseBean {

	private static final long serialVersionUID = 201505301521L;

	@XStreamAlias("versao")
	private String version;

	@XStreamAlias("qtd")
	private Integer quantity;

	@XStreamAlias("TipoPesquisa")
	private String typeTrack;

	@XStreamAlias("TipoResultado")
	private String typeResult;

	@XStreamImplicit
	private List<RequestObjectBean> object;

	public RequestBean() {
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getTypeTrack() {
		return this.typeTrack;
	}

	public void setTypeTrack(String typeTrack) {
		this.typeTrack = typeTrack;
	}

	public String getTypeResult() {
		return this.typeResult;
	}

	public void setTypeResult(String typeResult) {
		this.typeResult = typeResult;
	}

	public List<RequestObjectBean> getObject() {
		return this.object;
	}

	public void setObject(List<RequestObjectBean> object) {
		this.object = object;
	}

}
