package framework.remote.hessian;

import com.caucho.hessian.io.SerializerFactory;

public class ExtendSerializerFactory extends SerializerFactory{

	public ExtendSerializerFactory(){
		super();
		addFactory(new BigDecimalSerializerFactory());
	}
}
