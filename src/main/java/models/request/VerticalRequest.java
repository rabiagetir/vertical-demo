package models.request;

import java.util.ArrayList;

public class VerticalRequest {
    public VerticalRequest(String title, String shortTitle, String mediaId, ArrayList<VerticalMetaRequest> metas) {
        this.title = title;
        this.shortTitle = shortTitle;
        this.mediaId = mediaId;
        this.metas = metas;
    }

    public String title;
    public String shortTitle;
    public String mediaId;
    public ArrayList<VerticalMetaRequest> metas;

}

