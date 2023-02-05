package models.response;

public class VerticalMetaResponse {

    public VerticalMetaResponse(String shortTitle, String title, String lang, String slug, int verticalId, int version, int id) {
        this.shortTitle = shortTitle;
        this.title = title;
        this.lang = lang;
        this.slug = slug;
        this.verticalId = verticalId;
        this.version = version;
        this.id = id;
    }

    public String shortTitle;
    public String title;
    public String lang;
    public String slug;

    public int verticalId;

    public int version;

    public int id;

}
