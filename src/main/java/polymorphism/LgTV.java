package polymorphism;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tv") // application context�� getBean�� ���� "tv"�� �ش��ϴ� bean�� ������
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
		System.out.println("===> LgTV(3,Constructor Injection) ��ü ����");
		this.speaker = speaker;
		this.price = price;
	}

	public LgTV() {
		System.out.println("===> LgTV ��ü ����");
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public void powerOn() {
		System.out.println("LgTV---���� �Ҵ�.");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV---���� ����.");

	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
//		System.out.println("LgTV---���� �ø���");
	}

	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}

}
