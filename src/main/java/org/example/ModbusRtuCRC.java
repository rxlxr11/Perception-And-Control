package org.example;


public class ModbusRtuCRC {
    public static void main(String[] args) {
        byte[] data = {0x01, 0x02, 0x03};
        byte[] crc = calculateCRC(data);
        System.out.printf("%02X%02X", crc[0], crc[1]);
    }

    public static byte[] calculateCRC(byte[] data) {
        int crc = 0xFFFF;
        for (byte b : data) {
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
        return new byte[]{(byte) (crc & 0xFF), (byte) (crc >> 8)};
    }
}
