package models;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.jpa.GenericModel;

@Entity
public class Residence extends GenericModel {

	@Id
	public Long id;
	public String geolocation;
	public Long date;
	public boolean rented;
	public String tenant;
	public double zoom;
	public String photo;

	public Residence() {
		id = unsignedLong();
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
  public void update(Residence other) {
    this.geolocation = other.geolocation;
    this.date = other.date;
    this.rented = other.rented;
    this.tenant = other.tenant;
    this.zoom = other.zoom;
    this.photo = other.photo;
  }

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

	public static Residence findById(Long id) {
		return find("id", id).first();
	}

	/**
	 * Generate a long greater than zero
	 * @return Unsigned Long value greater than zero
	 */
	private Long unsignedLong() {
		long rndVal = 0;
		do {
			rndVal = new Random().nextLong();
		} while (rndVal <= 0);
		return rndVal;
	}
}