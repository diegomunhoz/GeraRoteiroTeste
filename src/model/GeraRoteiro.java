package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class GeraRoteiro {

	private Scanner leitorArquivo;
	private List<String> listaArquivos = new ArrayList<String>();
	private String campo;
	private boolean envio = false;

	private String inFileName;
	private String outFileName;

	public void processar() {
		try {
			this.leitorArquivo = new Scanner(new FileReader("entrada.txt"));

			while (this.leitorArquivo.hasNext()) {
				this.montarLista();
			}

			if (this.listaArquivos.size() > 0) {
				this.envio = true;
				this.copiarArquivo();
			} else {
				this.envio = false;
				JOptionPane.showMessageDialog(null, "FALHA NO PROCESSAMENTO!");
			}

			if (this.envio == true) {
				JOptionPane.showMessageDialog(null, "Processamento Concluído.");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void copiarArquivo() {
		for (String a : this.listaArquivos) {

			try {
				this.inFileName = "C:/Users/dmunhoz/Desktop/Roteiro de Teste/4XXU-FEMP4XXU-RTU.xls";
				this.outFileName = "C:/Users/dmunhoz/Desktop/Roteiro de Teste/"
						+ a + "-FEMP" + a + "-RTU.xls";

				FileInputStream in = new FileInputStream(inFileName);
				FileOutputStream out = new FileOutputStream(outFileName);
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void montarLista() {
		try {
			this.campo = (String) this.leitorArquivo.nextLine();
			this.listaArquivos.add(this.campo.trim().replace("FEMP", ""));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao montar lista.");
		}
	}

}
