/*
Borse, Akanksha
APCS-A
Period 5
PSET5-0
Spring Project
 */

package borse.five.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/* is a helper as well as a wrapper class for the list of people - wraps the
personList into an outer class that can be decorated with @XmlRootElement */
@XmlRootElement(name = "events")
public class EventListWrapper {

	private List<Event> events;

	public EventListWrapper(){
	}

	@XmlElement(name = "event")
	public List<Event> getEvents(){
		return events;
	}

	public void setEvents(List<Event> events){
		this.events = events;
	}
}
