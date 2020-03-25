package com.lloyvet.sys.service;

import com.lloyvet.sys.domain.News;
import com.lloyvet.sys.utils.DataGridView;
import com.lloyvet.sys.vo.NewsVo;

/**
 * 公告管理服务接口
 */


public interface NewsService {

    /**
     * 查询所有
     * @param newsVo
     * @return
     */
    DataGridView queryAllNews(NewsVo newsVo);

    /**
     * 添加公告
     * @param newsVo
     */
    void addNews(NewsVo newsVo);

    /**
     * 修改公告
     * @param newsVo
     */
    void updateNews(NewsVo newsVo);
    /**
     * 根据id删除公告
     * @param newsid
     * @return
     */
    void deleteNews(Integer newsid);

    /**
     * 批量删除公告
     * @param ids
     */
    void deleteBatchNews(Integer[] ids);

    News loadNewsById(Integer id);
}
