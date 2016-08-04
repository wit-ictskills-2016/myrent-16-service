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
	
	@Id
	public String uuid;
	public String geolocation;
	public Long date;
	public boolean rented;
	public String tenant;
	public double zoom;
	public String photo;

	public Residence() {
	  uuid = UUID.randomUUID().toString();
	  geolocation = "";
	  date = 0L;
	  rented = false;
	  tenant = "";
	  zoom = 0;
	  photo = "";
	}
	
	/**
	 * Update this Residence but leave id unchanged
	 * 
	 * @param other
	 */
	
	public static Residence findByUuid(String uuid) {
		return find("uuid", uuid).first();
	}

}