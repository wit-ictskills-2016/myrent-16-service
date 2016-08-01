package models;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.jpa.GenericModel;

@Entity
public class Residence extends GenericModel {

	@ManyToOne
	public Landlord landlord;
	
	@Id
	public String id;
	public String geolocation;
	public Long date;
	public boolean rented;
	public String tenant;
	public double zoom;
	public String photo;

	/**
	 * Update this Residence but leave id unchanged
	 * 
	 * @param other
	 */
//	public void update(Residence other) {
//		//this.id = other.id;
//		this.geolocation = other.geolocation;
//		this.date = other.date;
//		this.rented = other.rented;
//		this.tenant = other.tenant;
//		this.zoom = other.zoom;
//		this.photo = other.photo;
//	}

	/**
	 * Copy constructor
	 * @param other
	 */
	public Residence(Residence other) {
		this.id = other.id;
		this.geolocation = other.geolocation;
		this.date = other.date;
		this.rented = other.rented;
		this.tenant = other.tenant;
		this.zoom = other.zoom;
		this.photo = other.photo;
}
	
	public static Residence findById(String id) {
		return find("id", id).first();
	}

}