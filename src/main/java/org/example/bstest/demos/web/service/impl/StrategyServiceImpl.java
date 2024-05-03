package org.example.bstest.demos.web.service.impl;


import com.alibaba.druid.util.StringUtils;
import org.bson.types.ObjectId;
import org.example.bstest.demos.web.DTO.RouteResponseDTO;
import org.example.bstest.demos.web.DTO.StrategyDTO;
import org.example.bstest.demos.web.entity.StrategyEntity;
import org.example.bstest.demos.web.enums.ElementTypeEnum;
import org.example.bstest.demos.web.enums.ResponseStatusEnum;
import org.example.bstest.demos.web.mapper.mongodb.StrategyMapper;
import org.example.bstest.demos.web.service.ElementService;
import org.example.bstest.demos.web.service.StrategyService;
import org.example.bstest.demos.web.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class StrategyServiceImpl implements StrategyService {

    @Autowired
    StrategyMapper strategyMapper;

    @Autowired
    ElementService elementService;



    @Override
    public RouteResponseDTO insertStrategy(StrategyEntity strategyEntity) {
//        RouteResponseDTO checkResponse = strategyCheck(strategyEntity);
//        if(ResponseStatusEnum.FAIL.equals(checkResponse.getResponseStatusEnum())) {
//            return new RouteResponseDTO(ResponseStatusEnum.FAIL, checkResponse.getMessage());
//        };
        String currentTime = TimeUtils.getCurrentTimeWithScheme();
        strategyEntity.setCreateTime(currentTime);
        strategyMapper.insertStrategy(strategyEntity);
        return new RouteResponseDTO<Boolean>(ResponseStatusEnum.SUCESS, "添加策略成功！");
    }

    @Override
    public RouteResponseDTO deleteStrategy(String id) {
        RouteResponseDTO<ObjectId> routeResponseDTO = parseString2ObjectId(id);
        if(routeResponseDTO.getResponseStatusEnum().equals(ResponseStatusEnum.FAIL)) {
            return routeResponseDTO;
        }
        strategyMapper.deleteStrategy(routeResponseDTO.getResult());
        return new RouteResponseDTO(ResponseStatusEnum.SUCESS, "删除策略成功");
    }


    @Override
    public RouteResponseDTO updateStrategy(StrategyEntity strategyEntity) {
//        RouteResponseDTO checkResponse = strategyCheck(strategyEntity);
//        if(ResponseStatusEnum.FAIL.equals(checkResponse.getResponseStatusEnum())) {
//            return checkResponse;
//        };
        strategyMapper.updateStrategy(strategyEntity);
        return new RouteResponseDTO(ResponseStatusEnum.SUCESS, "更新策略成功");
    }


    @Override
    public RouteResponseDTO getStrategyList() {
        List<StrategyEntity> list =  strategyMapper.getStrategyList();
        list.forEach(System.out::println);
        return new RouteResponseDTO(list, ResponseStatusEnum.SUCESS);
    }


    @Override
    public RouteResponseDTO<StrategyEntity> getStrategyById(String id) {
        if(StringUtils.isEmpty(id)) {
            return new RouteResponseDTO(ResponseStatusEnum.FAIL, "失败原因：策略id为空！");
        }
        RouteResponseDTO<ObjectId> routeResponseDTO = parseString2ObjectId(id);
        if(routeResponseDTO.getResponseStatusEnum().equals(ResponseStatusEnum.FAIL)) {
            return new RouteResponseDTO(ResponseStatusEnum.FAIL, routeResponseDTO.getMessage());
        }
        StrategyEntity strategyEntity = strategyMapper.getStrategy(routeResponseDTO.getResult());
        if(ObjectUtils.isEmpty(strategyEntity)){
            return new RouteResponseDTO(ResponseStatusEnum.FAIL, "失败原因：未找到id对应的策略,id为："+id);
        }
        return new RouteResponseDTO<StrategyEntity>(strategyEntity, ResponseStatusEnum.SUCESS);
    }



    public RouteResponseDTO strategyCheck(StrategyEntity strategyEntity){
        if(Objects.isNull(strategyEntity.getElementList())){
            return new RouteResponseDTO(ResponseStatusEnum.FAIL, "失败原因：策略不存在组件，请检查策略合法性");
        }
        AtomicReference<Boolean> flag = new AtomicReference<>(false);
        for(String id : strategyEntity.getElementList()) {
            if(ElementTypeEnum.RECALL_TYPE.equals(elementService.getElementById(id).getElementTypeEnum())) {
                flag.set(true);
            }
            else if(flag.get().equals(false)) {
                return new RouteResponseDTO(ResponseStatusEnum.FAIL, "失败原因：策略应先使用召回组件，请检查策略合法性");
            }

        }
        return new RouteResponseDTO(ResponseStatusEnum.SUCESS);
    }


    public RouteResponseDTO<ObjectId> parseString2ObjectId(String id){
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (Exception e) {
            return new RouteResponseDTO<ObjectId>(ResponseStatusEnum.FAIL, "失败原因：策略id from String 2 ObjectId 失败，请检查id合法性");
        }
        return new RouteResponseDTO<ObjectId>(objectId, ResponseStatusEnum.SUCESS);
    }


    @Override
    public RouteResponseDTO<List<StrategyDTO>> entity2DtoHandle(RouteResponseDTO<List<StrategyEntity>> dtoOfEntity2Handle) {
        RouteResponseDTO<List<StrategyDTO>> res = new RouteResponseDTO<>();
        List<StrategyDTO> list =new ArrayList<>();
        dtoOfEntity2Handle.getResult().forEach(e->{
            list.add(StrategyDTO.fromEntity(e));
        });
        res.setResult(list);
        res.setCode(dtoOfEntity2Handle.getCode());
        res.setMessage(dtoOfEntity2Handle.getMessage());
        res.setResponseStatusEnum(dtoOfEntity2Handle.getResponseStatusEnum());
        return res;
    }


}
