package polymorphism;

public class BeanFactory {
	public Object getBean(String beanName) {
		if("samsung".equals(beanName)) {
			return new SamsungTV();
		} else if("lg".equals(beanName)) {
			return new LgTV();
		}
		return null;
	}
}
