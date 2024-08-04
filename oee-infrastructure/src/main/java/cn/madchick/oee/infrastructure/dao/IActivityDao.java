package cn.madchick.oee.infrastructure.dao;

import cn.madchick.oee.domain.activity.model.req.ActivityInfoLimitPageReq;
import cn.madchick.oee.domain.activity.model.vo.AlterStateVO;
import cn.madchick.oee.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 活动基础信息表DAO
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
@Mapper
public interface IActivityDao {
    /**
     * 插入数据
     * @param req 入参
     */
    void insert(Activity req);
    /**
     * 根据活动号查询活动信息
     * @param activityId 活动号
     * @return 活动信息
     */
    Activity queryActivityById(Long activityId);
    /**
     * 变更活动状态
     *
     * @param alterStateVO  [activityId、beforeState、afterState]
     * @return 更新数量
     */
    int alterState(AlterStateVO alterStateVO);

    /**
     * 查询活动分页数据数量
     *
     * @param req 入参
     * @return    结果
     */
    Long queryActivityInfoCount(ActivityInfoLimitPageReq req);

    /**
     * 查询活动分页数据列表
     *
     * @param req   入参
     * @return      结果
     */
    List<Activity> queryActivityInfoList(ActivityInfoLimitPageReq req);
}
