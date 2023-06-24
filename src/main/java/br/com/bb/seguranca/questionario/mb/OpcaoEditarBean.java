package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bb.seguranca.questionario.modelo.form.Opcao;
import br.com.bb.seguranca.questionario.service.OpcaoService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@ViewScoped
public class OpcaoEditarBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Opcao objOpcao;

	private List<Opcao> listaDeOpcoes;

	private Map<Long, Opcao> mapOpcoes;

	private Long idLong;

	@Inject
	private OpcaoService opcaoService;

	@PostConstruct
	private void init() {
		mapOpcoes = new HashMap<Long, Opcao>();
		listaDeOpcoes = new ArrayList<>();
		this.objOpcao = new Opcao();
		this.buscarTodasOpcoes();
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

	public void atualizaOpcao() {
		objOpcao.setDataCadastro(new Date());
		objOpcao.setMatriculaCadastro("F0394519");
		Integer index = listaDeOpcoes.indexOf(objOpcao);
		listaDeOpcoes.set(index, objOpcao);
		this.salvaOpcao();
	}

	public void setarOpcao() {
		objOpcao = mapOpcoes.get(idLong.longValue());
	}

	public void limpaOpcao() {
		this.objOpcao = new Opcao();

	}

	public void buscarTodasOpcoes() {
		listaDeOpcoes = opcaoService.buscaTodasOpcoes();
		for (Opcao opcao : listaDeOpcoes) {
			mapOpcoes.put(opcao.getIdOpcao().longValue(), opcao);
		}

	}

	public Opcao getObjOpcao() {
		return objOpcao;
	}

	public void setObjOpcao(Opcao objOpcao) {
		this.objOpcao = objOpcao;
	}

	public List<Opcao> getListaDeOpcoes() {
		return listaDeOpcoes;
	}

	public void setListaDeOpcoes(List<Opcao> listaDeOpcoes) {
		this.listaDeOpcoes = listaDeOpcoes;
	}

	public Map<Long, Opcao> getMapOpcoes() {
		return mapOpcoes;
	}

	public void setMapOpcoes(Map<Long, Opcao> mapOpcoes) {
		this.mapOpcoes = mapOpcoes;
	}

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

}
