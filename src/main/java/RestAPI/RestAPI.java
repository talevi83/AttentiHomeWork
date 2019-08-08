package RestAPI;

import org.testng.Assert;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestAPI {


    public static RestApiResponse sendPost(String urlAddress, String messageBody) {
        RestApiResponse response = new RestApiResponse();
        URL url;
        HttpURLConnection conn;

        try {
            url = new URL(urlAddress);
            conn = openHttpURLConnection(url, "POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            closeHttpConnection(conn, messageBody, response);

        }  catch (IOException e) {
            response.setErrorMessage(e.getMessage());
        }

        return response;
    }

    public static RestApiResponse sendGet(String urlAddress, boolean printRequest) {
        RestApiResponse response = null;
        URL url;
        HttpURLConnection conn;

        try {

            url = new URL(urlAddress);
            if(printRequest) System.out.println("Sending GET request to --> " + url.toString());
            conn = openHttpURLConnection(url, "GET");
            response = createResponse(conn);
            conn.disconnect();

        } catch(IOException e){
            if(response != null){
                response.setErrorMessage(e.getMessage());
            }
            System.err.println(e.getMessage());
        }
        return response;
    }

    private static HttpURLConnection openHttpURLConnection(URL url, String restMethod) throws IOException{
        HttpURLConnection conn;
        conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(100000);
        conn.setConnectTimeout(100000);
        conn.setDoOutput(true);
        conn.setRequestMethod(restMethod);

        return conn;
    }

    private static void closeHttpConnection(HttpURLConnection conn, String messageBody, RestApiResponse response) {
        try {
            OutputStream os = conn.getOutputStream();
            os.write(messageBody.getBytes());
            os.flush();
            os.close();

            response.setResponseCode(conn.getResponseCode());
            response.setResponseMessage(conn.getResponseMessage());

            InputStream in = new BufferedInputStream(conn.getInputStream());
            response.setResultBody(org.apache.commons.io.IOUtils.toString(in, "UTF-8"));

            conn.disconnect();

        } catch (IOException e) {
            response.setErrorMessage(e.getMessage());
            e.printStackTrace();
        }
    }

    private static RestApiResponse createResponse(HttpURLConnection con) throws IOException{
        RestApiResponse response = new RestApiResponse();

        response.setResponseCode(con.getResponseCode());
        Assert.assertTrue(response.getResponseCode() < 300 || response.getResponseCode() == 404, "Response code: " + response.getResponseCode() + ".");

        response.setResponseMessage(con.getResponseMessage());

        InputStream in = new BufferedInputStream(con.getInputStream());
        response.setResultBody(org.apache.commons.io.IOUtils.toString(in, "UTF-8"));

        return response;
    }
}