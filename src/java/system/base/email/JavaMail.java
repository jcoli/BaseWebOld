/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
*/


package system.base.email;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

/**
 * @Project ProjetosSAIBR 
 * @brief Classe JavaMail
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date   21/08/2014
 */
@ManagedBean(name = "javaMail")
@RequestScoped
public class JavaMail {
    
    final static Logger logger = Logger.getLogger(JavaMail.class);
    
    public static final String SERVER_SMTP = "cl-t058-333cl.privatedns.com";
    public static final String PORT_SERVER_SMTP = "465";
    private static final String STANDART_ACCOUNT = "jcoli@tecnocoli.com.br";
    private static final String PASSWORD_STANDART_ACCOUNT = "shady55";

    private String from = "sistema@sai-br.com";
    private String to;
     private String normalRecipient;
    private String hiddenRecipient;
    private String topic;
    private String message;
    private String standardMessage = "Este email foi gerado automaticamente pelo Sistema de Controle de Propostas da SAI-BR.";
    private String standardMessage1 = "A informacao contida neste e-mail e nos seus arquivos em anexo e CONFIDENCIAL. Em caso de receber esta mensagem por engano, pedimos que notifique ao remetente e destrua esse e-mail imediatamente.";
    private String link = "<a href=\"http://tecnocoli.no-ip.org/BaseWeb\">Acesso Remoto</a>";
    private String link1 = "<a href=\"http://192.168.51.13/BaseWeb\">Acesso Local</a>";


    private String anexo;
    
    public void sendEmail(String msg, String msg1, String assunto, String dest) {
        HtmlEmail email = new HtmlEmail();
        email.setSSLOnConnect(true);
        email.setHostName(SERVER_SMTP);
        //email.setSslSmtpPort(PORT_SERVER_SMTP);
        email.setSmtpPort(465);  
          
        //email.setAuthenticator(new DefaultAuthenticator(STANDART_ACCOUNT, PASSWORD_STANDART_ACCOUNT));
        email.setAuthentication(STANDART_ACCOUNT, PASSWORD_STANDART_ACCOUNT);
        try {
            email.setFrom(STANDART_ACCOUNT, "Sistema SAI-BR");
            email.setDebug(false);
            email.setSubject(assunto);

            StringBuilder builder = new StringBuilder();
            builder.append(msg);
            builder.append("<br />");
            builder.append(msg1);
            builder.append("<br />");
            builder.append(this.standardMessage);
            builder.append(this.standardMessage1);
            builder.append("<br />");
            builder.append(this.link);
            builder.append("<br />");
            builder.append("<br />");
            builder.append(this.link1);

            email.setHtmlMsg(builder.toString());
            email.addTo(dest);
            email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
    

}
