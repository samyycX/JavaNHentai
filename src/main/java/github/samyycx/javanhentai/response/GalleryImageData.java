package github.samyycx.javanhentai.response;

import lombok.Data;

import java.util.List;

@Data
public class GalleryImageData {

    /**
     * 本子主要内容的图片信息
     * @see GalleryPageData
     */
    List<GalleryPageData> pages;

    /**
     * 本子的封面图片信息
     * @see GalleryPageData
     */
    GalleryPageData cover;

    /**
     * 本子的缩略图图片信息
     * @see GalleryPageData
     */
    GalleryPageData thumbnail;

}
