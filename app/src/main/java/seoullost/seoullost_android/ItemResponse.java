package seoullost.seoullost_android;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResponse {
    @SerializedName("count")
    private int count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private List<Item> results;

    public int getCount() {
        return count;
    }

    public int getNext() {
        return next == null ? -1 : Integer.parseInt(next.split("page=")[1]);
    }

    public String getPrevious() {
        return previous;
    }

    public List<Item> getResults() {
        return results;
    }

    public boolean isLastPage() {
        return next == null;
    }
}
