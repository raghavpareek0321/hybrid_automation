// api/security/SignatureHelper.java
package api.security;
import java.nio.charset.StandardCharsets; import java.security.*; import java.util.Base64;

public class SignatureHelper {
  public static String canonicalize(String method, String path, String requestTime, String body) {
    // TODO: replace with exact ISA rule (order/separators/line breaks)
    return method + "\n" + path + "\n" + requestTime + "\n" + (body == null ? "" : body);
  }
  public static String signRSA256(PrivateKey key, String canonical) {
    try {
      Signature s = Signature.getInstance("SHA256withRSA");
      s.initSign(key);
      s.update(canonical.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(s.sign());
    } catch (Exception e) { throw new RuntimeException("Signing failed", e); }
  }
  public static boolean verifyRSA256(PublicKey key, String canonical, String base64Sig) {
    try {
      Signature s = Signature.getInstance("SHA256withRSA");
      s.initVerify(key);
      s.update(canonical.getBytes(StandardCharsets.UTF_8));
      return s.verify(Base64.getDecoder().decode(base64Sig));
    } catch (Exception e) { throw new RuntimeException("Verify failed", e); }
  }
}
