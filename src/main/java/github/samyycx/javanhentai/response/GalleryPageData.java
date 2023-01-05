package github.samyycx.javanhentai.response;

import lombok.Data;

@Data
public class GalleryPageData {

    /**
     * 图片的后缀<br>
     * "j" => jpg<br>
     * "p" => png<br>
     * "g" => gif (这个不确定，我没遇见过)<br>
     * 其他的没遇见过
     */
    String t;

    /**
     * 图片的宽
     */
    int w;

    /**
     * 图片的高
     */
    int h;

}
