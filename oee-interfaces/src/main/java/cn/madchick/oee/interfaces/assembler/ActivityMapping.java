package cn.madchick.oee.interfaces.assembler;

import cn.madchick.oee.domain.activity.model.vo.ActivityVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import cn.madchick.oee.rpc.activity.deploy.dto.ActivityDTO;

import java.util.List;

/**
 * @description: 活动对象转换配置
 * @author：jagger
 * @date: 2024/6/24
 * @Copyright： jagger - www.madchick.cn
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ActivityMapping extends IMapping<ActivityVO, ActivityDTO>{
    
    @Override
    List<ActivityDTO> sourceToTarget(List<ActivityVO> var1);

}
