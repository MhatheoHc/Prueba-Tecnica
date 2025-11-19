package com.pruebacenet.tests;
import java.time.Duration;
import java.util.Random;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

    public class RegistroUsuarioTest {
        
        private WebDriver driver;
        private WebDriverWait wait;
        private String baseUrl = "file:///C:/Users/mateh/OneDrive/Documentos/Prueba Técnica/prueba-automatizacion/src/test/resources/registro.html";
        private TestDataGenerator dataGenerator;
        
        @BeforeClass
        public void configuracionInicial() {
            // Descarga automática del driver
            WebDriverManager.chromedriver().setup();
            dataGenerator = new TestDataGenerator();
        }
        
        @BeforeMethod
        public void setup() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.get(baseUrl);
        }
        
        @Test(priority = 1, description = "Registro exitoso con datos válidos")
        public void testRegistroExitoso() {
            // Generar datos únicos
            String nombre = dataGenerator.generarNombreCompleto();
            String email = dataGenerator.generarEmail();
            String password = dataGenerator.generarPassword();
            
            // Llenar formulario
            WebElement campoNombre = driver.findElement(By.id("nombreCompleto"));
            campoNombre.sendKeys(nombre);
            
            WebElement campoEmail = driver.findElement(By.id("email"));
            campoEmail.sendKeys(email);
            
            WebElement campoPassword = driver.findElement(By.id("password"));
            campoPassword.sendKeys(password);
            
            WebElement campoConfirmacion = driver.findElement(By.id("confirmPassword"));
            campoConfirmacion.sendKeys(password);
            
            // Click en botón registrar
            WebElement btnRegistrar = driver.findElement(By.id("btnRegistrar"));
            btnRegistrar.click();
            
            // Verificar registro exitoso
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mensajeExito")));
            WebElement mensaje = driver.findElement(By.id("mensajeExito"));
            Assert.assertTrue(mensaje.isDisplayed(), "Mensaje de éxito no se muestra");
            Assert.assertEquals(mensaje.getText(), "Registro exitoso");
        }
        
        @Test(priority = 2, description = "Validar campos obligatorios vacíos")
        public void testCamposObligatorios() {
            // Intentar registrar sin llenar campos
            WebElement btnRegistrar = driver.findElement(By.id("btnRegistrar"));
            btnRegistrar.click();
            
            // Verificar mensajes de error
            WebElement errorNombre = driver.findElement(By.id("errorNombre"));
            Assert.assertTrue(errorNombre.isDisplayed());
            Assert.assertEquals(errorNombre.getText(), "El nombre es obligatorio");
            
            WebElement errorEmail = driver.findElement(By.id("errorEmail"));
            Assert.assertTrue(errorEmail.isDisplayed());
            
            WebElement errorPassword = driver.findElement(By.id("errorPassword"));
            Assert.assertTrue(errorPassword.isDisplayed());
        }
        
        @Test(priority = 3, description = "Validar formato de email inválido")
        public void testEmailInvalido() {
            String[] emailsInvalidos = {
                "correosinArroba.com",
                "@sindominio.com",
                "usuario@",
                "usuario @dominio.com"
            };
            
            for (String emailInvalido : emailsInvalidos) {
                WebElement campoEmail = driver.findElement(By.id("email"));
                campoEmail.clear();
                campoEmail.sendKeys(emailInvalido);
                
                WebElement btnRegistrar = driver.findElement(By.id("btnRegistrar"));
                btnRegistrar.click();
                
                WebElement errorEmail = driver.findElement(By.id("errorEmail"));
                Assert.assertTrue(errorEmail.isDisplayed(), 
                    "Error no se muestra para email: " + emailInvalido);
                Assert.assertTrue(errorEmail.getText().contains("formato inválido") || 
                    errorEmail.getText().contains("correo inválido"));
            }
        }
        
        @Test(priority = 4, description = "Validar complejidad de contraseña")
        public void testPasswordDebil() {
            String[] passwordsDebiles = {
                "123",           // Muy corta
                "abcdef",        // Sin números
                "123456",        // Sin letras
                "Abc12"          // Muy corta
            };
            
            for (String passwordDebil : passwordsDebiles) {
                WebElement campoPassword = driver.findElement(By.id("password"));
                campoPassword.clear();
                campoPassword.sendKeys(passwordDebil);
                
                WebElement btnRegistrar = driver.findElement(By.id("btnRegistrar"));
                btnRegistrar.click();
                
                WebElement errorPassword = driver.findElement(By.id("errorPassword"));
                Assert.assertTrue(errorPassword.isDisplayed(),
                    "Error no se muestra para password: " + passwordDebil);
                Assert.assertTrue(errorPassword.getText().contains("complejidad") ||
                    errorPassword.getText().contains("débil"));
            }
        }
        
        @Test(priority = 5, description = "Validar que contraseñas coincidan")
        public void testPasswordsNoCoinciden() {
            String password = dataGenerator.generarPassword();
            String passwordDiferente = "DiferentE123!";
            
            WebElement campoNombre = driver.findElement(By.id("nombreCompleto"));
            campoNombre.sendKeys(dataGenerator.generarNombreCompleto());
            
            WebElement campoEmail = driver.findElement(By.id("email"));
            campoEmail.sendKeys(dataGenerator.generarEmail());
            
            WebElement campoPassword = driver.findElement(By.id("password"));
            campoPassword.sendKeys(password);
            
            WebElement campoConfirmacion = driver.findElement(By.id("confirmPassword"));
            campoConfirmacion.sendKeys(passwordDiferente);
            
            WebElement btnRegistrar = driver.findElement(By.id("btnRegistrar"));
            btnRegistrar.click();
            
            WebElement errorConfirmacion = driver.findElement(By.id("errorConfirmPassword"));
            Assert.assertTrue(errorConfirmacion.isDisplayed());
            Assert.assertTrue(errorConfirmacion.getText().contains("no coinciden"));
        }
        
        @Test(priority = 6, description = "Validar límites de caracteres")
        public void testLimitesCaracteres() {
            // Nombre muy largo (más de 100 caracteres)
            String nombreLargo = "A".repeat(150);
            
            WebElement campoNombre = driver.findElement(By.id("nombreCompleto"));
            campoNombre.sendKeys(nombreLargo);
            
            String valorIngresado = campoNombre.getAttribute("value");
            Assert.assertTrue(valorIngresado.length() <= 100,
                "Campo permite más caracteres de lo permitido");
        }
        
        @Test(priority = 7, description = "Validar caracteres especiales en nombre")
        public void testCaracteresEspecialesNombre() {
            String nombreConEspeciales = "Juan<script>alert('test')</script>";
            
            WebElement campoNombre = driver.findElement(By.id("nombreCompleto"));
            campoNombre.sendKeys(nombreConEspeciales);
            
            WebElement campoEmail = driver.findElement(By.id("email"));
            campoEmail.sendKeys(dataGenerator.generarEmail());
            
            WebElement campoPassword = driver.findElement(By.id("password"));
            String password = dataGenerator.generarPassword();
            campoPassword.sendKeys(password);
            
            WebElement campoConfirmacion = driver.findElement(By.id("confirmPassword"));
            campoConfirmacion.sendKeys(password);
            
            WebElement btnRegistrar = driver.findElement(By.id("btnRegistrar"));
            btnRegistrar.click();
            
            // Verificar que se sanitizó o rechazó
            WebElement errorNombre = driver.findElement(By.id("errorNombre"));
            Assert.assertTrue(errorNombre.isDisplayed() ||
                !driver.getCurrentUrl().contains("script"));
        }
        
        @AfterMethod
        public void tearDown(ITestResult result) {
            if (driver != null) {
                // intentar guardar screenshot de la ejecución
                try {
                    saveScreenshot(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                driver.quit();
            }
        }

        private void saveScreenshot(ITestResult result) {
            if (driver == null) return;
            try {
                Path evidenciasDir = Paths.get("evidencias");
                Files.createDirectories(evidenciasDir);

                String methodName = result.getMethod().getMethodName();
                String status = result.isSuccess() ? "PASSED" : "FAILED";
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Path dest = evidenciasDir.resolve(methodName + "_" + status + "_" + timestamp + ".png");
                Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Clase auxiliar para generar datos de prueba
    class TestDataGenerator {
        private Random random = new Random();
        private long timestamp = System.currentTimeMillis();
        private int contador = 0;
        
        public String generarNombreCompleto() {
            String[] nombres = {"Juan", "María", "Pedro", "Ana", "Carlos"};
            String[] apellidos = {"García", "Rodríguez", "Martínez", "López", "González"};
            
            contador++;
            return nombres[random.nextInt(nombres.length)] + " " +
                apellidos[random.nextInt(apellidos.length)] + 
                contador;
        }
        
        public String generarEmail() {
            contador++;
            return "usuario" + timestamp + contador + "@testmail.com";
        }
        
        public String generarPassword() {
            String mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String minusculas = "abcdefghijklmnopqrstuvwxyz";
            String numeros = "0123456789";
            String especiales = "!@#$%^&*";
            
            StringBuilder password = new StringBuilder();
            
            // Garantizar al menos un carácter de cada tipo
            password.append(mayusculas.charAt(random.nextInt(mayusculas.length())));
            password.append(minusculas.charAt(random.nextInt(minusculas.length())));
            password.append(numeros.charAt(random.nextInt(numeros.length())));
            password.append(especiales.charAt(random.nextInt(especiales.length())));
            
            // Completar con caracteres aleatorios
            String todos = mayusculas + minusculas + numeros + especiales;
            for (int i = 4; i < 12; i++) {
                password.append(todos.charAt(random.nextInt(todos.length())));
            }
            
            return password.toString();
        }
    }