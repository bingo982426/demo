package com.aop.demo.controllr;

import com.aop.demo.model.Game;
import com.aop.demo.service.GameService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
