# 🏦 BancoJava — Sistema Bancario con Java Swing

Sistema bancario de escritorio desarrollado en Java, con interfaz gráfica Swing y arquitectura MVC. Demuestra los cuatro pilares de la Programación Orientada a Objetos: herencia, polimorfismo, abstracción y encapsulamiento.

---

## 📋 Descripción

Aplicación académica que simula las operaciones esenciales de un banco:

- Gestión de clientes y cuentas bancarias (ahorro e inversión)
- Gestión de empleados con cálculo de vacaciones por antigüedad
- Empresas inversoras con niveles de riesgo y retorno configurable
- Aplicación automática de intereses mensuales mediante `ScheduledExecutorService`
- Historial de transacciones por cuenta
- Reportes del sistema en tiempo real
- Demostración explícita de conceptos OOP mediante `BankDemoApp`

---

## 🏗️ Arquitectura

El proyecto sigue el patrón **MVC (Model-View-Controller)** con separación estricta de capas:

```
src/main/java/com/Banco/
│
├── AppBanco.java                    ← Punto de entrada + Scheduler de intereses
├── BankDemoApp.java                 ← Demostración completa de POO
│
├── model/
│   ├── domain/
│   │   ├── Person/
│   │   │   ├── Person.java          ← Clase base abstracta
│   │   │   └── Client.java          ← Hereda de Person
│   │   ├── Employee/
│   │   │   ├── Employee.java        ← Abstract: vacaciones por antigüedad
│   │   │   ├── Cashier.java
│   │   │   ├── Supervisor.java
│   │   │   └── Receptionist.java
│   │   └── Account/
│   │       ├── BankAccount.java     ← Clase abstracta de cuenta
│   │       ├── SavingsAccount.java  ← Ahorro con tasa de interés
│   │       └── InvestmentAccount.java ← Inversión con nivel de riesgo
│   ├── Investment/
│   │   └── InvestmentCompany.java
│   └── Transaction/
│       └── Transaction.java
│
├── controller/
│   ├── BankController.java          ← Fachada central para la Vista
│   ├── AccountController.java
│   ├── ClientController.java
│   ├── EmployeeController.java
│   ├── CompanyController.java
│   ├── TransactionController.java
│   └── ReportController.java
│
├── service/
│   ├── BankService.java             ← Orquestación de servicios
│   ├── AccountService.java
│   ├── ClientService.java
│   ├── EmployeeService.java
│   ├── CompanyService.java
│   ├── TransactionService.java
│   └── ReportService.java
│
├── repository/
│   ├── AccountRepository.java
│   ├── ClientRepository.java
│   ├── EmployeeRepository.java
│   ├── CompanyRepository.java
│   └── TransactionRepository.java
│
├── exceptions/
│   ├── BankingException.java        ← Base de todas las excepciones
│   ├── AccountOwnershipException.java
│   ├── AccountNotFoundException.java
│   ├── ClientNotFoundException.java
│   ├── EmployeeNotFoundException.java
│   ├── InsufficientFundsException.java
│   ├── InvalidAmountException.java
│   ├── InvalidTransactionException.java
│   ├── InvalidWithdrawalException.java
│   ├── MinimumBalanceException.java
│   ├── DuplicateResourceException.java
│   └── ValidationException.java
│
├── util/
│   ├── Constants.java
│   ├── InputHelper.java
│   ├── UIUtils.java
│   ├── Validator.java
│   ├── validator/
│   │   ├── BaseValidator.java
│   │   ├── AccountValidator.java
│   │   ├── EmployeeValidator.java
│   │   ├── AmountValidator.java
│   │   └── InputValidator.java
│   └── formatter/
│       ├── CurrencyFormatter.java
│       └── DateUtil.java
│
└── view/
    ├── BankApp.java                 ← Frame principal
    ├── base/
    │   └── BaseTablePanel.java
    ├── panel/
    │   ├── AccountPanel.java
    │   ├── ClientPanel.java
    │   ├── EmployeePanel.java
    │   ├── TransactionPanel.java
    │   ├── CompanyPanel.java
    │   ├── ReportPanel.java
    │   └── ReportTablePanel.java
    └── componet/
        └── StatusBar.java
```

---

## 📐 Diagrama de Herencia

```
Person  (abstract)
├── Client
└── Employee  (abstract)
    ├── Cashier
    ├── Supervisor
    └── Receptionist

BankAccount  (abstract)
├── SavingsAccount
└── InvestmentAccount
```

---

## ✅ Requisitos previos

| Herramienta | Versión mínima |
|---|---|
| Java (JDK) | 21 |
| Maven | 3.8+ |
| IntelliJ IDEA / Eclipse | Cualquier versión reciente |

---

## 🚀 Instalación y ejecución

**1. Clonar el repositorio**

```bash
git clone https://github.com/tu-usuario/banco-java.git
cd banco-java
```

**2. Compilar el proyecto**

```bash
mvn clean compile
```

**3. Ejecutar la aplicación con interfaz gráfica**

```bash
mvn exec:java -Dexec.mainClass="com.Banco.AppBanco"
```

**4. Ejecutar la demostración de POO por consola**

```bash
mvn exec:java -Dexec.mainClass="com.Banco.BankDemoApp"
```

---

## 🧪 Tests

El proyecto incluye una suite de pruebas unitarias con **JUnit 5** y **Mockito**, con cobertura mínima del 80% verificada por JaCoCo.

**Ejecutar todos los tests:**

```bash
mvn test
```

**Ver reporte de cobertura:**

```bash
mvn test jacoco:report
# Reporte disponible en: target/site/jacoco/index.html
```

### Estructura de tests

| Archivo | Capa | Tests |
|---|---|---|
| `SavingsAccountTest.java` | Modelo | Depósito exacto $1,000, saldo mínimo, interés compuesto |
| `InvestmentAccountTest.java` | Modelo | Depósito mínimo $25,000, fullWithdraw, cancelación |
| `EmployeeTest.java` | Modelo | Vacaciones (9 casos parametrizados), polimorfismo |
| `ClientTest.java` | Modelo | Encapsulamiento, gestión de cuentas, equals por ID |
| `InvestmentCompanyTest.java` | Modelo | Validaciones, niveles de riesgo (5 casos) |
| `AccountServiceTest.java` | Servicio | Ownership, mocks de repositorios, intereses |
| `PersonServicesTest.java` | Servicio | ClientService y EmployeeService con Mockito |
| `RepositoryTest.java` | Repositorio | CRUD, búsquedas, listas inmutables |
| `ValidatorTest.java` | Validadores | AccountValidator, EmployeeValidator, InputValidator |

---

## 📏 Reglas de negocio

### Cuenta de Ahorro (`SavingsAccount`)

| Regla | Valor |
|---|---|
| Primer depósito | Exactamente **$1,000** |
| Saldo mínimo | **$500** (no se puede dejar por debajo) |
| Tasa de interés | Configurable al crear la cuenta (0.0 – 1.0 anual) |
| Interés mensual | Se aplica automáticamente cada 30 días |

### Cuenta de Inversión (`InvestmentAccount`)

| Regla | Valor |
|---|---|
| Depósito inicial mínimo | **$25,000** |
| Saldo mínimo | **$10,000** |
| Retiro total | Mediante `fullWithdraw()` — cancela la cuenta |

### Vacaciones de empleados

```
Años trabajados = 0       →  0 días
Años trabajados = 1       →  5 días  (base)
Años trabajados = 2 a N   →  5 + 2 × (años - 1) días
Máximo                    → 20 días
```

| Años | Días |
|---|---|
| 0 | 0 |
| 1 | 5 |
| 5 | 13 |
| 8 | 19 |
| 10+ | 20 (tope) |

---

## 🔑 Conceptos OOP demostrados

### Herencia
`Person → Client / Employee` — `Employee → Cashier / Supervisor / Receptionist` — `BankAccount → SavingsAccount / InvestmentAccount`

### Polimorfismo
```java
BankAccount[] accounts = {
    new SavingsAccount("SA001", client, 1000.0, 0.05),
    new InvestmentAccount("IA001", client, 25000.0, company)
};
for (BankAccount acc : accounts) {
    acc.withdraw(100.0);  // comportamiento diferente por tipo
}
```

### Abstracción
`BankAccount` declara métodos abstractos que cada tipo de cuenta implementa de forma distinta:
- `isWithdrawalValid(double amount)`
- `getAccountType()`
- `getMinimumInitialBalance()`

### Encapsulamiento
Todos los atributos son `private`. Los setters validan antes de modificar:
```java
public void setSalary(double salary) {
    if (salary < 0)
        throw new IllegalArgumentException("Salary cannot be negative");
    this.salary = salary;
}
```

### Variables y métodos `static`
Cada entidad mantiene un contador de instancias creadas:
```java
Client.getClientCount()
Employee.getEmployeeCount()
BankAccount.getAccountCount()
InvestmentCompany.getCompanyCount()
```

---

## 🔒 Jerarquía de excepciones

```
BankingException  (RuntimeException base)
├── AccountNotFoundException
├── AccountOwnershipException
├── ClientNotFoundException
├── EmployeeNotFoundException
├── DuplicateResourceException
├── InsufficientFundsException
├── InvalidAmountException
├── InvalidTransactionException
├── InvalidWithdrawalException
├── MinimumBalanceException
└── ValidationException
```

---

## ⚙️ Constantes de negocio (`Constants.java`)

```java
SAVINGS_MINIMUM_INITIAL   = 1_000.0
SAVINGS_MINIMUM_BALANCE   =   500.0
INVESTMENT_MINIMUM_INITIAL = 25_000.0
INVESTMENT_MINIMUM_BALANCE = 10_000.0
VACATION_BASE_DAYS        = 5
VACATION_INCREMENT        = 2
VACATION_MAX_DAYS         = 20
RISK_LEVEL_MIN            = 1
RISK_LEVEL_MAX            = 5
```

---

## 🖥️ Interfaz gráfica

La aplicación `BankApp` incluye:

| Panel | Funcionalidad |
|---|---|
| **Dashboard** | Resumen en tiempo real: clientes, cuentas, empleados, inversiones |
| **Clients** | Registro y listado de clientes |
| **Employees** | Registro de cajeros, supervisores y recepcionistas con cálculo de vacaciones |
| **Accounts** | Apertura de cuentas, depósitos, retiros, aplicación de intereses |
| **Transactions** | Historial completo de operaciones |
| **Investors** | Gestión de empresas inversoras |
| **Reports** | Reportes textuales de clientes, empleados, cuentas y compañías |

---

## 📊 Cobertura de requisitos académicos

| Requisito | Estado |
|---|---|
| Modelación UML (clases y relaciones) | ✅ |
| Variables de instancia | ✅ |
| Variables y métodos `static` | ✅ |
| Herencia (Person, Employee, BankAccount) | ✅ |
| Polimorfismo explícito | ✅ |
| Abstracción (clases y métodos abstractos) | ✅ |
| Encapsulamiento (atributos privados + setters validados) | ✅ |
| Validaciones bancarias (depósitos, saldos mínimos) | ✅ |
| Manejo de excepciones personalizadas | ✅ |
| Arquitectura MVC | ✅ |
| Aplicación demostrativa (`BankDemoApp`) | ✅ |
| Pruebas unitarias (JUnit 5 + Mockito) | ✅ |

---

## 👤 Autor

Proyecto desarrollado como evidencia de aprendizaje para la **Actividad 1 — Clases y Objetos**.

Tecnologías: `Java 21` · `Java Swing` · `Maven` · `JUnit 5` · `Mockito` · `JaCoCo`
