package polymorphism;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tv") // application context의 getBean을 통해 "tv"에 해당하는 bean을 가져옴
public class LgTV implements TV {
	@Autowired 
//	@Resource(name="sony")
//	@Qualifier("apple")
	private Speaker speaker;
	private int price;
	public void initMethod() {
		
	}
	public void destroyMethod() {
		
	}
	public LgTV(Speaker speaker, int price) {
		System.out.println("===> LgTV(3,Constructor Injection) 객체 생성");
		this.speaker = speaker;
		this.price = price;
	}

	public LgTV() {
		System.out.println("===> LgTV 객체 생성");
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public void powerOn() {
		System.out.println("LgTV---전원 켠다.");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV---전원 끈다.");

	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
//		System.out.println("LgTV---볼륨 올린다");
	}

	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}

}
