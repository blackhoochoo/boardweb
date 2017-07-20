package polymorphism;

import org.springframework.stereotype.Component;

public class SonySpeaker implements Speaker {
	public SonySpeaker() {
		System.out.println("===> SonySpeaker 按眉 积己");
	}
	/* (non-Javadoc)
	 * @see polymorphism.Speaker#volumeUp()
	 */
	@Override
	public void volumeUp() {
		System.out.println("SonySpeaker--家府 棵赴促.");
	}
	/* (non-Javadoc)
	 * @see polymorphism.Speaker#volumeDown()
	 */
	@Override
	public void volumeDown() {
		System.out.println("SonySpeaker--家府 郴赴促.");
	}
}
