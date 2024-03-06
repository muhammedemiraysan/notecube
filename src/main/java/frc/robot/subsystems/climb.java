package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;
public class climb extends SubsystemBase{
    private CANSparkMax climb_neoMotor = new CANSparkMax(11, MotorType.kBrushless);
    public void init(){
        climb_neoMotor.restoreFactoryDefaults();
    }
    public void setMotor(double pov){
        SmartDashboard.putNumber("POV", Math.round(pov));
        if(Math.round(pov) == 0){
        climb_neoMotor.set(1);
        SmartDashboard.putNumber("Motor Speed", 1);
        };
        if(Math.round(pov) == 180){
        climb_neoMotor.set(-1);
        SmartDashboard.putNumber("Motor Speed", -1);
        }; 
        if(Math.round(pov) == -1){
        climb_neoMotor.set(0);
        SmartDashboard.putNumber("Motor Speed", 0);
        };
    }
}
