package com.crimsonlogic.hostelmanagementsystem.util;

import java.io.Serializable;
import java.util.Random;
 
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
 
public class PaymentIdGenerator implements IdentifierGenerator {
	private static final int MIN_ID = 10000; 
	private static final int MAX_ID = 99999; 
	private Random random = new Random();
 
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		long lid = MIN_ID + random.nextInt(MAX_ID - MIN_ID + 1);
		String id = "PAY" + lid;
		return id;
	}
}