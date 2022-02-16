package cn.codeprobe.butin.common.response;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Lionido on 12/2/2022
 * <p>
 * 统一封装返回实体类
 * {
 * code:业务状态吗
 * msg: 业务消息
 * data: 业务数据
 * success: 请求响应是否成功
 * }
 */

public class R extends LinkedHashMap<String, Object> {

    private static final String CODE_TAG = "code";
    private static final String MESSAGE_TAG = "msg";
    private static final String STATE_TAG = "isSuccess";


    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public R put(HashMap<String, Object> map) {
        putAll(map);
        return this;
    }

    public static R ok(Status status) {
        R r = ok(status.getCode(), status.getMsg());
        r.put(STATE_TAG, status.isSuccess());
        return r;
    }

    public static R ok(int code, String msg) {
        R r = new R();
        r.put(CODE_TAG, code);
        r.put(MESSAGE_TAG, msg);
        r.put(STATE_TAG, true);
        return r;
    }


    public static R error(Status status) {
        R r = error(status.getCode(), status.getMsg());
        r.put(STATE_TAG, status.isSuccess());
        return r;
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put(CODE_TAG, code);
        r.put(MESSAGE_TAG, msg);
        r.put(STATE_TAG, false);
        return r;
    }

}
