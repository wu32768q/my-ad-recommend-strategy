package org.example.bstest.demos.bishe.enums;

import org.example.bstest.demos.bishe.element.AbstractElement;
import org.example.bstest.demos.bishe.element.filter.AbstractFilter;
import org.example.bstest.demos.bishe.element.filter.ColumnEqualFilter;
import org.example.bstest.demos.bishe.element.filter.ColumnRangeFilter;
import org.example.bstest.demos.bishe.element.recall.AbstrackRecall;
import org.example.bstest.demos.bishe.element.recall.WholeTableRecall;
import org.example.bstest.demos.bishe.element.sort.AbstractSort;
import org.example.bstest.demos.bishe.element.sort.RandomSort;
import org.example.bstest.demos.bishe.element.sort.TopSort;


public enum ElementTypeEnum {
    RECALL_TYPE(AbstrackRecall.class),
    WHOLE_TABLE_RECALL(WholeTableRecall.class),
    FILTER_TYPE(AbstractFilter.class),
    COLUMN_RANGE_FILTER(ColumnRangeFilter.class),
    COLUMN_EQUAL_FILTER(ColumnEqualFilter.class),
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
