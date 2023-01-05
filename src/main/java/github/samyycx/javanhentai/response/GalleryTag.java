package github.samyycx.javanhentai.response;

import lombok.Data;

/**
 * 这个tag除了网站上本子信息里给出的tag以外，还包括artists等等信息，这些都属于tag<br>
 * 所有的详见成员变量 type
 */
@Data
public class GalleryTag {

    /**
     * tagId
     */
    int id;

    /**
     * tag的类型 (tag, artist, language, group, character, parody, category
     */
    String type;

    /**
     * tag的名字 (full color之类的)
     */
    String name;

    /**
     * tag的url (官网nhentai.net/后加的)
     */
    String url;

    /**
     * 包含这个tag的所有本子数量
     */
    int count;

}
