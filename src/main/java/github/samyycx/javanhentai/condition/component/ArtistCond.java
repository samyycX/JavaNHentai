package github.samyycx.javanhentai.condition.component;

public class ArtistCond implements Cond {

    private final String artist;

    public ArtistCond(String artist) {
        this.artist = artist;
    }

    @Override
    public String toParam() {
        return String.format("artist:'%s'",artist);
    }

}
