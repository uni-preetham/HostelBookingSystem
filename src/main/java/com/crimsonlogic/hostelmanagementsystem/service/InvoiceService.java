package com.crimsonlogic.hostelmanagementsystem.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.hostelmanagementsystem.entity.Booking;
import com.crimsonlogic.hostelmanagementsystem.entity.Payment;
import com.itextpdf.text.DocumentException;

public interface InvoiceService {
	public void generateInvoice(HttpServletResponse response, Booking booking, Payment payment) throws DocumentException, IOException;
	
}
