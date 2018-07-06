package com.aop.demo.controllr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.demo.model.Game;
import com.aop.demo.service.GameService;

@RestController
public class GameController {

  @Autowired
  GameService gameService;

  @GetMapping(value = "/all")
  public List<Game> getAll() {
    return gameService.getAll();
  }

  @GetMapping(value = "/all2")
  public List<Game> getAll2() {
    return gameService.all();
  }

  @PostMapping(value = "game")
  public int addGame() {
    return gameService.addGame();
  }
}
