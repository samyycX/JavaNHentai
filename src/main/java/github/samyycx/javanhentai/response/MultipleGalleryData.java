package github.samyycx.javanhentai.response;

import lombok.Data;

import java.util.List;

@Data
public class MultipleGalleryData {

    /**
     * @see GalleryData
     */
    List<GalleryData> result;

    /**
     * 一共有几页
     */
    int num_pages;

    /**
     * 一页显示多少个本子
     */
    int per_page;

}
