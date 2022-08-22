package Lesson6;

public class WundergroundData {
    private static final String PROTOCOL = "Http";
    private static final String WU_HOST = "api.wunderground.com";
    private String apiKey; // Wunderground requires a registered key to use services

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public URL featureUrl(String feature) throws MalformedURLException {
        // Standard Request URL Format: ${protocol}${WU_HOST}/api/${key}/${features}/${settings}/q/${query}
        return new URL(PROTOCOL, WU_HOST, String.format("/api/%s/%s", apiKey, feature));
    }

    public JsonNode fetchHistorical(String city, String state, String date)
            throws MalformedURLException, IOException {
        return new ObjectMapper().readTree(featureUrl(String.format("history_%s/q/%s/%s.json"
                , date, state, city)));
    }

    public WundergroundData() {
    }

    public WundergroundData(String key) {
        setApiKey(key);
    }
}