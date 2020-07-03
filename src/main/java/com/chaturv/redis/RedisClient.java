package com.chaturv.redis;

import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vineet on 5/6/16.
 */
public class RedisClient {

    //0. keytool -genkey -alias redis_test -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore redis_test.p12 -validity 365
    //1. keytool -exportcert -keystore redis_test.p12 -alias redis_test -file redis_test.p12.cert
    //2. keytool -import -trustcacerts -keystore /home/vineet/apps/jdk/jdk1.8.0_66/jre/lib/security/cacerts -storepass changeit -noprompt -alias redis_test -file redis_test.p12.cert

    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                (hostname, sslSession) -> hostname.equals("localhost"));
    }


    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException {
        RestTemplate template = new RestTemplate();

        String response = template.getForObject("https://localhost:8443/setValue?key=foo:key&value=bar:value", String.class);
        System.out.println(response);
    }
}
