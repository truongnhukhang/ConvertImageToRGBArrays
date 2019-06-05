package com.ocr.tool.service.impl;

import com.ocr.tool.dto.*;
import org.springframework.stereotype.Service;
import com.ocr.tool.service.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by truongnhukhang on 6/5/19.
 */
@Service
public class ImageServiceImpl implements ImageService {
  @Override
  public ImageInfoDto convertToImageInfoDto(BufferedImage bufferedImage) {
    int width;
    int height;
    float[][][][] output = null;
    try {
      width = bufferedImage.getWidth();
      height = bufferedImage.getHeight();
      output = new float[1][height][width][3];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color c = new Color(bufferedImage.getRGB(j, i));
          output[0][i][j][0] = Float.valueOf(c.getRed())/255;
          output[0][i][j][1] = Float.valueOf(c.getGreen())/255;
          output[0][i][j][2] = Float.valueOf(c.getBlue())/255;
        }
      }
      ImageInfoDto imageInfoDto = new ImageInfoDto();
      imageInfoDto.setInputs(output);
      return imageInfoDto;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public BufferedImage resizeImage(BufferedImage bufferedImage,int width,int height) {
    BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(bufferedImage, 0, 0, width, height, null);
    g.dispose();
    g.setComposite(AlphaComposite.Src);

    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.setRenderingHint(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    return resizedImage;
  }
}
