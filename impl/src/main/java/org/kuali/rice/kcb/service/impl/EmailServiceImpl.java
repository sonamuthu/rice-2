/*
 * Copyright 2007-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.kcb.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.kcb.bo.Message;
import org.kuali.rice.kcb.bo.MessageDelivery;
import org.kuali.rice.kcb.service.EmailService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.util.KNSConstants;
import org.springframework.beans.factory.annotation.Required;

import com.sun.mail.smtp.SMTPTransport;

/**
 * This class is responsible for implementing the service that sends emails to individuals.
 * @author Kuali Rice Team (rice.collab@kuali.org)
 * @auther Aaron Godert (ag266 at cornell dot edu)
 */
public class EmailServiceImpl implements EmailService {
    private static final String SMTP = "smtp";
    private static final String MAIL_SMTP_HOST = "mail.smtp.host";

    private static Logger LOG = Logger.getLogger(EmailServiceImpl.class);

    // values injected into these from Spring
    private String weburl;
    private String smtpHost = "localhost";
    private String defaultSender = "kcb@localhost";

    private final String DETAILACTION = "DetailView.form";

    /**
     * Sets the weburl attribute value (injected from Spring).
     * @param weburl
     */
    @Required
    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    /**
     * This method sets the SMTP Host value (injected from Spring).
     * @param smtpHost
     */
    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    /**
     * Sets the default sender address to use if no valid producer email address
     * was specified in the message
     * @param defaultSender the default sender address to use if no valid producer email address was specified in the message
     */
    public void setDefaultSender(String defaultSender) {
        this.defaultSender = defaultSender;
    }

    /**
     * First constructs the appropriately formatted mail message then sends it off.
     * @see org.kuali.rice.kcb.service.EmailService#sendNotificationEmail(org.kuali.rice.kcb.bo.MessageDelivery, java.lang.String, java.lang.String)
     */
    public Long sendEmail(MessageDelivery messageDelivery, String recipientEmailAddress, String emailFormat) throws Exception {
        // reconcile the need for custom rendering depending on message producer
        // or can we just have a single URL that redirects to original dochandler?

        Message message = messageDelivery.getMessage();
        String channelName = message.getChannel();

        String producer = message.getProducer();
        String sender = defaultSender;
        if (producer != null) {
            try {
                InternetAddress[] addresses = InternetAddress.parse(producer, false);
                if (addresses.length > 0) {
                    sender = addresses[0].toString();
                }
            } catch (AddressException ae) {
                // not a valid email address
            }
        }

        String title = messageDelivery.getMessage().getTitle();
        String subject = (channelName == null ? "" : channelName + " ") + (!StringUtils.isBlank(title) ? " - " + title : "");

        String format = "text/plain";
        String linebreak = "\n\n";

        // NOTE: we don't set the docId parameter in the link
        // This forces the detail view to not render an acknowledge
        // button
        String link = weburl +"/"+ DETAILACTION +"?" 
        + NotificationConstants.NOTIFICATION_CONTROLLER_CONSTANTS.MSG_DELIVERY_ID +"="+ messageDelivery.getId();

        if (emailFormat == null || emailFormat.equals("text")) {
        	// defaults values are good for text
        } else {  // html format
            format = "text/html";
            link = "<a href='"+ link +"'>Notification Detail</a>";
            linebreak = "<br /><br />";
        }

        LOG.debug("link: "+link);

        // construct the message
        StringBuffer sb = new StringBuffer();
        sb.append("You have a new notification. Click the link below to review its details.");
        sb.append(linebreak);
        sb.append(linebreak);
        sb.append(link);
        String content = sb.toString();

        LOG.debug("subject: "+subject);
        LOG.debug("sender: "+sender);
        LOG.debug("recipient: "+recipientEmailAddress);
        LOG.debug("content: "+content);

        // actually do the send
        sendEmail(content, subject, sender, recipientEmailAddress, format);

        return null;
    }

    /**
     * Uses JavaMail to send the mail messages to the appropriate recipient.
     * @param message message content
     * @param subject subject of message
     * @param from sender of message
     * @param sendTo recipient of message
     * @param format text or html 
     */
    protected void sendEmail(String message, String subject, String from, String sendTo, String format) {

        SMTPTransport transport = null;
        Session session = null;

        try {
            Properties props = System.getProperties();
            // TODO the smtp is hard coded...we should read it from the deliverer plugin
            props.put(MAIL_SMTP_HOST, smtpHost);
            session = Session.getDefaultInstance(props, null);
            session.setDebug(false);
            MimeMessage mail = new MimeMessage(session);
            mail.setFrom(new InternetAddress(from));
            // get the name and address for the person that sent feedback
            // and put it in the CC list              
            // send it to the feedback address defined in the props file
            mail.setRecipients(javax.mail.Message.RecipientType.TO, sendTo);

            mail.setSubject(subject);

            MimeBodyPart bodyPart1 = new MimeBodyPart();          

            bodyPart1.setText(message);
            bodyPart1.setContent(message, format);

            Multipart multiPart = new MimeMultipart();

            multiPart.addBodyPart(bodyPart1);
            mail.setContent(multiPart);
            mail.setSentDate(new java.util.Date());
            transport = (SMTPTransport) session.getTransport(SMTP);
            transport.connect();
            transport.sendMessage(mail, InternetAddress.parse(sendTo, false));

        } catch (Exception mex) {
            // do something useful if mex is an instance of SendFailedException
            // see JavaMail examples
            try {
                transport.close();
                session = null;
            } catch (Exception tex) {};
            LOG.error("Mailing Exception ",mex);
        } finally {
            try {
                LOG.debug("closing transport");
                transport.close(); 
                session = null;
            } catch (Exception tex) {};
        }
    }
}
