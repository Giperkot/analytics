package dto.twogis.auth;

public class AuthResult {

    private String sessionId;

    private String userId;

    private String apiVers;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApiVers() {
        return apiVers;
    }

    public void setApiVers(String apiVers) {
        this.apiVers = apiVers;
    }
}
