package youtubesearch.modeso.ch.youtubesearch.responsies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal Mohamed on 8/28/16.
 * www.modeso.ch
 */
public class ErrorResponse {

    @SerializedName("error")
    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
