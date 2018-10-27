package meli.mutants.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {
	
	private Long countMutantDna;
	private Long countHumanDna;
	
	public Stats(Long countMutantDna, Long countHumanDna) {
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
	}
	
	@JsonProperty("ratio")
	public Double getRatio() {
		return (double) countMutantDna / (double) countHumanDna;
	}
	
	@JsonProperty("count_mutant_dna")
	public Long getCountMutantDna() {
		return countMutantDna;
	}

	public void setCountMutantDna(Long countMutantDna) {
		this.countMutantDna = countMutantDna;
	}
	
	@JsonProperty("count_human_dna")
	public Long getCountHumanDna() {
		return countHumanDna;
	}

	public void setCountHumanDna(Long countHumanDna) {
		this.countHumanDna = countHumanDna;
	}

}
