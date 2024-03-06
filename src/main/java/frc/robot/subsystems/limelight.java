package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.math.controller.PIDController;
public class limelight {
    private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    //x location of the target
    private static NetworkTableEntry tx = table.getEntry("tx");
    //y location of the target
    private static NetworkTableEntry ty = table.getEntry("ty");
    //area of the target
    private static NetworkTableEntry ta = table.getEntry("ta");
    //does the limelight have a target
    private static NetworkTableEntry tv = table.getEntry("tv");
    
    final double ANGULAR_P = 0.03;
    final double ANGULAR_D = 0;
    
    public PIDController Turn_pid = new PIDController(ANGULAR_P, 0, ANGULAR_D);

    public static double getX(){
        return tx.getDouble(0.0);
    }
    
    public static double getY(){
        return ty.getDouble(0.0);
    }

    public static double getArea(){
        return ta.getDouble(0.0);
    }

    public static boolean isTargetAvalible(){
        return tv.getBoolean(false);
    }
    
}
