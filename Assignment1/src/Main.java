import Camera_DOF.model.LensManager;
import Camera_DOF.ui.CameraTextUI;

/**
 * Launch application
 */
public class Main {
    public static void main(String args) {
        LensManager manager = new LensManager();
        CameraTextUI ui = new CameraTextUI(manager);
        ui.show();
    }
}