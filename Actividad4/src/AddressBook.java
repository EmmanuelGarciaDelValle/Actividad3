import java.io.*;
import java.util.*;
public class AddressBook {
    private Map<String, String> contacto;
    private String nombreArchivo;

    public AddressBook(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.contacto = new HashMap<>();
        load();
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    contacto.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Map.Entry<String, String> entry : contacto.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacto.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void create(String numeroTelefono, String name) {
        contacto.put(numeroTelefono, name);
        save();
    }

    public void delete(String numeroTelefono) {
        contacto.remove(numeroTelefono);
        save();
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook("contacto.csv");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Borrar contacto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addressBook.list();
                    break;
                case 2:
                    System.out.print("Ingrese el número telefónico: ");
                    String numeroTelefono = scanner.nextLine();
                    System.out.print("Ingrese el nombre: ");
                    String name = scanner.nextLine();
                    addressBook.create(numeroTelefono, name);
                    break;
                case 3:
                    System.out.print("Ingrese el número telefónico a eliminar: ");
                    numeroTelefono = scanner.nextLine();
                    addressBook.delete(numeroTelefono);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}