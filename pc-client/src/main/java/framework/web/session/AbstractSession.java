package framework.web.session;

public abstract class AbstractSession implements ISession{

	@Override
	public void replace(Object key,Object value){
		throw new NoSuchMethodError();
	}
	
}
