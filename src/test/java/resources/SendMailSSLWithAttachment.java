package resources;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailSSLWithAttachment {
 // public static void main(String[] args) throws IOException
	public  void email() throws IOException    
	{		 
	   try {
		ZipUtility.pack("./test-output/HtmlReport", "./test-output/APIReport.zip");
	} catch (IOException e1) {
		System.out.println("Extent report zip path");
		e1.printStackTrace();
	}
	  // ZipUtil.pack(new File("./test-output/HtmlReport/ExtentHtml.html"), new File("./test-output/HtmlReport/APIReport.zip"));
	   
	   final SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss");
	   Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
	   String time =sdf.format(timestamp);
	   String zipPathTime ="./test-output/APIReport_"+time+".zip";
	        File oldName = 
	         new File("./test-output/APIReport.zip"); 
	        File newName =  
	         new File(zipPathTime); 
	 
	        if (oldName.renameTo(newName))  
	            System.out.println("Renamed successfully");         
	        else 
	            System.out.println("Error");         
	    	
	   
	   // Create object of Property file
		
	   Properties props = new Properties();

		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");

		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

					
						try {
							return new PasswordAuthentication(Utils.getGlobalValue("fromEmail"),Utils.getGlobalValue("emailPassword"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					
					}

				});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress(Utils.getGlobalValue("fromEmail")));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(Utils.getGlobalValue("toEmail")));
            
                        // Add the subject link
			message.setSubject("Compucom Connect API Automation Report");

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Set the body of email
			messageBodyPart1.setText("Hi Team\nPFA.. API Automation Report for compucom connect\nNote: Download the attached report to open it.");
			
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			
			// Mention the file which you want to send
			//String extentFileName = "./test-output/APIReport_"+time+".zip";			
			// Create data source and pass the filename
			DataSource source = new FileDataSource(zipPathTime);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(zipPathTime);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);


			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}

}