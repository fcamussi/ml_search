import mlsearcher.MLSearcher;

import java.util.Map;
import java.util.Scanner;

import mlsearcher.Item;
import mlsearcher.MLSite;

public class Example {

	public static void main(String[] args) {
		System.out.println("ID y nombres de sitios de Mercado Libre:");
		MLSite mlsite = new MLSite();
		try {
			mlsite.request();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> siteMap = mlsite.getSites();
		for (String siteId: siteMap.keySet()) {
			System.out.println(siteId + ": " + siteMap.get(siteId));
		}
		
		System.out.println("Ingrese ID del sitio: ");
		Scanner sc = new Scanner(System.in);
		String siteId = sc.nextLine();
		System.out.println("Ingrese palabras a buscar separadas por espacios: ");
		String words = sc.nextLine();
		sc.close();
		
		MLSearcher mlsearcher = new MLSearcher();
		mlsearcher.setSiteId(siteId);
		mlsearcher.setWords(MLSearcher.stringToStringList(words));
		System.out.println("Buscando...");
		try {
			mlsearcher.searchItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int count = mlsearcher.getFoundItems().size();
		System.out.println("Se encontraron los siguientes " + count + " artículos:");
		for (Item item : mlsearcher.getFoundItems()) {
			System.out.println(item.getTitle());
		}
	}
	
}
