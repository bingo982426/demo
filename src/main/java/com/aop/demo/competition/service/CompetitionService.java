package com.aop.demo.competition.service;

import com.aop.demo.competition.model.Competition;
import java.util.List;

/**
 * @author qy.ma
 * @version 0.1.0
 * @Description
 * @create 2020-06-30 20:57
 * @since 0.1.0
 **/
public interface CompetitionService {

  List<Competition> getAll();

  void addCompetition(Competition competition);
}
