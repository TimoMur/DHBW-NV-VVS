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

import de.dhbw.DHBWVVS.model.location.GeoPosition;
import de.dhbw.DHBWVVS.model.location.Location;
import de.dhbw.DHBWVVS.model.location.Mode;
import de.dhbw.DHBWVVS.model.location.Point;
import de.dhbw.DHBWVVS.model.location.PointOfInterest;
import de.dhbw.DHBWVVS.model.location.StopPoint;

public class LocationResponse {

	public static List<Location> process(String responseText) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(responseText));
		Document doc = builder.parse(is);

		NodeList locationInformationResponse = doc.getElementsByTagName("LocationInformationResponse");

		if (locationInformationResponse.getLength() != 1) {
			throw new Exception("Less or more than one LocationInformationResponse-Element");
		}

		Node locationInformationNode = locationInformationResponse.item(0);

		NodeList locationsNodes = locationInformationNode.getChildNodes();

		List<Location> locations = new ArrayList<Location>();

		for (int i = 0; i < locationsNodes.getLength(); i++) {
			NodeList c = locationsNodes.item(i).getChildNodes();

			Location location = new Location();
			List<Mode> modes = new ArrayList<Mode>();

			for (int j = 0; j < c.getLength(); j++) {

				Node n = c.item(j);

				if (n.getNodeName().equalsIgnoreCase("Probability")) {
					location.setProbability(Double.parseDouble(n.getTextContent()));
				} else if (n.getNodeName().equalsIgnoreCase("Complete")) {
					location.setComplete(Boolean.parseBoolean(n.getTextContent()));
				} else if (n.getNodeName().equalsIgnoreCase("Location")) {
					location = location(location, n);
				} else if (n.getNodeName().equalsIgnoreCase("Mode")) {
					NodeList modeElements = n.getChildNodes();
					Mode mode = new Mode();
					List<String> submodes = new ArrayList<String>();
					for (int k = 0; k < modeElements.getLength(); k++) {
						Node modeNode = modeElements.item(k);
						if (modeNode.getNodeName().equalsIgnoreCase("PtMode")) {
							mode.setName(modeNode.getTextContent());
						} else if (modeNode.getNodeName().endsWith("Submode")) {
							submodes.add(modeNode.getTextContent());
						}
					}
					mode.setSubmodes(submodes);
					modes.add(mode);
				}
				location.setModes(modes);

			}
			locations.add(location);

		}

		return locations;

	}

	private static Location location(Location location, Node nLocation) {

		NodeList nodeList = nLocation.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {

			Node n = nodeList.item(i);

			if (n.getNodeName().equalsIgnoreCase("GeoPosition")) {
				GeoPosition geoPosition = new GeoPosition();
				geoPosition.setLongitude(Double.parseDouble(n.getChildNodes().item(0).getTextContent()));
				geoPosition.setLatitude(Double.parseDouble(n.getChildNodes().item(1).getTextContent()));
				location.setGeoposition(geoPosition);
			} else if (n.getNodeName().equalsIgnoreCase("LocationName")) {
				NodeList nLocationName = n.getChildNodes();
				location.setName(nLocationName.item(0).getTextContent());
				location.setLanguage(nLocationName.item(1).getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("StopPoint")) {
				location.setPoint(stoppoint(n));
			} else if(n.getNodeName().equalsIgnoreCase("PointOfInterest")) {
				PointOfInterest poi = new PointOfInterest();
				poi.setType("PointOfInterest");
				location.setPoint(poi);
			}
		}
		return location;
	}

	private static Point stoppoint(Node n) {

		StopPoint stopPoint = new StopPoint();
		stopPoint.setType("StopPoint");
		
		NodeList list = n.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if(node.getNodeName().equalsIgnoreCase("StopPointRef")) {
				stopPoint.setRef(node.getTextContent());
			} else if(node.getNodeName().equalsIgnoreCase("LocalityRef")) {
				stopPoint.setLocalityRef(node.getTextContent());
			} else if(node.getNodeName().equalsIgnoreCase("StopPointName")) {
				stopPoint.setText(node.getChildNodes().item(0).getTextContent());
				stopPoint.setLanguage(node.getChildNodes().item(1).getTextContent());
			}
		}

		return stopPoint;
	}

}
