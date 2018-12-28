package com.lyarc.tp.corp.common.constant;

/**
 * 异常处理的错误码和信息
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年07月28日 16:24:37
 */
public class ExceptionCodeMessageConsts {
    public static final Integer MEEING_ROOM_NAME_NOT_REPEAT_CODE = 10501;
    public static final String MEEING_ROOM_NAME_NOT_REPEAT_MESSAGE = "会议室名称不能重复";
    public static final Integer MEEING_ROOM_CONTAINS_MEETING_CODE = 10505;
    public static final String MEEING_ROOM_CONTAINS_MEETING_MESSAGE = "该会议室下有会议";
    public static final Integer STATUS_NOT_LEGAL_CODE = 10506;
    public static final String STATUS_NOT_LEGAL_MESSAGE = "状态参数不合法";
    public static final Integer NOT_EXIST_CODE = 404;
    public static final String MEETING_ROOM_NOT_EXIST_MESSAGE = "该会议室不存在";
    public static final String MEETING_NOT_EXIST_MESSAGE = "该会议不存在";

}
