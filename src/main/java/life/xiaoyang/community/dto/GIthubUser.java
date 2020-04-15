package life.xiaoyang.community.dto;

import lombok.Data;

@Data
public class GIthubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
