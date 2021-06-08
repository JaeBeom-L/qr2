package kr.co.gda.qr;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class QRService {
	
	// 사용자 이름 지정 메서드
	public String getUserName() {
		return "이재범";
	}
	
	// 시스템 정보 추출 메서드
	public String getSystemInfo() {
		StringBuffer sf = new StringBuffer();
		String s = System.getProperty("os.name");
		sf.append(s);
		s = System.getProperty("os.version");
		sf.append(","+s);
		return sf.toString();
	}
	
	// 네트워크 정보 추출 메서드
	public String  getNetworkInfo() throws UnknownHostException {
		StringBuffer sf = new StringBuffer();
		InetAddress address = InetAddress.getLocalHost();
		String s = address.getHostName();
		sf.append(s);
		s = address.getHostAddress();
		sf.append(","+s);
		return sf.toString();
	}
}
