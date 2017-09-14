package projectDH;

class MentionsPerOrgan {
	String Organ;
	int ID;
	long TotalOrganMentions;
	float DensityPerMentions;
	long TotalMentions;
	
	public MentionsPerOrgan(String organ, int id) {
		ID= id;
		Organ=organ;
		TotalOrganMentions=0;
		DensityPerMentions=0;
		TotalMentions=0;
	}
	public void initialize(String organ) {
		Organ = organ;
		TotalOrganMentions=0;
		DensityPerMentions=0;
		TotalMentions=0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ""+Organ+""+ID+", "+TotalOrganMentions + ", "
				+ DensityPerMentions + ", " + TotalMentions+"";
	}
	public void increase() {
		TotalOrganMentions++;
		TotalMentions++;
		DensityPerMentions = TotalOrganMentions/TotalMentions;
	}
}
