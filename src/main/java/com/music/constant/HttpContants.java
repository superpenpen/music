package com.music.constant;

/**
 *
 *
 * @author Xiep
 * @date 2020-07-29 11:18
 */
public interface HttpContants {


    /**
     * 部署模型（启动推理服务）
     *
     */
    String MODEL_PYTORCH_START_URLS = "http://%s:%s/models";


    /**
     * 卸载模型（停止推理服务）
     *
     */
    String MODEL_PYTORCH_STOP_URLS = "http://%s:%s/models";


    /**
     * 重置默认版本
     *
     */
    String MODEL_PYTORCH_SET_DEFAULT = "http://%s:%s/models";


    /**
     * 推理任务
     *
     */
    String MODEL_PYTORCH_PREDICTION = "http://%s:%s/predictions";


}
