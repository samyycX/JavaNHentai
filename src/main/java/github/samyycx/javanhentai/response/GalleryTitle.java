package github.samyycx.javanhentai.response;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class GalleryTitle {

    /**
     * 本子的英文名 (不确定会不会null, 应该是不会）
     */
    String english;

    /**
     * 本子的日文名，可能是null
     */
    @Nullable
    String japanese;

    /**
     * 缩减版本子名，应该不会是null
     */
    String pretty;

}
