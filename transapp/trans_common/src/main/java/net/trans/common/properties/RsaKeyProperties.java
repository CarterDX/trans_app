package net.trans.common.properties;

import net.trans.common.utils.RsaUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;


import javax.annotation.PostConstruct;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *  token秘钥配置
 *  @Author Cx
 *  @Date 2021-02-03
 *  @Version 1.0
 */

@ConfigurationProperties("rsa.key")
public class RsaKeyProperties {

    private String pubKeyFile;
    private String priKeyFile;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void createRsaKey() throws Exception {
        publicKey = RsaUtils.getPublicKey(pubKeyFile);
        privateKey = RsaUtils.getPrivateKey(priKeyFile);
    }

    public String getPubKeyFile() {
        return pubKeyFile;
    }

    public void setPubKeyFile(String pubKeyFile) {
        this.pubKeyFile = pubKeyFile;
    }

    public String getPriKeyFile() {
        return priKeyFile;
    }

    public void setPriKeyFile(String priKeyFile) {
        this.priKeyFile = priKeyFile;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }
}
