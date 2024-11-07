import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//Esta clase servirá para que se puedan realizar todas las operaciones solicitadas en el proyecto.
public class BankManage {
     private Map<String, Client> clientes;

     public BankManage() {
         this.clientes = new HashMap<>();
     }

     //Registrar cliente y verificación de que no se repita el DNI.
     public void clientRegister(String name, String lastname, String dni, String email) {
         if (!clientes.containsKey(dni)) {
             Client newClient = new Client(name, lastname, dni, email);
             if (newClient.getDni() != null && newClient.getEmail() != null) {
                 clientes.put(dni, newClient);
             }
         } else {
             System.out.println("El DNI ya está registrado.");
         }
     }

     //Permite buscar el cliente.
     public Client searchClient(String dni) {
         return clientes.get(dni);
     }

     //Metodo para abrir una cuenta y cuenta creada.
     public void openAccount(String dni, BankAccount.AccountType accountType) {
         Client cliente = searchClient(dni);
         if (cliente != null) {
             BankAccount newAccount = new BankAccount(accountType);
             cliente.addAccount(newAccount);
             System.out.println("La cuenta ha sido creada. El número de cuenta es: " + newAccount.getAccountNumber());
         } else { System.out.println("El cliente no fue encontrado, por favor; registre al cliente antes de crear la cuenta.");
         }
     }

     //Depositar dinero.
     public void depositar(String accountNumber, double amount) {
         BankAccount cuenta = searchAccountNumber(accountNumber);
         if (cuenta != null) {
             boolean exito = cuenta.depositar(amount);
             if (exito) {
                 System.out.println("Depósito realizado. Su nuevo saldo es de: " + cuenta.getBalance());
             }
         }
         else { System.out.println("Cuenta no existente.");
         }
     }

     //Retiro de dinero.
     public void retirar(String numeroCuenta, double cantidad) {
         BankAccount cuenta = searchAccountNumber(numeroCuenta);
         if (cuenta != null) { boolean exito = cuenta.retirar(cantidad);
             if (exito) {
                 System.out.println("Retiro realizado con éxito.");
             }
         } else {
             System.out.println("Cuenta no encontrada.");
         }
     }

     //Metodo para revisar saldo.
     public void checkBalance(String accountNumber) {
         BankAccount cuenta = searchAccountNumber(accountNumber);
         if (cuenta != null) {
             System.out.println("El saldo en su cuenta es de: " + cuenta.getBalance());
         } else {
             System.out.println("Cuenta no existente.");
         }
     }

     //Metodo para buscar una cuenta.
     private BankAccount searchAccountNumber(String accountNumber) {
         return clientes.values().stream().
                 flatMap(cliente -> cliente.getAccounts().stream())
                 .filter(cuenta -> cuenta.getAccountNumber().equals(accountNumber))
                 .findFirst()
                 .orElse(null);
     }

    //Metodo para obtener todas las cuentas de un mismo cliente.
    public String getAccountsSummary(String dni) {
         Client cliente = searchClient(dni);
         if (cliente != null) {
             return cliente.getAccounts().stream()
                     .map(cuenta -> cuenta.getAccountNumber() + ". Saldo: " + cuenta.getBalance())
                     .collect(Collectors.joining(", "));
         } else {
             return "Cliente no existente.";
         }
     }

}
