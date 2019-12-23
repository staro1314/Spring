package com.example.spring;

import org.junit.Test;

import java.util.Base64;

/**
 * @author: Staro
 * @date: 2019/12/10 9:01
 * @Description:
 */
public class TokenEncry {

    @Test
    public void testEncodeToken(){
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
//                ".eyJzdWIiOiJTdWJqZWN0IiwidXNlcl9uYW1lIjoic3Rhcm8uZnkuemhhb0BtYWlsLmZveGNvbm4uY29tIiwic2NvcGUiOlsiYWxsIl0sImlzcyI6IkZpaUNsb3VkIiwiZXhwIjoxNTc1OTQ3ODg5LCJpYXQiOjE1NzU5NDYwODksImF1dGhvcml0aWVzIjpbImFkbWluIl0sImp0aSI6IjU3ZTVjNmM2LThiZTktNDUyOS04ZDA5LTE5YzkyZTEzNDM4YSIsImNsaWVudF9pZCI6IjY0MDBjMmY0LThlZmQtNDgxYy1hNDI3LWE3NDU3ZmRlOTRkMiJ9" +
//                       ".B2JB3tjxpFzf_zF6oiIQl9LndvYQaxXOZHh4RzpX8g4";
//        System.out.println(token);
        System.out.println();
        String token = "iDS_ZSH_FOXCONN_NB";

//        String tokenEncode = Base64.getEncoder().encodeToString(token.getBytes());
//        System.out.println(tokenEncode);
//        String offsetCode = tokenEncode.substring(tokenEncode.length() - 15);
//        String offsetCodeEncode = Base64.getEncoder().encodeToString(offsetCode.getBytes());
//        System.out.println(offsetCodeEncode);
//        System.out.println(offsetCodeEncode + tokenEncode);
//        System.out.println();
//        testDecoderToken(offsetCodeEncode + tokenEncode);


        String[] split = token.split("\\.");
        StringBuilder newToken = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            int subLen = split[i].length() / 2;
            String substring = split[i].substring(subLen);
            StringBuilder sb = new StringBuilder(substring);
            String suffixStr = sb.reverse().toString();
            String preStr = split[i].substring(0, subLen);
            if (i != (split.length - 1)) {
                newToken.append(preStr).append(suffixStr).append(".");
            } else {
                newToken.append(preStr).append(suffixStr);
            }
        }
        System.out.println(newToken.toString());
        String encodeString = Base64.getEncoder().encodeToString(newToken.toString().getBytes());
        System.out.println(encodeString);
        System.out.println();
        testDecoderToken(encodeString);

    }

    public void testDecoderToken(String encodeToken) {
        String decoderString = new String(Base64.getDecoder().decode(encodeToken));
        String[] split = decoderString.split("\\.");
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            int subLen = split[i].length() / 2;
            String substring = split[i].substring(subLen);
            String preStr = split[i].substring(0, subLen);
            StringBuilder sb = new StringBuilder(substring);
            String suffixStr = sb.reverse().toString();
            if (i != (split.length - 1)) {
                token.append(preStr).append(suffixStr).append(".");
            } else {
                token.append(preStr).append(suffixStr);
            }
        }
        System.out.println(token.toString());
    }

    @Test
    public void testDecoderToken2(){
//        String token="VVpxZVdsdFNVTTFSazVuZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmxlSEFpT2pFMU5qSTVNVEl5T1RJc0luVnpaWEpmYm1GdFpTSTZJblZ6WlhKZk1TSXNJbUYxZEdodmNtbDBhV1Z6SWpwYklsVlRSVklpWFN3aWFuUnBJam9pWkRnd09EWmpNREV0WXpjelppMDBPRGd6TFRnek9EY3RNREJpWWpCbVpUWTFNR1k1SWl3aVkyeHBaVzUwWDJsa0lqb2lZMnhwWlc1MFh6SWlMQ0p6WTI5d1pTSTZXeUpoYkd3aVhYMC5uUWZEcE81ZklaNUJKUXI4Rnl4UGtxb3NIUk14UERoTUZqeWltSUM1Rk5n";
        String token="VVpxZVdsdFNVTTFSazVuZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmxlSEFpT2pFMU5qSTVNVEl5T1RJc0luVnpaWEpmYm1GdFpTSTZJblZ6WlhKZk1TSXNJbUYxZEdodmNtbDBhV1Z6SWpwYklsVlRSVklpWFN3aWFuUnBJam9pWkRnd09EWmpNREV0WXpjelppMDBPRGd6TFRnek9EY3RNREJpWWpCbVpUWTFNR1k1SWl3aVkyeHBaVzUwWDJsa0lqb2lZMnhwWlc1MFh6SWlMQ0p6WTI5d1pTSTZXeUpoYkd3aVhYMC5uUWZEcE81ZklaNUJKUXI4Rnl4UGtxb3NIUk14UERoTUZqeWltSUM1Rk5n";
        String decoderString = new String(Base64.getDecoder().decode(token));
        System.out.println(decoderString);
        token="iDS_ZSH_FOXCONN_NB";
    }
}
