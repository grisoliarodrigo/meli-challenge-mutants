package meli.mutants.domain;

public class Stats {
	
	private Long mutantCount;
	private Long humanCount;
	
	public Stats(Long mutantCount, Long humanCount) {
		this.mutantCount = mutantCount;
		this.humanCount = humanCount;
	}
	
	public Double getRatio() {
		return (double) mutantCount / (double) humanCount;
	}

	public Long getMutantCount() {
		return mutantCount;
	}

	public void setMutantCount(Long mutantCount) {
		this.mutantCount = mutantCount;
	}

	public Long getHumanCount() {
		return humanCount;
	}

	public void setHumanCount(Long humanCount) {
		this.humanCount = humanCount;
	}

}
