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

  public static void getAllTweeters()
  {
    List<Landlord> Tweeters = Landlord.findAll();
    renderJSON(gson.toJson(Tweeters));
  }

  public static void getTweeter(String id)
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

  public static void createTweeter(JsonElement body)
  {
    Landlord landlord = gson.fromJson(body.toString(), Landlord.class);
    landlord.save();
    renderJSON(gson.toJson(landlord));
  }

  /**
   * Update fields in existing landlord (excluding id).
   * @param body
   */
  public static void updateTweeter(JsonElement body)
  {
    Landlord updatedLandlord = gson.fromJson(body.toString(), Landlord.class);
    Landlord landlord = Landlord.findById(updatedLandlord.uuid);
    landlord.update(updatedLandlord);
    landlord.save();
    renderJSON(gson.toJson(landlord));
  }


  public static void deleteTweeter(String uuid)
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
   * This method deletes all tweeters and residences that it owns.
   */
  public static void deleteAllTweeters()
  {
    List<Landlord> tweeters = Landlord.findAll();
    int numberTweeters = tweeters.size();
    for(int i = 0; i < numberTweeters; i += 1)
    {
      Landlord landlord = tweeters.get(i);
      List<Residence> residences = landlord.residences;
      for(int j = 0; j < residences.size(); j += 1)
      {
        Residence tweet = Residence.findById(residences.get(j).uuid);
        landlord.residences.remove(tweet);
        landlord.save();
        tweet.delete();   
      }
      landlord.delete();
    }
    renderText("success");
  }
}