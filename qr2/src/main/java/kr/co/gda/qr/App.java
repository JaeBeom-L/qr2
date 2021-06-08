package kr.co.gda.qr;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class App extends JFrame {
	static Logger log = LoggerFactory.getLogger(App.class); // log 출력을 위한 static 변수(클래스 안에서만 사용)
    public static void main( String[] args ) throws WriterException, IOException {
        
    	QRService qrService = new QRService(); // QRService 클래스의 객체 생성
        String userName = qrService.getUserName(); // 이름을 가져온다.
        String systemInfo = qrService.getSystemInfo(); // 시스템의 OS이름, 버전을 가져온다.
        String networkInfo = qrService.getNetworkInfo();// 시스템의 네트워크 정보를 가져온다.
        
        StringBuffer contents = new StringBuffer();// STRING타입의 변수들을 추가하기위해 StringBuffter 타입 사용
        contents.append(userName); // contents에 이름을 더한다.
        log.info(userName);// 디버깅코드
        contents.append(","+systemInfo);// contents에 시스템 정보를 더한다.
        log.info(systemInfo);// 디버깅코드
        contents.append(","+networkInfo);// contents에 네트워크 정보를 더한다.
        log.info(networkInfo);// 디버깅 코드
       
        Map<EncodeHintType, String> hints = new Hashtable<>(); // qr내용에 인코딩 설정을 위해 Map타입을 이용하여 key값을 인코딩설정타입 ,value로 문자열을 저장하는 객체 생성    
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");// map에 인코딩타입 utf-8 저장

        
        QRCodeWriter qrWriter = new QRCodeWriter(); // qr코드 기록을 위한 객체 생성
        BitMatrix martix = qrWriter.encode(contents.toString(), BarcodeFormat.QR_CODE, 300, 300, hints); // bitmatrix타입에 contents와 형식, 크기, 인코딩 타입을 설정
        // MatrixToImageConfig config = new MatrixToImageConfig(0xFFFFFFFF, 0xFF000000); qr 색상 배경색상 설정
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(martix);// 설정한 파일을 이용하여 qr이미지를 생성한다.
       
        String imageFileName = "myqr.png";// 만들어질 qr이미지 이름 지정
        ImageIO.write(qrImage, "png", new File(imageFileName)); // 이미지와, 타입, 위치를 입력해서 프로젝트에 파일 저장
        
        App app = new App(); // Jframe을 상속받은 객체 생성
        app.setTitle("QR");// 제목 qr 설정
        app.setLayout(new FlowLayout());// layout 설정
        
        ImageIcon icon = new ImageIcon(imageFileName);// 이미지 아이콘을 imageFileName이용하여 설정
        JLabel imageLabel = new JLabel(icon);// label에도 icon정보 지정
        app.add(imageLabel);// app에 더한다.
        
        app.setSize(400, 400);// 실행할 app 사이즈의 크기 지정
        app.setVisible(true);// 우리 눈으로 볼 수 있게 설정
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// x버튼을 누르면 프로그램이 종료 되도록 지정
    }
}