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
  * @param uuid
  * @param body
  */
  public static void createResidence(String uuid, JsonElement body)
  {
    Residence residence = gson.fromJson(body.toString(), Residence.class);
    Landlord landlord = Landlord.findById(uuid);
    landlord.residences.add(residence);
    residence.landlord = landlord;
    residence.save();
    renderJSON(gson.toJson(residence)); 
  }

  public static void getAllResidences()
  {
    List<Residence> residences = Residence.findAll();
    renderJSON(gson.toJson(residences));
  }
  
  /**
   * 
   * @param uuid : The uuid of the landlord (the residence list owner)
   */
  public static void getResidences(String uuid)
  {
    Landlord landlord = Landlord.findById(uuid);
    if (landlord == null)
    {
      notFound();
    }
    renderJSON(gson.toJson(landlord.residences));
  }
 
  /**
   * 
   * @param uuid : The landlord uuid. This is redundant here since 
   *             residence uuid a uuid and so unique.
   * @param residenceId : The uuid of residence sought.
   */
  public static void getResidence (String uuid, String residenceId)
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
  * @param uuid
  * @param residenceId
  */
  public static void deleteResidence(String uuid, String residenceId)
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