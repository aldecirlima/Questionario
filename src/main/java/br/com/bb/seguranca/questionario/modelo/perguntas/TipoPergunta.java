package br.com.bb.seguranca.questionario.modelo.perguntas;

public enum TipoPergunta {
	
	S_N("Sim/Não"),
	S_N_NA("Sim/Não/Não se aplica"),
	UN_SL("Seleção única"),
	MTPL_SL("Seleção múltipla"),
	TXT_CRT("Texto curto"), 
	TXT_LNG("Texto longo");

	private String descricao;

	TipoPergunta(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
