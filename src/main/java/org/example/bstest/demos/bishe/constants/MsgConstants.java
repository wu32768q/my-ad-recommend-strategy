package org.example.bstest.demos.bishe.constants;


public class MsgConstants {

    public static String FAIL_PREFIX = "业务执行失败，失败原因：";

    public static String CURRENT_TIME_PREFIX = "当前系统时间：";

    public static String COMMON_SEPARATOR = "----";

    public static String LACK_ELEMENT_DETAIL_MSG_OR_TYPE_ENUM = "未获取到策略中组件具体信息或组件缺少类型枚举，组件id为：";

    public static String  REQUESTDTO_LACK_DECORATOR_MSG= "requestDTO缺少装饰器信息";

    public static String BACKSTOP_TRIGGER= "触发分发兜底逻辑";

    public static String REQUEST_CHECK_FAILED = "请求校验失败，请检查请求参数";

    public static String BUILD_SUCCESS = "流量分发成功";

    public static String  PREHANDLE_SUCCESS_AND_START_PROECESS = "预处理完成，开始执行责任链选人逻辑" + COMMON_SEPARATOR;

    public static String COMPONENT_PROCESS_MESSAGE_PREFIX = "开始执行组件 ： ";

    public static String DECORATOR_PROCESS_MESSAGE_PREFIX = "使用的装饰器为 ： ";

    public static String AGENT_LIST_NOW_PREFIX = "当前推荐结果为 ： ";

    public static String RES_NUMBER_LIMIT = "执行出人限制逻辑";

    public static String BUILD_BY_CACHE = "经由缓存输出结果";

    public static String  STRATEGY_ID_TRANSFOROM_FAILED = "失败原因：策略id from String 2 ObjectId 失败，请检查id合法性";



}
