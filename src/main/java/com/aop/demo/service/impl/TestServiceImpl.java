package com.aop.demo.service.impl;

import com.aop.demo.competition.model.Competition;
import com.aop.demo.competition.service.CompetitionService;
import com.aop.demo.game.model.Game;
import com.aop.demo.game.service.GameService;
import com.aop.demo.service.TestService;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qy.ma
 * @version 0.1.0
 * @Description
 * @create 2020-06-30 20:48
 * @since 0.1.0
 **/
@Slf4j
@Service
public class TestServiceImpl implements TestService {

  @Autowired
  GameService gameService;
  @Autowired
  CompetitionService competitionService;

  @Override
  public List<Game> games() {
    return gameService.getAll();
  }

  @Override
  public List<Competition> matchs() {
    return competitionService.getAll();
  }

  @Override
  public void addGame() {
    Game game = new Game();
    game.setId(UUID.randomUUID().toString().replaceAll("-", ""));
    game.setName("cs 1.6");
    gameService.addGame(game);
  }

  @Override
  public void addMatch() {
    Competition competition = new Competition();
    competition.setId(UUID.randomUUID().toString().replaceAll("-", ""));
    competition.setName("冬季赛");
    competitionService.addCompetition(competition);

  }

  @Override
  public void join() {
    addGame();
    addMatch();
  }

}
