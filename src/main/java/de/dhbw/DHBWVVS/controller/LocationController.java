package de.dhbw.DHBWVVS.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import de.dhbw.DHBWVVS.model.location.LocationDAO;
import de.dhbw.DHBWVVS.model.request.RequestPayload;
import de.dhbw.DHBWVVS.model.request.Trias;
import de.dhbw.DHBWVVS.model.request.location.InitialInput;
import de.dhbw.DHBWVVS.model.request.location.LocationInformationRequest;
import de.dhbw.DHBWVVS.model.request.location.Restrictions;
import de.dhbw.DHBWVVS.model.request.location.ServiceRequest;
import de.dhbw.DHBWVVS.model.response.LocationResponse;

@RestController
@RequestMapping("/location")
public class LocationController {

	@Value("${ref}")
	private String ref;
	@Value("${url}")
	private String url;

	@GetMapping
	public List<LocationDAO> getLocations(@RequestParam(name = "name", required = true) String name) {
		Trias trias = new Trias();

		InitialInput initialInput = new InitialInput();
		initialInput.setLocationName(name);
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

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Trias.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(trias, sw);

			System.out.println(sw.toString());

			HttpClient client = HttpClient.newHttpClient();

			// @formatter:off
			HttpRequest requestHttp = HttpRequest
					.newBuilder(URI.create(url))
					.header("Content-Type", "text/xml")
					.POST(BodyPublishers.ofString(sw.toString()))
					.build();
			// @formatter:on

			HttpResponse<String> response = client.send(requestHttp, BodyHandlers.ofString());

			return LocationResponse.process(response.body());

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
