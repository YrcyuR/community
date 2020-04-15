package life.xiaoyang.community.dto;

import lombok.Data;

@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String message)
    {
        ResultDTO dto = new ResultDTO();
        dto.setCode(code);
        dto.setMessage(message);
        return dto;
    }

    public static ResultDTO okOf()
    {
        ResultDTO dto = new ResultDTO();
        dto.setCode(200);
        dto.setMessage("请求成功");
        return dto;
    }
}
