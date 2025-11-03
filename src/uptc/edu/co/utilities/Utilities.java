package uptc.edu.co.utilities;

import java.awt.Color;
import java.awt.Font;

public class Utilities {

    public static final String DATA_FOLDER = "data/";
    public static final String USERS_FILE = DATA_FOLDER + "users/users.json";
    public static final String HISTORIES = DATA_FOLDER + "usersHistory/";
    
     public static final Color MAIN_COLOR = new Color(196, 8, 8);
    public static final Color PANEL_REPORTVIEW_COLOR = new Color(200, 0, 0);
    public static final Color BUTTON_REPORTVIEW_COLOR = new Color(0, 150, 0);
    public static final Color BUTTON_COLOR_LOGIN = new Color(200, 0, 0);
    public static final Color BUTTON_COLOR_REGISTER = new Color(227, 11, 11);
    public static final Color RANKING_COLOR = new Color(180, 0, 0);
    public static final Color REGISTER_COLOR_FONDO = new Color(234, 19, 19);
    public static final Color SETTINGS_COLOR_FONDO = new Color(180, 0, 0);
    public static final Color SETTINGS_COLOR_FONDO_SLIDERBAR = new Color(150, 0, 0);
    public static final Color SETTINGS_COLOR_SOMBRA_SLIDERBAR = new Color(180, 0, 0);
    public static final Color SETTINGS_COLOR_BUTTON_RESET_SOMBRA_2 = new Color(150, 0, 0);
    public static final Color SETTINGS_COLOR_BUTTON_RESET = new Color(22, 120, 60);
    public static final Color STATISTICS_LABELS_COUNT = new Color(160, 0, 0);
    public static final Color SETTINGS_COLOR_SELECT_PAGE = new Color(190, 0, 0);
    public static final Color SETTINGS_COLOR_PANELES_CONFIG = new Color(180, 0, 0);
    public final static Color mainRed = Color.decode("#b21818");
    public final static Color darkRed = Color.decode("#ad1313");
    public final static Color borderRed = Color.decode("#c22f2f");
    public final static Color TASKPANEL_FONDO_PANELTASK = Color.decode("#b21818");
    public final static Color TASKPANEL_SCROLLPANE = Color.decode("#b21818");
    public final static Color TASKPANEL_SCROLLPANE_VERTICAL = Color.decode("#b21818");

    public static final Font STATISTICS_LABELS_VALUE_FONT = new Font("Arial", Font.BOLD, 28);
    public final static Font TITLE_FONT_HELP = new Font("Monospaced", Font.BOLD, 16);
    public final static Font TEXT_FONT_HELP = new Font("Monospaced", Font.ITALIC, 12);
    public final static Font LOGO_FONT = new Font("Arial", Font.BOLD, 18);
    public final static Font SUBTITLE_FONT_LOGIN = new Font("Arial", Font.BOLD, 14);
    public final static Font TITLE_FONT_RANKING = new Font("Arial", Font.BOLD, 22);
    public final static Font TITLE_FONT_REGISTER = new Font("Arial", Font.BOLD, 24);
    public final static Font SUBTITLE_FONT_REGISTER = new Font("Arial", Font.PLAIN, 18);
    public final static Font FONT_REGISTER_FIELDS = new Font("Arial", Font.ITALIC, 15);
    public final static Font FONT_SETTINGS_BUTTON_RESET = new Font("Monospaced", Font.BOLD, 18);
    public final static Font TASKPANEL_ADD_BUTTON_FONT = new Font("Arial", Font.BOLD, 14);

    public final static String[][] PAGES = {
        {"Bienvenido a la Ayuda", "En esta sección encontrarás información sobre cómo usar la aplicación Pomodoro Timer."},
        {"Configuración del temporizador", "Puedes ajustar los minutos de trabajo y descanso desde el menú principal."},
        {"Estadísticas", "Aquí podrás ver tus sesiones completadas y tu progreso general."},
        {"Atajos del teclado", "Usa las teclas rápidas para iniciar, pausar o reiniciar el temporizador fácilmente."},
        {"¡Gracias por usar Pomodoro Timer!", "Esperamos que esta herramienta te ayude a mejorar tu productividad."}
    };
}
