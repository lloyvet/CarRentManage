package com.lloyvet.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lloyvet.sys.domain.LogInfo;
import com.lloyvet.sys.domain.News;
import com.lloyvet.sys.domain.User;
import com.lloyvet.sys.mapper.LogInfoMapper;
import com.lloyvet.sys.mapper.NewsMapper;
import com.lloyvet.sys.service.LogInfoService;
import com.lloyvet.sys.service.NewsService;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.utils.WebUtils;
import com.lloyvet.sys.vo.LogInfoVo;
import com.lloyvet.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;
    @Override
    public DataGridView queryAllNews(NewsVo newsVo) {
        Page<Object> page = PageHelper.startPage(newsVo.getPage(),newsVo.getLimit());
        List<News> news = newsMapper.queryAllNews(newsVo);
        return new DataGridView(page.getTotal(),news);
    }

    @Override
    public void addNews(NewsVo newsVo) {
        newsVo.setCreatetime(new Date());
        User user = (User)WebUtils.getHttpSession().getAttribute("user");
        newsVo.setOpername(user.getRealname());
        newsMapper.insertSelective(newsVo);
    }

    @Override
    public void updateNews(NewsVo newsVo) {
        newsMapper.updateByPrimaryKeySelective(newsVo);
    }

    @Override
    public void deleteNews(Integer newsid) {
        newsMapper.deleteByPrimaryKey(newsid);
    }

    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer id : ids) {
            deleteNews(id);
        }
    }

    @Override
    public News loadNewsById(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }
}
