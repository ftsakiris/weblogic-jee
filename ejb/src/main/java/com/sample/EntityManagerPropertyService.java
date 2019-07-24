package com.sample;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class EntityManagerPropertyService {

    public Map getProperties() {
        final Map props = new HashMap();
        props.put(PersistenceUnitProperties.JTA_DATASOURCE, "jdbc/ENSDS");
        return props;
    }

}
