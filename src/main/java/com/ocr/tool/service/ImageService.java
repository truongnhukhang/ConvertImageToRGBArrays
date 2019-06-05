package com.ocr.tool.service;

import com.ocr.tool.dto.*;
import java.awt.image.BufferedImage;

/**
 * Created by truongnhukhang on 6/5/19.
 */
public interface ImageService {
  public ImageInfoDto convertToImageInfoDto(BufferedImage bufferedImage);
  public BufferedImage resizeImage(BufferedImage bufferedImage,int width,int height);
}
