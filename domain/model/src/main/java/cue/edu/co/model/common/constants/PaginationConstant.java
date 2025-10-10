package cue.edu.co.model.common.constants;

import cue.edu.co.model.common.enums.SortDirection;

public class PaginationConstant {

    private PaginationConstant(){}

    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 10;
    public static final int MAX_SIZE = 100;
    public static final String DEFAULT_SORT_BY = "createdAt";
    public static final SortDirection DEFAULT_SORT_DIRECTION = SortDirection.ASC;
}
