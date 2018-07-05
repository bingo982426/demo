package com.aop.demo.service;


import com.aop.demo.model.Game;
import java.util.List;

public interface GameService {

  List<Game> getAll();

  List<Game> all();

}
