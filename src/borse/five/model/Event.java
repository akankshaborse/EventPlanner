/*
Borse, Akanksha
APCS-A
Period 5
PSET5-0
Spring Project
 */

package borse.five.model;

import javax.xml.bind.annotation.XmlSeeAlso;

/* adapter classes between java bean properties 
and javafx properties */
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;


/* grand-children of the above adapter classes, that provide full implementation 
of properties in JavaFX */
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;



/* model class for an event */
@XmlSeeAlso({Event.class})
public class Event {

	private final StringProperty weddingName; 		 /* field for name of wedding */
	private final DoubleProperty weddingPrice;		 /* field for price of wedding */
	private final IntegerProperty guestCount;		 /* field for number of guests */


	public Event() {
		this(null, null, null);
	}

	public Event(String weddingName, Integer weddingPrice, Integer guestCount){
		this.weddingName = new SimpleStringProperty(weddingName);
		this.weddingPrice = new SimpleDoubleProperty(weddingPrice);
		this.guestCount = new SimpleIntegerProperty(guestCount);
	}

	/* property getter */
	public String getWeddingName() {
		return weddingName.get();
	}

	/* property setter */
	public void setWeddingName(String weddingName){
		this.weddingName.set(weddingName);
	}
	/* property getter */
	public Double getWeddingPrice() {
		return weddingPrice.get();
	}

	/* property setter */
	public void setWeddingPrice(Double weddingPrice){
		this.weddingPrice.set(weddingPrice);
	}

	/* property getter */
	public Integer getGuestCount(){
		return guestCount.get();
	}

	/* property setter */
	public void setGuestCount(Integer guestCount){
		this.guestCount.set(guestCount);
	}

	public StringProperty weddingNameProperty() {
		return weddingName;
	}

	public IntegerProperty guestCountProperty() {
		return guestCount;
	}

	public DoubleProperty weddingPriceProperty() {
		return weddingPrice;
	}

	public Event getEvents() {
		return null;
	}
}
