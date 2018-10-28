package meli.mutants.domain;

import org.springframework.data.annotation.Id;

public class Human {
	
	@Id
    private String id;
	private String [] dna;
	private boolean mutant;
	
	public Human() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String [] getDna() {
		return dna;
	}

	public void setDna(String [] dna) {
		this.dna = dna;
	}

	public boolean isMutant() {
		return mutant;
	}

	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
	
}
