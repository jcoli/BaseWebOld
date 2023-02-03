/*
 * Copyright 2014 Tecnocoli/Jeferson Coli
 * http://www.tecnocoli.com.br 
 * All rights reserved
 */
package system.base.email;

import java.util.*;
import javax.activation.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.log4j.Logger;
import system.base.email.AuthEmail;

/**
 *
 * @brief Classe JavaMailBean
 * @author Jeferson Coli jcoli@tecnocoli.com.br
 * @date 02/05/2014
 *
 */
@ManagedBean(name = "javaMailBean")
@RequestScoped
public class JavaMailBean {

    final static Logger logger = Logger.getLogger(JavaMailBean.class);
    
    public static final String SERVER_SMTP = "cl-t058-333cl.privatedns.com";
    public static final int PORT_SERVER_SMTP = 465;
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

    public void sendAuthenticated() {
        FacesContext context = FacesContext.getCurrentInstance();
        StringBuilder createMsgToSend = new StringBuilder();
        createMsgToSend.append(this.message);
        //createMsgToSend.append("\n\r");
        createMsgToSend.append("<br />");
        createMsgToSend.append("<br />");
        createMsgToSend.append("<br />");
        createMsgToSend.append("<br />");
        createMsgToSend.append(this.standardMessage);
        createMsgToSend.append("<br />");
        createMsgToSend.append("<br />");
        createMsgToSend.append(this.standardMessage1);
        createMsgToSend.append("<br />");
        createMsgToSend.append("<br />");
        createMsgToSend.append("<br />");
        createMsgToSend.append(this.link);
        createMsgToSend.append("<br />");
        createMsgToSend.append("<br />");
        createMsgToSend.append(this.link1);

        String msgToSend = createMsgToSend.toString();

        Properties config = new Properties();
        config.put("mail.smtp.host", JavaMailBean.SERVER_SMTP);
        config.put("mail.smtp.port", JavaMailBean.PORT_SERVER_SMTP);
        config.put("mail.smtp.auth", "true");

        Session sessao = Session.getInstance(config, new AuthEmail(JavaMailBean.STANDART_ACCOUNT, JavaMailBean.PASSWORD_STANDART_ACCOUNT));

        try {
            MimeMessage email = new MimeMessage(sessao);
            email.setFrom(new InternetAddress(this.from));
            email.setRecipient(Message.RecipientType.TO, new InternetAddress(this.to));

            InternetAddress[] normais = this.montaDestinatarios(this.normalRecipient);
            if (normais != null) {
                email.setRecipients(Message.RecipientType.CC, normais);
            }

            InternetAddress[] ocultos = this.montaDestinatarios(this.hiddenRecipient);
            if (ocultos != null) {
                email.setRecipients(Message.RecipientType.BCC, ocultos);
            }

            email.setSubject(this.topic);
            email.setSentDate(new Date());

            // Preparando o body from email
            MimeMultipart partesEmail = new MimeMultipart();
            MimeBodyPart bodyEmail = new MimeBodyPart();
            bodyEmail.setContent(msgToSend, "text/html");
            partesEmail.addBodyPart(bodyEmail);

            // Anexando arquivo
            if (this.anexo != null && this.anexo.trim().length() > 0) {
                MimeBodyPart anexo = new MimeBodyPart();
                FileDataSource arquivo = new FileDataSource(this.anexo);
                anexo.setDataHandler(new DataHandler(arquivo));
                anexo.setFileName(arquivo.getName());
                partesEmail.addBodyPart(anexo);
            }

            email.setContent(partesEmail);

            Transport.send(email);

//            context.addMessage(null, new FacesMessage("E-mail enviado com sucesso"));

        } catch (AddressException e) {
            FacesMessage msg = new FacesMessage("Erro ao montar mensagem de e-mail! Erro: " + e.getMessage());
//            context.addMessage(null, msg);
            return;
        } catch (MessagingException e) {
            FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: " + e.getMessage());
//            context.addMessage(null, msg);
            return;
        }
    }

    public void sendSimple() {
        FacesContext context = FacesContext.getCurrentInstance();

        Properties config = new Properties();
        config.put("mail.smtp.host", JavaMailBean.SERVER_SMTP);
        config.put("mail.smtp.port", JavaMailBean.PORT_SERVER_SMTP);

        Session sessao = Session.getInstance(config);

        try {
            MimeMessage email = new MimeMessage(sessao);
            email.setFrom(new InternetAddress(this.from));
            email.setRecipient(Message.RecipientType.TO, new InternetAddress(this.to));

            InternetAddress[] normais = this.montaDestinatarios(this.normalRecipient);
            if (normais != null) {
                email.setRecipients(Message.RecipientType.CC, normais);
            }

            InternetAddress[] ocultos = this.montaDestinatarios(this.hiddenRecipient);
            if (normais != null) {
                email.setRecipients(Message.RecipientType.BCC, ocultos);
            }

            email.setSubject(this.topic);
            email.setSentDate(new Date());
            email.setText(this.message);
            Transport.send(email);

//            context.addMessage(null, new FacesMessage("E-mail enviado com sucesso"));

        } catch (AddressException e) {
            FacesMessage msg = new FacesMessage("Erro ao montar mensagem de e-mail! Erro: " + e.getMessage());
//            context.addMessage(null, msg);
            return;
        } catch (MessagingException e) {
            FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: " + e.getMessage());
//            context.addMessage(null, msg);
            return;
        }
    }

    private InternetAddress[] montaDestinatarios(String destinatarios) throws AddressException {
        InternetAddress[] emails = null;
        if (destinatarios != null && destinatarios.trim().length() > 0) {
            String[] list = destinatarios.split(";");
            emails = new InternetAddress[list.length];
            for (int i = 0; i < list.length; i++) {
                emails[i] = new InternetAddress(list[i]);
            }
        }
        return emails;
    }

    public String getDe() {
        return from;
    }

    public void setDe(String de) {
        this.from = de;
    }

    public String getPara() {
        return to;
    }

    public void setPara(String para) {
        this.to = para;
    }

    public String getDestinatariosNormais() {
        return normalRecipient;
    }

    public void setDestinatariosNormais(String destinatariosNormais) {
        this.normalRecipient = destinatariosNormais;
    }

    public String getDestinatariosOcultos() {
        return hiddenRecipient;
    }

    public void setDestinatariosOcultos(String destinatariosOcultos) {
        this.hiddenRecipient = destinatariosOcultos;
    }

    public String getAssunto() {
        return topic;
    }

    public void setAssunto(String assunto) {
        this.topic = assunto;
    }

    public String getMensagem() {
        return message;
    }

    public void setMensagem(String mensagem) {
        this.message = mensagem;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public String getMsgPadrao() {
        return standardMessage;
    }

    public void setMsgPadrao(String msgPadrao) {
        this.standardMessage = msgPadrao;
    }

    public String getMsgPadrao1() {
        return standardMessage1;
    }

    public void setMsgPadrao1(String msgPadrao1) {
        this.standardMessage1 = msgPadrao1;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    

}
