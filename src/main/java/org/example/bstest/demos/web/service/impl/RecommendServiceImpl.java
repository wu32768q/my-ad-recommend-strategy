package org.example.bstest.demos.web.service.impl;


import org.example.bstest.demos.web.constants.DbConstants;
import org.example.bstest.demos.web.constants.MsgConstants;
import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.DTO.RouteResponseDTO;
import org.example.bstest.demos.web.decorators.AbstractElementDecorator;
import org.example.bstest.demos.web.decorators.element.CommonElementDecorator;
import org.example.bstest.demos.web.element.AbstractElement;
import org.example.bstest.demos.web.element.recall.WholeTableRecall;
import org.example.bstest.demos.web.element.sort.RandomSort;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;
import org.example.bstest.demos.web.entity.StrategyEntity;
import org.example.bstest.demos.web.enums.ElementTypeEnum;
import org.example.bstest.demos.web.enums.ResponseStatusEnum;
import org.example.bstest.demos.web.mapper.mysql.Ad2StrategyMapper;
import org.example.bstest.demos.web.mapper.mysql.AgentMaterialMapper;
import org.example.bstest.demos.web.proxy.ApplicationContextProxy;
import org.example.bstest.demos.web.proxy.CaffieneCacheProxy;
import org.example.bstest.demos.web.service.*;
import org.example.bstest.demos.web.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;


@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    Ad2StrategyMapper ad2StrategyMapper;

    @Autowired
    ElementService elementService;

    @Autowired
    StrategyService strategyService;

    @Autowired
    ApplicationContextProxy applicationContextProxy;

    @Autowired
    AgentMaterialMapper agentMaterialMapper;

    @Autowired
    AdService adService;



    @Autowired
    RecommendPreService recommendPreService;


    @Override
    public RecommendResponseDTO doRecommend(RecommendRequestDTO recommendRequestDTO) {

//        构造并初始化response
        RecommendResponseDTO recommendResponseDTO = buildInitRecommeendResponse();
        Runtime runtime = Runtime.getRuntime();
//        System.out.println("runtime" + " " +runtime.availableProcessors() );

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

//        recommendPreService.fillStrategyIdByAdId(recommendRequestDTO);
//        填充策略id
        fillRequeset(recommendRequestDTO);
//        获取展位对应策略id
        String newStrategyId = recommendRequestDTO.getStrategyId();
//        获取id对应策略
        RouteResponseDTO<StrategyEntity> routeResponseDTO = strategyService.getStrategyById(newStrategyId);
//        策略service响应结果拆包解析
        if(ResponseStatusEnum.FAIL.equals(routeResponseDTO.getResponseStatusEnum())) {
             return RecommendResponseDTO.builder()
                     .msg(routeResponseDTO.getMessage())
                     .code(routeResponseDTO.getCode())
                     .traceLog(new ArrayList<>())
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
//            System.out.println(elementTypeEnum);
            try {
//                获取组件class,枚举类对象常量不为空所以不用判空
                Class<? extends AbstractElement> myClass = elementTypeEnum.getElementClass();
//                System.out.println(myClass);
//                反射创建对象
//                AbstractElement element2Process = myClass.newInstance();
                AbstractElement element2Process = applicationContextProxy.getBean(myClass);
//                element2Process.process(recommendRequestDTO, recommendResponseDTO, elementEntity);
                if(ObjectUtils.isEmpty(recommendRequestDTO.getDecoratorClass())) {
                    recommendRequestDTO.setDecoratorClass(CommonElementDecorator.class);
                    String sb4msg = getCommonTimePrefix4Msg() + MsgConstants.FAIL_PREFIX + MsgConstants.REQUESTDTO_LACK_DECORATOR_MSG;
                    recommendResponseDTO.getTraceLog().add(sb4msg);
                }
                Class<? extends AbstractElementDecorator> decoratorClass = ObjectUtils.isEmpty(recommendRequestDTO.getDecoratorClass()) ? CommonElementDecorator.class : recommendRequestDTO.getDecoratorClass();
//                System.out.println(decoratorClass);
//                获取request指定的装饰器
                AbstractElementDecorator elementDecorator = applicationContextProxy.getBean(decoratorClass);
//                装饰器对元素封装
                elementDecorator.setAbstractElement(element2Process);
//                链路记录
                String sb4msg = getCommonTimePrefix4Msg() + MsgConstants.COMPONENT_PROCESS_MESSAGE_PREFIX
                        + myClass.getSimpleName() + MsgConstants.COMMON_SEPARATOR
                        + MsgConstants.DECORATOR_PROCESS_MESSAGE_PREFIX + decoratorClass.getSimpleName();
                recommendResponseDTO.getTraceLog().add(sb4msg);

//                执行单个builder具体逻辑
                elementDecorator.process(recommendRequestDTO, recommendResponseDTO, elementEntity);
                sb4msg = getCommonTimePrefix4Msg() + MsgConstants.AGENT_LIST_NOW_PREFIX
                        + recommendResponseDTO.getAgentEntityList();
                recommendResponseDTO.getTraceLog().add(sb4msg);
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }

            if(isResEnough(recommendRequestDTO, recommendResponseDTO)) {

                this.limitAgentList(recommendRequestDTO, recommendResponseDTO);
                recommendResponseDTO.getTraceLog().add(getCommonTimePrefix4Msg() + MsgConstants.RES_NUMBER_LIMIT);

                buildFinalResponse(recommendRequestDTO, recommendResponseDTO, true,
                        getCommonTimePrefix4Msg() + MsgConstants.BUILD_SUCCESS);
                return recommendResponseDTO;
            }
        }
//        后处理兜底保险逻辑
        recommendResponseDTO.getTraceLog().add(getCommonTimePrefix4Msg() + MsgConstants.BACKSTOP_TRIGGER);
        try {
            this.backstopHandle(recommendRequestDTO, recommendResponseDTO, new ElementEntity());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//        限制推荐数量
        this.limitAgentList(recommendRequestDTO, recommendResponseDTO);

        recommendResponseDTO.getTraceLog().add(getCommonTimePrefix4Msg() + MsgConstants.RES_NUMBER_LIMIT);

        buildFinalResponse(recommendRequestDTO, recommendResponseDTO, true,
                getCommonTimePrefix4Msg() + MsgConstants.BUILD_SUCCESS);

//        变动response中agent的推荐次数
        List<AgentEntity> agentList = recommendResponseDTO.getAgentEntityList();
        updateRecommendCountByAgentList(agentList, recommendRequestDTO);
        return recommendResponseDTO;
    }

    @Override
    public  RecommendResponseDTO buildInitRecommeendResponse() {
        return RecommendResponseDTO.builder()
                .agentEntityList(new ArrayList<>())
                .sortedAgentEntityList(new ArrayList<>())
                .traceLog(new ArrayList<>())
                .build();
    }

    @Override
    public Boolean isResEnough(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO) {
        List<AgentEntity> list = recommendResponseDTO.getAgentEntityList();
        if(recommendRequestDTO.getExpectNumber() <= list.size()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }



//    兜底出人逻辑处理
    @Override
    public void backstopHandle(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) throws InstantiationException, IllegalAccessException {
        int currentSize = recommendResponseDTO.getAgentEntityList().size();
        if(currentSize >= recommendRequestDTO.getExpectNumber()) {
            return;
        }
        AbstractElement element = applicationContextProxy.getBean(WholeTableRecall.class);
        AbstractElementDecorator elementDecorator = applicationContextProxy.getBean(CommonElementDecorator.class);
        elementDecorator.setAbstractElement(element);
        elementDecorator.process(recommendRequestDTO, recommendResponseDTO, elementEntity);
    }

//    请求校验
    @Override
    public Boolean requestCheck(RecommendRequestDTO recommendRequestDTO) {
        String adId = recommendRequestDTO.getAdId();
        String strategyId = recommendRequestDTO.getStrategyId();
        if(!StringUtils.hasText(adId) && !StringUtils.hasText(strategyId) ||  Integer.compare(0, recommendRequestDTO.getExpectNumber()) > -1) {
            return Boolean.FALSE;
        }



        return Boolean.TRUE;
    }

//    构造出人失败响应dto
    public void buildFinalResponse(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, Boolean flag, String msg) {
        if(recommendResponseDTO == null) {
            recommendResponseDTO = RecommendResponseDTO.builder().build();
        }
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
        return MsgConstants.CURRENT_TIME_PREFIX + TimeUtils.getCurrentTimeWithScheme()
                + MsgConstants.COMMON_SEPARATOR;
    }

    @Override
    public void limitAgentList(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO) {
        if(recommendRequestDTO.getExpectNumber() >= recommendResponseDTO.getAgentEntityList().size()) {
            return;
        }
        AbstractElement abstractElement = applicationContextProxy.getBean(RandomSort.class);

        abstractElement.process(recommendRequestDTO, recommendResponseDTO, new ElementEntity());
    }


    @Async
    @Override
    public void updateRecommendCountByAgentList(List<AgentEntity> agentList, RecommendRequestDTO recommendRequestDTO) {
        agentList.forEach(e->{
                    String id = e.getId();
                    int recommendCount = agentMaterialMapper.getRecommendCountById(id, recommendRequestDTO.getTableName2Recall());
                    agentMaterialMapper.increaseRecommendCountById(id, recommendRequestDTO.getTableName2Recall(), recommendCount + 1);
                }
        );
    }

    @Override
    public RecommendRequestDTO buildInitRecommendRequest(String tableName2Recall,String strategyId, String adId, int expectNumber) {
        return RecommendRequestDTO.builder()
                .tableName2Recall(tableName2Recall)
                .strategyId(strategyId)
                .adId(adId)
                .expectNumber(expectNumber > 0 ? expectNumber : 1)
                .build();
    }



}
