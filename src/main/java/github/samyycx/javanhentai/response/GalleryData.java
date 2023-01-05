package github.samyycx.javanhentai.response;

import lombok.Data;

import java.util.List;

@Data
public class GalleryData {

    /**
     * 本子的id
     */
    int id;

    /**
     * 本子的媒体文件id (获取图片资源的时候用的)
     */
    String media_id;

    /**
     * 本子的标题 (有english, japanese, pretty三种版本)
     * @see GalleryTitle
     */
    GalleryTitle title;

    /**
     * 本子的图片数据
     * @see GalleryImageData
     */
    GalleryImageData images;

    /**
     * 扫译器 (?)
     * 不确定是什么用处，目前搜出来的这个值都是空的""
     */
    String scanlator;

    /**
     * 上传的秒数时间戳
     */
    long uploadDate;

    /**
     * 本子的tag信息
     * @see GalleryTag
     */
    List<GalleryTag> tags;

    /**
     * 本子的页数
     */
    int num_pages;

    /**
     * 本子的喜欢人数
     */
    int num_favourites;

}
