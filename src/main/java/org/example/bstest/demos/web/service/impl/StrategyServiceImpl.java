package org.example.bstest.demos.web.service.impl;


import org.bson.types.ObjectId;
import org.example.bstest.demos.web.DTO.RouteResponseDTO;
import org.example.bstest.demos.web.entity.StrategyEntity;
import org.example.bstest.demos.web.enums.ElementTypeEnum;
import org.example.bstest.demos.web.enums.ResponseStatusEnum;
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
        if(ResponseStatusEnum.FAIL.equals(checkResponse.getResponseStatusEnum())) {
            return new RouteResponseDTO(ResponseStatusEnum.FAIL, checkResponse.getMsg());
        };
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
        RouteResponseDTO checkResponse = strategyCheck(strategyEntity);
        if(ResponseStatusEnum.FAIL.equals(checkResponse.getResponseStatusEnum())) {
            return checkResponse;
        };
        strategyMapper.updateStrategy(strategyEntity);
        return new RouteResponseDTO(ResponseStatusEnum.SUCESS, "更新策略成功");
    }


    @Override
    public RouteResponseDTO getStrategyList() {
        return new RouteResponseDTO(strategyMapper.getStrategyList(), ResponseStatusEnum.SUCESS);
    }


    @Override
    public RouteResponseDTO getStrategyById(String id) {
        RouteResponseDTO<ObjectId> routeResponseDTO = parseString2ObjectId(id);
        if(routeResponseDTO.getResponseStatusEnum().equals(ResponseStatusEnum.FAIL)) {
            return routeResponseDTO;
        }
        StrategyEntity strategyEntity = strategyMapper.getStrategy(routeResponseDTO.getResult());
        if(ObjectUtils.isEmpty(strategyEntity)){
            return new RouteResponseDTO(ResponseStatusEnum.FAIL, "未找到id对应的策略");
        }
        return new RouteResponseDTO<StrategyEntity>(strategyEntity, ResponseStatusEnum.SUCESS);
    }


    public RouteResponseDTO strategyCheck(StrategyEntity strategyEntity){
        AtomicReference<Boolean> flag = new AtomicReference<>(false);
        for(String id : strategyEntity.getElementList()) {
            if(ElementTypeEnum.RECALL.equals(elementService.getElementById(id).getElementTypeEnum())) {
                flag.set(true);
            }
            else if(flag.get().equals(false)) {
                return new RouteResponseDTO(ResponseStatusEnum.FAIL, "策略应先使用召回组件，请检查策略合法性");
            }

        }
        return new RouteResponseDTO(ResponseStatusEnum.SUCESS);
    }


    public RouteResponseDTO<ObjectId> parseString2ObjectId(String id){
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (Exception e) {
            return new RouteResponseDTO<ObjectId>(ResponseStatusEnum.FAIL, "策略id from String 2 ObjectId 失败，请检查id合法性");
        }
        return new RouteResponseDTO<ObjectId>(objectId, ResponseStatusEnum.SUCESS);
    }


}
