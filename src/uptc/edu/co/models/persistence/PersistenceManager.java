/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.models.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.UserHistory;
import uptc.edu.co.models.settings.SystemSettings;
import uptc.edu.co.models.user.User;
import uptc.edu.co.utilities.Utilities;

/**
 *
 * @author alber
 */
public class PersistenceManager {

    private final Gson gson = new Gson();

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
    }

    public boolean deleteUser(int userId) throws IOException {
        List<User> users = loadUsers();
        boolean remove= false;
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
    public boolean saveSession(Session session) {
        try {
            Map<Integer, Session> sessions = loadAllSessions();
            sessions.put(session.getSessionId(), session);
            return saveToFile(Utilities.SESSIONS_FILE, sessions);
        } catch (Exception e) {
            return false;
        }
    }

    public Session loadSession(int sessionId) {

        Map<Integer, Session> sessions = loadAllSessions();
        return sessions.get(sessionId);
    }

    private Map<Integer, Session> loadAllSessions() {
        try {
            String json = readFromFile(Utilities.SESSIONS_FILE);
            if (json == null || json.trim().isEmpty()) {
                return new HashMap<>();
            }

            TypeToken<Map<Integer, Session>> token = new TypeToken<Map<Integer, Session>>() {
            };
            return gson.fromJson(json, token.getType());

        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    // ------------------ USER HISTORY ------------------
    public boolean saveUserHistory(UserHistory history) {
        try {
            List<UserHistory> histories = loadAllHistories();

            // Actualizar o agregar historial
            boolean found = false;
            for (int i = 0; i < histories.size(); i++) {
                UserHistory h = histories.get(i);
                if (h.getUserId() == history.getUserId()
                        && h.getDate().equals(history.getDate())) {
                    histories.set(i, history);
                    found = true;
                    break;
                }
            }

            if (!found) {
                histories.add(history);
            }

            return saveToFile(Utilities.HISTORIES_FILE, histories);

        } catch (Exception e) {
            return false;
        }
    }

    public UserHistory loadUserHistory(int userId, LocalDate date) {
        List<UserHistory> histories = loadAllHistories();
        for (UserHistory history : histories) {
            if (history.getUserId() == userId && history.getDate().equals(date)) {
                return history;
            }
        }
        return null;
    }

    public List<UserHistory> loadUserHistory(int userId, LocalDate fromDate, LocalDate toDate) {
        List<UserHistory> histories = loadAllHistories();
        List<UserHistory> filtered = new ArrayList<>();

        for (UserHistory history : histories) {
            if (history.getUserId() == userId) {
                LocalDate date = history.getDate();
                if (!date.isBefore(fromDate) && !date.isAfter(toDate)) {
                    filtered.add(history);
                }
            }
        }

        return filtered;
    }

    private List<UserHistory> loadAllHistories() {
        try {
            String json = readFromFile(Utilities.HISTORIES_FILE);
            if (json == null || json.trim().isEmpty()) {
                return new ArrayList<>();
            }

            TypeToken<List<UserHistory>> token = new TypeToken<List<UserHistory>>() {
            };
            return gson.fromJson(json, token.getType());

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // ------------------ SETTINGS ------------------
    public boolean saveSettings(SystemSettings settings) {
        return saveToFile(Utilities.SETTINGS_FILE, settings);
    }

    public SystemSettings loadSettings() {
        try {
            String json = readFromFile(Utilities.SETTINGS_FILE);
            if (json == null || json.trim().isEmpty()) {
                return new SystemSettings(); // valores por defecto
            }

            SystemSettings settings = gson.fromJson(json, SystemSettings.class);
            return settings != null ? settings : new SystemSettings();

        } catch (Exception e) {
            return new SystemSettings();
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
