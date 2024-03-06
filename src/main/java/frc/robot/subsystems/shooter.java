package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.*;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class shooter extends SubsystemBase{
    private CANSparkMax shooter_upMotor = new CANSparkMax(12, MotorType.kBrushed);
    private CANSparkMax shooter_lowMotor = new CANSparkMax(13, MotorType.kBrushed);
    public void init(){
        shooter_upMotor.restoreFactoryDefaults();
        shooter_lowMotor.restoreFactoryDefaults();
    }
    public void setMotor(Boolean button1,Boolean button2,Boolean button3){
        SmartDashboard.putBoolean("Button1", button1);
        SmartDashboard.putBoolean("Button2", button2);
        if(button1 == true){
        shooter_upMotor.set(1);
        shooter_lowMotor.set(1);
        SmartDashboard.putNumber("Shooter Motor Speed", 1);
        };
        if(button2 == true){
        shooter_upMotor.set(-1);
        shooter_lowMotor.set(-1);
        SmartDashboard.putNumber("Shooter Motor Speed", -1);
        }; 
        if((button3) == true ){
        shooter_upMotor.set(0);
        shooter_lowMotor.set(0);
        SmartDashboard.putNumber("Shooter Motor Speed", 0);
        };
    }
}
