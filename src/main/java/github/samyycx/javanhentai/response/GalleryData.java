package github.samyycx.javanhentai.response;

import github.samyycx.javanhentai.NHentaiAPI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
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

    boolean parsed = false;
    List<String> parodies;
    List<String> characters;
    List<String> realStringTags;
    List<String> artists;
    List<String> groups;
    boolean isTranslated;
    String language;
    List<String> categories;

    public List<String> getParodies() {
        parse();
        return parodies;
    }

    public List<String> getCharacters() {
        parse();
        return characters;
    }

    public List<String> getRealStringTags() {
        parse();
        return realStringTags;
    }

    public List<String> getArtists() {
        parse();
        return artists;
    }

    public List<String> getGroups() {
        parse();
        return groups;
    }

    public boolean isTranslated() {
        parse();
        return isTranslated;
    }

    public String getLanguage() {
        parse();
        return language;
    }

    public List<String> getCategories() {
        parse();
        return categories;
    }

    public List<URL> getAllImage() {
        return NHentaiAPI.getAllImage(this);
    }

    public List<URL> getAllThumbnail() {
        return NHentaiAPI.getAllThumbnail(this);
    }

    public URL getCover() {
        return NHentaiAPI.getCover(this);
    }

    private void parse() {
        if (parsed) return;
        parodies = new ArrayList<>();
        characters = new ArrayList<>();
        realStringTags = new ArrayList<>();
        artists = new ArrayList<>();
        groups = new ArrayList<>();
        isTranslated = false;
        language = "";
        categories = new ArrayList<>();

        for (GalleryTag tag : tags) {
            switch (tag.getType()) {
                case "language":
                    if ("translated".equals(tag.getName())) {
                        isTranslated = true;
                    } else {
                        language = tag.getName();
                    }
                    break;
                case "tag":
                    realStringTags.add(tag.getName());
                    break;
                case "category":
                    categories.add(tag.getName());
                    break;
                case "artist":
                    artists.add(tag.getName());
                    break;
                case "group":
                    groups.add(tag.getName());
                    break;
                case "parody":
                    parodies.add(tag.getName());
                    break;
                case "character":
                    characters.add(tag.getName());

            }
        }
        parsed = true;
    }

}
