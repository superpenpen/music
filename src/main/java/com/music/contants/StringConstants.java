package com.music.contants;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package com.cstor.cvideoai.contants
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2019/5/28 17:27
 */
public interface StringConstants {

    Integer SUCCESS_CODE = 100;
    String SUCCESS_MSG = "请求成功";

    /**
     * session中存放用户信息的key值
     */
    String SESSION_USER_INFO = "userInfo";
    String SESSION_USER_PERMISSION = "userPermission";


    /**
     * 库信息管理
     */
    String SHOW_LIBRARY_INFO = "show_library_info";
    String LIBRARY_SHOW_OBJECTS = "library_show_objects";
    String LIBRARY_ADD_OBJECTS = "library_add_objects";
    String LIBRARY_DELETE_OBJECTS = "library_delete_objects";
    String LIBRARY_DELETE_ALL_OBJECT = "library_delete_all_object";

}
