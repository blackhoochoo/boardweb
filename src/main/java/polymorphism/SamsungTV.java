package polymorphism;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("samsungtv")
public class SamsungTV implements TV {
	@Autowired
//	@Qualifier("apple")
//	@Resource(name="apple")
	private Speaker speaker;
	private int price;
	
	public void initMethod() {
//		System.out.println("객체 초기화 작업 처리..");
	}
	
	public void destroyMethod() {
//		System.out.println("객체 삭제 전에 처리할 로직 처리..");
	}
	public SamsungTV() {
		super();
		System.out.println("===> SamsungTV) 객체 생성");
	}
	
	public SamsungTV(Speaker speaker) {
		System.out.println("===> SamsungTV(2) 객체 생성");
		this.speaker = speaker;
	}
	
	public SamsungTV(Speaker speaker, int price) {
		System.out.println("===> SamsungTV(3) 객체 생성");
		this.speaker = speaker;
		this.price = price;
	}

	public void setSpeaker(Speaker speaker) {
		System.out.println("===> setSpeaker() 호출");
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		System.out.println("===> setPrice() 호출");
		this.price = price;
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTV---전원 켠다. (가격: " + price + ")");
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV---전원 끈다.");
	}

	@Override
	public void volumeUp() {
//		System.out.println("SamsungTV---소리 올린다.");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
//		System.out.println("SamsungTV---소리 내린다.");
		speaker.volumeDown();
	}

}
