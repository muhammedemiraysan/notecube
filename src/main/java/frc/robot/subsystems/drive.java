package frc.robot.subsystems;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.*;
public class drive extends SubsystemBase{
    //private CANSparkMax m_lweft_followMotor = new CANSparkMax(1, MotorType.kBrushed);
    private WPI_VictorSPX m_left_followMotor = new WPI_VictorSPX(16);
    private WPI_TalonSRX m_left_leadMotor = new WPI_TalonSRX(15);
    private WPI_VictorSPX m_right_followMotor = new WPI_VictorSPX(17);
    private WPI_VictorSPX m_right_leadMotor = new WPI_VictorSPX(18);
    private static DifferentialDrive m_robotDrive;

    public void init(){
        m_robotDrive = new DifferentialDrive(m_left_leadMotor, m_right_leadMotor); 
        m_left_followMotor.follow(m_left_leadMotor);
        m_right_followMotor.follow(m_right_leadMotor);
        
        m_left_followMotor.setSafetyEnabled(false);
        m_right_followMotor.setSafetyEnabled(false);
        
        m_left_leadMotor.setSafetyEnabled(false);
        m_right_leadMotor.setSafetyEnabled(false);
        
        m_right_leadMotor.setInverted(true);
        m_right_followMotor.setInverted(true);

    }
    
    public void arcadeDrive(double x,double y){
        // m_robotDrive.arcadeDrive(x, y);
        m_robotDrive.tankDrive(x, y);
    }
}