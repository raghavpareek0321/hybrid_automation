// api/security/KeyLoader.java
package api.security;
import java.nio.file.*; import java.security.*; import java.security.spec.*;
import java.util.Base64;

public class KeyLoader {
  public static PrivateKey loadPrivatePem(String classpath) {
    try {
      String pem = new String(KeyLoader.class.getResourceAsStream(classpath).readAllBytes());
      String b64 = pem.replaceAll("-----\\w+ PRIVATE KEY-----", "").replaceAll("\\s",""); 
      byte[] der = Base64.getDecoder().decode(b64);
      PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(der);
      return KeyFactory.getInstance("RSA").generatePrivate(spec);
    } catch (Exception e) { throw new RuntimeException("Private key load failed: " + classpath, e); }
  }
  public static PublicKey loadPublicPem(String classpath) {
    try {
      String pem = new String(KeyLoader.class.getResourceAsStream(classpath).readAllBytes());
      String b64 = pem.replaceAll("-----\\w+ PUBLIC KEY-----", "").replaceAll("\\s","");
      byte[] der = Base64.getDecoder().decode(b64);
      X509EncodedKeySpec spec = new X509EncodedKeySpec(der);
      return KeyFactory.getInstance("RSA").generatePublic(spec);
    } catch (Exception e) { throw new RuntimeException("Public key load failed: " + classpath, e); }
  }
}
