package com.App.GetYourGuide.service;

import com.App.GetYourGuide.domain.MailDetails;
import com.App.GetYourGuide.domain.Order;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);
    private static final String CANCELLED_ORDER = "You cancelled the order";

    public void sendEmail(MailDetails mailDetails) {

        LOGGER.info("Starting email creation...");

        try {
            SimpleMailMessage mailMessage = createMail(mailDetails);
            javaMailSender.send(mailMessage);
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.info("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMail(final MailDetails mailDetails){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreplyGetYourGuide@gmail.com");
        mailMessage.setTo(mailDetails.getMailTo());
        mailMessage.setSubject(mailDetails.getSubject());
        mailMessage.setText(mailDetails.getMessage());
        return mailMessage;
    }

    public void sendEmailAfterCreatingOrder(Order newOrder){
        sendEmail(new MailDetails(
                newOrder.getCustomer().getEmail(),
                "A new order has been created",
                "Hello " + newOrder.getCustomer().getName() + "!" + "/n" + "A new order number " + newOrder.getOrderId()
                        + "has been created. You can pay for your order within two days otherwise it will be cancelled"
        ));
    }

    public void earlyCancellationEmail(Order cancelledOrder){
        sendEmail(new MailDetails(cancelledOrder.getCustomer().getEmail(),
                CANCELLED_ORDER, "Hello " +  cancelledOrder.getCustomer().getName() +
                "Your order number" + cancelledOrder.getOrderId() + "is cancelled. The entire deposit will be refunded"));
    }

    public void laterCancellationEmail(Order cancelledOrder){
        sendEmail(new MailDetails(cancelledOrder.getCustomer().getEmail(),
                CANCELLED_ORDER, "Hello " +  cancelledOrder.getCustomer().getName() +
                "Your order number" + cancelledOrder.getOrderId() + "is cancelled. There were less than two days left " +
                "until the ordered mountain tour - only half the amount will be refunded"));
    }

}
