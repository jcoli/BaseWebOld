package system.base.util;

import java.util.*;
import java.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.String;
import java.text.*;
import java.awt.Color;
import java.lang.*;
import org.apache.log4j.Logger;
import system.base.web.ContextBean;

public class CommandExec {
    
    final static Logger logger = Logger.getLogger(CommandExec.class);

    private int dia = 0;
    private int month = 0;
    private int year = 0;
    private int nyear = 0;
    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    private String sdia;
    private String nsdia;
    private String smonth;
    private String nsmonth;
    private String syear;
    private String nsyear;
    private String shour;
    private String sminute;
    private String ssecond;
    Calendar calendar = new GregorianCalendar();
    GregorianCalendar calendario;

    public int execCommand(String strCommand) throws InterruptedException {
        int retorno;
        int retorno1;
        int code = 1;
        try {
            salvaLog(strCommand);
            Process processo = Runtime.getRuntime().exec(strCommand);

            BufferedReader buffer = new BufferedReader(new InputStreamReader(processo.getErrorStream()));
            String erro = "";
            while ((erro = buffer.readLine()) != null) {

                salvaLog(erro);
            }

            retorno1 = processo.waitFor();
            retorno = processo.exitValue();
            salvaLog("Processo retornou codigo de saida: " + retorno);
            if (retorno != 0) {
                salvaLog("===================");
                salvaLog("ERRO");
                salvaLog("Erro codigo: " + retorno);
                salvaLog("===================");

                code = retorno;
            } else {
                code = 0;
            }

        } catch (IOException io) {
            salvaLog(io.getMessage());
            logger.error(io);
            return code;
        } catch (NullPointerException np) {
            logger.error(np);
            salvaLog(np.getMessage());
            return code;
        } catch (SecurityException np) {
            salvaLog(np.getMessage());
            logger.error(np);
            return code;
        } catch (IllegalArgumentException np) {
            logger.error(np);
            salvaLog(np.getMessage());
            return code;
        }
        return code;

    }

    protected void salvaLog(String texto) {
        
         String path;
        ContextBean contextoBean = ContextUtil.getContextoBean();
        path = contextoBean.getPath();


        String Dados = path+"log-" + DayStamp() + ".log";
        String fileTexto = TimeStamp() + " - " + texto;//+NEWLINE;
        File arq = new File(Dados);
        //eventOutput("Salvando LOG");
        try {
            if (arq.exists()) {//Se o arquivo existir ele salva, se nï¿½o, eu nï¿½o sei....
                FileWriter arquivo = new FileWriter(arq, true);
                BufferedWriter grava = new BufferedWriter(arquivo);
                grava.newLine();
                grava.append(fileTexto);
                //grava.write(fileTexto);
                grava.close();
                arquivo.close();
            } else {
                FileWriter arquivo = new FileWriter(arq, true);
                BufferedWriter grava = new BufferedWriter(arquivo);
                grava.append(fileTexto);
                //grava.write(fileTexto);
                grava.close();
                arquivo.close();
            }
        } catch (IOException e) {
            logger.error("Erro 01 :" + e.toString());
        } catch (SecurityException erro2) {
            logger.error("Erro 02 :" + erro2.toString());
        }
    }

    public String TimeStamp() {

        String STimeStamp = null;
        CalcTimeStamp();
        STimeStamp = (sdia + "/" + smonth + "/" + syear + "-" + shour + ":" + sminute);

        return STimeStamp;

    }

    public String DayStamp() {

        String STimeStamp = null;
        CalcTimeStamp();
        STimeStamp = (sdia + smonth + syear);

        return STimeStamp;

    }

    public void CalcTimeStamp() {

        calendario = new GregorianCalendar();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        month = calendario.get(Calendar.MONTH);
        year = calendario.get(Calendar.YEAR);
        hour = calendario.get(Calendar.HOUR_OF_DAY);
        minute = calendario.get(Calendar.MINUTE);
        second = calendario.get(Calendar.SECOND);

        if (dia < 10) {
            sdia = "0" + dia;
        } else {
            sdia = Integer.toString(dia);
        }
        if (month < 9) {
            (smonth) = "0" + (++month);
        } else {
            smonth = Integer.toString(++month);
        }
        if (year < 10) {
            syear = "0" + year;
        } else {
            syear = Integer.toString(year);
        }
        if (hour < 10) {
            shour = "0" + hour;
        } else {
            shour = Integer.toString(hour);
        }
        if (minute < 10) {
            sminute = "0" + minute;
        } else {
            sminute = Integer.toString(minute);
        }
        if (second < 10) {
            ssecond = "0" + second;
        } else {
            ssecond = Integer.toString(second);
            //
        }

    }

    public void copyFile(String fileName, String folder, InputStream in) {
        String path;
        ContextBean contextoBean = ContextUtil.getContextoBean();
        path = contextoBean.getPath();
        
        

        String destination = path + folder + "/";
        try {
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination
                    + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            salvaLog("===================");
            salvaLog("Salvo o arquivo " + fileName);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public File[] fileList(String folder) {
        int i = 0;
        File file = new File(folder);
        File files[] = file.listFiles();
        while (i != files.length) {
            String name = files[i].getName();
            String path = files[i].getAbsolutePath();
            //String path1 = files[i].getCanonicalPath();
            String path2 = files[i].getPath();
            salvaLog("Arquivo " + name);
            salvaLog("Nome do path: " + path);
            salvaLog("Nome do path2: " + path2);
            i++;
        }

        return files;
    }

}
