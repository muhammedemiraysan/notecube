package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.*;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Timer;
import com.revrobotics.RelativeEncoder;

public class shooter extends SubsystemBase{
    static int Upshooterspeed = 0;
    static int Lowshooterspeed = 0;
    static boolean shooting = false; 
    private CANSparkMax shooter_upMotor = new CANSparkMax(14, MotorType.kBrushless);
    private CANSparkMax shooter_lowMotor = new CANSparkMax(12, MotorType.kBrushed);
    private RelativeEncoder shooter_encoder;
    private final Timer m_timer = new Timer();
    public void init(){
        shooter_upMotor.restoreFactoryDefaults();
        shooter_lowMotor.restoreFactoryDefaults();
        shooter_encoder = shooter_upMotor.getEncoder();
    }
    public void setMotor(Boolean button1,Boolean button2,Boolean button12,boolean button22,boolean autonomous_shooter){
        SmartDashboard.putBoolean("Button1", button1);
        SmartDashboard.putBoolean("Button2", button2);
        SmartDashboard.putBoolean("Button12", button12);
        SmartDashboard.putBoolean("Button22", button22);
        SmartDashboard.putNumber("Up Shooter Motor Speed", Upshooterspeed);
        SmartDashboard.putNumber("Low Shooter Motor Speed", Lowshooterspeed);
        SmartDashboard.putNumber("Encoder Velocity", shooter_encoder.getVelocity());
        shooter_upMotor.set(-Upshooterspeed);
        shooter_lowMotor.set(-Lowshooterspeed); 
    if(shooting == false){
        if(button2 == true){
            if(shooter_encoder.getVelocity() > 50){
                shooting = true;
                m_timer.start();
            }
            if(shooter_encoder.getVelocity() <= 50){
                Upshooterspeed = 1;
                Lowshooterspeed = 0;
            }
        }
        if(button1 == true){
            Upshooterspeed = -1;
            Lowshooterspeed = -1;
        }
        if(autonomous_shooter == true){
            if(shooter_encoder.getVelocity() > 50){
                shooting = true;
                m_timer.start();
            }
            if(shooter_encoder.getVelocity() <= 50){
                Upshooterspeed = 1;
                Lowshooterspeed = 0;
            }
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
    if(shooting == true){
       if(m_timer.get() < 1){
        Upshooterspeed = 1;
        Lowshooterspeed = 1;
       } 
       if(m_timer.get() > 1){
        Upshooterspeed = 1;
        Lowshooterspeed = 1;
        m_timer.reset();
        m_timer.stop();
        shooting = false;
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
}}
