package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Game phase enum.
 */
public enum GamePhase implements Serializable, Packable {
  LandBuyPhase("Land Buy"), PlayerTurnPhase("Player Turn"), MulePlacementPhase("Mule Placement");

  private final String name;

  GamePhase(String nameInput) {
    name = nameInput;
  }

  @Override
  public String toString() {
    return name;
  }
}
