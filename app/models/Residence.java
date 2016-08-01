package models;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	public UUID uuid;

	public String geolocation;
	public Date date;
	public boolean rented;
	public String tenant;
	public double zoom;
	public String photo;

	/**
	 * Update this Residence but leave id unchanged
	 * 
	 * @param other
	 */
	public void update(Residence other) {
		this.geolocation = other.geolocation;
		this.date = other.date;
		this.rented = other.rented;
		this.tenant = other.tenant;
		this.zoom = other.zoom;
		this.photo = other.photo;
	}

	public static Residence findByUuid(String uuid) {
		return find("uuid", uuid).first();
	}

}