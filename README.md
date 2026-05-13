C:\Users\Admin\.jdks\ms-21.0.11\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2025.3.3\lib\idea_rt.jar=49752" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "C:\Users\Admin\OneDrive - Periferia IT Corp SAS\Documentos\CompraVenta\Banco\Banco\target\classes" com.Banco.BankDemoApp
╔═══════════════════════════════════════════════════════════════╗
║   DEMOSTRACIÓN DE PROGRAMACIÓN ORIENTADA A OBJETOS           ║
║   Sistema Bancario - Evidencia de Clases y Objetos           ║
╚═══════════════════════════════════════════════════════════════╝

┌─────────────────────────────────────────────────────────────┐
│ 1. DEMOSTRACIÓN DE HERENCIA                                 │
└─────────────────────────────────────────────────────────────┘

► Person (clase abstracta padre)
  ├── Client (hereda de Person)
  └── Employee (hereda de Person)
      ├── Cashier (hereda de Employee)
      ├── Supervisor (hereda de Employee)
      └── Receptionist (hereda de Employee)

✓ Cliente creado: Client[id=1001, name=María González, accounts=0]
  → Hereda getId() y getFullName() de Person

✓ Empleados creados:
  • Cajero: Pedro Martínez
    → Hereda de Person: id=2001
    → Hereda de Employee: calculateVacationDays() = 9 días

  • Supervisor: Ana López
    → Hereda de Person: id=2002
    → Hereda de Employee: calculateVacationDays() = 20 días

  • Recepcionista: Carlos Ruiz
    → Hereda de Person: id=2003
    → Hereda de Employee: calculateVacationDays() = 5 días

─────────────────────────────────────────────────────────────

┌─────────────────────────────────────────────────────────────┐
│ 2. DEMOSTRACIÓN DE POLIMORFISMO                             │
└─────────────────────────────────────────────────────────────┘

► Uso de referencias del PADRE para objetos HIJO

═══ Polimorfismo: Person[] contiene Client y Employee ═══

Array de tipo Person[] almacena diferentes subtipos:
  [0] Tipo real: Client          | ID: 1001 | Nombre: Laura Hernández
  [1] Tipo real: Cashier         | ID: 2001 | Nombre: Roberto Silva
  [2] Tipo real: Supervisor      | ID: 2002 | Nombre: Carmen Díaz
  [3] Tipo real: Client          | ID: 1002 | Nombre: Diego Ramírez

═══ Polimorfismo: Employee[] con diferentes puestos ═══

Array de tipo Employee[] - Método polimórfico calculateVacationDays():
  • CASHIER         | Sofía Torres | Años:  2 → Vacaciones:  7 días
  • SUPERVISOR      | Miguel Ángel Pérez | Años:  8 → Vacaciones: 19 días
  • RECEPTIONIST    | Valentina Castro | Años:  1 → Vacaciones:  5 días
  • CASHIER         | Andrés Morales | Años:  4 → Vacaciones: 11 días

═══ Polimorfismo: BankAccount[] con diferentes tipos ═══

Array de tipo BankAccount[] - Método polimórfico withdraw():
  • SA001                | Balance: $  1.000,00 | Tipo: Savings Account
    ✓ Retiro exitoso de $100,00 | Nuevo balance: $900,00
  • IA001                | Balance: $ 25.000,00 | Tipo: Investment Account
    ✓ Retiro exitoso de $100,00 | Nuevo balance: $24.900,00
  • SA002                | Balance: $  1.000,00 | Tipo: Savings Account
    ✓ Retiro exitoso de $100,00 | Nuevo balance: $900,00
  • IA002                | Balance: $ 50.000,00 | Tipo: Investment Account
    ✓ Retiro exitoso de $100,00 | Nuevo balance: $49.900,00

─────────────────────────────────────────────────────────────

┌─────────────────────────────────────────────────────────────┐
│ 3. DEMOSTRACIÓN DE ABSTRACCIÓN                              │
└─────────────────────────────────────────────────────────────┘

► Las clases abstractas definen CONTRATOS que las clases concretas implementan

═══ Person (abstracta) ═══
  ✗ No se puede instanciar: new Person(1, "Nombre")
  ✓ Define estructura común: id, fullName, equals(), hashCode()
  ✓ Clases concretas: Client, Employee

═══ Employee (abstracta, hereda de Person) ═══
  ✗ No se puede instanciar: new Employee(...)
  ✓ Método abstracto: getEmployeeType()
  ✓ Método concreto: calculateVacationDays()
  ✓ Clases concretas: Cashier, Supervisor, Receptionist

Cada empleado DEBE implementar getEmployeeType() (método abstracto):
  • Cashier → getEmployeeType() = CASHIER
  • Supervisor → getEmployeeType() = SUPERVISOR
  • Receptionist → getEmployeeType() = RECEPTIONIST

═══ BankAccount (abstracta) ═══
  ✗ No se puede instanciar: new BankAccount(...)
  ✓ Métodos abstractos:
    - isWithdrawalValid(amount)
    - getAccountType()
    - getMinimumInitialBalance()
  ✓ Clases concretas: SavingsAccount, InvestmentAccount

Cada cuenta DEBE implementar getAccountType() (método abstracto):
  • SavingsAccount → getAccountType() = "Savings Account"
  • InvestmentAccount → getAccountType() = "Investment Account"

─────────────────────────────────────────────────────────────

┌─────────────────────────────────────────────────────────────┐
│ 4. DEMOSTRACIÓN DE ENCAPSULAMIENTO                          │
└─────────────────────────────────────────────────────────────┘

► Los atributos son PRIVADOS y protegidos con validaciones

═══ Protección de datos en Client ═══
  ✗ NO se puede acceder: cliente.id (es private final)
  ✗ NO se puede acceder: cliente.fullName (es private)
  ✓ Acceso controlado: cliente.getId() = 7001
  ✓ Acceso controlado: cliente.getFullName() = "Ricardo Navarro"

═══ Validaciones en setters ═══
  Intentando establecer nombre vacío...
  ✓ Validación exitosa: Name must not be blank

  Intentando establecer nombre válido...
  ✓ Nombre actualizado: Ricardo Navarro Actualizado

═══ Validaciones en Employee ═══
  Salario actual: $2600.0
  Intentando establecer salario negativo: -1000.0
  ✓ Validación exitosa: Salary cannot be negative

  Intentando establecer salario válido: 3000.0
  ✓ Salario actualizado: $3000.0

─────────────────────────────────────────────────────────────

┌─────────────────────────────────────────────────────────────┐
│ 5. DEMOSTRACIÓN DE VARIABLES Y MÉTODOS STATIC              │
└─────────────────────────────────────────────────────────────┘

► Variables static: compartidas por TODAS las instancias

═══ Contador estático de Client ═══
  Clientes totales antes: 6
  Clientes totales después: 9
  ✓ Incremento: 3 clientes

═══ Contador estático de Employee ═══
  Empleados totales antes: 13
  Empleados totales después: 17
  ✓ Incremento: 4 empleados

═══ Contador estático de BankAccount ═══
  Cuentas totales antes: 6
  Cuentas totales después: 9
  ✓ Incremento: 3 cuentas

═══ Método static de InvestmentCompany ═══
  Compañías totales antes: 3
  Compañías totales después: 5
  ✓ Incremento: 2 compañías

─────────────────────────────────────────────────────────────

┌─────────────────────────────────────────────────────────────┐
│ 6. DEMOSTRACIÓN DE REGLAS DE NEGOCIO                        │
└─────────────────────────────────────────────────────────────┘

═══ Cuenta de Ahorro: Depósito inicial $1,000 ═══
  Intentando crear cuenta con $500 (mínimo es $1,000)...
  ✓ Validación exitosa: Initial balance $500,00 is below required minimum $1000,00 for SavingsAccount

  Creando cuenta con $1,000 (depósito válido)...
  ✓ Cuenta creada: SA201 | Balance: $1000.0

═══ Cuenta de Ahorro: Saldo mínimo $500 ═══
  Balance actual: $1000.0
  Intentando retirar $600 (dejaría $400 < $500)...
  ✓ Validación exitosa: Retiro bloqueado

  Intentando retirar $400 (dejaría $600 ≥ $500)...
  ✓ Retiro exitoso | Nuevo balance: $600.0

═══ ~~Cuenta de Inversión:~~ Depósito inicial $25,000 ═══
  Intentando crear cuenta con $10,000 (mínimo es $25,000)...
  ✓ Validación exitosa: Initial balance $10000,00 is below required minimum $25000,00 for InvestmentAccount

  Creando cuenta con $30,000 (depósito válido)...
  ✓ Cuenta creada: IA201 | Balance: $30000.0

═══ Cálculo de vacaciones de empleados ═══
  Regla: 5 días base + 2 días/año (máximo 20 días)

  •  1 años →  5 días de vacaciones
  •  5 años → 13 días de vacaciones
  • 10 años → 20 días de vacaciones
  • 15 años → 20 días de vacaciones

═══ Intereses mensuales en cuenta de ahorro ═══
  Balance inicial: $1000.0
  Tasa anual: 12% (1% mensual)
  Balance después de 1 mes: $1010,00
  Balance después de 2 meses: $1020,10
  Balance después de 3 meses: $1030,30

─────────────────────────────────────────────────────────────


╔═══════════════════════════════════════════════════════════════╗
║   FIN DE LA DEMOSTRACIÓN                                      ║
╚═══════════════════════════════════════════════════════════════╝

Process finished with exit code 0
