package RestAPI;

import java.util.Map;

public class RestRequestBuilder {
    private String baseUrl;
    private String restMethod;
    private String messageBody;

    public RestRequestBuilder setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public RestRequestBuilder setRestMethod(String restMethod) {
        this.restMethod = restMethod;
        return this;
    }

    public RestRequestBuilder setMessageBody(String messageBody) {
        this.messageBody = messageBody;
        return this;
    }

    public RestRequestBuilder addAttribute(Map<String, String> attributes) {

        if(attributes != null && attributes.size() > 0) {
            baseUrl = baseUrl + "?";
            StringBuilder sb = new StringBuilder("");
            for (Map.Entry<String,String> entry : attributes.entrySet()){
                sb.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            baseUrl += sb.toString().substring(0, sb.toString().length()-1);
        }

        return this;
    }

    public RestApiResponse build(){
        RestApiResponse response = null;

        if(restMethod == null || restMethod.equals("")){
            System.err.println("The REST method is undefined. setting default REST as POST.");
            restMethod = "post";
        }

        if(restMethod.toLowerCase().equals("post")) {
            response = RestAPI.sendPost(baseUrl, messageBody);
        }
        if(restMethod.toLowerCase().equals("get")) {
            response = RestAPI.sendGet(baseUrl, true);
        }


        return response;
    }
}