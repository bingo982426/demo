package com.aop.demo.service;


import java.util.List;

import com.aop.demo.model.Game;

public interface GameService {

  List<Game> getAll();

  List<Game> all();

  int addGame();
}
