package app;

public class Dependente extends Deducao {
    private String nomeDependente;
	private String parentescoDependente;

    public Dependente() {
        super();
        this.nomeDependente = "";
        this.parentescoDependente = "";
    }

    public Dependente(String nome, String parentesco) {
        super(189.59f);
        this.nomeDependente = nome;
        this.parentescoDependente = parentesco;
    }

    public String getNomeDependente() {
        return this.nomeDependente;
    }

    public String getParentescoDependente() {
        return this.parentescoDependente;
    }

    public void setNomeDependente(String nome) {
        this.nomeDependente = nome;
    }

    public void setParentescoDependente(String parentesco) {
        this.parentescoDependente = parentesco;
    }

}
