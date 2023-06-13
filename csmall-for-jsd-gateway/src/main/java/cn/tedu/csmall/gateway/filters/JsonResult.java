package cn.tedu.csmall.gateway.filters;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 统一响应结果类型
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Data
@Accessors(chain = true)
public class JsonResult implements Serializable {
    /**
     * 操作结果的状态码（状态标识）
     */
    private Integer state;
    /**
     * 操作失败时的提示文本
     */
    private String message;
    /**
     * 操作成功时响应的数据
     */
    private Object data;

}
