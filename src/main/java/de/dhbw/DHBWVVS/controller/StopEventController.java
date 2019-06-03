package de.dhbw.DHBWVVS.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

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

import de.dhbw.DHBWVVS.model.departure.DepartureDAO;
import de.dhbw.DHBWVVS.model.request.RequestPayload;
import de.dhbw.DHBWVVS.model.request.Trias;
import de.dhbw.DHBWVVS.model.request.departure.Location;
import de.dhbw.DHBWVVS.model.request.departure.LocationRef;
import de.dhbw.DHBWVVS.model.request.departure.Parameter;
import de.dhbw.DHBWVVS.model.request.departure.StopEventRequest;
import de.dhbw.DHBWVVS.model.request.departure.StopEventType;
import de.dhbw.DHBWVVS.model.request.location.ServiceRequest;
import de.dhbw.DHBWVVS.model.response.DepartureResponse;

@RestController
@RequestMapping("/departure")
public class StopEventController {

	@Value("${ref}")
	private String ref;
	@Value("${url}")
	private String url;

	/*
	 * Long term: GET -> POST Parameter taken as JSON data in HTTP-Request-Body
	 */
	@GetMapping
	public DepartureDAO departures(@RequestParam(name = "locRef", required = true) String locRef) {

		Trias trias = new Trias();

		StopEventRequest request = new StopEventRequest();
		Location location = new Location();
		LocationRef locationRef = new LocationRef(locRef);
		location.setLocationRef(locationRef);

		/*
		 * Request parameter: - Departure - Max. 20 results - Realtime data should be
		 * included
		 */
		Parameter params = new Parameter();
		params.setIncludeRealtimeData(true);
		params.setNumberOfResults(20);
		params.setStopEventType(StopEventType.DEPARTURE.toString());

		/*
		 *
		 * TODO: Filter need to be implemented // List<PtModeFilter> ptModeFilter = new
		 * ArrayList<PtModeFilter>(); // PtModeFilter filter = new PtModeFilter(); //
		 * filter.setExclude(false); // filter.setPtMode(PtMode.RAIL); //
		 * params.setPtModeFilter(ptModeFilter);
		 * 
		 */

		request.setLocation(location);
		request.setParams(params);

		/*
		 * TODO: Builder Pattern for Request
		 */
		RequestPayload requestPayload = new RequestPayload();
		requestPayload.setRequest(request);

		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setRequestorRef(ref);
		serviceRequest.setRequestTimestamp("2019-05-24T16:00:00");
		serviceRequest.setPayload(requestPayload);
		trias.setRequest(serviceRequest);

		try {
			JAXBContext jaxbContext;
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
			
			return DepartureResponse.process(response.body());
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
//		} catch (SAXException e) {
//			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
