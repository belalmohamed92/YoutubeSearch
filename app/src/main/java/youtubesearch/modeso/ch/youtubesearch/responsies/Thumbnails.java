package youtubesearch.modeso.ch.youtubesearch.responsies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal Mohamed on 8/28/16.
 * www.modeso.ch
 */
public class Thumbnails {

    @SerializedName("high")
    private High high;

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }
}
