package com.Banco;

import com.Banco.model.Investment.InvestmentCompany;
import com.Banco.model.domain.Account.BankAccount;
import com.Banco.model.domain.Account.InvestmentAccount;
import com.Banco.model.domain.Account.SavingsAccount;
import com.Banco.model.domain.Employee.Cashier;
import com.Banco.model.domain.Employee.Employee;
import com.Banco.model.domain.Employee.Receptionist;
import com.Banco.model.domain.Employee.Supervisor;
import com.Banco.model.domain.Person.Client;
import com.Banco.model.domain.Person.Person;

/**
 * Aplicación demostrativa del uso de Programación Orientada a Objetos.
 *
 * <p>Esta clase demuestra explícitamente los siguientes conceptos de POO:</p>
 * <ul>
 *   <li><b>Herencia:</b> Clases derivadas heredando comportamiento de clases base</li>
 *   <li><b>Polimorfismo:</b> Uso de referencias del padre para objetos hijo</li>
 *   <li><b>Abstracción:</b> Uso de clases y métodos abstractos</li>
 *   <li><b>Encapsulamiento:</b> Protección de datos con modificadores de acceso</li>
 *   <li><b>Variables y métodos static:</b> Contadores de clase compartidos</li>
 * </ul>
 *
 * <p><b>Requisito académico:</b> Evidencia de Clases y Objetos - Actividad de aprendizaje 1</p>
 *
 * @author Sistema Bancario Java
 * @version 1.0
 * @since 2025-05-12
 */
public class BankDemoApp {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║   DEMOSTRACIÓN DE PROGRAMACIÓN ORIENTADA A OBJETOS           ║");
        System.out.println("║   Sistema Bancario - Evidencia de Clases y Objetos           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");

        // ══════════════════════════════════════════════════════════════════
        // 1. DEMOSTRACIÓN DE HERENCIA
        // ══════════════════════════════════════════════════════════════════
        demostracionHerencia();

        // ══════════════════════════════════════════════════════════════════
        // 2. DEMOSTRACIÓN DE POLIMORFISMO
        // ══════════════════════════════════════════════════════════════════
        demostracionPolimorfismo();

        // ══════════════════════════════════════════════════════════════════
        // 3. DEMOSTRACIÓN DE ABSTRACCIÓN
        // ══════════════════════════════════════════════════════════════════
        demostracionAbstraccion();

        // ══════════════════════════════════════════════════════════════════
        // 4. DEMOSTRACIÓN DE ENCAPSULAMIENTO
        // ══════════════════════════════════════════════════════════════════
        demostracionEncapsulamiento();

        // ══════════════════════════════════════════════════════════════════
        // 5. DEMOSTRACIÓN DE VARIABLES Y MÉTODOS STATIC
        // ══════════════════════════════════════════════════════════════════
        demostracionMiembrosEstaticos();

        // ══════════════════════════════════════════════════════════════════
        // 6. DEMOSTRACIÓN DE REGLAS DE NEGOCIO
        // ══════════════════════════════════════════════════════════════════
        demostracionReglasNegocio();

        System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║   FIN DE LA DEMOSTRACIÓN                                      ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    /**
     * Demuestra el concepto de HERENCIA.
     *
     * <p>Muestra cómo las clases hijas (Client, Employee) heredan atributos
     * y métodos de la clase padre (Person), y cómo Employee se especializa
     * en Cashier, Supervisor y Receptionist.</p>
     */
    private static void demostracionHerencia() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ 1. DEMOSTRACIÓN DE HERENCIA                                 │");
        System.out.println("└─────────────────────────────────────────────────────────────┘\n");

        System.out.println("► Person (clase abstracta padre)");
        System.out.println("  ├── Client (hereda de Person)");
        System.out.println("  └── Employee (hereda de Person)");
        System.out.println("      ├── Cashier (hereda de Employee)");
        System.out.println("      ├── Supervisor (hereda de Employee)");
        System.out.println("      └── Receptionist (hereda de Employee)\n");

        // Crear instancias de clases derivadas
        Client cliente = new Client(1001, "María González");
        Employee cajero = new Cashier(2001, "Pedro Martínez", 2500.0, 3);
        Employee supervisor = new Supervisor(2002, "Ana López", 4500.0, 10);
        Employee recepcionista = new Receptionist(2003, "Carlos Ruiz", 2200.0, 1);

        System.out.println("✓ Cliente creado: " + cliente);
        System.out.println("  → Hereda getId() y getFullName() de Person");
        System.out.println();

        System.out.println("✓ Empleados creados:");
        System.out.println("  • Cajero: " + cajero.getFullName());
        System.out.println("    → Hereda de Person: id=" + cajero.getId());
        System.out.println("    → Hereda de Employee: calculateVacationDays() = " + cajero.calculateVacationDays() + " días");
        System.out.println();
        System.out.println("  • Supervisor: " + supervisor.getFullName());
        System.out.println("    → Hereda de Person: id=" + supervisor.getId());
        System.out.println("    → Hereda de Employee: calculateVacationDays() = " + supervisor.calculateVacationDays() + " días");
        System.out.println();
        System.out.println("  • Recepcionista: " + recepcionista.getFullName());
        System.out.println("    → Hereda de Person: id=" + recepcionista.getId());
        System.out.println("    → Hereda de Employee: calculateVacationDays() = " + recepcionista.calculateVacationDays() + " días");
        System.out.println();

        printSeparator();
    }

    /**
     * Demuestra el concepto de POLIMORFISMO.
     *
     * <p>Utiliza referencias de la clase padre para almacenar objetos de clases hijas,
     * demostrando que el comportamiento se determina en tiempo de ejecución.</p>
     */
    private static void demostracionPolimorfismo() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ 2. DEMOSTRACIÓN DE POLIMORFISMO                             │");
        System.out.println("└─────────────────────────────────────────────────────────────┘\n");

        System.out.println("► Uso de referencias del PADRE para objetos HIJO\n");

        // ── Polimorfismo con Person ──
        System.out.println("═══ Polimorfismo: Person[] contiene Client y Employee ═══\n");

        Person[] personas = {
                new Client(1001, "Laura Hernández"),
                new Cashier(2001, "Roberto Silva", 2600.0, 5),
                new Supervisor(2002, "Carmen Díaz", 5000.0, 12),
                new Client(1002, "Diego Ramírez")
        };

        System.out.println("Array de tipo Person[] almacena diferentes subtipos:");
        for (int i = 0; i < personas.length; i++) {
            Person p = personas[i];
            System.out.printf("  [%d] Tipo real: %-15s | ID: %d | Nombre: %s%n",
                    i, p.getClass().getSimpleName(), p.getId(), p.getFullName());
        }
        System.out.println();

        // ── Polimorfismo con Employee ──
        System.out.println("═══ Polimorfismo: Employee[] con diferentes puestos ═══\n");

        Employee[] empleados = {
                new Cashier(3001, "Sofía Torres", 2400.0, 2),
                new Supervisor(3002, "Miguel Ángel Pérez", 4800.0, 8),
                new Receptionist(3003, "Valentina Castro", 2100.0, 1),
                new Cashier(3004, "Andrés Morales", 2700.0, 4)
        };

        System.out.println("Array de tipo Employee[] - Método polimórfico calculateVacationDays():");
        for (Employee emp : empleados) {
            System.out.printf("  • %-15s | %s | Años: %2d → Vacaciones: %2d días%n",
                    emp.getEmployeeType(),
                    emp.getFullName(),
                    emp.getYearsWorked(),
                    emp.calculateVacationDays());
        }
        System.out.println();

        // ── Polimorfismo con BankAccount ──
        System.out.println("═══ Polimorfismo: BankAccount[] con diferentes tipos ═══\n");

        Client propietario = new Client(4001, "Gabriela Mendoza");
        InvestmentCompany company = new InvestmentCompany("INV001", "Global Investments", 0.08, 2, 0.95);

        BankAccount[] cuentas = {
                new SavingsAccount("SA001", propietario, 1000.0, 0.05),
                new InvestmentAccount("IA001", propietario, 25000.0, company),
                new SavingsAccount("SA002", propietario, 1000.0, 0.04),
                new InvestmentAccount("IA002", propietario, 50000.0, company)
        };

        System.out.println("Array de tipo BankAccount[] - Método polimórfico withdraw():");
        for (BankAccount cuenta : cuentas) {
            System.out.printf("  • %-20s | Balance: $%,10.2f | Tipo: %s%n",
                    cuenta.getAccountNumber(),
                    cuenta.getBalance(),
                    cuenta.getAccountType());

            // Intentar retiro polimórfico
            try {
                double retiro = 100.0;
                cuenta.withdraw(retiro);
                System.out.printf("    ✓ Retiro exitoso de $%.2f | Nuevo balance: $%,.2f%n",
                        retiro, cuenta.getBalance());
            } catch (Exception e) {
                System.out.printf("    ✗ Retiro rechazado: %s%n", e.getMessage());
            }
        }
        System.out.println();

        printSeparator();
    }

    /**
     * Demuestra el concepto de ABSTRACCIÓN.
     *
     * <p>Muestra cómo las clases abstractas (Person, Employee, BankAccount)
     * definen contratos que las clases concretas deben implementar.</p>
     */
    private static void demostracionAbstraccion() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ 3. DEMOSTRACIÓN DE ABSTRACCIÓN                              │");
        System.out.println("└─────────────────────────────────────────────────────────────┘\n");

        System.out.println("► Las clases abstractas definen CONTRATOS que las clases concretas implementan\n");

        System.out.println("═══ Person (abstracta) ═══");
        System.out.println("  ✗ No se puede instanciar: new Person(1, \"Nombre\")");
        System.out.println("  ✓ Define estructura común: id, fullName, equals(), hashCode()");
        System.out.println("  ✓ Clases concretas: Client, Employee\n");

        System.out.println("═══ Employee (abstracta, hereda de Person) ═══");
        System.out.println("  ✗ No se puede instanciar: new Employee(...)");
        System.out.println("  ✓ Método abstracto: getEmployeeType()");
        System.out.println("  ✓ Método concreto: calculateVacationDays()");
        System.out.println("  ✓ Clases concretas: Cashier, Supervisor, Receptionist\n");

        // Demostración práctica
        Employee[] empleados = {
                new Cashier(5001, "Luis Fernando", 2500.0, 3),
                new Supervisor(5002, "Patricia Gómez", 4500.0, 7),
                new Receptionist(5003, "Camila Rojas", 2200.0, 2)
        };

        System.out.println("Cada empleado DEBE implementar getEmployeeType() (método abstracto):");
        for (Employee emp : empleados) {
            System.out.printf("  • %s → getEmployeeType() = %s%n",
                    emp.getClass().getSimpleName(),
                    emp.getEmployeeType());
        }
        System.out.println();

        System.out.println("═══ BankAccount (abstracta) ═══");
        System.out.println("  ✗ No se puede instanciar: new BankAccount(...)");
        System.out.println("  ✓ Métodos abstractos:");
        System.out.println("    - isWithdrawalValid(amount)");
        System.out.println("    - getAccountType()");
        System.out.println("    - getMinimumInitialBalance()");
        System.out.println("  ✓ Clases concretas: SavingsAccount, InvestmentAccount\n");

        Client cliente = new Client(6001, "Fernando Vargas");
        InvestmentCompany company = new InvestmentCompany("INV002", "Tech Investments", 0.10, 3, 0.90);

        BankAccount[] cuentas = {
                new SavingsAccount("SA003", cliente, 1000.0, 0.05),
                new InvestmentAccount("IA003", cliente, 25000.0, company)
        };

        System.out.println("Cada cuenta DEBE implementar getAccountType() (método abstracto):");
        for (BankAccount cuenta : cuentas) {
            System.out.printf("  • %s → getAccountType() = \"%s\"%n",
                    cuenta.getClass().getSimpleName(),
                    cuenta.getAccountType());
        }
        System.out.println();

        printSeparator();
    }

    /**
     * Demuestra el concepto de ENCAPSULAMIENTO.
     *
     * <p>Muestra cómo los atributos privados están protegidos y solo son
     * accesibles mediante getters/setters, garantizando la integridad de los datos.</p>
     */
    private static void demostracionEncapsulamiento() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ 4. DEMOSTRACIÓN DE ENCAPSULAMIENTO                          │");
        System.out.println("└─────────────────────────────────────────────────────────────┘\n");

        System.out.println("► Los atributos son PRIVADOS y protegidos con validaciones\n");

        Client cliente = new Client(7001, "Ricardo Navarro");

        System.out.println("═══ Protección de datos en Client ═══");
        System.out.println("  ✗ NO se puede acceder: cliente.id (es private final)");
        System.out.println("  ✗ NO se puede acceder: cliente.fullName (es private)");
        System.out.println("  ✓ Acceso controlado: cliente.getId() = " + cliente.getId());
        System.out.println("  ✓ Acceso controlado: cliente.getFullName() = \"" + cliente.getFullName() + "\"");
        System.out.println();

        System.out.println("═══ Validaciones en setters ═══");
        try {
            System.out.println("  Intentando establecer nombre vacío...");
            cliente.setFullName("");  // Debe fallar
        } catch (Exception e) {
            System.out.println("  ✓ Validación exitosa: " + e.getMessage());
        }

        try {
            System.out.println("\n  Intentando establecer nombre válido...");
            cliente.setFullName("Ricardo Navarro Actualizado");
            System.out.println("  ✓ Nombre actualizado: " + cliente.getFullName());
        } catch (Exception e) {
            System.out.println("  ✗ Error: " + e.getMessage());
        }
        System.out.println();

        Employee empleado = new Cashier(7002, "Daniela Cruz", 2600.0, 4);

        System.out.println("═══ Validaciones en Employee ═══");
        System.out.println("  Salario actual: $" + empleado.getSalary());

        try {
            System.out.println("  Intentando establecer salario negativo: -1000.0");
            empleado.setSalary(-1000.0);
        } catch (Exception e) {
            System.out.println("  ✓ Validación exitosa: " + e.getMessage());
        }

        try {
            System.out.println("\n  Intentando establecer salario válido: 3000.0");
            empleado.setSalary(3000.0);
            System.out.println("  ✓ Salario actualizado: $" + empleado.getSalary());
        } catch (Exception e) {
            System.out.println("  ✗ Error: " + e.getMessage());
        }
        System.out.println();

        printSeparator();
    }

    /**
     * Demuestra el uso de VARIABLES Y MÉTODOS STATIC.
     *
     * <p>Muestra cómo los contadores de clase son compartidos entre todas las instancias
     * y cómo se accede a ellos sin necesidad de crear objetos.</p>
     */
    private static void demostracionMiembrosEstaticos() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ 5. DEMOSTRACIÓN DE VARIABLES Y MÉTODOS STATIC              │");
        System.out.println("└─────────────────────────────────────────────────────────────┘\n");

        System.out.println("► Variables static: compartidas por TODAS las instancias\n");

        System.out.println("═══ Contador estático de Client ═══");
        int clientesAntes = Client.getClientCount();
        System.out.println("  Clientes totales antes: " + clientesAntes);

        Client c1 = new Client(8001, "Cliente Uno");
        Client c2 = new Client(8002, "Cliente Dos");
        Client c3 = new Client(8003, "Cliente Tres");

        int clientesDespues = Client.getClientCount();
        System.out.println("  Clientes totales después: " + clientesDespues);
        System.out.println("  ✓ Incremento: " + (clientesDespues - clientesAntes) + " clientes\n");

        System.out.println("═══ Contador estático de Employee ═══");
        int empleadosAntes = Employee.getEmployeeCount();
        System.out.println("  Empleados totales antes: " + empleadosAntes);

        Employee e1 = new Cashier(8101, "Empleado A", 2500.0, 2);
        Employee e2 = new Supervisor(8102, "Empleado B", 4500.0, 8);
        Employee e3 = new Receptionist(8103, "Empleado C", 2200.0, 1);
        Employee e4 = new Cashier(8104, "Empleado D", 2600.0, 3);

        int empleadosDespues = Employee.getEmployeeCount();
        System.out.println("  Empleados totales después: " + empleadosDespues);
        System.out.println("  ✓ Incremento: " + (empleadosDespues - empleadosAntes) + " empleados\n");

        System.out.println("═══ Contador estático de BankAccount ═══");
        Client propietario = new Client(8201, "Propietario Múltiple");
        int cuentasAntes = BankAccount.getAccountCount();
        System.out.println("  Cuentas totales antes: " + cuentasAntes);

        BankAccount a1 = new SavingsAccount("SA100", propietario, 1000.0, 0.05);
        BankAccount a2 = new SavingsAccount("SA101", propietario, 1000.0, 0.04);
        InvestmentCompany company = new InvestmentCompany("INV100", "Demo Co.", 0.08, 2, 0.95);
        BankAccount a3 = new InvestmentAccount("IA100", propietario, 25000.0, company);

        int cuentasDespues = BankAccount.getAccountCount();
        System.out.println("  Cuentas totales después: " + cuentasDespues);
        System.out.println("  ✓ Incremento: " + (cuentasDespues - cuentasAntes) + " cuentas\n");

        System.out.println("═══ Método static de InvestmentCompany ═══");
        int companiesAntes = InvestmentCompany.getCompanyCount();
        System.out.println("  Compañías totales antes: " + companiesAntes);

        InvestmentCompany comp1 = new InvestmentCompany("COMP1", "Company One", 0.07, 1, 0.98);
        InvestmentCompany comp2 = new InvestmentCompany("COMP2", "Company Two", 0.09, 3, 0.92);

        int companiesDespues = InvestmentCompany.getCompanyCount();
        System.out.println("  Compañías totales después: " + companiesDespues);
        System.out.println("  ✓ Incremento: " + (companiesDespues - companiesAntes) + " compañías\n");

        printSeparator();
    }

    /**
     * Demuestra las REGLAS DE NEGOCIO específicas del sistema bancario.
     *
     * <p>Valida restricciones como depósitos mínimos, saldos mínimos,
     * cálculo de vacaciones y otras reglas del dominio.</p>
     */
    private static void demostracionReglasNegocio() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ 6. DEMOSTRACIÓN DE REGLAS DE NEGOCIO                        │");
        System.out.println("└─────────────────────────────────────────────────────────────┘\n");

        Client cliente = new Client(9001, "Javier Sánchez");

        System.out.println("═══ Cuenta de Ahorro: Depósito inicial $1,000 ═══");
        try {
            System.out.println("  Intentando crear cuenta con $500 (mínimo es $1,000)...");
            SavingsAccount cuentaInvalida = new SavingsAccount("SA200", cliente, 500.0, 0.05);
        } catch (Exception e) {
            System.out.println("  ✓ Validación exitosa: " + e.getMessage());
        }

        try {
            System.out.println("\n  Creando cuenta con $1,000 (depósito válido)...");
            SavingsAccount cuentaValida = new SavingsAccount("SA201", cliente, 1000.0, 0.05);
            System.out.println("  ✓ Cuenta creada: " + cuentaValida.getAccountNumber() +
                    " | Balance: $" + cuentaValida.getBalance());
        } catch (Exception e) {
            System.out.println("  ✗ Error: " + e.getMessage());
        }
        System.out.println();

        System.out.println("═══ Cuenta de Ahorro: Saldo mínimo $500 ═══");
        SavingsAccount ahorro = new SavingsAccount("SA202", cliente, 1000.0, 0.05);
        System.out.println("  Balance actual: $" + ahorro.getBalance());

        try {
            System.out.println("  Intentando retirar $600 (dejaría $400 < $500)...");
            ahorro.withdraw(600.0);
        } catch (Exception e) {
            System.out.println("  ✓ Validación exitosa: Retiro bloqueado");
        }

        try {
            System.out.println("\n  Intentando retirar $400 (dejaría $600 ≥ $500)...");
            ahorro.withdraw(400.0);
            System.out.println("  ✓ Retiro exitoso | Nuevo balance: $" + ahorro.getBalance());
        } catch (Exception e) {
            System.out.println("  ✗ Error: " + e.getMessage());
        }
        System.out.println();

        System.out.println("═══ Cuenta de Inversión: Depósito inicial $25,000 ═══");
        InvestmentCompany company = new InvestmentCompany("INV200", "Premium Inv.", 0.10, 2, 0.96);

        try {
            System.out.println("  Intentando crear cuenta con $10,000 (mínimo es $25,000)...");
            InvestmentAccount invInvalida = new InvestmentAccount("IA200", cliente, 10000.0, company);
        } catch (Exception e) {
            System.out.println("  ✓ Validación exitosa: " + e.getMessage());
        }

        try {
            System.out.println("\n  Creando cuenta con $30,000 (depósito válido)...");
            InvestmentAccount invValida = new InvestmentAccount("IA201", cliente, 30000.0, company);
            System.out.println("  ✓ Cuenta creada: " + invValida.getAccountNumber() +
                    " | Balance: $" + invValida.getBalance());
        } catch (Exception e) {
            System.out.println("  ✗ Error: " + e.getMessage());
        }
        System.out.println();

        System.out.println("═══ Cálculo de vacaciones de empleados ═══");
        Employee[] empleados = {
                new Cashier(9101, "Empleado un año", 2500.0, 1),
                new Supervisor(9102, "Empleado cinco años", 4500.0, 5),
                new Receptionist(9103, "Empleado diez años", 2200.0, 10),
                new Cashier(9104, "Empleado quince años", 2700.0, 15)
        };

        System.out.println("  Regla: 5 días base + 2 días/año (máximo 20 días)\n");
        for (Employee emp : empleados) {
            int vacaciones = emp.calculateVacationDays();
            System.out.printf("  • %2d años → %2d días de vacaciones%n",
                    emp.getYearsWorked(), vacaciones);
        }
        System.out.println();

        System.out.println("═══ Intereses mensuales en cuenta de ahorro ═══");
        SavingsAccount cuentaInteres = new SavingsAccount("SA300", cliente, 1000.0, 0.12);
        System.out.println("  Balance inicial: $" + cuentaInteres.getBalance());
        System.out.println("  Tasa anual: 12% (1% mensual)");

        cuentaInteres.applyMonthlyInterest();
        System.out.println("  Balance después de 1 mes: $" + String.format("%.2f", cuentaInteres.getBalance()));

        cuentaInteres.applyMonthlyInterest();
        System.out.println("  Balance después de 2 meses: $" + String.format("%.2f", cuentaInteres.getBalance()));

        cuentaInteres.applyMonthlyInterest();
        System.out.println("  Balance después de 3 meses: $" + String.format("%.2f", cuentaInteres.getBalance()));
        System.out.println();

        printSeparator();
    }

    /**
     * Imprime un separador visual entre secciones.
     */
    private static void printSeparator() {
        System.out.println("─────────────────────────────────────────────────────────────\n");
    }
}