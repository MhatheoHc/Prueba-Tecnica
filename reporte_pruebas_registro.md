# Reporte de Pruebas - Registro de Usuario

Fecha: 2025-11-19

Resumen ejecutivo:

- **Suite:** Suite Pruebas Automatización
- **Test:** Pruebas Registro (clase `com.pruebacenet.tests.RegistroUsuarioTest`)
- **Total tests ejecutados:** 7
- **Pasaron:** 7
- **Fallaron:** 0
- **Omitidos:** 0
- **Duración total reportada:** 14.152 ms (ver `target/surefire-reports/testng-results.xml`)

Archivos de evidencia consultados:

- `src/test/java/com.pruebacenet/tests/RegistroUsuarioTest.java`  (implementación de los casos de prueba)
- `src/test/resources/registro.html`  (aplicación bajo prueba)
- `target/surefire-reports/junitreports/TEST-com.pruebacenet.tests.RegistroUsuarioTest.xml`  (junit-style XML)
- `target/surefire-reports/testng-results.xml`  (detalle por método, tiempos y timestamps)
- `target/surefire-reports/emailable-report.html`  (resumen legible)
- `target/surefire-reports/index.html`  (vista TestNG con tiempos cronológicos)

Resumen de casos (extraído de los reportes):

1) testRegistroExitoso
   - Descripción: Registro exitoso con datos válidos
   - Estado: PASADO
   - Tiempo (aprox.): 859 ms (ver `junitreports` / `testng-results.xml`)

2) testCamposObligatorios
   - Descripción: Validar campos obligatorios vacíos
   - Estado: PASADO
   - Tiempo (aprox.): 225 ms

3) testEmailInvalido
   - Descripción: Validar formato de email inválido
   - Estado: PASADO
   - Tiempo (aprox.): 1074 ms

4) testPasswordDebil
   - Descripción: Validar complejidad de contraseña
   - Estado: PASADO
   - Tiempo (aprox.): 1000 ms

5) testPasswordsNoCoinciden
   - Descripción: Validar que contraseñas coincidan
   - Estado: PASADO
   - Tiempo (aprox.): 540 ms

6) testLimitesCaracteres
   - Descripción: Validar límites de caracteres
   - Estado: PASADO
   - Tiempo (aprox.): 269 ms

7) testCaracteresEspecialesNombre
   - Descripción: Validar caracteres especiales en nombre
   - Estado: PASADO
   - Tiempo (aprox.): 554 ms

Evidencias (fragmentos relevantes):

- `target/surefire-reports/junitreports/TEST-com.pruebacenet.tests.RegistroUsuarioTest.xml` (cabecera):

```
<testsuite hostname="MATEO01" failures="0" tests="7" name="com.pruebacenet.tests.RegistroUsuarioTest" time="13.611" errors="0" timestamp="2025-11-19T14:11:38 COT" skipped="0">
  ...
</testsuite>
```

- `target/surefire-reports/testng-results.xml` (resumen de suite / conteos):

```
<testng-results ignored="0" total="7" passed="7" failed="0" skipped="0">
  <suite started-at="2025-11-19T14:11:24 COT" name="Suite Pruebas Automatización" finished-at="2025-11-19T14:11:38 COT" duration-ms="14152">
    ...
  </suite>
</testng-results>
```

- `target/surefire-reports/emailable-report.html` (tabla resumen):

```
Pruebas Registro  — 7 passed, 0 skipped, 0 failed, Time (ms): 14.152
```

Tiempos y orden cronológico (extracto del `index.html` / panel "Times"):

- `testEmailInvalido`: 1074 ms
- `testPasswordDebil`: 1000 ms
- `testRegistroExitoso`: 859 ms
- `testCaracteresEspecialesNombre`: 554 ms
- `testPasswordsNoCoinciden`: 540 ms
- `testLimitesCaracteres`: 269 ms
- `testCamposObligatorios`: 225 ms

Observaciones sobre las pruebas y la aplicación bajo prueba (`registro.html`):

- La validación de campos, formato de email y complejidad de contraseña se implementa en JavaScript en `registro.html` y los tests interactúan con los elementos por `id` (p.ej. `nombreCompleto`, `email`, `password`, `confirmPassword`, `btnRegistrar`).
- Hay manejo de mensajes de error y un `mensajeExito` que aparece para el caso exitoso.
- Los tests usan Selenium + ChromeDriver (WebDriverManager) — por eso las ejecuciones toman ~200–1000 ms por test en esta corrida.

Consola / salida en tiempo de ejecución:

La salida completa de la ejecución se guardó en `evidencias/mvn_test_output.txt`.

Evidencias generadas (carpeta `evidencias/`):

- `evidencias/mvn_test_output.txt`  (salida completa de la ejecución de `mvn test`)
- `evidencias/testRegistroExitoso_PASSED_*.png`
- `evidencias/testCamposObligatorios_PASSED_*.png`
- `evidencias/testEmailInvalido_PASSED_*.png`
- `evidencias/testPasswordDebil_PASSED_*.png`
- `evidencias/testPasswordsNoCoinciden_PASSED_*.png`
- `evidencias/testLimitesCaracteres_PASSED_*.png`
- `evidencias/testCaracteresEspecialesNombre_PASSED_*.png`

Puedes abrir `evidencias/mvn_test_output.txt` para ver la salida completa de la consola y abrir las imágenes PNG desde el explorador de archivos o un visor de imágenes.

---
Actualicé el código de pruebas para capturar screenshots por método y guardarlos en `evidencias/` antes de cerrar el navegador.
# Reporte de Pruebas - Registro de Usuario

Fecha: 2025-11-19

Resumen ejecutivo:

- **Suite:** Suite Pruebas Automatización
- **Test:** Pruebas Registro (clase `com.pruebacenet.tests.RegistroUsuarioTest`)
- **Total tests ejecutados:** 7
- **Pasaron:** 7
- **Fallaron:** 0
- **Omitidos:** 0
- **Duración total reportada:** 14.152 ms (ver `target/surefire-reports/testng-results.xml`)

Archivos de evidencia consultados:

- `src/test/java/com/pruebacenet/tests/RegistroUsuarioTest.java`  (implementación de los casos de prueba)
- `src/test/resources/registro.html`  (aplicación bajo prueba)
- `target/surefire-reports/junitreports/TEST-com.pruebacenet.tests.RegistroUsuarioTest.xml`  (junit-style XML)
- `target/surefire-reports/testng-results.xml`  (detalle por método, tiempos y timestamps)
- `target/surefire-reports/emailable-report.html`  (resumen legible)
- `target/surefire-reports/index.html`  (vista TestNG con tiempos cronológicos)

Resumen de casos (extraído de los reportes):

1) testRegistroExitoso
   - Descripción: Registro exitoso con datos válidos
   - Estado: PASADO
   - Tiempo (aprox.): 859 ms (ver `junitreports` / `testng-results.xml`)

2) testCamposObligatorios
   - Descripción: Validar campos obligatorios vacíos
   - Estado: PASADO
   - Tiempo (aprox.): 225 ms

3) testEmailInvalido
   - Descripción: Validar formato de email inválido
   - Estado: PASADO
   - Tiempo (aprox.): 1074 ms

4) testPasswordDebil
   - Descripción: Validar complejidad de contraseña
   - Estado: PASADO
   - Tiempo (aprox.): 1000 ms

5) testPasswordsNoCoinciden
   - Descripción: Validar que contraseñas coincidan
   - Estado: PASADO
   - Tiempo (aprox.): 540 ms

6) testLimitesCaracteres
   - Descripción: Validar límites de caracteres
   - Estado: PASADO
   - Tiempo (aprox.): 269 ms

7) testCaracteresEspecialesNombre
   - Descripción: Validar caracteres especiales en nombre
   - Estado: PASADO
   - Tiempo (aprox.): 554 ms

Evidencias (fragmentos relevantes):

- `target/surefire-reports/junitreports/TEST-com.pruebacenet.tests.RegistroUsuarioTest.xml` (cabecera):

```
<testsuite hostname="MATEO01" failures="0" tests="7" name="com.pruebacenet.tests.RegistroUsuarioTest" time="13.611" errors="0" timestamp="2025-11-19T14:11:38 COT" skipped="0">
  ...
</testsuite>
```

- `target/surefire-reports/testng-results.xml` (resumen de suite / conteos):

```
<testng-results ignored="0" total="7" passed="7" failed="0" skipped="0">
  <suite started-at="2025-11-19T14:11:24 COT" name="Suite Pruebas Automatización" finished-at="2025-11-19T14:11:38 COT" duration-ms="14152">
    ...
  </suite>
</testng-results>
```

- `target/surefire-reports/emailable-report.html` (tabla resumen):

```
Pruebas Registro  — 7 passed, 0 skipped, 0 failed, Time (ms): 14.152
```

Tiempos y orden cronológico (extracto del `index.html` / panel "Times"):

- `testEmailInvalido`: 1074 ms
- `testPasswordDebil`: 1000 ms
- `testRegistroExitoso`: 859 ms
- `testCaracteresEspecialesNombre`: 554 ms
- `testPasswordsNoCoinciden`: 540 ms
- `testLimitesCaracteres`: 269 ms
- `testCamposObligatorios`: 225 ms

Observaciones sobre las pruebas y la aplicación bajo prueba (`registro.html`):

- La validación de campos, formato de email y complejidad de contraseña se implementa en JavaScript en `registro.html` y los tests interactúan con los elementos por `id` (p.ej. `nombreCompleto`, `email`, `password`, `confirmPassword`, `btnRegistrar`).
- Hay manejo de mensajes de error y un `mensajeExito` que aparece para el caso exitoso.
- Los tests usan Selenium + ChromeDriver (WebDriverManager) — por eso las ejecuciones toman ~200–1000 ms por test en esta corrida.

Consola / salida en tiempo de ejecución:


