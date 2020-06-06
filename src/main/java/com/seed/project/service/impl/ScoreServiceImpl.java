package com.seed.project.service.impl;

import com.seed.project.mapper.ScoreMapper;
import com.seed.project.entity.Score;
import com.seed.project.service.ScoreService;
import com.seed.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/05/30.
 */
@Service
@Transactional
public class ScoreServiceImpl extends AbstractService<Score> implements ScoreService {
    @Resource
    private ScoreMapper scoreMapper;

}
