package de.dhbw.DHBWVVS.model.response;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import de.dhbw.DHBWVVS.model.departure.DepartureDAO;
import de.dhbw.DHBWVVS.model.departure.PtSituation;
import de.dhbw.DHBWVVS.model.departure.StopEvent;

public class DepartureResponse {
	
	public DepartureDAO process(String responseText) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(responseText));
		Document doc = builder.parse(is);
		
		NodeList stopEventResponse = doc.getElementsByTagName("StopEventResponse");
		
		if (stopEventResponse.getLength() != 1) {
			throw new Exception("Less or more than one StopEventResponse-Element");
		}
		
		NodeList responseChildren = stopEventResponse.item(0).getChildNodes();
		
		DepartureDAO departure = new DepartureDAO();
		List<PtSituation> situations;
		List<StopEvent> stopEvents = new ArrayList<>();
		
		for (int i = 0; i < responseChildren.getLength(); i++) {
			
			Node n = responseChildren.item(i);
			
			if(n.getNodeName().equalsIgnoreCase("StopEventResponseContext")) {
				situations = situations(n);
			} else if(n.getNodeName().equalsIgnoreCase("StopEventResult")) {
				stopEvents.add(stopEvent(n));
			}
			
		}
		
		return null;
	}

	private StopEvent stopEvent(Node n) {
		List<PtSituation> situations = new ArrayList<PtSituation>();
		
		NodeList situationsNodes = n.getChildNodes().item(0).getChildNodes();
		
		for (int i = 0; i < situationsNodes.getLength(); i++) {
			
		}
		
		return null;
	}

	private List<PtSituation> situations(Node n) {
		return null;
	}
	
}
