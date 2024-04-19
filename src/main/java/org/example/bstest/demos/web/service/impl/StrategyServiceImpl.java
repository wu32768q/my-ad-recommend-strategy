package org.example.bstest.demos.web.service.impl;


import org.bson.types.ObjectId;
import org.example.bstest.demos.web.DTO.RouteResponseDTO;
import org.example.bstest.demos.web.entity.StrategyEntity;
import org.example.bstest.demos.web.enums.ElementTypeEnum;
import org.example.bstest.demos.web.enums.StatusEnum;
import org.example.bstest.demos.web.mapper.StrategyMapper;
import org.example.bstest.demos.web.service.ElementService;
import org.example.bstest.demos.web.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class StrategyServiceImpl implements StrategyService {

    @Autowired
    StrategyMapper strategyMapper;

    @Autowired
    ElementService elementService;


    @Override
    public RouteResponseDTO insertStrategy(StrategyEntity strategyEntity) {
        RouteResponseDTO checkResponse = strategyCheck(strategyEntity);
        if(StatusEnum.FAIL.equals(checkResponse.getStatusEnum())) {
            return new RouteResponseDTO(StatusEnum.FAIL, checkResponse.getMsg());
        };
        strategyMapper.insertStrategy(strategyEntity);
        return new RouteResponseDTO<Boolean>(StatusEnum.SUCESS, "添加策略成功！");
    }

    @Override
    public RouteResponseDTO deleteStrategy(String id) {
        RouteResponseDTO<ObjectId> routeResponseDTO = parseString2ObjectId(id);
        if(routeResponseDTO.getStatusEnum().equals(StatusEnum.FAIL)) {
            return routeResponseDTO;
        }
        strategyMapper.deleteStrategy(routeResponseDTO.getResult());
        return new RouteResponseDTO(StatusEnum.SUCESS, "删除策略成功");
    }


    public RouteResponseDTO updateStrategy(StrategyEntity strategyEntity) {
        RouteResponseDTO checkResponse = strategyCheck(strategyEntity);
        if(StatusEnum.FAIL.equals(checkResponse.getStatusEnum())) {
            return checkResponse;
        };
        strategyMapper.updateStrategy(strategyEntity);
        return new RouteResponseDTO(StatusEnum.SUCESS, "更新策略成功");
    }


    @Override
    public RouteResponseDTO getStrategyList() {
        return new RouteResponseDTO(strategyMapper.getStrategyList(), StatusEnum.SUCESS);
    }


    @Override
    public RouteResponseDTO getStrategyById(String id) {
        RouteResponseDTO<ObjectId> routeResponseDTO = parseString2ObjectId(id);
        if(routeResponseDTO.getStatusEnum().equals(StatusEnum.FAIL)) {
            return routeResponseDTO;
        }
        StrategyEntity strategyEntity = strategyMapper.getStrategy(routeResponseDTO.getResult());
        if(ObjectUtils.isEmpty(strategyEntity)){
            return new RouteResponseDTO(StatusEnum.FAIL, "未找到id对应的策略");
        }
        return new RouteResponseDTO<StrategyEntity>(strategyEntity, StatusEnum.SUCESS);
    }


    public RouteResponseDTO strategyCheck(StrategyEntity strategyEntity){
        AtomicReference<Boolean> flag = new AtomicReference<>(false);
        for(String id : strategyEntity.getElementList()) {
            if(ElementTypeEnum.RECALL.equals(elementService.getElementById(id).getElementEnum())) {
                flag.set(true);
            }
            else if(flag.get().equals(false)) {
                return new RouteResponseDTO(StatusEnum.FAIL, "策略应先使用召回组件，请检查策略合法性");
            }

        }
        return new RouteResponseDTO(StatusEnum.SUCESS);
    }


    public RouteResponseDTO<ObjectId> parseString2ObjectId(String id){
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (Exception e) {
            return new RouteResponseDTO<ObjectId>(StatusEnum.FAIL, "策略id from String 2 ObjectId 失败，请检查id合法性");
        }
        return new RouteResponseDTO<ObjectId>(objectId, StatusEnum.SUCESS);
    }


}
