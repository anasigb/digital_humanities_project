package projectDH;

public class DataPerMention {
	int organ_index;
	long lineNumber;
	
	public DataPerMention() {
		lineNumber=0;
		
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param mentionIndex
	 * @param pageNumber
	 * @param firstIndexInSentence
	 * @param lastIndexInSentence
	 */
	
	public DataPerMention(int organ_index,  long lineNumber) {
		super();
		this.organ_index=organ_index;
		this.lineNumber = lineNumber;
		}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + organ_index + ", " +  lineNumber +"";
	}
	

}
