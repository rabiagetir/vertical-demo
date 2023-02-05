package models.response;

import models.request.VerticalMetaRequest;

import java.util.ArrayList;

public class VerticalResponse {
    public VerticalResponse(int id, int version, int sequence, boolean isActive, String title, String shortTitle, String mediaId, ArrayList<VerticalMetaResponse> metas) {
        this.id = id;
        this.version = version;
        this.sequence = sequence;
        this.isActive = isActive;
        this.title = title;
        this.shortTitle = shortTitle;
        this.mediaId = mediaId;
        this.metas = metas;
    }

    public int id;

    public int version;

    public int sequence;

    public boolean isActive;
    public String title;
    public String shortTitle;
    public String mediaId;
    public ArrayList<VerticalMetaResponse> metas;

}
