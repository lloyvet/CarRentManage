package com.lloyvet.sys.controller;

import com.lloyvet.sys.domain.News;
import com.lloyvet.sys.service.NewsService;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.utils.ResultObj;
import com.lloyvet.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通告管理控制器
 */
@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 加载 角色列表返回DataGridView
     */
    @RequestMapping("loadAllNews")
    public DataGridView loadAllNews(NewsVo news){
        return newsService.queryAllNews(news);
    }
    /**
     * 添加公告
     */
    @RequestMapping("addNews")
    public ResultObj addNews(NewsVo newsVo){
        try{
            newsService.addNews(newsVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 删除一个通告
     */
    @RequestMapping("deleteNews")
    public ResultObj deleteNews(NewsVo newsVo){
        try {
            newsService.deleteNews(newsVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 删除多个通告
     */
    @RequestMapping("deleteBatchNews")
    public ResultObj deleteBatchNews(NewsVo newsVo){
        try {
            newsService.deleteBatchNews(newsVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 更新通告
     */
    @RequestMapping("updateNews")
    public ResultObj updateNews(NewsVo newsVo){
        try{
            newsService.updateNews(newsVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 根据id查询通告内容
     */
    @RequestMapping("loadNewsById")
    public News loadNewsById(Integer id){
        return newsService.loadNewsById(id);
    }
}
