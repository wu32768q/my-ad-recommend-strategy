package org.example.bstest.demos.web.enums;

import org.example.bstest.demos.web.element.AbstractElement;
import org.example.bstest.demos.web.element.filter.AbstractFilter;
import org.example.bstest.demos.web.element.filter.ColumnFilter;
import org.example.bstest.demos.web.element.recall.AbstrackRecall;
import org.example.bstest.demos.web.element.recall.WholeTableRecall;
import org.example.bstest.demos.web.element.sort.AbstractSort;
import org.example.bstest.demos.web.element.sort.RandomSort;
import org.example.bstest.demos.web.element.sort.TopSort;


public enum ElementTypeEnum {
    RECALL_TYPE(AbstrackRecall.class),
    WHOLE_TABLE_RECALL(WholeTableRecall.class),
    FILTER_TYPE(AbstractFilter.class),
    COLUMN_FILTER(ColumnFilter.class),
    SORT_TYPE(AbstractSort.class),
    TOPSORT_TYPE(TopSort.class),
    RANDOMSORT_TYPE(RandomSort.class);


    final Class<? extends AbstractElement> elementClass;

    private ElementTypeEnum(Class<? extends AbstractElement> elementClass) {
        this.elementClass = elementClass;
    }

    public Class<? extends AbstractElement> getElementClass() {
        return elementClass;
    }
}
