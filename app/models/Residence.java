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

import play.db.jpa.Model;

@Entity
public class Residence extends Model{

  public String geolocation;
  public Long date;
  public boolean rented;
  public String tenant;
  public double zoom;
  public String photo;

  public Residence() {
    geolocation = "";
    date = 0L;
    rented = false;
    tenant = "";
    zoom = 0;
    photo = "";
  }

  public static Residence findById(Long id) {
    return find("id", id).first();
  }
  
}