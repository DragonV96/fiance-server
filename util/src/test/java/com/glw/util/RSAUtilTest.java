package com.glw.util;

import org.junit.Test;

/**
 * @author : glw
 * @date : 2019/7/23
 * @time : 17:27
 * @Description : RSA工具类测试
 */
public class RSAUtilTest {

    static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUVRkgM7kMQMfVu5dpIw0qiBI0\n" +
            "Vlb217bDixcFRZahYqKfGrEpnZzXVUFSOwAaVHYipZ+Ze6avRnUqU3IrGs9SG9ay\n" +
            "EZfjcIpxLIEqTpxi06Oxts03CPz5/mAFHxsGJUbmjyPIcCcIg6U+08EU3pF0GOG4\n" +
            "h7xDiDaHk2ly/EP5ZwIDAQAB";
    static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANRVGSAzuQxAx9W7\n" +
            "l2kjDSqIEjRWVvbXtsOLFwVFlqFiop8asSmdnNdVQVI7ABpUdiKln5l7pq9GdSpT\n" +
            "cisaz1Ib1rIRl+NwinEsgSpOnGLTo7G2zTcI/Pn+YAUfGwYlRuaPI8hwJwiDpT7T\n" +
            "wRTekXQY4biHvEOINoeTaXL8Q/lnAgMBAAECgYAMA3SUediydMfZs+9xJ2aLTX+a\n" +
            "LnhlJvdUQP5gcft/zhDdZ6k2bZB+Lnivql7rHnRAqZZKHNDlgdsHNglznuSz6jTB\n" +
            "SzFXK5TBJA9GKoOKOEjxMUI6SCehNRxiZB4Skdi6lfBw8Nm91mTkbA1CWpSGQajK\n" +
            "vfhQUv/tM/in/7fjIQJBAOxJjgI5Mi3mbl4F5e+wfGqMiQ3sR0gpgH7sOiBaoyvj\n" +
            "cBBSlBRCM8mhz7GPP0lihi+GoYNsHXanQGyTHrWB6LMCQQDmC+7Dy+N1Lt6SzWiz\n" +
            "Tv2euSmcxIYYg0tT/EANWyBs3ycNqK+2kLFeaoX9WW9qLgsIoKeIqKM3YFEZurcd\n" +
            "nj59AkEAurhhdRZXtffrbXeBvciceOJ3h74Ykkjhlur8AAc//HWtX+ixxdSO8h5h\n" +
            "bzIA0+/8k9/zEFTGb7Y5bdNQqCRxiwJAK/m7+tHO+WesRIPfr+c5tE3RFpa6y5TN\n" +
            "oZBm7iatXCKMZKAKduvR/CqA0p12HuYOVefgHs+d48gVuMVom9DUQQJANahuzlG5\n" +
            "e/w9EPDqm/BbEizd+FSCQhl9vQWgGqIxvKj8JL569rZzMri4bWgwOpRfObRc0U0L\n" +
            "ERahN82RjiF6+A==";

    @Test
    public void signTest() {
        String text = "glw";
        String sign = RSAUtil.sign(text, privateKey);
        System.out.println(sign);
        System.out.println(RSAUtil.verify(text, sign, publicKey));
    }
}
