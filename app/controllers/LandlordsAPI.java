package controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import models.Residence;
import models.Landlord;
import play.mvc.Controller;
import utils.GsonStrategy;

public class LandlordsAPI extends Controller
{
  static Gson gson = new GsonBuilder()
  .setExclusionStrategies(new GsonStrategy())
  .create();

  public static void getAllLandlords()
  {
    List<Landlord> Landlords = Landlord.findAll();
    renderJSON(gson.toJson(Landlords));
  }

  public static void getLandlorder(String id)
  {
    Landlord landlord = Landlord.findById(id);
    if (landlord == null)
    {
      notFound();
    }
    else
    {
      renderJSON(gson.toJson(landlord));
    }
  }

  public static void createLandlord(JsonElement body)
  {
    Landlord landlord = gson.fromJson(body.toString(), Landlord.class);
    landlord.save();
    renderJSON(gson.toJson(landlord));
  }

  /**
   * Update fields in existing landlord (excluding id).
   * @param body
   */
  public static void updateLandlord(JsonElement body)
  {
    Landlord updatedLandlord = gson.fromJson(body.toString(), Landlord.class);
    Landlord landlord = Landlord.findById(updatedLandlord.uuid);
    landlord.update(updatedLandlord);
    landlord.save();
    renderJSON(gson.toJson(landlord));
  }


  public static void deleteLandlord(String uuid)
  {
    Landlord landlord = Landlord.findById(uuid);
    if (landlord == null)
    {
      notFound("No Landlord with ID" + uuid);
    }
    else
    {
      landlord.delete();
      renderJSON(gson.toJson(landlord));
    }
  }
  
  /**
   * This method deletes all landlords and residences that it owns.
   */
  public static void deleteAllLandlords()
  {
    List<Landlord> landlords = Landlord.findAll();
    int numberLandlords = landlords.size();
    for(int i = 0; i < numberLandlords; i += 1)
    {
      Landlord landlord = landlords.get(i);
      List<Residence> residences = landlord.residences;
      for(int j = 0; j < residences.size(); j += 1)
      {
        Residence residence = Residence.findById(residences.get(j).uuid);
        landlord.residences.remove(residence);
        landlord.save();
        residence.delete();   
      }
      landlord.delete();
    }
    renderText("success");
  }
}