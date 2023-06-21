package cwh.hbnu.community.common.api;


public interface IErrorCode {
    /**
     * 错误编码: -1失败;200成功
     * @return 错误编码
     */
    Integer getCode();

    /**
     * @return 错误描述
     */
    String getMessage();
}
