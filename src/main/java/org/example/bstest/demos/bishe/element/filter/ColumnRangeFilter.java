package org.example.bstest.demos.bishe.element.filter;

import org.example.bstest.demos.bishe.DTO.RecommendRequestDTO;
import org.example.bstest.demos.bishe.DTO.RecommendResponseDTO;
import org.example.bstest.demos.bishe.entity.AgentEntity;
import org.example.bstest.demos.bishe.entity.elementEntity.ElementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColumnRangeFilter extends  AbstractFilter {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void process(RecommendRequestDTO recommendRequestDTO,
                        RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        this.doFilter(recommendRequestDTO, recommendResponseDTO, elementEntity);
    }

    @Override
    public void doFilter(RecommendRequestDTO recommendRequestDTO,
                         RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        List<AgentEntity> list = recommendResponseDTO.getAgentEntityList();
//        System.out.println(",,,," + elementEntity);
        list = list.stream().filter(e -> {
            try {
                Field field = AgentEntity.class.getDeclaredField(elementEntity.getColumnName());
                field.setAccessible(true);
//                System.out.println("......" + field.get(e));
                if (((int) field.get(e)) < elementEntity.getRightRule()
                        && ((int) field.get(e)) > elementEntity.getLeftRule()) {
                    return true;
                }
                return false;
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
        recommendResponseDTO.setAgentEntityList(list);
    }
}
