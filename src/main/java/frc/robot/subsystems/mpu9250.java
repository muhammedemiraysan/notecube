package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;

public class mpu9250{
    private static final int MPU9250_ADDRESS = 0x68; // MPU-9250'in I2C adresi
    private static final int WHO_AM_I_REG = 0x75; // Kimliğini kontrol etmek için kullanılan kayıt adresi
    private static final int WHO_AM_I_EXPECTED = 0x71; // Beklenen kimlik değeri

    private static final int ACCEL_XOUT_H = 0x3B; // İvmeölçer verisinin başlangıç adresleri
    private static final int ACCEL_XOUT_L = 0x3C;
    private static final int ACCEL_YOUT_H = 0x3D;
    private static final int ACCEL_YOUT_L = 0x3E;
    private static final int ACCEL_ZOUT_H = 0x3F;
    private static final int ACCEL_ZOUT_L = 0x40;

    private I2C mpu9250 = new I2C(I2C.Port.kOnboard, MPU9250_ADDRESS);

    public void readSensorData() {
        byte[] buffer = new byte[1];
        mpu9250.read(WHO_AM_I_REG, 1, buffer); // Kimlik kontrolü
        if (buffer[0] != WHO_AM_I_EXPECTED) {
            System.out.println("MPU-9250'ye baglanti yok");
            return;
        }
        System.out.println("MPU-9250 ile baglanti var");

        while (true) {
            byte[] accelData = new byte[6];
            mpu9250.read(ACCEL_XOUT_H, 6, accelData); // İvmeölçer verisini oku

            // İvmeölçer verilerini işleme
            short accelX = (short) ((accelData[0] << 8) | accelData[1]);
            short accelY = (short) ((accelData[2] << 8) | accelData[3]);
            short accelZ = (short) ((accelData[4] << 8) | accelData[5]);

            // İvmeölçer verilerini yazdırma
            System.out.println("X: " + accelX + ", Y: " + accelY + ", Z: " + accelZ);

            Timer.delay(0.1); // Veri alım aralığını belirleme (örneğin, 0.1 saniye)
        }
    }
}