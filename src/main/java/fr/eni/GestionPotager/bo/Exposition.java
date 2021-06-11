package fr.eni.GestionPotager.bo;

public enum Exposition {

	SOLEIL("Soleil"), 
	MI_OMBRE("Mi ombre"), 
	OMBRE("Ombre");
	
	
	 private final String displayValue;

	private Exposition(String displayValue) {
		this.displayValue = displayValue;
	}
	
	
	public String getDisplayValue() {
        return displayValue;
    }
	
}
