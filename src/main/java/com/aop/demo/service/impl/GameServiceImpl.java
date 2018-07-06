package com.aop.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aop.demo.mapper.GameMapper;
import com.aop.demo.model.Game;
import com.aop.demo.service.GameService;

@Service
public class GameServiceImpl implements GameService {

  @Autowired
  GameMapper gameMapper;


  @Override
  public List<Game> getAll() {
    return gameMapper.selectAll();
  }

  @Override
  public List<Game> all() {
    return gameMapper.selectAll();
  }

  @Override
  public int addGame() {
    Game game = new Game();
    game.setId(UUID.randomUUID().toString());
    game.setEname("cs 1.6");
    game.setName("cs 1.6");
    game.setSname("cs");
    game.setCreatedBy("6666666666666666666666666666");
    game.setDtCreated(LocalDateTime.now());
    int result = gameMapper.insert(game);
    if (true) {
      throw new RuntimeException();
    }
    return result;
  }
}
