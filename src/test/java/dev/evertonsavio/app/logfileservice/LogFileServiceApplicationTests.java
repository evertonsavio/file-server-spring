package dev.evertonsavio.app.logfileservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LogFileServiceApplicationTests {

	@Test
	void contextLoads() {

		byte a = (byte) 0b10010110;

		System.out.println("A byte representation is: " +  a);
		int i = 150;
		byte b = (byte) i; //0b11111101;

		System.out.println("Decimal representation: " + b);
		System.out.println("Decimal unsigned representation: " + Byte.toUnsignedInt(b));

		System.out.println("HEX OF 150: " + Integer.toHexString(150 & 0xFF));
		System.out.println("HEX OF -106: " + Integer.toHexString(-106 & 0xFF));

		System.out.println("-----------------------------------------");

		String hex = "FF";
		short i1 = Short.parseShort(hex.substring(0, 2), 16);
		System.out.println(i1);
		byte myByte = (byte) i1;
		System.out.println(myByte);
		System.out.println(Integer.toHexString(myByte & 0xFF));

		byte b2 = -1;
		byte b3 = (byte) 0b11111111;
		int s = 255;
		byte b4 = (byte) s;
		System.out.println(Byte.toUnsignedInt(b4));

		System.out.println("b3: " + b3);
		System.out.println(Integer.toHexString(b2 & 0xFF));
		String hexCode = String.format("%02x", Byte.parseByte(String.valueOf(b2)));
		System.out.println(hexCode);

	}

}
