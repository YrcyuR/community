package life.xiaoyang.community.community.provider;

import com.alibaba.fastjson.JSON;
import life.xiaoyang.community.community.dto.AccessTokenDTO;
import life.xiaoyang.community.community.dto.GIthubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String name = response.body().string();
//            System.out.println("name            "+name);
            String token = name.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
        }
        return null;
    }

    public GIthubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
//            System.out.println("string            "+string);
            GIthubUser gIthubUser = JSON.parseObject(string, GIthubUser.class);
            return gIthubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
