package Frame;


import java.util.ArrayList;

public class Tool
{
    public static String leftZero(int Byte, long Data){
        String Hex = Long.toHexString(Data);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Byte; i++) {
            sb.append("00");
        }
        int sb_len = sb.length();
        int hex_len = Hex.length();
        sb.replace(sb_len-hex_len, sb_len, Hex);
        return sb.toString();
    }



    public static int[] calculateCRC( String frame) {
        int crc = 0xFFFF;
        int j = 0;
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0 ; i < frame.length()-1; i+=2){
            data.add(Integer.parseInt(frame.substring(i, i+2)));
        }
        for (int k = 0 ; k < data.size(); k++) {
            int b = data.get(k);
            crc ^= b & 0xFF;
            for (int i = 0; i < 8; i++) {

                if ((crc & 0x0001) != 0) {
                    crc >>= 1;
                    crc ^= 0xA001;
                } else {
                    crc >>= 1;
                }
            }
        }
        return new int[]{(int) (crc & 0x00FF), (int) (crc & 0xFF00 >> 8)};
    }

    public static String bytesToHexString(byte[] bytes, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02X", bytes[i]));
        }
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    public static void main(String[] args) {
        long l = 38;
        //String s = leftZero(2, l);
        //Long L = new Long(s);
        int [] t = calculateCRC("010203");
        System.out.println(" "+t[0]+t[1]);
    }

}
