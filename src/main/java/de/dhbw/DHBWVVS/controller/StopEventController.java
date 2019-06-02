package de.dhbw.DHBWVVS.controller;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.dhbw.DHBWVVS.model.request.RequestPayload;
import de.dhbw.DHBWVVS.model.request.Trias;
import de.dhbw.DHBWVVS.model.request.departure.Location;
import de.dhbw.DHBWVVS.model.request.departure.LocationRef;
import de.dhbw.DHBWVVS.model.request.departure.Parameter;
import de.dhbw.DHBWVVS.model.request.departure.StopEventRequest;
import de.dhbw.DHBWVVS.model.request.departure.StopEventType;
import de.dhbw.DHBWVVS.model.request.location.ServiceRequest;

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
	public String departures(@RequestParam(name = "locRef", required = true) String locRef) {

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
		params.setStopEventType(StopEventType.DEPARTURE);

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
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}

}
