package br.com.caelum.auron.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.auron.dao.ParticipanteDao;
import br.com.caelum.auron.dao.SorteioDao;
import br.com.caelum.auron.modelo.Par;
import br.com.caelum.auron.modelo.Sorteador;
import br.com.caelum.auron.modelo.Sorteio;
import br.com.caelum.auron.modelo.SorteioException;

@Named
@RequestScoped
public class SorteioBean {

	@Inject
	private Sorteio sorteio;

	@Inject
	private ParticipanteDao participanteDao;
	
	@Inject
	private SorteioDao sorteioDao;

	public void sortear() {

		try {
			Sorteador sorteador = new Sorteador(sorteio, participanteDao.getParticipantes());
			sorteador.sortear();
			sorteioDao.inserir(sorteio);
		} catch (SorteioException e) {
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public Sorteio getSorteio() {
		return sorteio;
	}
	
	public List<Par> getPares() {
		return sorteioDao.getPares();
	}

}
