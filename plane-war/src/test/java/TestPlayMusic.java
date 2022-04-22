import com.sky.util.MusicUtil;
import org.junit.Test;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 描述：测试用例
 *
 * @author sukai
 * @date 2022/4/21
 */
public class TestPlayMusic {

    @Test
    public void playMusic() throws Exception {
        URL url = this.getClass().getClassLoader().getResource("");
        assert url != null;
        String filePath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.name()) + "music/bgm.mp3";
        System.out.println(filePath);
        MusicUtil.play(filePath);
    }

}
