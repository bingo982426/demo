package com.aop.demo.service;

import com.aop.demo.game.model.Game;
import com.aop.demo.competition.model.Competition;
import java.util.List;

/**
 * @author qy.ma
 * @version 0.1.0
 * @Description
 * @create 2020-06-30 20:48
 * @since 0.1.0
 **/
public interface TestService {

  List<Game> games();

  List<Competition> matchs();

  void addGame();

  void addMatch();

  void join();
}
