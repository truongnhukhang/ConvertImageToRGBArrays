package com.ocr.tool.controller;

import com.ocr.tool.dto.ImageInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ocr.tool.service.ImageService;
import com.ocr.tool.service.OCRService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by truongnhukhang on 6/5/19.
 */

@Controller
public class ImageProcessingController {

  @Autowired
  ImageService imageService;

  @Autowired
  OCRService ocrService;

  @RequestMapping(value = "/image/upload",method = RequestMethod.POST)
  public @ResponseBody Map<String,Object> handleImage(@RequestParam("file") MultipartFile file, Model model) throws IOException {
    String fileName = file.getName();
    BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
    bufferedImage = imageService.resizeImage(bufferedImage,299,299);
    ImageInfoDto imageInfoDto = imageService.convertToImageInfoDto(bufferedImage);
    Map<String,Object> result = ocrService.getAdultResult(imageInfoDto);
    return result;
  }
}
