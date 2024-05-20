package org.example.bstest.demos.bishe.enums;

import org.example.bstest.demos.bishe.decorators.AbstractElementDecorator;
import org.example.bstest.demos.bishe.decorators.element.CommonElementDecorator;

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
