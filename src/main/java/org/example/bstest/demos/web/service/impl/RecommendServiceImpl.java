package org.example.bstest.demos.web.service.impl;


import com.alibaba.druid.util.StringUtils;
import org.example.bstest.demos.web.constants.DbConstants;
import org.example.bstest.demos.web.constants.MsgConstants;
import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.DTO.RouteResponseDTO;
import org.example.bstest.demos.web.decorators.AbstractElementDecorator;
import org.example.bstest.demos.web.decorators.element.CommonElementDecorator;
import org.example.bstest.demos.web.element.AbstractElement;
import org.example.bstest.demos.web.element.recall.WholeTableRecall;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.example.bstest.demos.web.entity.SortedAgentEntity;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;
import org.example.bstest.demos.web.entity.StrategyEntity;
import org.example.bstest.demos.web.enums.ElementTypeEnum;
import org.example.bstest.demos.web.enums.ResponseStatusEnum;
import org.example.bstest.demos.web.mapper.mysql.Ad2StrategyMapper;
import org.example.bstest.demos.web.service.ElementService;
import org.example.bstest.demos.web.service.RecommendService;
import org.example.bstest.demos.web.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    Ad2StrategyMapper ad2StrategyMapper;

    @Autowired
    ElementService elementService;

    @Autowired
    StrategyService strategyService;


    @Override
    public RecommendResponseDTO doRecommend(RecommendRequestDTO recommendRequestDTO) {
//       构造并初始化response
        RecommendResponseDTO recommendResponseDTO = buildInitRecommeendResponse();

//        request参数检查
        Boolean check = requestCheck(recommendRequestDTO);
        if(Boolean.FALSE.equals(check)) {
            buildFinalResponse(recommendRequestDTO, recommendResponseDTO, check,
                    this.getCommonTimePrefix4Msg()
                            + MsgConstants.FAIL_PREFIX
                            + MsgConstants.REQUEST_CHECK_FAILED
            );
            return recommendResponseDTO;
        }
//        填充策略id
        fillStrategyIdByAdId(recommendRequestDTO);
        fillRequeset(recommendRequestDTO);
//        获取展位对应策略id
        String strategyId = recommendRequestDTO.getStrategyId();
//        获取id对应策略
        RouteResponseDTO<StrategyEntity> routeResponseDTO = strategyService.getStrategyById(strategyId);
//        策略service响应结果拆包解析
        if(ResponseStatusEnum.FAIL.equals(routeResponseDTO.getResponseStatusEnum())) {
             return RecommendResponseDTO.builder()
                     .msg(routeResponseDTO.getMessage())
                     .code(routeResponseDTO.getCode())
                     .build();
        }
        StrategyEntity strategyEntity = routeResponseDTO.getResult();
//         获取各组件id对应ctx的list
        List<String> list = strategyEntity.getElementList();
        recommendResponseDTO.getTraceLog().add(getCommonTimePrefix4Msg() + MsgConstants.PREHANDLE_SUCCESS_AND_START_PROECESS);
        for (int position = 0; position < list.size(); position++) {
            ElementEntity elementEntity = elementService.getElementById(list.get(position));
//            判空
            if(ObjectUtils.isEmpty(elementEntity) || ObjectUtils.isEmpty(elementEntity.getElementTypeEnum())) {
                StringBuilder sb4msg= new StringBuilder();
                sb4msg.append(getCommonTimePrefix4Msg())
                        .append(MsgConstants.FAIL_PREFIX)
                        .append(MsgConstants.LACK_ELEMENT_DETAIL_MSG_OR_TYPE_ENUM)
                        .append(list.get(position));
                recommendResponseDTO.getTraceLog().add(sb4msg.toString());
                continue;
            }
            ElementTypeEnum elementTypeEnum = elementEntity.getElementTypeEnum();
            try {
//                获取组件class,枚举类对象常量不为空所以不用判空
                Class<? extends AbstractElement> myClass = elementTypeEnum.getElementClass();
//                反射创建对象
                AbstractElement element2Process = myClass.newInstance();
                if(ObjectUtils.isEmpty(recommendRequestDTO.getDecoratorClass())) {
                    String sb4msg = getCommonTimePrefix4Msg() + MsgConstants.FAIL_PREFIX + MsgConstants.REQUESTDTO_LACK_DECORATOR_MSG;
                    recommendResponseDTO.getTraceLog().add(sb4msg);
                    continue;
                }
//                获取request指定的装饰器
                AbstractElementDecorator elementDecorator = recommendRequestDTO.getDecoratorClass().newInstance();
//                装饰器对元素封装
                elementDecorator.setAbstractElement(element2Process);
//                执行单个builder具体逻辑
                elementDecorator.process(recommendRequestDTO, recommendResponseDTO);
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }

            if(isResEnough(recommendRequestDTO, recommendResponseDTO)) {
                buildFinalResponse(recommendRequestDTO, recommendResponseDTO, true,
                        getCommonTimePrefix4Msg() + MsgConstants.BUILD_SUCCESS);
                return recommendResponseDTO;
            }
        }
//        后处理兜底保险逻辑
        recommendResponseDTO.getTraceLog().add(MsgConstants.BACKSTOP_TRIGGER);
        this.backstopHandle(recommendRequestDTO, recommendResponseDTO);
        buildFinalResponse(recommendRequestDTO, recommendResponseDTO, true,
                getCommonTimePrefix4Msg() + MsgConstants.BUILD_SUCCESS);
        return recommendResponseDTO;
    }

    @Override
    public  RecommendResponseDTO buildInitRecommeendResponse() {
        return RecommendResponseDTO.builder()
                .agentEntityList(Collections.<AgentEntity>emptyList())
                .sortedAgentEntityList(Collections.<SortedAgentEntity>emptyList())
                .build();
    }

    @Override
    public Boolean isResEnough(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO) {
        List<AgentEntity> list = recommendResponseDTO.getAgentEntityList();
        if(recommendRequestDTO.getExpectNumber() < list.size()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public void fillStrategyIdByAdId(RecommendRequestDTO recommendRequestDTO) {
        if(StringUtils.isEmpty(recommendRequestDTO.getStrategyId())) {
            return ;
        }
        String strategyId = ad2StrategyMapper.getStrategyIdByAdId(recommendRequestDTO.getAdId());
        recommendRequestDTO.setStrategyId(strategyId);
    }

//    兜底出人逻辑处理
    @Override
    public void backstopHandle(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO) {
        int currentSize = recommendResponseDTO.getAgentEntityList().size();
        if(currentSize >= recommendRequestDTO.getExpectNumber()) {
            return;
        }
        AbstractElement element = new WholeTableRecall();
        AbstractElementDecorator elementDecorator = new CommonElementDecorator(element);
        elementDecorator.process(recommendRequestDTO, recommendResponseDTO);
    }

//    请求校验
    @Override
    public Boolean requestCheck(RecommendRequestDTO recommendRequestDTO) {
        String adId = recommendRequestDTO.getAdId();
        if(StringUtils.isEmpty(adId) ||  Integer.compare(0, recommendRequestDTO.getExpectNumber()) > -1) {
            return Boolean.FALSE;
        }



        return Boolean.TRUE;
    }

//    构造出人失败响应dto
    public void buildFinalResponse(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, Boolean flag, String msg) {

        recommendResponseDTO.setCode(flag ? 200 : -1);
        recommendResponseDTO.setMsg(msg);
        recommendResponseDTO.getTraceLog().add(msg);
    }


    @Override
    public void fillRequeset(RecommendRequestDTO recommendRequestDTO) {
        if(ObjectUtils.isEmpty(recommendRequestDTO.getDecoratorClass())) {
            recommendRequestDTO.setDecoratorClass(CommonElementDecorator.class);
        }
        if(ObjectUtils.isEmpty(recommendRequestDTO.getTableName2Recall())) {
            recommendRequestDTO.setTableName2Recall(DbConstants.COMMON_MYSQL_TABLE_4_AGENT_RECALL);
        }
    }

    @Override
    public String getCommonTimePrefix4Msg() {
        return MsgConstants.CURRENT_TIME_PREFIX + new Date()
                + MsgConstants.COMMON_SEPARATOR;
    }
}
