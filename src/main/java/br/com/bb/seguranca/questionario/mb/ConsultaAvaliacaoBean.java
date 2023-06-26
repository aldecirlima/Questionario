package br.com.bb.seguranca.questionario.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.bb.seguranca.questionario.modelo.form.Avaliacao;
import br.com.bb.seguranca.questionario.service.AvaliacaoService;
import br.com.bb.seguranca.questionario.util.FacesMessages;

@Named
@SessionScoped
public class ConsultaAvaliacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AvaliacaoService avaliacaoService;

	private Avaliacao objAvaliacao;

	private Boolean parametroPassagem;

	private List<Avaliacao> listAvaliacoes;

	@PostConstruct
	private void init() {
		parametroPassagem = true;
		objAvaliacao = new Avaliacao();
		listAvaliacoes = avaliacaoService.buscaAvaliacoes();
//		for (Avaliacao avaliacao : listAvaliacoes) {
//			System.out.println(avaliacao.getSecoes().get(0).getSecaoBase().getNomeSecao());
//		}
	}

	public void carregaAvaliacao() {

		try {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("avaliacao", objAvaliacao);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("passagem", parametroPassagem);
			FacesContext.getCurrentInstance().getExternalContext().redirect("consultaResposta.xhtml");
			PrimeFaces.current().ajax().update("formConsultaResposta");
			PrimeFaces.current().ajax().update("buttonVoltar");
		} catch (Exception e) {
			FacesMessages.error("Não foi possível carregar a visualização");
		}

	}
	
	public void voltaPaginaConsultas() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().getFlash();
			FacesContext.getCurrentInstance().getExternalContext().redirect("consultas.xhtml");		
		} catch (Exception e) {
			FacesMessages.error("Não foi possível retornar à pagina anterior.");
		}
	}

	public List<Avaliacao> getListAvaliacoes() {
		return listAvaliacoes;
	}

	public void setListAvaliacoes(List<Avaliacao> listAvaliacoes) {
		this.listAvaliacoes = listAvaliacoes;
	}

	public Avaliacao getObjAvaliacao() {
		return objAvaliacao;
	}

	public void setObjAvaliacao(Avaliacao objAvaliacao) {
		this.objAvaliacao = objAvaliacao;
	}

	public Boolean getParametroPassagem() {
		return parametroPassagem;
	}

	public void setParametroPassagem(Boolean parametroPassagem) {
		this.parametroPassagem = parametroPassagem;
	}

}
