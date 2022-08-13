import mlsearcher.MlSearcher;
import mlsearcher.Item;

public class Example {

	public static void main(String[] args) {
		try {
			String palabras = "MSX    talent";
			
			MlSearcher mlsearcher = new MlSearcher();
			mlsearcher.setSiteId("MLA");
			mlsearcher.setWords(MlSearcher.stringToStringList(palabras));
			mlsearcher.searchItems();
			for (Item item : mlsearcher.getFoundItems()) {
				System.out.print(item.getPermalink() + "\n");
			}
			System.out.print(mlsearcher.getFoundItems().size() + "\n");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
