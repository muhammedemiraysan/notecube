package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.*;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Timer;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
public class shooter extends SubsystemBase{
    public static int Upshooterspeed = 0;
    public static int Amfimotorspeed = 0;
    public static int Lowshooterspeed = 0;
    public static boolean mode_amfi = false;
    static boolean shooting = false; 
    private CANSparkMax shooter_upMotor = new CANSparkMax(12, MotorType.kBrushless);
    private CANSparkMax shooter_upMotor2 = new CANSparkMax(11, MotorType.kBrushless);
    private CANSparkMax amfimotor = new CANSparkMax(14, MotorType.kBrushless);
    private Spark shooter_lowMotor = new Spark(0);    
    private RelativeEncoder shooter_encoder;
    
    public void init(){
        shooter_upMotor.restoreFactoryDefaults();
        shooter_upMotor2.restoreFactoryDefaults();
        amfimotor.restoreFactoryDefaults();
        shooter_lowMotor.setSafetyEnabled(false);
        shooter_encoder = shooter_upMotor.getEncoder();
    }
    public void setMotor(Boolean button1,Boolean button2,Boolean button12,boolean button22,boolean autonomoous_shooter,boolean amfi){
        SmartDashboard.putBoolean("Button1", button1);
        SmartDashboard.putBoolean("Button2", button2);
        SmartDashboard.putBoolean("Button12", button12);
        SmartDashboard.putBoolean("Button22", button22);
        SmartDashboard.putBoolean("amfial", amfi);
        SmartDashboard.putNumber("Up Shooter Motor Speed", Upshooterspeed);
        SmartDashboard.putNumber("Low Shooter Motor Speed", Lowshooterspeed);
        SmartDashboard.putNumber("Encoder Velocity", shooter_encoder.getVelocity());
        if(mode_amfi == true){amfimotor.set(Upshooterspeed);}
        if(mode_amfi == false){        
            shooter_upMotor.set(Upshooterspeed);
            shooter_upMotor2.set(-Upshooterspeed);
            shooter_lowMotor.set(Lowshooterspeed);}        
    if(shooting == false){
        if(button2 == true){
            Upshooterspeed = 1;
            Lowshooterspeed = 1;
        }
        if(button1 == true){
            Upshooterspeed = -1;
            Lowshooterspeed = -1;
        }
        if(button12 == true){
            Upshooterspeed = 0;
            Lowshooterspeed = 0;
        }
        if(button22 == true){
            Upshooterspeed = 0;
            Lowshooterspeed = 0;
        }
        if(amfi == true){
            if(mode_amfi == true){
                mode_amfi = false;
            }
            else if(mode_amfi == false){
                mode_amfi = true;
            }
        }
        if(autonomoous_shooter == true){
            Upshooterspeed = 1;
            Lowshooterspeed = 1;
        if(button12 == true){
            Upshooterspeed = 0;
            Lowshooterspeed = 0;
        }
        if(button22 == true){
            Upshooterspeed = 0;
            Lowshooterspeed = 0;
        }
        }
    }
    }


        // if(shooting == false){
        //     if(button2 == true){
        //         // shooting = true;
        //         // m_timer.start();
        //         Upshooterspeed = 1;
                

        //     }
        //     if(autonomous_shooter == true){
        //         shooting = true;
        //         m_timer.start();
        //     }
        //     if(button1 == true){
        //         // Upshooterspeed = -1;
        //         // Lowshooterspeed = -1;
        //         Lowshooterspeed = 1;
        //     }
        //     if(button12 == true){
        //         Upshooterspeed = 0;
        //         Lowshooterspeed = 0;
        //     }
        //     if(button22 == true){
        //         Upshooterspeed = 0;
        //         Lowshooterspeed = 0;
        //     }}
        // if(shooting == true){
        //     if(m_timer.get() < 1){
        //         Upshooterspeed = 1;
        //         Lowshooterspeed = 0;
        //     }
        //     if(m_timer.get() > 1){
        //         Upshooterspeed = 1;
        //         Lowshooterspeed = 1;
        //     }
        //     if(m_timer.get() > 2.5){
        //         Upshooterspeed = 0;
        //         Lowshooterspeed = 0;
        //         m_timer.reset();
        //         m_timer.stop();
        //         shooting = false;
        //     }
        
    // }
}
