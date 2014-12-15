package db;

public class DBWriterFactory {

	public static DBWriter createDBWriter(String className){
	    Class clazz = null;
	    Object writerObj = null;
		try {
			clazz = Class.forName("db." + className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    try {
			writerObj = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	    return (DBWriter)writerObj;
	}
}
