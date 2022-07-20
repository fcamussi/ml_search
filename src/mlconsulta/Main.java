package mlconsulta;

public class Main {

	public static void main(String[] args) {
		try {
			MLConsulta mlconsulta = new MLConsulta();
			mlconsulta.setSitio("MLA"); // <- Constantes?: MLBuscar.Sitio.MLA
			String [] palabrasClave = {"MSX", "talent"};
			mlconsulta.setPalabrasClave(palabrasClave);
			mlconsulta.BuscarProducto();
			System.out.print("\n" + mlconsulta.listaRegistro.size() + "\n");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}