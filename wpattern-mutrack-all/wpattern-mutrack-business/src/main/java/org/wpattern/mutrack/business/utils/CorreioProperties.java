package org.wpattern.mutrack.business.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:business.properties")
public class CorreioProperties {

	@Value("${correio.usuario}")
	private String correioUsuario;

	@Value("${correio.senha}")
	private String correioSenha;

	public CorreioProperties() {
	}

	public String getCorreioUsuario() {
		return this.correioUsuario;
	}
	public String getCorreioSenha() {
		return this.correioSenha;
	}

}
