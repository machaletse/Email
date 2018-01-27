package email;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author desarrollo_ifrem
 */
public class EmailSender {
    public static boolean sendEmail(String from,String password,String message,String to[]){
        String host ="smtp.gmail.com";
      //   Properties props = new Properties();
         Properties props = System.getProperties();
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.host",host);
         props.put("mail.smtp.user", from);
         props.put("mail.smtp.password", password);
         props.put("mail.smtp.port", 587);
         props.put("mail.smtp.auth", "true");
         Session session = Session.getDefaultInstance(props , null);
         
         MimeMessage mimeM = new  MimeMessage(session);
         
         try {
         mimeM.setFrom(new InternetAddress(from));   
         
         //now get the address of recipent address
         InternetAddress[] toAddresses = new InternetAddress[to.length];
             for (int i = 0; i < to.length; i++) 
             {
              toAddresses[i] = new InternetAddress(to[i]);
             }
                 for (int i = 0; i < toAddresses.length; i++) {
                   
                   
                     mimeM.setRecipient(Message.RecipientType.TO, toAddresses[i]);                            ;
                                         
                 }
                 
                 mimeM.setSubject("mail using javaimal apo");
                 //set message to assue, AFAIR
                 
                 mimeM.setText(message);
                 Transport t = session.getTransport("smtp");
                t.connect(host,from,password);
                t.sendMessage(mimeM, mimeM.getAllRecipients());
                t.close();
                return true;
                 
                 
        } catch (MessagingException me) {
            me.printStackTrace();
        }
         
         
         
        return false;
    }
    
}
