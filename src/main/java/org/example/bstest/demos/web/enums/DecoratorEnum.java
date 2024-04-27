package org.example.bstest.demos.web.enums;

import org.example.bstest.demos.web.decorators.AbstractElementDecorator;
import org.example.bstest.demos.web.decorators.element.CommonElementDecorator;
import org.example.bstest.demos.web.element.AbstractElement;

public enum DecoratorEnum {

    COMMON_ELEMEENT_DECORATOR(CommonElementDecorator.class),
    ABSTRACT_ELEMENT_DECORATOR(AbstractElementDecorator.class)


    ;

    final Class<? extends AbstractElementDecorator> decoratorClass;

    private DecoratorEnum(Class<? extends AbstractElementDecorator> decoratorClass) {
        this.decoratorClass = decoratorClass;
    }

    public Class<? extends AbstractElementDecorator> getDecoratorClass() {
        return decoratorClass;
    }

}
