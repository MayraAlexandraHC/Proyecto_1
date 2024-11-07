
public class Main {
    public static void main(String[] args) {
            BankManage banco = new BankManage();
            // Registro de clientes
            banco.clientRegister("Juan", "Perez", "12345678", "juan.perez@correo.com");
            banco.clientRegister("Ana", "Lopez", "87654321", "ana.lopez@correo.com");
            banco.clientRegister("Laura", "Rivas", "87654321", "l.riv@correo.com");

            // Apertura de cuentas bancarias
            banco.openAccount("12345678", BankAccount.AccountType.AHORROS);
            banco.openAccount("12345678", BankAccount.AccountType.AHORROS);
            banco.openAccount("12345678", BankAccount.AccountType.CORRIENTE);
            banco.openAccount("87654321", BankAccount.AccountType.CORRIENTE);

            System.out.println(banco.getAccountsSummary("12345678"));

            banco.depositar("CA50481668332767",500);
            banco.retirar("CA50481668332767",600);
            banco.checkBalance("CA50481668332767");
            banco.depositar("CC34595426161568",500);
            banco.retirar("CC34595426161568",1200);
            banco.checkBalance("CC34595426161568");
        }


    }