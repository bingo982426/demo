package com.aop.demo.game.service;


import com.aop.demo.game.model.Game;
import java.util.List;

public interface GameService {

  List<Game> getAll();

  void addGame(Game game);
}
