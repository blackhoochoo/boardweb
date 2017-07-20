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
//		System.out.println("��ü �ʱ�ȭ �۾� ó��..");
	}
	
	public void destroyMethod() {
//		System.out.println("��ü ���� ���� ó���� ���� ó��..");
	}
	public SamsungTV() {
		super();
		System.out.println("===> SamsungTV) ��ü ����");
	}
	
	public SamsungTV(Speaker speaker) {
		System.out.println("===> SamsungTV(2) ��ü ����");
		this.speaker = speaker;
	}
	
	public SamsungTV(Speaker speaker, int price) {
		System.out.println("===> SamsungTV(3) ��ü ����");
		this.speaker = speaker;
		this.price = price;
	}

	public void setSpeaker(Speaker speaker) {
		System.out.println("===> setSpeaker() ȣ��");
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		System.out.println("===> setPrice() ȣ��");
		this.price = price;
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTV---���� �Ҵ�. (����: " + price + ")");
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV---���� ����.");
	}

	@Override
	public void volumeUp() {
//		System.out.println("SamsungTV---�Ҹ� �ø���.");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
//		System.out.println("SamsungTV---�Ҹ� ������.");
		speaker.volumeDown();
	}

}
