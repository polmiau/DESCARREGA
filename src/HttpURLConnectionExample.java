import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

    private final String USER_AGENT = "Mozilla/5.0";

    /*public static void main(String[] args) throws Exception {

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();

       // System.out.println("\nTesting 2 - Send Http POST request");
        //http.sendPost();

    }*/

    // HTTP GET request
    private void sendGet() throws Exception {

        String url = "http://plusdedo.tk/v1/serie/mr.-robot";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("Cookie", "user=s%3Ae%3Ae7188083f7eaed1d5172f1f8710a8803.rLJZvS7NGJLhwgaogFpl3Ra%2B1PFNITN4d6FTfz%2FGJwY; token=s%3Ae%3Ad53adcbe14d2e9623918944a05d8f1a98b0ec753da17482026bccc85a161f03d153e6b38a0c09d98a46f405db2de7596.boZOk4fseH3zrfesOad8s0wcOOthCHej6IFbACUub64; auth=s%3Ae%3A1c0068411ba173d0c67c29b1b588984bab17f303d5f9543e475cea3301274f1584d3521dde9945eaedf09a46caa40c140e35aac20d242a1b86f59056542bc487acafaf0ee3a9120ad691fc67f892b482790b6b0896de93f081d8e1f0515f10e7b0d62a548a8485aec96e0d89d31fdf41e98cdf2a34e7538b24af622078eb3a48248e467bb2ffd8634e68905910727c644083c754de5d51da014b506205ca1cb9feb42503a8442645573f88767e079d7099408ab05b117fa7dadabe8cddc90baaf50272882526fffe54898efd52f497786519060ff4ff99eada1a9a7109dee9ec542584b5b5f8f9eb0dd53970a6080b9656cc23c2f971a6c02fe6321e41b7f6e5280847ab6ea30c8c98d2db79120b1b844e091581524acec93841d36eea401a51009a7c55577e5f7e2f193e53749a437e7c29e59642f9e98cc27d07241eb126fb56f52c82b8913b218418ce06d6be6c53eef4e9f477a80a2e3cfa08268922b5a6a4e6569a9414e093cfff220cccafa9a781acb4d964a5b27b2f07262d1cb43bf195249894a09d387e25e05e04930082270409c162d18b81dc39aa2a5d593b25ab20c532dbf1633c4eeec988a8362c5352d6976153e7a291a6e7b876cd995f9cacb1794aadfa2c39cae83ba7e79dc15e3da02322e48a2fc44a2b2f667cdad5e7aaeaed2cf63c4b2dabd04e065f266241e7406e0cd11441681f1e9313166c9de638e179d14abaa121c34cf9a63452059d8bbb8664506f82b936145f68fbfa53328b02aa3534d63dd026c48c2e99d3a8a1edd14cbacc44982de3fef1a83fca345af88581929381c4140561e70181b4e11535e28f23350290c4e3844f5910d795fa495c267fc44f756f346250d7673b6829d4f452a444d7edfc343936d3551797cfec4820a1a9d64333cedbe9ce1fdacf2cb1c73e5518a4d79f5c04e2784b5a4e6c2c128c52db6e7fc0a46081d9af95ee0a163ad41ca1b07824a8945eaced59b7f7e1.KXmgQzzqDhqkim%2B3n84xMFVsT7NaUo5Yo%2BgWpFybuGg; io=4_HdLJnOiF49iWeEAAGS");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    // HTTP POST request
    private void sendPost() throws Exception {

        String url = "https://selfsolve.apple.com/wcResults.do";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}