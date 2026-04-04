package com.fullhouse.utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javafx.scene.image.Image;

/**
 * The type Qr code generator.
 */
public class QRCodeGenerator {

    /**
     * Create qr ımage ımage.
     *
     * @param qrData the qr data
     * @return the ımage
     * @throws Exception the exception
     */
    public static Image createQRImage(String qrData) throws Exception {
        int width = 200;
        int height = 200;

        Map<EncodeHintType, Object> map = new HashMap<>();
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        map.put(EncodeHintType.MARGIN, 2);

        BitMatrix matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.QR_CODE, width, height, map);

        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "PNG", outputStream);
        return new Image(new ByteArrayInputStream(outputStream.toByteArray()));
    }
}