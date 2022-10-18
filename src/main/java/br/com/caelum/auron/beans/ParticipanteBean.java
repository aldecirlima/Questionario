package br.com.caelum.auron.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import br.com.caelum.auron.dao.ParticipanteDao;
import br.com.caelum.auron.modelo.Participante;

@Named
@RequestScoped
public class ParticipanteBean {

	@Inject
	private Participante participante;

	@Inject
	private ParticipanteDao participanteDao;

	@Inject
	private Subject user;

	@Inject
	private FacesContext ctx;

	private List<Participante> participantes;

	public void cadastrar() {
		participanteDao.inserir(participante);
		participantes.add(participante);
		participante = new Participante();

	}

	public List<Participante> getParticipantes() {
		if (participantes == null) {
			participantes = participanteDao.getParticipantes();
		}
		return participantes;
	}

	public String login() {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(participante.getEmail(), participante.getSenha());
			user.login(token);
			return "sorteio?faces-redirect=true";
		} catch (AuthenticationException e) {
			ctx.addMessage(null, new FacesMessage("E-mail ou senha inválidos..."));
		}
		return null;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

}
