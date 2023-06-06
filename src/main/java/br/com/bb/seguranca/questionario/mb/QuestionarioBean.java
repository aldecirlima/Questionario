package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.Questionario;

@Named
@SessionScoped
public class QuestionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Questionario objQuestionario;

	public void initialize() {
		objQuestionario = new Questionario();
	}
	
	public void testes() {
		System.out.println("Título: " + objQuestionario.getNomeQuestionario());
		System.out.println("Gravação: " + objQuestionario.getMatriculaGravacao());
		System.out.println("Ativação: " + objQuestionario.getMatriculaAtivacao());
		
	}
	
	public String redirect(String pagina) {
		return pagina + "?faces-redirect=true";
	}

	public Questionario getObjQuestionario() {
		return objQuestionario;
	}

	public void setObjQuestionario(Questionario objQuestionario) {
		this.objQuestionario = objQuestionario;
	}

}
