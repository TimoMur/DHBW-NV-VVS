package de.dhbw.DHBWVVS;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import de.dhbw.DHBWVVS.model.request.InitialInput;
import de.dhbw.DHBWVVS.model.request.LocationInformationRequest;
import de.dhbw.DHBWVVS.model.request.RequestPayload;
import de.dhbw.DHBWVVS.model.request.Restrictions;
import de.dhbw.DHBWVVS.model.request.ServiceRequest;
import de.dhbw.DHBWVVS.model.request.Trias;

@SpringBootApplication
public class DhbwVvsApplication implements CommandLineRunner {

	@Value("${ref}")
	private String ref;
	@Value("${url}")
	private String url;
	
	public static void main(String[] args) {
		SpringApplication.run(DhbwVvsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Trias trias = new Trias();

		InitialInput initialInput = new InitialInput();
		initialInput.setLocationName("Stadtmitte");
		Restrictions restrictions = new Restrictions();
		restrictions.setIncludePtModes(true);

		LocationInformationRequest request = new LocationInformationRequest();
		request.setInitialInput(initialInput);
		request.setRestrictions(restrictions);

		RequestPayload payload = new RequestPayload();
		payload.setRequest(request);

		ServiceRequest req = new ServiceRequest();
		req.setRequestorRef(ref);
		req.setRequestTimestamp("2019-04-05T12:00:00");
		req.setPayload(payload);

		trias.setRequest(req);

		JAXBContext jaxbContext = JAXBContext.newInstance(Trias.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(trias, sw);

		System.out.println(sw.toString());

		HttpClient client = HttpClient.newHttpClient();

		// @formatter:off
		HttpRequest requestHttp = HttpRequest.newBuilder(URI.create(url))
				.header("Content-Type", "text/xml")
				.POST(BodyPublishers.ofString(sw.toString()))
				.build();
		// @formatter:on

		HttpResponse<String> response = client.send(requestHttp, BodyHandlers.ofString());
		
		System.out.println(response.body());
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(response.body()));
	    Document doc = builder.parse(is);
	    
	    NodeList list = doc.getElementsByTagName("PtMode");
	    for (int i = 0; i < list.getLength(); i++) {
			System.out.println(list.item(i));
		}
		
	}

}
