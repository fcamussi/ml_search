package mlbuscar;

public class Main {

	public static void main(String[] args) {
		try {
			MLBuscar mlbuscar = new MLBuscar();
			String [] palabrasClave = {"msx", "talent"}; 
			mlbuscar.setPalabrasClave(palabrasClave);
			mlbuscar.ConsultarProducto();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
