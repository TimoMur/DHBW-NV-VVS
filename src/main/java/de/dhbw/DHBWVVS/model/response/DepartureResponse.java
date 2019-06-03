package de.dhbw.DHBWVVS.model.response;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import de.dhbw.DHBWVVS.model.departure.Affect;
import de.dhbw.DHBWVVS.model.departure.AffectedStopPoint;
import de.dhbw.DHBWVVS.model.departure.AffectedVehicleJourney;
import de.dhbw.DHBWVVS.model.departure.DepartureDAO;
import de.dhbw.DHBWVVS.model.departure.PtSituation;
import de.dhbw.DHBWVVS.model.departure.StopEvent;
import de.dhbw.DHBWVVS.model.departure.ThisCall;
import de.dhbw.DHBWVVS.model.departure.VVSService;
import de.dhbw.DHBWVVS.model.departure.ValidityPeriod;
import de.dhbw.DHBWVVS.model.location.Mode;

/**
 * @author timom
 *
 */
public class DepartureResponse {

	public static DepartureDAO process(String responseText) throws Exception {
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
		List<PtSituation> situations = null;
		List<StopEvent> stopEvents = new ArrayList<StopEvent>();

		for (int i = 0; i < responseChildren.getLength(); i++) {

			Node n = responseChildren.item(i);

			if (n.getNodeName().equalsIgnoreCase("StopEventResponseContext")) {
				situations = situations(n);
			} else if (n.getNodeName().equalsIgnoreCase("StopEventResult")) {
				stopEvents.add(stopEvent(n));
			}

		}

		departure.setSituations(situations);
		departure.setStopEvents(stopEvents);

		return departure;
	}

	private static StopEvent stopEvent(Node stopEventResult) {

		StopEvent stopEvent = new StopEvent();

		NodeList resultChilds = stopEventResult.getChildNodes();

		for (int i = 0; i < resultChilds.getLength(); i++) {
			Node n = resultChilds.item(i);

			if (n.getNodeName().equalsIgnoreCase("ResultId")) {
				stopEvent.setId(n.getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("StopEvent")) {
				stopEvent = fillEvent(stopEvent, n);
			}

		}

		return stopEvent;
	}

	private static StopEvent fillEvent(StopEvent stopEvent, Node stopEventNode) {

		NodeList stopEventChilds = stopEventNode.getChildNodes();

		for (int i = 0; i < stopEventChilds.getLength(); i++) {
			Node n = stopEventChilds.item(i);

			if (n.getNodeName().equalsIgnoreCase("ThisCall")) {
				stopEvent.setThisCall(thisCall(n));
			} else if (n.getNodeName().equalsIgnoreCase("Service")) {
				stopEvent.setService(service(n));
			}

		}

		return stopEvent;
	}

	private static ThisCall thisCall(Node thisCallNode) {

		ThisCall thisCall = new ThisCall();
		NodeList callAtStopChilds = thisCallNode.getChildNodes().item(0).getChildNodes();

		for (int i = 0; i < callAtStopChilds.getLength(); i++) {
			Node n = callAtStopChilds.item(i);

			if (n.getNodeName().equalsIgnoreCase("StopPointRef")) {
				thisCall.setStopPointRef(n.getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("StopPointName")) {
				thisCall.setStopPointName(n.getChildNodes().item(0).getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("PlannedBay")) {
				thisCall.setPlannedBy(n.getChildNodes().item(0).getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("ServiceDeparture")) {
				DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
				LocalDateTime timetabledTime = LocalDateTime.parse(n.getChildNodes().item(0).getTextContent(),
						formatter);
				thisCall.setTimetabledTime(timetabledTime);
				if(n.getChildNodes().item(1) != null) {
				LocalDateTime estimatedTime = LocalDateTime.parse(n.getChildNodes().item(1).getTextContent(),
						formatter);
					thisCall.setEstimatedTime(estimatedTime);
				}
			} else if (n.getNodeName().equalsIgnoreCase("SituationFullRef")) {
				thisCall.setParticipantRef(n.getChildNodes().item(0).getTextContent());
				thisCall.setSituationNumber(Integer.parseInt(n.getChildNodes().item(1).getTextContent()));
			}
		}

		return thisCall;
	}

	private static VVSService service(Node serviceNode) {

		VVSService vvsService = new VVSService();
		NodeList serviceChilds = serviceNode.getChildNodes();

		for (int i = 0; i < serviceChilds.getLength(); i++) {

			Node n = serviceChilds.item(i);

			if (n.getNodeName().equalsIgnoreCase("OperatingDayRef")) {
				vvsService.setOperatingDayRef(n.getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("JourneyRef")) {
				vvsService.setJourneyRef(n.getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("LineRef")) {
				vvsService.setLineRef(n.getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("PublishedLineName")) {
				vvsService.setPublishedLineName(n.getChildNodes().item(0).getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("OperatorRef")) {
				vvsService.setOperatorRef(n.getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("RouteDescription")) {
				vvsService.setRouteDescription(n.getChildNodes().item(0).getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("OriginStopPointRef")) {
				vvsService.setOriginStopPointRef(n.getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("OriginText")) {
				vvsService.setOriginText(n.getChildNodes().item(0).getTextContent());
				vvsService.setLanguage(n.getChildNodes().item(1).getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("DestinationText")) {
				vvsService.setDestinationText(n.getChildNodes().item(0).getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("DestinationStopPointRef")) {
				vvsService.setDestinationStopPointRef(n.getTextContent());
			} else if (n.getNodeName().equalsIgnoreCase("Mode")) {
				Mode mode = new Mode();
				mode.setName(n.getChildNodes().item(0).getTextContent());
				ArrayList<String> submodes = new ArrayList<String>();
				submodes.add(n.getChildNodes().item(2).getTextContent());
				mode.setSubmodes(submodes);
				vvsService.setMode(mode);
				vvsService.setModeName(n.getChildNodes().item(n.getChildNodes().getLength() - 1).getChildNodes().item(0)
						.getTextContent());
			}
		}

		return vvsService;
	}

	private static List<PtSituation> situations(Node n) {
		List<PtSituation> situations = new ArrayList<PtSituation>();

		NodeList situationsNodes = n.getChildNodes().item(0).getChildNodes();

		for (int i = 0; i < situationsNodes.getLength(); i++) {
			PtSituation situation = new PtSituation();
			NodeList situationChildNodes = situationsNodes.item(i).getChildNodes();
			for (int j = 0; j < situationChildNodes.getLength(); j++) {
				Node sitAttributeNode = situationChildNodes.item(j);

				if (sitAttributeNode.getNodeName().equalsIgnoreCase("CreationTime")) {
					situation.setCreationTime(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("ParticipantRef")) {
					situation.setParticipantRef(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("SituationNumber")) {
					situation.setSituationNumber(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("Version")) {
					situation.setVersion(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("Source")) {
					situation.setSource(source(sitAttributeNode));
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("Progress")) {
					situation.setProgress(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("ValidityPeriod")) {
					situation.setValidityPeriod(validityPeriod(sitAttributeNode));
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("UnknownReason")) {
					situation.setUnknownReason(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("Priority")) {
					situation.setPriority(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("Audience")) {
					situation.setAudience(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("ScopeType")) {
					situation.setScopeType(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("Planned")) {
					situation.setPlanned(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("Language")) {
					situation.setLanguage(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("Summary")) {
					situation.setSummary(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("Description")) {
					situation.setDescription(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("Detail")) {
					situation.setDetail(sitAttributeNode.getTextContent());
				} else if (sitAttributeNode.getNodeName().equalsIgnoreCase("Affects")) {
					situation.setAffects(affects(sitAttributeNode));
				}

			}

			situations.add(situation);

		}

		return situations;
	}

	private static List<Affect> affects(Node affects) {

		NodeList affectChilds = affects.getChildNodes();

		List<Affect> affectsList = new ArrayList<Affect>();

		for (int i = 0; i < affectChilds.getLength(); i++) {

			Node affectedNode = affectChilds.item(i);
			if (affectedNode.getNodeName().equalsIgnoreCase("VehicleJourneys")) {
				NodeList vehicleJourneys = affectedNode.getChildNodes();
				for (int j = 0; j < vehicleJourneys.getLength(); j++)
					affectsList.add(vehicleJourney(vehicleJourneys.item(j)));

			} else if (affectedNode.getNodeName().equalsIgnoreCase("StopPoints")) {
				NodeList stopPoints = affectedNode.getChildNodes();
				for (int j = 0; j < stopPoints.getLength(); j++)
					affectsList.add(affectedStopPoint(stopPoints.item(j)));
			}

		}

		return affectsList;
	}

	/**
	 * @param vehicleJourneysNode AffectedVehicleJourney Node
	 * @return AffectedVehicleJourney Object
	 */
	private static AffectedVehicleJourney vehicleJourney(Node vehicleJourneysNode) {
		NodeList vjChilds = vehicleJourneysNode.getChildNodes();

		AffectedVehicleJourney affectedVehicleJourney = new AffectedVehicleJourney();

		for (int i = 0; i < vjChilds.getLength(); i++) {
			Node node = vjChilds.item(i);
			if (node.getNodeName().equalsIgnoreCase("LineRef")) {
				affectedVehicleJourney.setLineRef(node.getTextContent());
			} else if (node.getNodeName().equalsIgnoreCase("Route")) {
				affectedVehicleJourney.setRoute(node.getTextContent());
			}
		}
		return affectedVehicleJourney;
	}

	/**
	 * @param affectedStopPointNode AffectedStopPoint Node
	 * @return AffectedStopPoint Object
	 */
	private static AffectedStopPoint affectedStopPoint(Node affectedStopPointNode) {

		NodeList aspChilds = affectedStopPointNode.getChildNodes();

		AffectedStopPoint affectedStopPoint = new AffectedStopPoint();

		for (int i = 0; i < aspChilds.getLength(); i++) {
			Node node = aspChilds.item(i);
			if (node.getNodeName().equalsIgnoreCase("StopPointRef")) {
				affectedStopPoint.setStopPointRef(node.getTextContent());
			}
		}
		return affectedStopPoint;
	}

	private static String source(Node sourceNode) {
		return sourceNode.getChildNodes().item(0).getTextContent();
	}

	private static ValidityPeriod validityPeriod(Node validityPeriodNode) {

		ValidityPeriod validityPeriod = new ValidityPeriod();

		NodeList childs = validityPeriodNode.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node node = childs.item(i);
			if (node.getNodeName().equalsIgnoreCase("StartTime")) {
				validityPeriod.setStartTime(node.getTextContent());
			} else if (node.getNodeName().equalsIgnoreCase("EndTime")) {
				validityPeriod.setEndTime(node.getTextContent());
			}
		}

		return validityPeriod;
	}

}
