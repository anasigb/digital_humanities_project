package projectDH;

import java.io.*;
import java.util.*;

public class analysis {

	public static void main(String[] args) {
	//	String[] organs = {"heart", "lung", "liver", "stomach", "kidney", "colon"};
		String[] organs = {"tarchea", "bronchi", "lobes", "pleura", "bronchus", "bronchioles"};
		BufferedReader in=null;
		String line=null;
		int linesPerPage=38, page=0;
		long lineIndex=0;
		Vector<MentionsPerOrgan> OrgansMentions = new Vector<MentionsPerOrgan>();
		Vector<Vector<DataPerMention>> MentionsData= new Vector<Vector<DataPerMention>>();
		int[] mentions = new int[organs.length];
		for(int i=0;i<organs.length;i++){
			mentions[i]=0;
			OrgansMentions.add(new MentionsPerOrgan(organs[i],i));
			MentionsData.add(new Vector<DataPerMention>());
		}
		try{
			//in = new BufferedReader(new FileReader("surgical_anatomy.txt"));
			in = new BufferedReader(new FileReader("manual_of_sergury.txt"));
			
			line = in.readLine();
		}catch(Exception e){
			System.out.println("File not open");
			return;
		}
		Writer writer1=null,writer2=null;
		try {
			writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data.txt"), "utf-8"));
			writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("statistics.txt"), "utf-8"));
		
		}catch(Exception e){
			System.out.println("failed file writing");
        	return;
		}
		while(line != null)
        {
			lineIndex++;
			if(lineIndex>linesPerPage*(page+1)) page++;
            try{line = in.readLine();}
            catch(Exception e){
            	System.out.println("failed loading line");
            	return;
            }
            
            for(int j=0;j<organs.length;j++){
            	if(line!=null){
	            	if(line.contains(organs[j])==true){
	            		++mentions[j];
						MentionsData.elementAt(j).add(new DataPerMention(j, lineIndex));
						OrgansMentions.elementAt(j).increase();
						//System.out.println(line);
						
					}
				}
			}
        }
        int max=0;
        for(int j=0; j<organs.length;j++){
        	if(max<MentionsData.elementAt(j).size()){
        		max=MentionsData.elementAt(j).size();
        	}
        }
        int i;
		for(i=0;i<max;i++){
			for(int j=0;j<organs.length;j++){
				if(MentionsData.elementAt(j).size()-1>=i){
					try {
						writer1.write(""+MentionsData.elementAt(j).elementAt(i).lineNumber+", ");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						if(MentionsData.elementAt(j).size()>0){
							writer1.write(""+MentionsData.elementAt(j).elementAt(MentionsData.elementAt(j).size()-1).lineNumber+", ");
						}
						else{
							writer1.write(""+0+", ");
						}
							//						writer1.write(""+0+", ");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			try {
				writer1.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		for( i=0;i<organs.length;i++){
			for(int j=0; j<organs.length;j++){
				if(j!=i){
					OrgansMentions.elementAt(i).TotalMentions+=mentions[j];
				}
			}
		}

		for( i=0; i<MentionsData.size();i++){ // organs
			try {
				OrgansMentions.elementAt(i).DensityPerMentions=OrgansMentions.elementAt(i).TotalOrganMentions/OrgansMentions.elementAt(i).TotalMentions;
				writer2.write(OrgansMentions.elementAt(i).toString()+"\n");
				
			} catch (IOException e) {
				System.out.println("couldn't write statistics");
				e.printStackTrace();
				return;
			}
		}
		try{
			in.close();
			writer1.close();
			writer2.close();
		}catch(Exception e){}
	}
}
	



