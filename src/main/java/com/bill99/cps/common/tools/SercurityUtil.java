package com.bill99.cps.common.tools;

import java.security.InvalidKeyException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SercurityUtil {

	/**
	 * �������
	 * 
	 * @param eti
	 * @param encKey
	 * @return
	 */
	public static String decryptTxnInfo(String eti, String encKey) {

		byte[] edata = BCDASCII.fromASCIIToBCD(eti, 0, eti.length(), false);
		byte[] data = decryptData(encKey, edata, true);
		String str = BCDASCII.fromBCDToASCIIString(data, 1, data[0], false);
		return str.replace("D", "=");
	}

	/**
	 * �������
	 * 
	 * @param ti
	 * @param encKey
	 * @return
	 */
	public static String encryptTxnInfo(String ti, String encKey) {

		byte[] d = BCDASCII.fromASCIIToBCD(ti, 0, ti.length(), false);
		byte[] data = new byte[d.length + 1];
		data[0] = (byte) ti.length();
		System.arraycopy(d, 0, data, 1, d.length);
		byte[] edata = encryptData(encKey, data, true);
		return BCDASCII.fromBCDToASCIIString(edata, 0, edata.length * 2, false);
	}

	public static byte[] decryptData(String strPkey, byte[] encData, boolean padding) {
		byte[] pkey = BCDASCII.fromASCIIToBCD(strPkey, 0, 16, false);
		SimpleDESCrypto crypto = new SimpleDESCrypto(pkey, padding);
		return crypto.decrypt(encData);
	}

	public static byte[] encryptData(String strPkey, byte[] data, boolean padding) {
		byte[] pkey = BCDASCII.fromASCIIToBCD(strPkey, 0, 16, false);

		SimpleDESCrypto crypto = new SimpleDESCrypto(pkey, padding);
		return crypto.encrypt(data);
	}

	/*public static void main(String[] args) {
		String key = "E9E3CF7C1ABD8947";
		String srcStr = "FFBF6B296727232D";

		String decStr = SercurityUtil.decryptTxnInfo(srcStr, key);

	}*/
}

class SimpleDESCrypto {
	private Cipher theDESCipher;

	private SecretKey key;
	private boolean paddingFlag = false;

	private boolean initCipherFlag = false;

	private int initCipherMode;

	public SimpleDESCrypto() {
		try {
			if (paddingFlag == true) {
				theDESCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			} else {
				theDESCipher = Cipher.getInstance("DES/ECB/NoPadding");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private void instanceSimpleDESCrypto(byte[] keyBuf, boolean aPaddingFlag) {
		try {
			paddingFlag = aPaddingFlag;
			if (paddingFlag == true) {
				theDESCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			} else {
				theDESCipher = Cipher.getInstance("DES/ECB/NoPadding");
			}

			key = new SecretKeySpec(keyBuf, "DES");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public SimpleDESCrypto(byte[] keyBuf, boolean aPaddingFlag) {
		instanceSimpleDESCrypto(keyBuf, aPaddingFlag);
	}

	public SimpleDESCrypto(byte[] keyBuf) {
		instanceSimpleDESCrypto(keyBuf, false);
	}

	public void setPaddingFlag(boolean aPaddingFlag) {
		paddingFlag = aPaddingFlag;
		initCipherFlag = false;
	}

	public boolean getPaddingFlag() {
		return paddingFlag;
	}

	public void setKey(byte[] keyBuf) {
		key = new SecretKeySpec(keyBuf, "DES");
		initCipherFlag = false;
	}

	public byte[] getKey() {
		return key.getEncoded();
	}

	private void initCipher(int mode) throws InvalidKeyException {
		if (initCipherFlag == false || mode != initCipherMode) {
			theDESCipher.init(mode, key);
			initCipherFlag = true;
			initCipherMode = mode;
		}
	}

	public byte[] encrypt(byte[] input, int inputOffset, int inputLen) {
		try {
			initCipher(Cipher.ENCRYPT_MODE);
			return theDESCipher.doFinal(input, inputOffset, inputLen);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	public byte[] encrypt(byte[] input) {
		return encrypt(input, 0, input.length);
	}

	public int encrypt(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) {
		try {
			initCipher(Cipher.ENCRYPT_MODE);
			return theDESCipher.doFinal(input, inputOffset, inputLen, output, outputOffset);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public int encrypt(byte[] input, int inputOffset, int inputLen, byte[] output) {
		return encrypt(input, inputOffset, inputLen, output, 0);
	}

	public byte[] decrypt(byte[] input, int inputOffset, int inputLen) {
		try {
			initCipher(Cipher.DECRYPT_MODE);
			return theDESCipher.doFinal(input, inputOffset, inputLen);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public byte[] decrypt(byte[] input) {
		return decrypt(input, 0, input.length);
	}

	public int decrypt(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) {
		try {
			initCipher(Cipher.DECRYPT_MODE);
			return theDESCipher.doFinal(input, inputOffset, inputLen, output, outputOffset);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public int decrypt(byte[] input, int inputOffset, int inputLen, byte[] output) {
		return decrypt(input, inputOffset, inputLen, output, 0);
	}

	public void setEncryptMode() {
		try {
			initCipher(Cipher.ENCRYPT_MODE);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public void setDecryptMode() {
		try {
			initCipher(Cipher.DECRYPT_MODE);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public byte[] update(byte[] input, int inputOffset, int inputLen) {
		return theDESCipher.update(input, inputOffset, inputLen);
	}

	public byte[] update(byte[] input, int inputLen) {
		return update(input, 0, inputLen);
	}

	public byte[] update(byte[] input) {
		return update(input, 0, input.length);
	}

	public int update(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) {
		try {
			return theDESCipher.update(input, inputOffset, inputLen, output, outputOffset);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public int update(byte[] input, int inputOffset, int inputLen, byte[] output) {
		return update(input, inputOffset, inputLen, output, 0);
	}

	public byte[] doFinal(byte[] input, int inputOffset, int inputLen) {
		try {
			return theDESCipher.doFinal(input, inputOffset, inputLen);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public byte[] doFinal(byte[] input) {
		return doFinal(input, 0, input.length);
	}

	public int doFinal(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) {
		try {
			return theDESCipher.doFinal(input, inputOffset, inputLen, output, outputOffset);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public int doFinal(byte[] input, int inputOffset, int inputLen, byte[] output) {
		return doFinal(input, inputOffset, inputLen, output, 0);
	}
}

class BCDASCII {
	/**
	 * ��ĸ'A'��ASCII����ֵ
	 */
	public final static byte ALPHA_A_ASCII_VALUE = 0x41;

	/**
	 * ��ĸ'a'��ASCII����ֵ
	 */
	public final static byte ALPHA_a_ASCII_VALUE = 0x61;

	/**
	 * ����'0'��ASCII����ֵ
	 */
	public final static byte DIGITAL_0_ASCII_VALUE = 0x30;

	private BCDASCII() {
	}

	/**
	 * ��BCD����ת����ASCII����.
	 * 
	 * @param bcdBuf
	 *            , BCD���뻺����
	 * @param bcdOffset
	 *            , BCD���뻺������ʼƫ��
	 * @param asciiBuf
	 *            , ASCII���뻺����
	 * @param asciiOffset
	 *            , ASCII���뻺�������ʼƫ��
	 * @param asciiLen
	 *            , ����ASCII����ʱ����Ϣ����
	 * @param rightAlign
	 *            , �����ASCII��ʱ���õ��Ҷ��뷽ʽ��־
	 * @return, ASCII���뻺����
	 */
	public static void fromBCDToASCII(byte[] bcdBuf, int bcdOffset, byte[] asciiBuf, int asciiOffset, int asciiLen,
			boolean rightAlignFlag) {
		int cnt;

		if (((asciiLen & 1) == 1) && rightAlignFlag) {
			cnt = 1;
			asciiLen++;
		} else
			cnt = 0;

		for (; cnt < asciiLen; cnt++, asciiOffset++) {
			asciiBuf[asciiOffset] = (byte) ((((cnt) & 1) == 1) ? (bcdBuf[bcdOffset++] & 0x0f)
					: ((bcdBuf[bcdOffset] >> 4) & 0x0f));
			asciiBuf[asciiOffset] = (byte) (asciiBuf[asciiOffset] + ((asciiBuf[asciiOffset] > 9) ? (ALPHA_A_ASCII_VALUE - 10)
					: DIGITAL_0_ASCII_VALUE));
		}
	}

	/**
	 * ��BCD����ת����ASCII����.
	 * 
	 * @param bcdBuf
	 *            , BCD���뻺����
	 * @param asciiLen
	 *            , ͳһ����ASCII����ʱ����Ϣ����
	 * @param rightAlignFlag
	 *            , �����ASCII��ʱ���õ��Ҷ��뷽ʽ��־
	 * @return, ASCII���뻺����
	 */
	public static byte[] fromBCDToASCII(byte[] bcdBuf, int bcdOffset, int asciiLen, boolean rightAlignFlag) {
		byte[] asciiBuf = new byte[asciiLen];
		fromBCDToASCII(bcdBuf, bcdOffset, asciiBuf, 0, asciiLen, rightAlignFlag);

		return asciiBuf;
	}

	public static String fromBCDToASCIIString(byte[] bcdBuf, int bcdOffset, int asciiLen, boolean rightAlignFlag) {
		try {
			return new String(fromBCDToASCII(bcdBuf, bcdOffset, asciiLen, rightAlignFlag), "GBK");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * ��ASCII����ת����BCD����.
	 * 
	 * @param asciiBuf
	 *            , ASCII���뻺����
	 * @param asciiOffset
	 *            , ASCII���뻺�������ʼƫ��
	 * @param asciiLen
	 *            , ����ASCII����ʱ����Ϣ����
	 * @param bcdBuf
	 *            , BCD���뻺����
	 * @param bcdOffset
	 *            , BCD���뻺������ʼƫ��
	 * @param rightAlignFlag
	 *            , �����ASCII��ʱ���õ��Ҷ��뷽ʽ��־
	 */
	public static void fromASCIIToBCD(byte[] asciiBuf, int asciiOffset, int asciiLen, byte[] bcdBuf, int bcdOffset,
			boolean rightAlignFlag) {
		int cnt;
		byte ch, ch1;

		if (((asciiLen & 1) == 1) && rightAlignFlag) {
			ch1 = 0;
		} else {
			ch1 = 0x55;
		}

		for (cnt = 0; cnt < asciiLen; cnt++, asciiOffset++) {
			if (asciiBuf[asciiOffset] >= ALPHA_a_ASCII_VALUE)
				ch = (byte) (asciiBuf[asciiOffset] - ALPHA_a_ASCII_VALUE + 10);
			else if (asciiBuf[asciiOffset] >= ALPHA_A_ASCII_VALUE)
				ch = (byte) (asciiBuf[asciiOffset] - ALPHA_A_ASCII_VALUE + 10);
			else if (asciiBuf[asciiOffset] >= DIGITAL_0_ASCII_VALUE)
				ch = (byte) (asciiBuf[asciiOffset] - DIGITAL_0_ASCII_VALUE);
			else
				ch = 0x00;

			if (ch1 == 0x55)
				ch1 = ch;
			else {
				bcdBuf[bcdOffset] = (byte) (ch1 << 4 | ch);
				bcdOffset++;
				ch1 = 0x55;
			}
		}

		if (ch1 != 0x55)
			bcdBuf[bcdOffset] = (byte) (ch1 << 4);
	}

	public static void fromASCIIToBCD(String asciiStr, int asciiOffset, int asciiLen, byte[] bcdBuf, int bcdOffset,
			boolean rightAlignFlag) {
		try {
			byte[] asciiBuf = asciiStr.getBytes("GBK");
			fromASCIIToBCD(asciiBuf, asciiOffset, asciiLen, bcdBuf, bcdOffset, rightAlignFlag);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * ��ASCII����ת����BCD����.
	 * 
	 * @param asciiBuf
	 *            , ASCII���뻺����
	 * @param asciiOffset
	 *            , ASCII���뻺�������ʼƫ��
	 * @param asciiLen
	 *            , ͳһ����ASCII����ʱ����Ϣ����
	 * @param rightAlignFlag
	 *            , �����ASCII��ʱ���õ��Ҷ��뷽ʽ��־
	 * @return, BCD���뻺����
	 */
	public static byte[] fromASCIIToBCD(byte[] asciiBuf, int asciiOffset, int asciiLen, boolean rightAlignFlag) {
		byte[] bcdBuf = new byte[(asciiLen + 1) / 2];
		fromASCIIToBCD(asciiBuf, asciiOffset, asciiLen, bcdBuf, 0, rightAlignFlag);

		return bcdBuf;
	}

	public static byte[] fromASCIIToBCD(String asciiStr, int asciiOffset, int asciiLen, boolean rightAlignFlag) {
		try {
			byte[] asciiBuf = asciiStr.getBytes("GBK");
			return fromASCIIToBCD(asciiBuf, asciiOffset, asciiLen, rightAlignFlag);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}