package com.kdu.common.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.URL;
import java.security.cert.X509Certificate;

public class SSLConnect {

    private static final String URL = "https://www.kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode=";

    // always verify the host - dont check for certificate
    final HostnameVerifier DO_NOT_VERIFY = (hostname, session) -> true;

    public static Document getDocument(String code) throws Exception {
        SSLConnect sslConnect = new SSLConnect();
        String url = convertUrl(code);
        sslConnect.postHttps(url, 1000, 1000);
        return Jsoup.connect(url).get();
    }

    private static String convertUrl(String code) {
        return URL + code;
    }

    /**
     * Trust every server - don't check for any certificate
     */
    private void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HttpsURLConnection postHttps(String url, int connTimeout, int readTimeout) {
        trustAllHosts();

        HttpsURLConnection https;
        try {
            https = (HttpsURLConnection) new URL(url).openConnection();
            https.setHostnameVerifier(DO_NOT_VERIFY);
            https.setConnectTimeout(connTimeout);
            https.setReadTimeout(readTimeout);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return https;
    }
}
