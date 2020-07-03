package com.aop.demo.game.service.impl;

import com.aop.demo.game.mapper.GameMapper;
import com.aop.demo.game.model.Game;
import com.aop.demo.game.service.GameService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

  @Autowired
  GameMapper gameMapper;

  @Override
  public List<Game> getAll() {
    return gameMapper.selectAll();
  }

  @Override
  public void addGame(Game game) {
    gameMapper.insertSelective(game);
    if (true) {
      throw new RuntimeException("错误了");
    }
  }
}
