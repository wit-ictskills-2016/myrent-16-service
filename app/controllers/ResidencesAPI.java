  package controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import models.Residence;
import models.Landlord;
import play.mvc.Controller;
import utils.GsonStrategy;

public class ResidencesAPI extends Controller
{

  static Gson gson = new GsonBuilder()
      .setExclusionStrategies(new GsonStrategy())
      .create();

 /**
  * 
  * @param id
  * @param body
  */
  public static void createResidence(String id, JsonElement body)
  {
    Residence residence = gson.fromJson(body.toString(), Residence.class);
    Landlord landlord = Landlord.findById(id);
    landlord.residences.add(residence);
    residence.landlord = landlord;
    residence.save();
    renderJSON(gson.toJson(residence)); 
  }

//  public static void createResidence(JsonElement body)
//  {
//    Residence residence = gson.fromJson(body.toString(), Residence.class);
//    Landlord landlord = Landlord.findById(id);
//    landlord.residences.add(residence);
//    residence.landlord = landlord;
//    residence.save();
//    renderJSON(gson.toJson(residence)); 
//  }
  
  public static void getAllResidences()
  {
    List<Residence> residences = Residence.findAll();
    renderJSON(gson.toJson(residences));
  }
  
  /**
   * 
   * @param id : The id of the landlord (the residence list owner)
   */
  public static void getResidences(String id)
  {
    Landlord landlord = Landlord.findById(id);
    if (landlord == null)
    {
      notFound();
    }
    renderJSON(gson.toJson(landlord.residences));
  }
 
  /**
   * 
   * @param id
   * @param residenceId
   */
  public static void getResidence (String id, String residenceId)
  {
   Residence residence = Residence.findById(residenceId);
   if (residence == null)
   {
     notFound();
   }
   else
   {
     renderJSON(gson.toJson(residence));
   }
  }
 /**
  *  
  * @param id
  * @param residenceId
  */
  public static void deleteResidence(String id, String residenceId)
  {
    Residence residence = Residence.findById(residenceId);
    if (residence == null)
    {
      notFound();
    }
    else
    {
      residence.delete();
      renderText("success");
    }
  }
  
  public static void deleteAllResidences()
  {
    Residence.deleteAll();
    renderText("success");
  }  
}