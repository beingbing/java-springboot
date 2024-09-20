package be.springboot.pp.reflection.beans;

@MyBean
public class WebApi {
    private DBAccessor dbAccessor;

    private Logger logger;

    public DBAccessor getDbAccessor() {
        return dbAccessor;
    }

    public Logger getLogger() {
        return logger;
    }
}