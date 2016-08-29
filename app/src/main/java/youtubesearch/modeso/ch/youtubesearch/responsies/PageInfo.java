package youtubesearch.modeso.ch.youtubesearch.responsies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal Mohamed on 8/28/16.
 * www.modeso.ch
 */
public class PageInfo {

    @SerializedName("totalResults")
    private long totalResults;

    @SerializedName("resultsPerPage")
    private int resultsPerPage;

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
}
