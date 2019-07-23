package com.music.contants;

/**
 * @ClassName IntContants
 * @Description int类型 常量
 * @Author xiep
 * @Date 2019/5/21 16:59
 **/
public interface IntContants {

    /**
     *  星火厂商 ID
     */
    int XINGHUO_FACTORY_ID=1;

    /**
     *  订阅 性能数据类型
     */
    int SUBSCRIBE_TYPE_PERFORMS = 1;

    /**
     *  订阅 告警数据类型
     */
    int SUBSCRIBE_TYPE_PERFORMS_ALARMS = 2;

    /**
     *  订阅状态 禁用
     */
    int SUBSCRIBE_STATUS_FORBIDDEN = 0;

    /**
     *  订阅状态 启用
     */
    int  SUBSCRIBE_STATUS_ON = 1;

    /**
     *  订阅状态 取消
     */
    int  SUBSCRIBE_STATUS_OFF = 2;

    /**
     *  订阅回调类型 http
     */
    int SUBSCRIBE_CALLBACK_TYPE_HTTP = 1;




    /**
     *  性能数据类型 平台
     */
    int PERFORM_DATA_TYPE_PLATFORM = 0;
    /**
     *  性能数据类型 物理机
     */
    int PERFORM_DATA_TYPE_PHYSICAL = 1;
    /**
     *  性能数据类型 虚拟机
     */
    int PERFORM_DATA_TYPE_VIRTUAL = 2;
    /**
     *  性能数据类型 服务
     */
    int PERFORM_DATA_TYPE_SERVER = 3;
    /**
     *  性能数据类型 进程
     */
    int PERFORM_DATA_TYPE_PROCESS = 4;


    /**
     *  告警数据类型 平台
     */
    int ALARM_DATA_TYPE_PLATFORM = 0;
    /**
     *  告警数据类型 物理机
     */
    int ALARM_DATA_TYPE_PHYSICAL = 1;
    /**
     *  告警数据类型 虚拟机
     */
    int ALARM_DATA_TYPE_VIRTUAL = 2;
    /**
     *  告警数据类型 服务
     */
    int ALARM_DATA_TYPE_SERVER = 3;
    /**
     *  告警数据类型 进程
     */
    int ALARM_DATA_TYPE_PROCESS = 4;

    /**
     *  订阅回调类型  http
     */
    int PERFORM_DATA_CALLBACK_TYPE_HTTP = 1;


    /**
     * 超级管理员用户ID
     */
    int SUPER_ADMIN_USER_ID = 1;

    /**
     * 超级管理员角色ID
     */
    int SUPER_ADMIN_ROLE_ID = 1;

    /**
     * 角色权限状态：启用
     */
    int ROLE_PERMISSION_STATUS_ON = 1;

    /**
     * 角色权限状态：禁用
     */
    int ROLE_PERMISSION_STATUS_OFF = 0;

    /**
     * 用户初始ID
     */
    int INIT_USER_ID = 0;

    /**
     * 角色初始ID
     */
    int INTI_ROLE_ID = 0;

}
