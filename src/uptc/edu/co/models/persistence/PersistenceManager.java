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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.session.UserHistory;
import uptc.edu.co.models.settings.Settings;
import uptc.edu.co.models.user.User;
import uptc.edu.co.utilities.Utilities;

/**
 *
 * @author alber
 */
public class PersistenceManager {

    private Gson gson;
    // datos que estaran en la persistencia

    // Simulando almacenamiento en memoria con HashMaps
    // ------------------ USER ------------------
    public boolean saveUser(User user) {
        try {
            List<User> users = loadAllUsers();
            if (users == null) {
                users = new ArrayList<>();
            }

            // Actualizar o agregar usuario
            boolean found = false;
            for (int i = 0; i < users.size(); i++) {
                if (getUserId(users.get(i)) == getUserId(user)) {
                    users.set(i, user);
                    found = true;
                    break;
                }
            }

            if (!found) {
                users.add(user);
            }

            return saveToFile(Utilities.USERS_FILE, users);

        } catch (Exception e) {
            return false;
        }
    }

    public User loadUser(String email) {
        List<User> users = loadAllUsers();
        if (users != null) {
            for (User user : users) {
                if (getUserEmail(user).equals(email)) {
                    return user;
                }
            }
        }
        return null;
    }

    public List<User> loadAllUsers() {
        try {
            String json = readFromFile(Utilities.USERS_FILE);
            if (json == null || json.trim().isEmpty()) {
                return new ArrayList<>();
            }

            TypeToken<List<NormalUser>> token = new TypeToken<List<NormalUser>>() {
            };
            List<NormalUser> normalUsers = gson.fromJson(json, token.getType());

            return new ArrayList<User>(normalUsers);

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean deleteUser(int userId) {
        try {
            List<User> users = loadAllUsers();
            users.removeIf(user -> getUserId(user) == userId);
            return saveToFile(Utilities.USERS_FILE, users);
        } catch (Exception e) {
            return false;
        }
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
    public boolean saveSettings(Settings settings) {
        return saveToFile(Utilities.SETTINGS_FILE, settings);
    }

    public Settings loadSettings() {
        try {
            String json = readFromFile(Utilities.SETTINGS_FILE);
            if (json == null || json.trim().isEmpty()) {
                return new Settings(); // valores por defecto
            }

            Settings settings = gson.fromJson(json, Settings.class);
            return settings != null ? settings : new Settings();

        } catch (Exception e) {
            return new Settings();
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

    // Helper methods para obtener datos de User (ya que es interface)
    private int getUserId(User user) {
        if (user instanceof NormalUser) {
            return ((NormalUser) user).getId();
        } else if (user instanceof AdminUser) {
            return ((AdminUser) user).getId();
        }
        return 0;
    }

    private String getUserEmail(User user) {
        if (user instanceof NormalUser) {
            return ((NormalUser) user).getEmail();
        } else if (user instanceof AdminUser) {
            return ((AdminUser) user).getEmail();
        }
        return "";
    }

}
