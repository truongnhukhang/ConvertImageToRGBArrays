package com.ocr.tool.service;

import com.ocr.tool.dto.ImageInfoDto;

import java.util.Map;

/**
 * Created by truongnhukhang on 6/5/19.
 */
public interface OCRService {
  public Map<String,Object> getAdultResult(ImageInfoDto imageInfoDto);

}
