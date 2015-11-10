package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Created by sayem on 9/24/2015.
 */
public enum GamePhase implements Serializable, Packable {
  LandBuyPhase("Land Buy"), PlayerTurnPhase("Player Turn"), MulePlacementPhase("Mule Placement");

  private String name;

  GamePhase(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
