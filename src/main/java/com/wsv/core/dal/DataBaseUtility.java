package com.wsv.core.dal;

import com.wsv.core.util.ConfigProperty;
import org.apache.commons.dbcp2.BasicDataSource;

public class DataBaseUtility {
    private static BasicDataSource dataSource;
    private static ConfigProperty property = ConfigProperty.getInstance();

    public static BasicDataSource getDataSource()
    {
        if (dataSource == null)
        {
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName(property.readProperty("db.driver"));
            ds.setUrl(property.readProperty("db.url"));
            ds.setUsername(property.readProperty("db.user"));
            ds.setPassword(property.readProperty("db.password"));

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);

            dataSource = ds;
        }
        return dataSource;
    }
}
