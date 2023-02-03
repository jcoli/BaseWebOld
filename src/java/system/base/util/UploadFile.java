package system.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

public class UploadFile {
    
        final static Logger logger = Logger.getLogger(UploadFile.class);

	private String diretorio;
	private String caminho;
	private byte[] arquivo;
	private String nome;

	public UploadFile() {
	}

	public String getNome() {
		return nome;
	}

	public String getRealPath() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext
				.getResponse();

		FacesContext aFacesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) aFacesContext
				.getExternalContext().getContext();

		return context.getRealPath("/");
	}

	public void fileUpload(FileUploadEvent event, String type, String diretorio) {
		try {
			this.nome = new java.util.Date().getTime() + type;
			this.caminho = getRealPath() + diretorio + getNome();
			this.arquivo = event.getFile().getContents();

			File file = new File(getRealPath() + diretorio);
			file.mkdirs();

		} catch (Exception ex) {
			logger.error("Erro no upload do arquivo" + ex);
		}
	}

	public void gravar() {

		try {

			FileOutputStream fos;
			fos = new FileOutputStream(this.caminho);
			fos.write(this.arquivo);
			fos.close();

		} catch (Exception ex) {
			logger.error(ex);
		}

	}

}
