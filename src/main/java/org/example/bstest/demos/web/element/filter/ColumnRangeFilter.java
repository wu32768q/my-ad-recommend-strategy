package org.example.bstest.demos.web.element.filter;

import org.example.bstest.demos.web.DTO.RecommendRequestDTO;
import org.example.bstest.demos.web.DTO.RecommendResponseDTO;
import org.example.bstest.demos.web.entity.AgentEntity;
import org.example.bstest.demos.web.entity.elementEntity.ElementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class ColumnRangeFilter extends  AbstractFilter{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void process(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        this.doFilter(recommendRequestDTO, recommendResponseDTO, elementEntity);
    }

    @Override
    public void doFilter(RecommendRequestDTO recommendRequestDTO, RecommendResponseDTO recommendResponseDTO, ElementEntity elementEntity) {
        List<AgentEntity> list = recommendResponseDTO.getAgentEntityList();
        list.stream().filter(e->{
            try {
                Field field = elementEntity.getClass().getDeclaredField(elementEntity.getColumnName());
                field.setAccessible(true);
                if((int) field.get(e) < elementEntity.getRightRule() || (int) field.get(e) > elementEntity.getLeftRule()) {
                    return true;
                }
                return false;
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            return false;
        });
    }
}
