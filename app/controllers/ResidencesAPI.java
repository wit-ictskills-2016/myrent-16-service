package controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import models.Residence;
import play.mvc.Controller;

public class ResidencesAPI extends Controller {

  static Gson gson = new GsonBuilder().create();

  /**
   * 
   * @param id
   * @param body
   */
  public static void createResidence(JsonElement body) {
    Residence residence = gson.fromJson(body.toString(), Residence.class);
    Residence res = new Residence();
    residence.id = res.id;
    residence.save();
    renderJSON(gson.toJson(residence));
  }

  /**
   * This is an update and differs from createResidence in that the primary key
   * (id) is used to retrieve the original residence which updated with the incoming residence's fields.
   * 
   * @param body
   *          The modified residence
   */
  public static void updateResidence(JsonElement body) {
    Residence modifiedResidence = gson.fromJson(body.toString(), Residence.class);
    Residence residence = Residence.findById(modifiedResidence.id);
    if (residence != null) {
      residence.update(modifiedResidence);
      residence.save();
      renderJSON(gson.toJson(residence));
    } else {
      notFound();
    }

  }

  public static void getResidence(Long id) {
    Residence residence = Residence.findById(id);
    if (residence == null) {
      notFound();
    } else {
      renderJSON(gson.toJson(residence));
    }
  }

  public static void getResidences() {
    List<Residence> residences = Residence.findAll();
    renderJSON(gson.toJson(residences));
  }

  /**
   * 
   * @param id
   * @param residenceId
   */
  public static void deleteResidence(Long id) {
    Residence residence = Residence.findById(id);
    if (residence == null) {
      notFound();
    } else {
      residence.delete();
      renderText("success");
    }
  }

}