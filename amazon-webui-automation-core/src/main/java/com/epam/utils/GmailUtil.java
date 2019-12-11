package com.epam.utils;

import org.apache.commons.lang3.ArrayUtils;

import javax.mail.*;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.constant.GeneralConstants.SMTP_PROPERTIES_NAME;
import static com.epam.utils.PropertyLoader.getProperties;

public class GmailUtil {
    public static String getAmazonOTP(String username, String password) {
        String result = null;
        Properties props = getProperties(SMTP_PROPERTIES_NAME);
        Session session = Session.getDefaultInstance(props, null);
        Store store = null;

        try {
            store = session.getStore("imaps");
            store.connect("smtp.gmail.com", username, password);

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);

            Message[] messagesArray = inbox.getMessages();
            ArrayUtils.reverse(messagesArray);


            for (Message message : messagesArray) {
                if (message.getSubject().equals("Amazon Authentication")) {
                    Object content = message.getContent();
                    Multipart multiPart = (Multipart) content;
                    result = processMultiPart(multiPart);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(store).close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static String processMultiPart(Multipart content) throws Exception {
        for (int i = 0; i < content.getCount(); i++) {
            BodyPart bodyPart = content.getBodyPart(i);
            Object o = bodyPart.getContent();
            if (o instanceof String) {
                return parseOTPCode((String) o);
            } else if (o instanceof Multipart) {
                return processMultiPart((Multipart) o);
            }
        }
        return null;
    }

    private static String parseOTPCode(String message) {
        String openTag = "<p class=\"otp\">";
        String closeTag = "</p>";
        Pattern p = Pattern.compile(openTag + "(\\d+)" + closeTag);
        Matcher matcher = p.matcher(message);
        if (matcher.find()) {
            return message.substring(matcher.start() + openTag.length(), matcher.end() - closeTag.length());
        }
        return null;
    }
}
