package com.aop.demo.controllr;

import com.aop.demo.competition.model.Competition;
import com.aop.demo.game.model.Game;
import com.aop.demo.service.TestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

  @Autowired
  TestService testService;

  @GetMapping(value = "games")
  public List<Game> getAll() {
    return testService.games();
  }

  @GetMapping(value = "matchs")
  public List<Competition> getAll2() {
    return testService.matchs();
  }

  @PostMapping(value = "game")
  public void addGame() {
    testService.addGame();
  }

  @PostMapping(value = "match")
  public void addMatch() {
    testService.addMatch();
  }

  @PostMapping(value = "join")
  public void join() {
    testService.join();
  }
}
