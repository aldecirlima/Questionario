package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.perguntas.Opcao;
import br.com.bb.seguranca.questionario.service.OpcaoService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@ViewScoped
public class OpcaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Opcao objOpcao;

	private Long idLong;

	@Inject
	private OpcaoService opcaoService;

	@PostConstruct
	private void init() {
		objOpcao = new Opcao();
	}

	public void salvaOpcao() {
		if (objOpcao.getIdOpcao() == null) {
			objOpcao.setDataCadastro(new Date());
			objOpcao.setMatriculaCadastro("F0394519");
			objOpcao.setAtiva(1);
		}
		try {
			this.objOpcao = opcaoService.persisteOpcao(objOpcao);
			FacesMessages.info("Opção ID: " + objOpcao.getIdOpcao() + " salva com sucesso!");
			this.objOpcao = new Opcao();
		} catch (Exception e) {
			FacesMessages.error("Erro ao salvar opção: " + e.getMessage());
		}
	}

	public Opcao getObjOpcao() {
		return objOpcao;
	}

	public void setObjOpcao(Opcao objOpcao) {
		this.objOpcao = objOpcao;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

}
