package com.music.constant;

/**
 *
 *
 * @author Xiep
 * @date 2020-07-29 11:18
 */
public interface IntConstants {

    /**
     * 超级管理员
     */
    int ROLE_SUPER_ADMIN = 0;

    /**
     * 普通管理员
     */
    int ROLE_ADMIN = 1;

    /**
     * 普通用户
     */
    int ROLE_NORMAL_USER=2;


    /**
     * 状态 成功
     */
    int STATE_SUCCESS = 0;

    /**
     * 状态 失败
     */
    int STATE_FAIL = 1;


    /**
     * 状态 运行
     */
    int STATE_IS_RUNNING= 0;

    /**
     * 状态 未运行
     */
    int STATE_IS_NOT_RUNNING= 1;

    /**
     * 状态 失败
     */
    int STATE_IS_FAILED= 2;

    /**
     * 请求成功
     */
    int REQUEST_RESPONSE_SUCCESS= 200;


    /**
     *  模型使用版本 pytorch
     */
    int FRAMEWORK_VERSION_PYTORCH = 1;

    /**
     * 模型使用版本 tensorflow
     */
    int FRAMEWORK_VERSION_TENSORFLOW = 2;

    /**
     * 推理任务类型 图片
     */
    int ILLATION_TASK_TYPE_PIC=0;

    /**
     * 推理任务类型 视频
     */
    int ILLATION_TASK_TYPE_VIDEO=1;

    /**
     * 模型任务返回类型 字符串
     */
    int MODEL_RESPONSE_TYPE_STRING=0;

    /**
     * 模型任务返回类型 JSON
     */
    int MODEL_RESPONSE_TYPE_JSON=1;

    /**
     * 模型任务返回类型 图片
     */
    int MODEL_RESPONSE_TYPE_PIC=2;

    /**
     * 模型任务返回类型 二进制
     */
    int MODEL_RESPONSE_TYPE_BLOB=3;






}
