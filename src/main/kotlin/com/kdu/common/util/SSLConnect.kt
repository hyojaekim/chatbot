package com.kdu.common.util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

class SSLConnect {

    // always verify the host - dont check for certificate
    private val DO_NOT_VERIFY = HostnameVerifier { hostname: String, session: SSLSession -> true }

    /**
     * Trust every server - don't check for any certificate
     */
    private fun trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
        })

        // Install the all-trusting trust manager
        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, trustAllCerts, SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun postHttps(url: String, connTimeout: Int, readTimeout: Int): HttpsURLConnection? {
        trustAllHosts()
        val https: HttpsURLConnection
        try {
            https = java.net.URL(url).openConnection() as HttpsURLConnection
            https.hostnameVerifier = DO_NOT_VERIFY
            https.connectTimeout = connTimeout
            https.readTimeout = readTimeout
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return https
    }

    companion object {
        private const val URL = "https://www.kduniv.ac.kr/www/index.php?pCode=1414720005&CgCode="

        @Throws(Exception::class)
        fun getDocument(code: String): Document {
            val sslConnect = SSLConnect()
            val url = convertUrl(code)
            sslConnect.postHttps(url, 1000, 1000)
            return Jsoup.connect(url).get()
        }

        private fun convertUrl(code: String): String {
            return URL + code
        }
    }
}