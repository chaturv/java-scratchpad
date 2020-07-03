package com.chaturv.fileio.split.register;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.security.PrivateKey;
import java.util.UUID;

public class RegisterUpload {

    private static final String RSA_PRIVATE_KEY_LOC = "rsa.private.key.loc";

    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                (hostname, sslSession) -> hostname.equals("localhost"));
    }

    //1. read private key
    //2. encrypt random string
    //3. send request with encrypted string
    //4A. if SU, return
    //4B. if ERR (key not recognized), return
    //4C. if WAIT, return - let client decide

    private PrivateKey privateKey;
    RestTemplate template;

    private RegisterUpload() {
//        this.privateKey = readPrivateKey();
        this.template = new RestTemplate();
    }

    public RegistrationStatus register(String fileName) {
        String url = "https://localhost:8443/registerUpload?fileName=%s"; //TODO: send string encrypted with private key

        String random = UUID.randomUUID().toString();
        String encrypted = ""; //TODO: encrypt using private key

        url = String.format(url, fileName);
        RegistrationStatus status = template.getForObject(url, RegistrationStatus.class);
        System.out.println(status);

        return status;
    }


    //1. read System property RSA_PRIVATE_KEY_LOC
    private PrivateKey readPrivateKey() {
        String location = System.getProperty(RSA_PRIVATE_KEY_LOC);
        if (StringUtils.isBlank(location)) {
            throw new RuntimeException("RSA private key location not set via system property : " +  RSA_PRIVATE_KEY_LOC);
        }

        //TODO: use SSHRsaCrypto
        return null;
    }

    public static RegisterUpload get() {
        return Holder.get();
    }

    private static class Holder {
        private static RegisterUpload instance;

        private static RegisterUpload get() {
            if (instance == null) {
                instance = new RegisterUpload();
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        RegisterUpload.get().register("Five-C-s-of-Cinematography.pdf");
    }
}
