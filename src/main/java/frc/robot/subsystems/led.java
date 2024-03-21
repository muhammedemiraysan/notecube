package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;

public class led extends SubsystemBase{
    private int m_rainbowFirstPixelHue;
    // Must be a PWM header, not MXP or DIO
    private final AddressableLED m_led = new AddressableLED(1);
    // Reuse buffer
    // Default to a length of 60, start empty output
    // Length is expensive to set, so only set it once, then just update data
    private final AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(96);
    public void init(){
        m_led.setLength(m_ledBuffer.getLength());
        // Set the data
        m_led.setData(m_ledBuffer);
        m_led.start();
    }
    public void rainbow() {
        // For every pixel
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
          // Calculate the hue - hue is easier for rainbows because the color
          // shape is a circle so only one value needs to precess
          final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
          // Set the value
          m_ledBuffer.setHSV(i, hue, 255, 128);
        }
        // Increase by to make the rainbow "move"
        m_rainbowFirstPixelHue += 3;
        // Check bounds
        m_rainbowFirstPixelHue %= 180;
        m_led.setData(m_ledBuffer);
      }
    public void onecolor(int r,int g,int b, int s, int f){
        for (var i = s; i < f; i++) {
            // Sets the specified LED to the RGB values for red
            m_ledBuffer.setRGB(i, r, g, b);
         }
         
         m_led.setData(m_ledBuffer);
    }
    public void anim(int r,int g,int b,int s,int f){
      for (int i = s; i < f; i++) {
        m_ledBuffer.setRGB(i, r, g, b);
        m_led.setData(m_ledBuffer);
        Timer.delay(0.01);
    }

    // LED'leri sırayla söndür
    for (int i = f - 1; i >= s; i--) {
        m_ledBuffer.setRGB(i,0,0,0 );
        m_led.setData(m_ledBuffer);
        Timer.delay(0.01);
    }
    }
}
