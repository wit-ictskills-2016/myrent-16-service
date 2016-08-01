package models;

import java.util.ArrayList;
import java.util.List;

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
	public String uuid;
	public String firstName;
	public String lastName;

	public void update(Landlord other) {
		this.firstName = other.firstName;
		this.lastName = other.lastName;
	}
}