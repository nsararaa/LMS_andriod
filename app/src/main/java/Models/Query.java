package Models;


import com.example.lms.R;

import java.util.Date;
import java.util.UUID;



public class Query {
    private String id;
    private String recipient;
    private String queryText;
    private String response;
    private Date submissionDate;
    private QueryStatus status;

    public enum QueryStatus {
        PENDING("Pending", R.color.error_color),
        RESPONDED("Responded", R.color.success_color);

        private final String label;
        private final int colorResId;

        QueryStatus(String label, int colorResId) {
            this.label = label;
            this.colorResId = colorResId;
        }

        public String getLabel() { return label; }
        public int getColorResId() { return colorResId; }
    }

    public Query(String recipient, String queryText) {
        this.id = UUID.randomUUID().toString();
        this.recipient = recipient;
        this.queryText = queryText;
        this.submissionDate = new Date();
        this.status = QueryStatus.PENDING;
    }


    public String getId() { return id; }
    public String getRecipient() { return recipient; }
    public String getQueryText() { return queryText; }
    public String getResponse() { return response; }
    public void setResponse(String response) {
        this.response = response;
        this.status = QueryStatus.RESPONDED;
    }
    public Date getSubmissionDate() { return submissionDate; }
    public QueryStatus getStatus() { return status; }
}
