package com.asc.holiday.utils;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

/**
 * User:dijian
 * Date:2018/6/28
 */
public class StringSequenceGenerator extends SequenceGenerator implements IdentifierGenerator {

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(new LongType(), params, serviceRegistry);
    }

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        Serializable sequnece = super.generate(session, object);
        Long id = Long.parseLong(sequnece.toString());
        return String.format("%1$012d", id);
    }
}
