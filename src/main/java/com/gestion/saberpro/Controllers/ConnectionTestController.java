package com.gestion.saberpro.Controllers;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectionTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db")
    public String testDb() {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            return String.format(
                "✅ Conectado a:<br> - URL: %s<br> - Producto: %s<br> - Versión: %s",
                meta.getURL(),
                meta.getDatabaseProductName(),
                meta.getDatabaseProductVersion()
            );
        } catch (Exception e) {
            return "❌ Error conectando a la base de datos: " + e.getMessage();
        }
    }
}