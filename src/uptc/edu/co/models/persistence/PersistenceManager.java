/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.Settings;
import uptc.edu.co.models.user.User;
import uptc.edu.co.utilities.LocalDateTimeAdapter;
import uptc.edu.co.utilities.Utilities;

/**
 *
 * @author alber
 */
public class PersistenceManager {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();
    
    public PersistenceManager() {
        ensureDirectoriesExist();
    }

    private void ensureDirectoriesExist() {
        try {
            // Crear carpeta data/users si no existe
            File usersDir = new File("data/users");
            if (!usersDir.exists()) {
                usersDir.mkdirs();
                System.out.println("✅ Carpeta creada: " + usersDir.getAbsolutePath());
            }
            
            // Crear carpeta data/usersHistory si no existe
            File historiesDir = new File("data/usersHistory");
            if (!historiesDir.exists()) {
                historiesDir.mkdirs();
                System.out.println("✅ Carpeta creada: " + historiesDir.getAbsolutePath());
            }
            
            // Crear archivo users.json vacío si no existe
            File usersFile = new File(Utilities.USERS_FILE);
            if (!usersFile.exists()) {
                usersFile.getParentFile().mkdirs();
                try (FileWriter writer = new FileWriter(usersFile)) {
                    writer.write("[]"); // Array vacío
                }
                System.out.println("✅ Archivo creado: " + usersFile.getAbsolutePath());
            }
            
        } catch (IOException e) {
            System.err.println("❌ Error al crear estructura de carpetas: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // ------------------ USER ------------------
    public List<User> loadUsers() {
        try (FileReader reader = new FileReader(Utilities.USERS_FILE)) {
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void saveUsers(List<User> users) throws IOException {
        try (FileWriter writer = new FileWriter(Utilities.USERS_FILE)) {
            gson.toJson(users, writer);
        }
    }

    public User loadUser(String email) {
        List<User> list = loadUsers();
        User user = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmail() == null ? email == null : list.get(i).getEmail().equals(email)) {
                user = list.get(i);
                i = list.size();
            }
        }
        return user;
    }

    public void addUser(User newUser) throws IOException {
        List<User> users = loadUsers();
        users.add(newUser);
        saveUsers(users);
    }

    public boolean deleteUser(int userId) throws IOException {
        List<User> users = loadUsers();
        boolean remove = false;
        for (int i = 0; i < users.size(); i++) {
            if (userId == users.get(i).getId()) {
                users.remove(i);
                remove = true;
                i = users.size();
            }
        }

        if (remove) {
            saveUsers(users);
        }
        return remove;
    }

    // ------------------ SESSION ------------------
    public boolean saveSession(int userId, Session session) {
        try {
            String filePath = Utilities.HISTORIES + "history_" + userId + ".json";
            session.setSessionId(userId);
            // 🔹 Sobrescribe directamente porque ya cargaste y actualizaste en memoria
            return saveToFile(filePath, session);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Session> loadAllSessions() {
        List<Session> sessions = new ArrayList<>();
        File directory = new File(Utilities.HISTORIES); // ruta donde están los JSON

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Directorio de sesiones no encontrado: " + Utilities.HISTORIES);
            return sessions;
        }

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                try (FileReader reader = new FileReader(file)) {
                    Session session = gson.fromJson(reader, Session.class);
                    if (session != null) {
                        sessions.add(session);
                    }
                } catch (IOException e) {
                    System.err.println("Error al leer sesión: " + file.getName());
                    e.printStackTrace();
                }
            }
        }
        return sessions;
    }

    public Session loadSession(int userId) {
        try {
            String filePath = Utilities.HISTORIES + "history_" + userId + ".json";
            File file = new File(filePath);

            if (!file.exists()) {
                // Si no existe, crear sesión vacía
                return new Session();
            }

            try (FileReader reader = new FileReader(file)) {
                Session session = gson.fromJson(reader, Session.class);
                if (session == null) {
                    session = new Session();
                }

                // Asegurar que las listas no sean nulas
                if (session.getHistoryPomodoros() == null) {
                    session.setHistory(new ArrayList<>());
                }
                if (session.getTaskList() == null) {
                    session.setTaskList(new ArrayList<>());
                }

                if (session.getSettings() == null) {
                    session.setSettings(new Settings());
                }

                return session;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Session();
        }
    }

    // ------------------ UTILITY METHODS ------------------
    private boolean saveToFile(String filename, Object data) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(data, writer);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private String readFromFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            StringBuilder content = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                content.append((char) ch);
            }
            return content.toString();
        } catch (FileNotFoundException e) {
            // Archivo no existe, es normal en primera ejecución
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
