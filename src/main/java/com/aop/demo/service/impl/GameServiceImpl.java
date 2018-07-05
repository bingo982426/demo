package com.aop.demo.service.impl;

import com.aop.demo.mapper.GameMapper;
import com.aop.demo.model.Game;
import com.aop.demo.service.GameService;
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
  public List<Game> all() {
    return gameMapper.selectAll();
  }
}
