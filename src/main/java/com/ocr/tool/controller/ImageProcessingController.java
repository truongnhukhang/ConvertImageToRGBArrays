package com.ocr.tool.controller;

import com.ocr.tool.dto.ImageInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
  public String handleImage(@RequestParam("file") MultipartFile file,Model model) throws IOException {
    String fileName = file.getName();
    BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
    bufferedImage = imageService.resizeImage(bufferedImage,299,299);
    ImageInfoDto imageInfoDto = imageService.convertToImageInfoDto(bufferedImage);
    Map<String,Object> result = ocrService.getAdultResult(imageInfoDto);
    if(result!=null) {
      if(result.get("outputs")!=null) {
        model.addAttribute("adult",((ArrayList<ArrayList>)result.get("outputs")).get(0).get(0));
        model.addAttribute("nonAdult",((ArrayList<ArrayList>)result.get("outputs")).get(0).get(1));
      } else {
        model.addAttribute("error",result.entrySet().stream().map((stringObjectEntry -> stringObjectEntry.getKey()+"="+stringObjectEntry.getValue())).collect(Collectors.joining(",")));
      }
    }
    return "adult-result";
  }
}
