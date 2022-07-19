package mlbuscar;

public class Main {

	public static void main(String[] args) {
		try {
			MLBuscar mlbuscar = new MLBuscar();
			mlbuscar.setSitio("MLA");
			String [] palabrasClave = {"msx", "talent"}; 
			mlbuscar.setPalabrasClave(palabrasClave);
			mlbuscar.ConsultarProducto();
			System.out.print("\n" + mlbuscar.listaRegistro.size() + "\n");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
