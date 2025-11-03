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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.UserHistory;
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

    public void addUser(User newUser) throws IOException {
        List<User> users = loadUsers();
        users.add(newUser);
        saveUsers(users);
        createHistory(newUser.getId());
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

            // Cargar el historial como objeto UserHistory
            UserHistory history = loadUserHistory(userId);
            history.setNumSessions(history.getNumSessions()+ 1);
            // Añadir la nueva sesión
            session.setSessionId(history.getNumSessions());
            history.addCompletedSession(session);

            // Guardar nuevamente
            return saveToFile(filePath, history);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Session> loadSessionsByDate(int userId, LocalDate date) {
        String filePath = Utilities.HISTORIES + "history" + userId + ".json";
        Map<String, List<Session>> userHistory = loadUserHistoryFile(filePath);

        return userHistory.getOrDefault(date.toString(), new ArrayList<>());
    }

    private Map<String, List<Session>> loadUserHistoryFile(String filePath) {
        try {
            String json = readFromFile(filePath);
            if (json == null || json.trim().isEmpty()) {
                return new HashMap<>();
            }

            TypeToken<Map<String, List<Session>>> token = new TypeToken<Map<String, List<Session>>>() {
            };
            return gson.fromJson(json, token.getType());

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    // ------------------ USER HISTORY ------------------
    public boolean createHistory(int userId) {
        try {
            File file = new File(Utilities.HISTORIES + "history_" + userId + ".json");

            // Si ya existe, no lo vuelve a crear
            if (file.exists()) {
                return false;
            }

            // Crear un historial vacío
            UserHistory newHistory = new UserHistory(userId);

            // Guardar el archivo vacío
            return saveToFile(file.getPath(), newHistory);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UserHistory loadUserHistory(int userId) {
        try {
            String path = Utilities.HISTORIES + "history_" + userId + ".json";
            String json = readFromFile(path);

            if (json == null || json.trim().isEmpty()) {
                return new UserHistory(userId); // Devuelve vacío si no hay datos
            }

            return gson.fromJson(json, UserHistory.class);

        } catch (Exception e) {
            e.printStackTrace();
            return new UserHistory(userId);
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
