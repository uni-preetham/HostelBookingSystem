package com.crimsonlogic.hostelmanagementsystem.util;

import java.io.Serializable;
import java.util.Random;
 
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
 
public class BookingIdGenerator implements IdentifierGenerator {
	private static final int MIN_ID = 100; 
	private static final int MAX_ID = 999; 
	private Random random = new Random();
 
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		long lid = MIN_ID + random.nextInt(MAX_ID - MIN_ID + 1);
		String id = "BK" + lid;
		return id;
	}
}