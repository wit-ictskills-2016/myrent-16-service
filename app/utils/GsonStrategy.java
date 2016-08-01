package utils;

import models.Residence;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonStrategy implements ExclusionStrategy
{

  public boolean shouldSkipClass(Class<?> arg0)
  {
    return false;
  }

  public boolean shouldSkipField(FieldAttributes f)
  {
    return (f.getDeclaringClass() == Residence.class && f.getName().equals("landlord"));
  }
}