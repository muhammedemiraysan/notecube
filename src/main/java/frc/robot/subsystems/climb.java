package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
public class climb extends SubsystemBase{
    private WPI_VictorSPX climb_neoMotor2 = new WPI_VictorSPX(14);
    private WPI_VictorSPX climb_neoMotor = new WPI_VictorSPX(11);
    public void init(){
        climb_neoMotor.setSafetyEnabled(false);
        climb_neoMotor2.setSafetyEnabled(false);
    }
    public void setMotor(double pov){
        SmartDashboard.putNumber("POV", Math.round(pov));
        if(Math.round(pov) == 0){
        climb_neoMotor.set(-1);
        climb_neoMotor2.set(-1);
        SmartDashboard.putNumber("Motor Speed", 1);
        };
        if(Math.round(pov) == 180){
        climb_neoMotor.set(1);
        climb_neoMotor2.set(1);
        SmartDashboard.putNumber("Motor Speed", -1);
        }; 
        if(Math.round(pov) == -1){
        climb_neoMotor.set(0);
        climb_neoMotor2.set(0);
        SmartDashboard.putNumber("Motor Speed", 0);
        };
    }
}
