package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.jpa.GenericModel;

@Entity
public class Landlord extends GenericModel {

	@OneToMany(mappedBy="landlord", cascade=CascadeType.ALL)
	public List<Residence> residences = new ArrayList<Residence>();

	@Id
	public String id;
	public String firstName;
	public String lastName;

	public Landlord (String firstName, String lastName) {
		id = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void update(Landlord other) {
		this.firstName = other.firstName;
		this.lastName = other.lastName;
	}
	
	public static Landlord findById(String id) {
		return find("id", id).first();
	}
}