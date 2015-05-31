package org.wpattern.mutrack.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.business.beans.RequestBean;
import org.wpattern.mutrack.business.beans.RequestEventBean;
import org.wpattern.mutrack.business.beans.RequestObjectBean;
import org.wpattern.mutrack.business.utils.CorreioProperties;
import org.wpattern.mutrack.utils.business.ITracker;
import org.wpattern.mutrack.utils.business.beans.PackageBean;
import org.wpattern.mutrack.utils.business.beans.PackageEventBean;
import org.wpattern.mutrack.utils.business.exceptions.ParserException;

import com.thoughtworks.xstream.XStream;

@Component
public class Tracker implements ITracker {

	private static final String URL_PATH = "http://websro.correios.com.br/sro_bin/sroii_xml.eventos";

	private static final String DATE_PATTERN = "dd/MM/yyyy hh:mm";

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

	private XStream xStream;

	@Inject
	private CorreioProperties correioProperties;

	public Tracker() {
		this.xStream = new XStream();
		this.xStream.processAnnotations(RequestBean.class);
		this.xStream.ignoreUnknownElements();
	}

	@Override
	public List<PackageBean> track(List<String> codes) {
		String concatCodes = "";

		for (String code : codes) {
			concatCodes += code;
		}

		String params = String.format("usuario=%s&senha=%s&tipo=L&resultado=T&objetos=%s",
				this.correioProperties.getCorreioUsuario(), this.correioProperties.getCorreioSenha(), concatCodes);

		String requestResult = this.request(params);

		return this.convertToPackage(this.parseRequest(requestResult));
	}

	private RequestBean parseRequest(String requestResult) {
		RequestBean requestBean = null;

		try {
			requestBean = (RequestBean) this.xStream.fromXML(requestResult);
		} catch (Exception e) {
			throw new ParserException(e.getMessage());
		}

		if (this.LOGGER.isInfoEnabled()) {
			this.LOGGER.info(requestBean);
		}

		return requestBean;
	}

	private List<PackageBean> convertToPackage(RequestBean requestBean) {
		List<PackageBean> packages = new ArrayList<PackageBean>();

		try {
			for (RequestObjectBean obj : requestBean.getObject()) {
				PackageBean pack = new PackageBean();

				pack.setPackageCode(obj.getPackageCode());

				for (RequestEventBean objEvent : obj.getEvents()) {
					PackageEventBean event = new PackageEventBean();

					event.setStatus(objEvent.getStatus());
					event.setDate(this.DATE_FORMAT.parse(objEvent.getDate() + " " + objEvent.getHour()));
					event.setDescription(objEvent.getDescription());
					event.setComment(objEvent.getComment());
					event.setLocation(objEvent.getLocation());
					event.setCepUnity(objEvent.getCep());
					event.setCity(objEvent.getCity());
					event.setUf(objEvent.getUf());

					pack.addEvent(event);
				}

				packages.add(pack);
			}
		} catch (ParseException e) {
			this.LOGGER.error(e.getMessage(), e);
			throw new ParserException(e.getMessage(), e);
		}

		return packages ;
	}

	private String request(String params) {
		StringBuilder strBuilder = new StringBuilder();

		try {
			URL url = new URL(URL_PATH);
			URLConnection connection = url.openConnection();

			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setAllowUserInteraction(false);

			PrintStream outStream = new PrintStream(connection.getOutputStream());

			outStream.println(params);
			outStream.close();

			BufferedReader subProcessInputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line = null;

			while ((line = subProcessInputReader.readLine()) != null) {
				strBuilder.append(line + "\n");
			}
		} catch (MalformedURLException e) {
			this.LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			this.LOGGER.error(e.getMessage(), e);
		}

		return strBuilder.toString();
	}

}
