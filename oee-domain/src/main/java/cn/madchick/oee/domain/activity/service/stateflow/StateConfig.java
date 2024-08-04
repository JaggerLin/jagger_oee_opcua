package cn.madchick.oee.domain.activity.service.stateflow;

import cn.madchick.oee.common.Constants;
import cn.madchick.oee.domain.activity.service.stateflow.event.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
// * @description: 状态流转配置, 用于初始化状态流转的处理类，将状态枚举和状态处理类放入map中
 * @author：jagger
 * @date: 2024/6/17
 * @Copyright： jagger - www.madchick.cn
 */
public class StateConfig {

   @Resource
    private ArraignmentState arraignmentState;

    @Resource
    private CloseState closeState;

    @Resource
    private DoingState doingState;

    @Resource
    private EditingState editingState;

    @Resource
    private OpenState openState;

    @Resource
    private PassState passState;

    @Resource
    private RefuseState refuseState;

    /**
     * 状态组:ConcurrentHashMap用来存储状态流转的处理类, key为状态枚举, value为状态处理类，是一个线程安全的map
     */
    protected Map<Enum<Constants.ActivityState>, AbstractState> stateGroup = new ConcurrentHashMap<>();

    /**
     * 初始化方法，将状态枚举和状态处理类放入stateGroup中
     * PostConstruct注解是在依赖注入完成后执行的方法，用于初始化数据
     */
    @PostConstruct
    public void init() {
        stateGroup.put(Constants.ActivityState.ARRAIGNMENT, arraignmentState);
        stateGroup.put(Constants.ActivityState.CLOSE, closeState);
        stateGroup.put(Constants.ActivityState.DOING, doingState);
        stateGroup.put(Constants.ActivityState.EDIT, editingState);
        stateGroup.put(Constants.ActivityState.OPEN, openState);
        stateGroup.put(Constants.ActivityState.PASS, passState);
        stateGroup.put(Constants.ActivityState.REFUSE, refuseState);
    }
}
