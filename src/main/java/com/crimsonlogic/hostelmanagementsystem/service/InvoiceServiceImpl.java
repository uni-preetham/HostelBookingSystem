package com.crimsonlogic.hostelmanagementsystem.service;

import com.crimsonlogic.hostelmanagementsystem.entity.Booking;
import com.crimsonlogic.hostelmanagementsystem.entity.Payment;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    public void generateInvoice(HttpServletResponse response, Booking booking, Payment payment) throws DocumentException, IOException {
        // Set the response type to PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=invoice_" + booking.getBookingId() + ".pdf");

        // Create a new document
        Document document = new Document(PageSize.A4);

        // Attach the PdfWriter to the document and the servlet response's output stream
        PdfWriter.getInstance(document, response.getOutputStream());

        // Open the document for writing
        document.open();

     // Add image at the top
        try {
        	URL url = new URL("https://raw.githubusercontent.com/uni-preetham/Test/master/Group%201.png"); 
            Image img = Image.getInstance(url); // Update with your image path
            img.setAlignment(Element.ALIGN_LEFT);
            img.scaleToFit(100f, 100f); // Adjust size as needed
            document.add(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        

        
        
        // Set fonts
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font regularFont = new Font(Font.FontFamily.HELVETICA, 12);

        // Add invoice title
        document.add(new Paragraph("Invoice", titleFont));

        // Add booking details
        PdfPTable bookingTable = new PdfPTable(2);
        bookingTable.setWidthPercentage(100);
        bookingTable.setSpacingBefore(10f);

        document.addTitle("Invoice");
        document.addSubject("Booking and Payment Invoice");

        addTableHeader(bookingTable, "Booking Details");
        bookingTable.addCell("Booking ID");
        bookingTable.addCell(booking.getBookingId());
        bookingTable.addCell("Tenant Name");
        bookingTable.addCell(booking.getTenant().getTenantFname() + " " + booking.getTenant().getTenantLname());
        bookingTable.addCell("Room Type");
        bookingTable.addCell(booking.getRoom().getRoomType());
        bookingTable.addCell("Check-In Date");
        bookingTable.addCell(booking.getCheckInDate().toString());
        bookingTable.addCell("Check-Out Date");
        bookingTable.addCell(booking.getCheckOutDate().toString());
        bookingTable.addCell("Total Price");
        bookingTable.addCell(booking.getTotalPrice().toString());

        document.add(bookingTable);

        // Add payment details
        PdfPTable paymentTable = new PdfPTable(2);
        paymentTable.setWidthPercentage(100);
        paymentTable.setSpacingBefore(10f);

        addTableHeader(paymentTable, "Payment Details");
        paymentTable.addCell("Payment ID");
        paymentTable.addCell(payment.getPaymentId());
        paymentTable.addCell("Payment Date");
        paymentTable.addCell(payment.getPaymentDate().toString());
        paymentTable.addCell("Payment Method");
        paymentTable.addCell(payment.getPaymentMethod());
        paymentTable.addCell("Amount Paid");
        paymentTable.addCell(payment.getAmount().toString());

        document.add(paymentTable);

        // Close the document
        document.close();
    }

    private void addTableHeader(PdfPTable table, String headerTitle) {
        PdfPCell header = new PdfPCell(new Phrase(headerTitle));
        header.setColspan(2);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header);
    }
}
