package com.aop.demo.competition.service.impl;

import com.aop.demo.competition.mapper.CompetitionMapper;
import com.aop.demo.competition.model.Competition;
import com.aop.demo.competition.service.CompetitionService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qy.ma
 * @version 0.1.0
 * @Description
 * @create 2020-06-30 20:58
 * @since 0.1.0
 **/
@Slf4j
@Service
public class CompetitionServiceImpl implements CompetitionService {

  @Autowired
  CompetitionMapper competitionMapper;

  @Override
  public List<Competition> getAll() {
    return competitionMapper.selectAll();
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void addCompetition(Competition competition) {
    competitionMapper.insertSelective(competition);
    if (true) {
      throw new RuntimeException("报错");
    }
  }
}
