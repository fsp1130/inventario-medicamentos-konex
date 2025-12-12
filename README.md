# 💊 Sistema de Inventario y Ventas de Medicamentos - Konex

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-17-red.svg)](https://angular.io/)
[![PrimeNG](https://img.shields.io/badge/PrimeNG-17.18.0-blue.svg)](https://primeng.org/)
[![Oracle](https://img.shields.io/badge/Oracle-Database-red.svg)](https://www.oracle.com/database/)

Sistema completo de gestión de inventario y ventas de medicamentos desarrollado con arquitectura hexagonal, Spring Boot en el backend y Angular con PrimeNG en el frontend.

---

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Arquitectura](#-arquitectura)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Ejecución](#-ejecución)
- [Pruebas](#-pruebas)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [API Endpoints](#-api-endpoints)
- [Capturas de Pantalla](#-capturas-de-pantalla)
- [Contribución](#-contribución)
- [Licencia](#-licencia)
- [Contacto](#-contacto)

---

## ✨ Características

### Backend
- ✅ **Arquitectura Hexagonal** (Puertos y Adaptadores)
- ✅ **CRUD completo** de medicamentos
- ✅ **Sistema de ventas** con validaciones de negocio
- ✅ **Gestión de inventario** automática
- ✅ **Paginación y filtros** en consultas
- ✅ **Validaciones robustas** de fechas y stock
- ✅ **Manejo de excepciones** centralizado
- ✅ **Base de datos Oracle** con soporte H2 para desarrollo
- ✅ **Pruebas unitarias** con JUnit y Mockito
- ✅ **Documentación** con Swagger (opcional)

### Frontend
- ✅ **Interfaz moderna** con PrimeNG
- ✅ **Diseño responsive** para móviles y tablets
- ✅ **Tablas paginadas** con búsqueda y filtros
- ✅ **Modales interactivos** para CRUD y ventas
- ✅ **Validaciones en tiempo real**
- ✅ **Notificaciones toast** para feedback
- ✅ **Confirmaciones de eliminación**
- ✅ **Cálculo automático** de totales de venta

---

## 🛠️ Tecnologías

### Backend
| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 17 | Lenguaje de programación |
| Spring Boot | 3.2.0 | Framework backend |
| Spring Data JPA | 3.2.0 | Persistencia de datos |
| Oracle Database | 21c | Base de datos principal |
| H2 Database | 2.2.224 | Base de datos de desarrollo |
| MapStruct | 1.5.5 | Mapeo de objetos |
| Lombok | 1.18.30 | Reducción de boilerplate |
| JUnit 5 | 5.10.0 | Testing unitario |
| Mockito | 5.3.1 | Mocking para tests |
| Maven | 3.9+ | Gestión de dependencias |

### Frontend
| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Angular | 17.3.0 | Framework frontend |
| TypeScript | 5.4.2 | Lenguaje tipado |
| PrimeNG | 17.18.0 | Componentes UI |
| PrimeIcons | 7.0.0 | Iconos |
| RxJS | 7.8.0 | Programación reactiva |
| SCSS | - | Preprocesador CSS |

### DevOps
- Docker (para Oracle Database)
- Git & GitHub
- JaCoCo (cobertura de código)
- SonarQube (análisis de código)

---

## 🏗️ Arquitectura

### Arquitectura Hexagonal (Backend)

```
┌─────────────────────────────────────────────────────────┐
│                    ADAPTERS (IN)                         │
│  ┌─────────────────────────────────────────────────┐   │
│  │     REST Controllers (API Endpoints)             │   │
│  │     - MedicamentoController                      │   │
│  │     - VentaController                            │   │
│  └─────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│                   DOMAIN (Core)                          │
│  ┌──────────────┐         ┌──────────────────────┐     │
│  │   Models     │         │   Business Logic     │     │
│  │  - Medicam.  │         │   - Validations      │     │
│  │  - Venta     │         │   - Calculations     │     │
│  └──────────────┘         └──────────────────────┘     │
│                                                          │
│  ┌──────────────────────────────────────────────┐      │
│  │            Ports (Interfaces)                 │      │
│  │  Input Ports  │  Output Ports                │      │
│  │  (Use Cases)  │  (Repository)                │      │
│  └──────────────────────────────────────────────┘      │
└─────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│                  ADAPTERS (OUT)                          │
│  ┌─────────────────────────────────────────────────┐   │
│  │     JPA Repositories (Database)                  │   │
│  │     - MedicamentoRepositoryAdapter               │   │
│  │     - VentaRepositoryAdapter                     │   │
│  └─────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────┘
```

### Flujo de Datos
1. **Request** → Controller (Adapter IN)
2. Controller → **Use Case** (Domain)
3. Use Case → **Repository Port** (Domain Interface)
4. Repository Adapter (Adapter OUT) → **Database**
5. **Response** ← Reverse path

---

## 📦 Requisitos Previos

### Software Necesario
- **Java JDK 17+** - [Descargar](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.9+** - [Descargar](https://maven.apache.org/download.cgi)
- **Node.js 18+** - [Descargar](https://nodejs.org/)
- **npm 9+** - (Incluido con Node.js)
- **Angular CLI 17** - `npm install -g @angular/cli`
- **Docker Desktop** - [Descargar](https://www.docker.com/products/docker-desktop/) (para Oracle)
- **Git** - [Descargar](https://git-scm.com/)

### Opcional
- **Oracle Database 21c** (o usar Docker)
- **IntelliJ IDEA** / **Eclipse** (para backend)
- **Visual Studio Code** (para frontend)

---

## 🚀 Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/inventario-medicamentos-konex.git
cd inventario-medicamentos-konex
```

### 2. Configurar Base de Datos (Oracle con Docker)

```bash
# Iniciar Oracle en Docker
docker run -d \
  --name oracle-konex \
  -p 1521:1521 \
  -p 5500:5500 \
  -e ORACLE_PWD=oracle123 \
  gvenzl/oracle-xe:21-slim

# Esperar 2-3 minutos hasta que Oracle esté listo
docker logs -f oracle-konex

# Crear usuario y tablas (cuando veas "DATABASE IS READY")
docker exec -it oracle-konex sqlplus sys/oracle123@localhost:1521/XEPDB1 as sysdba

# Ejecutar (dentro de SQL*Plus):
CREATE USER konex_user IDENTIFIED BY konex_password;
GRANT CONNECT, RESOURCE, CREATE SESSION, CREATE TABLE, CREATE SEQUENCE TO konex_user;
GRANT UNLIMITED TABLESPACE TO konex_user;
EXIT;
```

### 3. Backend - Spring Boot

```bash
cd backend

# Compilar el proyecto
mvn clean install

# Ejecutar (perfil dev con H2 - sin Oracle)
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# O con Oracle
mvn spring-boot:run
```

El backend estará disponible en: **http://localhost:8080**

### 4. Frontend - Angular

```bash
cd frontend/inventario-medicamentos-frontend

# Instalar dependencias
npm install

# Ejecutar en desarrollo
ng serve

# O con puerto específico
ng serve --port 4200
```

El frontend estará disponible en: **http://localhost:4200**

---

## ⚙️ Configuración

### Backend - application.yml

**Para Oracle:**
```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@localhost:1521/XEPDB1
    username: konex_user
    password: konex_password
```

**Para H2 (desarrollo):**
```yaml
# Ejecutar con: mvn spring-boot:run -Dspring-boot.run.profiles=dev
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    username: sa
    password: 
```

### Frontend - environment.ts

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

---

## 🎮 Ejecución

### Opción 1: Ejecución Manual

**Terminal 1 - Backend:**
```bash
cd backend
mvn spring-boot:run
```

**Terminal 2 - Frontend:**
```bash
cd frontend/inventario-medicamentos-frontend
ng serve
```

**Abrir navegador:** http://localhost:4200

### Opción 2: Build de Producción

**Backend:**
```bash
cd backend
mvn clean package
java -jar target/inventario-medicamentos-1.0.0.jar
```

**Frontend:**
```bash
cd frontend/inventario-medicamentos-frontend
ng build --configuration production
# Los archivos estarán en: dist/
```

---

## 🧪 Pruebas

### Backend - Pruebas Unitarias

```bash
cd backend

# Ejecutar todos los tests
mvn test

# Ejecutar tests con cobertura
mvn clean test jacoco:report

# Ver reporte de cobertura
# Abrir: target/site/jacoco/index.html
```

### Frontend - Pruebas

```bash
cd frontend/inventario-medicamentos-frontend

# Ejecutar tests
ng test

# Tests con cobertura
ng test --code-coverage

# Ver reporte: coverage/index.html
```

### SonarQube (Análisis de Código)

```bash
# Iniciar SonarQube (Docker)
docker run -d --name sonarqube -p 9000:9000 sonarqube

# Analizar proyecto
mvn sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=tu_token
```

---

## 📁 Estructura del Proyecto

```
inventario-medicamentos-konex/
├── backend/                                    # Spring Boot Backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/konex/inventario/
│   │   │   │   ├── domain/                    # Dominio (Core)
│   │   │   │   │   ├── model/                 # Entidades de negocio
│   │   │   │   │   └── port/                  # Puertos (interfaces)
│   │   │   │   │       ├── in/                # Casos de uso
│   │   │   │   │       └── out/               # Repositorios
│   │   │   │   ├── application/               # Lógica de aplicación
│   │   │   │   │   └── service/               # Servicios
│   │   │   │   └── infrastructure/            # Adaptadores
│   │   │   │       └── adapter/
│   │   │   │           ├── in/web/            # REST Controllers
│   │   │   │           └── out/persistence/   # JPA Repositories
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   └── test/                              # Pruebas unitarias
│   └── pom.xml
│
├── frontend/                                   # Angular Frontend
│   ├── src/
│   │   ├── app/
│   │   │   ├── core/
│   │   │   │   └── models/                    # Modelos TypeScript
│   │   │   ├── features/
│   │   │   │   ├── medicamentos/              # Módulo medicamentos
│   │   │   │   │   ├── components/
│   │   │   │   │   └── services/
│   │   │   │   └── ventas/                    # Módulo ventas
│   │   │   │       ├── components/
│   │   │   │       └── services/
│   │   │   ├── app.component.*
│   │   │   ├── app.module.ts
│   │   │   └── app-routing.module.ts
│   │   ├── environments/
│   │   └── styles.scss
│   ├── angular.json
│   └── package.json
│
├── database/
│   └── database-setup.sql                     # Scripts SQL
│
├── docs/                                       # Documentación
│   └── arquitectura.md
│
├── .gitignore
└── README.md
```

---

## 🌐 API Endpoints

### Base URL
```
http://localhost:8080/api
```

### Medicamentos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/medicamentos` | Listar medicamentos (paginado) |
| GET | `/medicamentos/{id}` | Obtener medicamento por ID |
| GET | `/medicamentos/filtrar` | Filtrar por nombre/laboratorio |
| POST | `/medicamentos` | Crear nuevo medicamento |
| PUT | `/medicamentos/{id}` | Actualizar medicamento |
| DELETE | `/medicamentos/{id}` | Eliminar medicamento |

### Ventas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/ventas` | Listar ventas (paginado) |
| GET | `/ventas/{id}` | Obtener venta por ID |
| GET | `/ventas/filtrar` | Filtrar por rango de fechas |
| POST | `/ventas` | Realizar nueva venta |

### Ejemplos de Uso

**Crear Medicamento:**
```bash
curl -X POST http://localhost:8080/api/medicamentos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Ibuprofeno 400mg",
    "laboratorio": "Bayer",
    "fechaFabricacion": "2024-01-15",
    "fechaVencimiento": "2026-01-15",
    "cantidadStock": 100,
    "valorUnitario": 5000.00
  }'
```

**Realizar Venta:**
```bash
curl -X POST http://localhost:8080/api/ventas \
  -H "Content-Type: application/json" \
  -d '{
    "medicamentoId": 1,
    "cantidad": 10
  }'
```

---

## 📸 Capturas de Pantalla

### Panel de Medicamentos
![Medicamentos](docs/screenshots/medicamentos.png)

### Realizar Venta
![Venta](docs/screenshots/venta.png)

### Consulta de Ventas
![Ventas](docs/screenshots/ventas.png)

---

## 🤝 Contribución

Las contribuciones son bienvenidas. Para contribuir:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

### Estándares de Código

- **Backend**: Seguir convenciones de Java y Spring Boot
- **Frontend**: Seguir Angular Style Guide
- **Tests**: Mantener cobertura mínima del 70%
- **Commits**: Usar Conventional Commits

---

## 📝 Roadmap

- [ ] Implementar autenticación JWT
- [ ] Agregar roles de usuario (Admin, Vendedor)
- [ ] Dashboard con estadísticas
- [ ] Reportes en PDF/Excel
- [ ] Notificaciones de medicamentos próximos a vencer
- [ ] Historial de cambios (Auditoría)
- [ ] API REST documentada con Swagger/OpenAPI
- [ ] Dockerización completa (Docker Compose)
- [ ] CI/CD con GitHub Actions

---

## 📄 Licencia

Este proyecto fue desarrollado como prueba técnica para **Konex**.

---

## 👨‍💻 Autor

**Tu Nombre**
- GitHub: [@tu-usuario](https://github.com/tu-usuario)
- LinkedIn: [Tu Perfil](https://linkedin.com/in/tu-perfil)
- Email: tu-email@ejemplo.com

---

## 🙏 Agradecimientos

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Angular](https://angular.io/)
- [PrimeNG](https://primeng.org/)
- [Oracle](https://www.oracle.com/)
- Konex - Por la oportunidad

---

## 📚 Documentación Adicional

- [Guía de Instalación Detallada](docs/INSTALLATION.md)
- [Documentación de API](docs/API.md)
- [Arquitectura del Sistema](docs/ARCHITECTURE.md)
- [Guía de Contribución](docs/CONTRIBUTING.md)

---

<div align="center">

**⭐ Si este proyecto te fue útil, considera darle una estrella en GitHub ⭐**

Desarrollado con ❤️ para Konex

</div>